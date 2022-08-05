package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.EmailMailboxCreateReqVO;
import cn.iocoder.yudao.module.system.dal.mysql.emailmailbox.EmailMailboxMapper;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxServiceImpl;
import org.junit.jupiter.api.Disabled;
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

    private static final String TO_EMAIL = "yourTestEmail@gmail.com";  //收件人邮箱

    private static final String FROM_EMAIL = "test@gmail.com";  //发件人邮箱

    private static final String AUTH_CODE = "yourAuthCode";  //发件人授权码

    @Autowired
    private EmailMailboxServiceImpl service;

    @Autowired
    private EmailMailboxMapper emailMailboxMapper;

    @Test
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
    public void createTest01(){
        EmailMailboxCreateReqVO reqVO = new EmailMailboxCreateReqVO();
        reqVO.setFromEmail(FROM_EMAIL);
        reqVO.setAuthCode(AUTH_CODE);
        reqVO.setRemark("测试邮件备注");
        reqVO.setStatus(1);
        reqVO.setCode("Test_Email");
        reqVO.setSignature("AAAAA");
        service.createEmailMailbox(reqVO);
    }



}
