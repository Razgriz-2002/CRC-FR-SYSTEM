package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.EmailMailboxCreateReqVO;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxService;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxServiceImpl;
import cn.iocoder.yudao.module.system.service.email.mailbox.send.EmailMailboxSendService;
import cn.iocoder.yudao.module.system.service.email.mailbox.send.EmailMailboxSendServiceImpl;
import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.impl.google.GmailEmailPath;
import cn.razgriz.email.core.impl.qq.QQEmailPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * 对EmailPath.send功能进行测试
 * @author : Razgriz
 * @since : 2022/8/3 10:37
 */
@Import({EmailMailboxServiceImpl.class,EmailMailboxSendServiceImpl.class})
public class EmailSendTest extends BaseDbUnitTest {

    private static final String TO_EMAIL = "yourTestEmail@gmail.com";  //收件人邮箱

    private static final String FROM_EMAIL = "test@gmail.com";  //发件人邮箱

    private static final String AUTH_CODE = "yourAuthCode";  //发件人授权码

    @Autowired
    EmailMailboxSendService sendService;

    @Autowired
    EmailMailboxService mailboxService;

    @Test
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
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
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
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
        reqDTO.setAuthCode(AUTH_CODE);
        reqDTO.setToEmail(TO_EMAIL);
        reqDTO.setTitle("测试邮件");
        reqDTO.setFromEmail(FROM_EMAIL);
        reqDTO.setContent("这是一封测试邮件");
        System.out.println("发送邮件");
        emailPath.send(reqDTO);

    }

    @Test
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
    public void emailServiceTest03() throws Exception {
        //首先向数据库插入一条信息
        EmailMailboxCreateReqVO reqVO = new EmailMailboxCreateReqVO();
        reqVO.setFromEmail(FROM_EMAIL);
        reqVO.setAuthCode(AUTH_CODE);
        reqVO.setRemark("测试邮件备注");
        reqVO.setStatus(1);
        reqVO.setCode("GMAIL");
        reqVO.setSignature("AAAAA");
        Long id = mailboxService.createEmailMailbox(reqVO);
        System.out.println("邮件id"+id);

        EmailMailboxReqDTO reqDTO = new EmailMailboxReqDTO();
        reqDTO.setMailboxId(id);
        reqDTO.setToEmail(TO_EMAIL);
        reqDTO.setContent("这是一封测试邮件");
        reqDTO.setTitle("测试邮件");
        System.out.println("发送邮件");
        sendService.send(reqDTO);
    }


}