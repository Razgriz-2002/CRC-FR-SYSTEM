package cn.razgriz.email.core;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.dto.EmailRespDTO;
import cn.razgriz.email.core.enums.EmailErrorCodeConstants;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Path的抽象类 其中定义了不同Email公司提供的服务中相同的部分 比如Properties类
 * @author : Razgriz
 * @since : 2022/8/1 18:45
 */
@Slf4j
public abstract class AbstractEmailPath implements EmailPath{

    protected final Properties properties = new Properties();

    /**
     * 发送邮件的函数 返回值是EmailCommonResult 暂时未封装
     * 之后随着回调函数等接口的编写会逐步完善
     * @author Razgriz
     * @param
     * @return EmailCommonResult
     */

    @Override
    public EmailCommonResult<EmailRespDTO> send(EmailReqDTO emailReqDTO) throws Exception {
        //发送邮件函数
        //分为以下几个部分
        //1.封装共有Properties属性
        //2.封装子类私有Properties属性
        //3.调用子类特有发送函数
        log.info("[system]:准备发送邮件");
        setPropertiesParams(emailReqDTO);
        doSetProperties(emailReqDTO);
        sendEmail(emailReqDTO);
        log.info("[system]:发送邮件完毕");
        //TODO:完善返回值
        return null;
    }

    /**
     * 子类方法中要实现的 每一个子类独有的发送信息的方法
      * @author Razgriz
      * @param
      * @return
      */
    //子类要实现的 特有的属性封装
    protected abstract void doSetProperties(EmailReqDTO emailReqDTO);

    /**
     * 发送邮件的子类中共有的设置参数方法 这里将其综合出来
     *
      * @author Razgriz
      * @param
      * @return
      */

    private void setPropertiesParams(EmailReqDTO emailReqDTO){
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.enable", true);
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.user",emailReqDTO.getFromEmail());
        properties.setProperty("mail.password", emailReqDTO.getAuthCode());

    }

    /**
     * 发送邮件的公共函数 抽象出来作为公共部分
     * @author Razgriz
     * @param
     * @return
     */

    private void sendEmail(EmailReqDTO emailReqDTO) throws Exception {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        //根据属性以及授权类返回Session实体类
        Session mailSession = Session.getInstance(properties, authenticator);
        // 创建邮件消息
        // 有发件人 收件人 标题等信息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        String username = properties.getProperty("mail.user");
        InternetAddress form = new InternetAddress(username);
        message.setFrom(form);

        // 设置收件人
        InternetAddress toAddress = new InternetAddress(emailReqDTO.getToEmail());
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 设置邮件标题
        message.setSubject(emailReqDTO.getTitle());

        // 设置邮件的内容体
        message.setContent(emailReqDTO.getContent(), "text/html;charset=UTF-8");
        // 发送邮件 并且抓取异常 对其进行处理后抛出
        try {
            Transport.send(message);
        }catch (Exception e){
            //将异常转换为标准模式后抛出
            throw ServiceExceptionUtil.exception(parseException(e));
        }


    }

    /**
     * 解析邮件发送时返回的异常的函数
     * 目的是将异常转换成统一形式并返回
     * @author Razgriz
     * @param
     * @return
     */
    private ErrorCode parseException(Exception e){
        String exp = e.toString();
        int begin = exp.indexOf((int)' ');  //找到第一个空格的位置
        int end = exp.indexOf((int)' ',begin+1);  //找到第二个空格的位置
        String errorCode = exp.substring(begin+1,end);  //截取子串
        //随后根据错误码进行解析 并且返回统一模式的错误码
        return EmailCodeMapping.apply(errorCode);
    }



}
