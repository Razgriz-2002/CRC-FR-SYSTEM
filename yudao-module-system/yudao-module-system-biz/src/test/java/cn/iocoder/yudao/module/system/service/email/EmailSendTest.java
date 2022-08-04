package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.EmailMailboxCreateReqVO;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxSendService;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxSendServiceImpl;
import cn.iocoder.yudao.module.system.service.emailmailbox.EmailMailboxService;
import cn.iocoder.yudao.module.system.service.emailmailbox.EmailMailboxServiceImpl;
import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.impl.google.GmailEmailPath;
import cn.razgriz.email.core.impl.qq.QQEmailPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author : Razgriz
 * @since : 2022/8/3 10:37
 */
@Import({EmailMailboxServiceImpl.class,EmailMailboxSendServiceImpl.class})
public class EmailSendTest extends BaseDbUnitTest {

    @Autowired
    EmailMailboxSendService sendService;

    @Autowired
    EmailMailboxService mailboxService;

    @Test
    public void emailServiceTest01() throws Exception {
        EmailMailboxReqDTO reqDTO = new EmailMailboxReqDTO();
        reqDTO.setMailboxId(1L);
        EmailMailboxSendService sendService = new EmailMailboxSendServiceImpl();
        //sendService.send(reqDTO);
    }

    /**
     * 反射加载发送渠道类测试
     * @author Razgriz
     * @param
     * @return
     */
    @Test
    public void emailServiceTest02() throws Exception {
        System.out.println(QQEmailPath.class); //QQ:cn.razgriz.email.core.impl.qq.QQEmailPath
        System.out.println(GmailEmailPath.class); //Google:cn.razgriz.email.core.impl.google.GmailEmailPath
        String googleClassPath = "cn.razgriz.email.core.impl.google.GmailEmailPath";  //以Google邮箱为例进行测试
        Class googleClass = Class.forName(googleClassPath);
        Object obj = googleClass.newInstance();  //获取实例
        System.out.println((EmailPath)obj);
        EmailPath emailPath = (EmailPath) obj;  //获取EmailPath
        //装载dto
        EmailReqDTO reqDTO = new EmailReqDTO();
        reqDTO.setAuthCode("wpecmmwqaxlmqsje");
        reqDTO.setToEmail("2713721325@qq.com");
        reqDTO.setTitle("测试邮件");
        reqDTO.setFromEmail("A2713721325@gmail.com");
        reqDTO.setContent("这是一封测试邮件");
        System.out.println("发送邮件");
        emailPath.send(reqDTO);

    }

    @Test
    public void emailServiceTest03() throws Exception {
        //首先向数据库插入一条信息
        EmailMailboxCreateReqVO reqVO = new EmailMailboxCreateReqVO();
        reqVO.setFromEmail("A2713721325@gmail.com");
        reqVO.setAuthCode("wpecmmwqaxlmqsje");
        reqVO.setRemark("测试邮件备注");
        reqVO.setStatus(1);
        reqVO.setCode("GMAIL");
        reqVO.setSignature("AAAAA");
        Long id = mailboxService.createEmailMailbox(reqVO);
        System.out.println("邮件id"+id);

        EmailMailboxReqDTO reqDTO = new EmailMailboxReqDTO();
        reqDTO.setMailboxId(id);
        reqDTO.setToEmail("2713721325@qq.com");
        reqDTO.setContent("这是一封测试邮件");
        reqDTO.setTitle("测试邮件");
        System.out.println("发送邮件");
        sendService.send(reqDTO);
    }


}
