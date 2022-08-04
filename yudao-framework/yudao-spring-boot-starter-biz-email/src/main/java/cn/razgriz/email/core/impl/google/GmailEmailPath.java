package cn.razgriz.email.core.impl.google;

import cn.razgriz.email.core.AbstractEmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import lombok.NoArgsConstructor;

/**
 * @author : Razgriz
 * @since : 2022/8/2 10:16
 */
@NoArgsConstructor  //为支持反射 添加无参构造
public class GmailEmailPath extends AbstractEmailPath {
    @Override
    protected void doSetProperties(EmailReqDTO emailReqDTO) {
        properties.setProperty("mail.smtp.host","smtp.gmail.com");
    }

}
