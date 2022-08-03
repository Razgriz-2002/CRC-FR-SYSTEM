package cn.iocoder.yudao.module.system.service.email.mailbox;


import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.impl.google.GmailEmailPath;
import cn.razgriz.email.core.impl.qq.QQEmailPath;
import io.swagger.models.properties.EmailProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * EmailMailboxService的实现类
 * 在该类中 调用dao层的方法实现根据id的mailbox查询
 * 找到每个配置好的mailbox中的授权码 用户名 渠道等信息
 * @author : Razgriz
 * @since : 2022/8/2 10:35
 */
@Service
@Slf4j
public class EmailMailboxSendServiceImpl implements EmailMailboxSendService {

    @Override
    public void send(EmailMailboxReqDTO dto) throws Exception {
        EmailPath emailPath = null;
        if(dto.getMailboxId().equals("QQ_MAIL")){
            emailPath = new QQEmailPath();
        } else if (dto.getMailboxId().equals("GMAIL")) {
            emailPath = new GmailEmailPath();
        }
        EmailReqDTO emailReqDTO = new EmailReqDTO();
        emailReqDTO.setToEmail("2713721325@qq.com");
        emailReqDTO.setFromEmail("A2713721325@gmail.com");
        emailReqDTO.setContent("测试邮件");
        emailReqDTO.setAuthCode("wpecmmwqaxlmqsje");
        emailReqDTO.setTitle("测试邮件");
        emailPath.send(emailReqDTO);
    }
}
