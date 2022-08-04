package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.EmailMailboxCreateReqVO;
import cn.iocoder.yudao.module.system.dal.mysql.emailmailbox.EmailMailboxMapper;
import cn.iocoder.yudao.module.system.service.emailmailbox.EmailMailboxService;
import cn.iocoder.yudao.module.system.service.emailmailbox.EmailMailboxServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * 测试Mailbox的Service层
 * @author : Razgriz
 * @since : 2022/8/3 14:38
 */
@Import(EmailMailboxServiceImpl.class)
public class EmailServiceTest extends BaseDbUnitTest {

    @Autowired
    private EmailMailboxServiceImpl service;

    @Autowired
    private EmailMailboxMapper emailMailboxMapper;

    @Test
    public void createTest01(){
        EmailMailboxCreateReqVO reqVO = new EmailMailboxCreateReqVO();
        reqVO.setFromEmail("A2713721325@gmail.com");
        reqVO.setAuthCode("wpecmmwqaxlmqsje");
        reqVO.setRemark("测试邮件备注");
        reqVO.setStatus(1);
        reqVO.setCode("Test_Email");
        reqVO.setSignature("AAAAA");
        service.createEmailMailbox(reqVO);
    }



}
