package cn.iocoder.yudao.module.system.service.email;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.EmailTemplateCreateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.module.system.service.emailtemplate.EmailTemplateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * 邮件模板服务测试类
 * @author : Razgriz
 * @since : 2022/8/4 14:33
 */
@Import(EmailTemplateServiceImpl.class)
public class EmailTemplateTest extends BaseDbUnitTest {

    @Autowired
    EmailTemplateServiceImpl templateService;

    /**
     * 增删改查测试
     * @author Razgriz
     * @param
     * @return
     */
    @Test
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

    



}
