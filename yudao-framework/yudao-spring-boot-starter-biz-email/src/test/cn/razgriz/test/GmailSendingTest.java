package cn.razgriz.test;

import cn.razgriz.email.core.AbstractEmailPath;
import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.impl.google.GmailEmailPath;
import cn.razgriz.email.core.impl.qq.QQEmailPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author : Razgriz
 * @since : 2022/8/1 14:13
 */
public class GmailSendingTest {


    private static final String USER = "2713721325@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "vebapglekjvgdcgg"; // 客户端授权码

    private static final String GmailString = "wpecmmwqaxlmqsje";  //google gmail授权码
    /**
     *  邮件收发测试类1
      * @author Razgriz
      * @param
      * @return
      */
    @Test
    public void mailSendingTest01() throws Exception{
        //初始化默认参数
        try{
            sendMail("2713721325@qq.com","测试邮件","这是一封测试邮件");
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    public void mailSendingTest02()throws Exception{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        EmailPath emailPath = new QQEmailPath();
        EmailReqDTO dto = new EmailReqDTO();
        dto.setContent("测试邮件"+formatter.format(date));
        dto.setAuthCode("vebapglekjvgdcgg");
        dto.setFromEmail("2713721325@qq.com");
        dto.setToEmail("2713721325@qq.com");
        dto.setTitle("测试邮件");
        try{
            emailPath.send(dto);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    /**
     * 使用Gmail功能发送测试邮件
     * @author Razgriz
     * @param
     * @return
     */

    @Test
    public void  mailSendingTest03() throws Exception {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        EmailPath emailPath = new GmailEmailPath();
        EmailReqDTO dto = new EmailReqDTO();
        dto.setContent("测试邮件"+formatter.format(date));
        dto.setAuthCode("wpecmmwqaxlmqsje");
        dto.setFromEmail("A22713721325@gmail.com");
        dto.setToEmail("2713721325@qq.com");
        dto.setTitle("测试邮件");
        try{
            emailPath.send(dto);
        }catch (Exception e){
            System.out.println(e);
        }
//        emailPath.send(dto);
    }
    private static boolean sendMail(String to, String text, String title){
        try {
            //申请java security权限
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            //定义properties容器 用于存放向服务器发送的各个属性
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            //采用465
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.ssl.enable", true);
            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            //重写授权类 传入用户名以及密码
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            //根据属性以及授权类返回Session实体类
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            // 有发件人 收件人 标题等信息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");

            //
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
