package cn.razgriz.email.core.impl.google;

import cn.razgriz.email.core.AbstractEmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;

/**
 * @author : Razgriz
 * @since : 2022/8/2 10:16
 */
public class GmailEmailPath extends AbstractEmailPath {
    @Override
    protected void doSetProperties(EmailReqDTO emailReqDTO) {
        properties.setProperty("mail.smtp.host","smtp.gmail.com");
    }

}
