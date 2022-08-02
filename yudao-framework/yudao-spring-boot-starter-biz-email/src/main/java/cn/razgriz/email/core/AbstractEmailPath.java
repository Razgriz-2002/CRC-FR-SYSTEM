package cn.razgriz.email.core;

import cn.razgriz.email.core.dto.EmailReqDTO;
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

    @Override
    public void send(EmailReqDTO emailReqDTO) throws Exception {
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

        // 发送邮件
        Transport.send(message);
    }




}
