package cn.razgriz.email.core.impl.qq;

import cn.razgriz.email.core.AbstractEmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;

/**
 * QQ邮箱对于邮件发送接口的实现类
 * @author : Razgriz
 * @since : 2022/8/1 18:52
 */
public class QQEmailPath extends AbstractEmailPath {



    @Override
    protected void doSetProperties(EmailReqDTO emailReqDTO) {
        properties.setProperty("mail.smtp.host","smtp.qq.com");
    }
}
