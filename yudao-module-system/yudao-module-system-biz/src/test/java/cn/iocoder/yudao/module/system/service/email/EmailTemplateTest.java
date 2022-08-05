package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.api.email.dto.EmailPostmanReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.EmailMailboxCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.EmailTemplateCreateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxService;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxServiceImpl;
import cn.iocoder.yudao.module.system.service.email.mailbox.send.EmailMailboxSendServiceImpl;
import cn.iocoder.yudao.module.system.service.email.postman.EmailPostman;
import cn.iocoder.yudao.module.system.service.email.postman.EmailPostmanImpl;
import cn.iocoder.yudao.module.system.service.email.postman.template.EmailTemplateService;
import cn.iocoder.yudao.module.system.service.email.postman.template.EmailTemplateServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件模板服务测试类
 * @author : Razgriz
 * @since : 2022/8/4 14:33
 */
@Import({EmailTemplateServiceImpl.class, EmailMailboxServiceImpl.class, EmailPostmanImpl.class,EmailMailboxSendServiceImpl.class})
public class EmailTemplateTest extends BaseDbUnitTest {

    private static final String TO_EMAIL = "yourTestEmail@gmail.com";  //收件人邮箱

    private static final String FROM_EMAIL = "test@gmail.com";  //发件人邮箱

    private static final String AUTH_CODE = "yourAuthCode";  //发件人授权码

    @Autowired
    EmailTemplateService templateService;

    @Autowired
    EmailMailboxService mailboxService;

    @Autowired
    EmailPostman postman;

    /**
     * 增删改查测试
     * @author Razgriz
     * @param
     * @return
     */
    @Test
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
    public void emailTemplateTest01() throws Exception {
        EmailTemplateCreateReqVO reqVO = new EmailTemplateCreateReqVO();
        reqVO.setApiTemplateId("aaa");
        reqVO.setContent("您的验证码是:{code}");
        reqVO.setCode("一号模板");
        reqVO.setMailboxCode("GMAIL");
        reqVO.setName("模板1");
        reqVO.setParams("123123");
        reqVO.setStatus(1);
        reqVO.setMailboxId(1L);
        reqVO.setType(1);
        reqVO.setRemark("测试");
        //新增测试
        Long emailTemplate = templateService.createEmailTemplate(reqVO);
        //查询测试 返回值和以上设置值应相同
        EmailTemplateDO emailTemplate1 = templateService.getEmailTemplate(emailTemplate);
        System.out.println(emailTemplate1);
        //删除测试 打印null
        templateService.deleteEmailTemplate(emailTemplate);
        EmailTemplateDO emailTemplate2 = templateService.getEmailTemplate(emailTemplate);
        System.out.println(emailTemplate2);
    }

    /**
     * 完整业务测试
     * 即Postman传入模板号以及参数
     * 从模板信息中匹配对应数据
     * 从数据库查找对应渠道
     * 经过EmailPath发送邮件信息
     * @author Razgriz
     * @param
     * @return
     */
    @Test
    @Disabled //TODO:请将用户名和授权码修改后再运行该方法
    public void emailTemplateTest02() throws Exception{
        //首先添加两张表内容
        Long id = insertTemplateData(insertMailboxData());
        System.out.println(id);
        //之后根据id查询组装发邮件
        EmailPostmanReqDTO reqDTO = new EmailPostmanReqDTO();
        reqDTO.setToEmail(TO_EMAIL);
        reqDTO.setTitle("测试邮件");
        reqDTO.setTemplateId(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code","A1234");
        reqDTO.setParams(map);
        //最后调用postman相关方法完成收发
        System.out.println("发送邮件");
        for(int i = 0;i < 1;i++){
            postman.send(reqDTO);
        }

    }

    private Long insertMailboxData() throws Exception {
        EmailMailboxCreateReqVO reqVO = new EmailMailboxCreateReqVO();
        reqVO.setFromEmail(FROM_EMAIL);
        reqVO.setAuthCode(AUTH_CODE);
        reqVO.setRemark("测试邮件备注");
        reqVO.setStatus(1);
        reqVO.setCode("GMAIL");
        reqVO.setSignature("AAAAA");
        Long id = mailboxService.createEmailMailbox(reqVO);
        return id;
    }

    private Long insertTemplateData(Long id)throws Exception{
        EmailTemplateCreateReqVO reqVO = new EmailTemplateCreateReqVO();
        reqVO.setApiTemplateId("aaa");
        reqVO.setContent("您的验证码是:{code}");
        reqVO.setCode("一号模板");
        reqVO.setMailboxCode("GMAIL");
        reqVO.setName("模板1");
        reqVO.setParams("123123");
        reqVO.setStatus(1);
        reqVO.setMailboxId(id);
        reqVO.setType(1);
        reqVO.setRemark("测试");
        Long emailTemplate = templateService.createEmailTemplate(reqVO);
        return emailTemplate;
    }



}
