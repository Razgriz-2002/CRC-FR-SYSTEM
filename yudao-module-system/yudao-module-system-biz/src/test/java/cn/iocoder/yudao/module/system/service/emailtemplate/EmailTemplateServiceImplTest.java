package cn.iocoder.yudao.module.system.service.emailtemplate;

import cn.iocoder.yudao.module.system.service.email.postman.template.EmailTemplateServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.module.system.dal.mysql.emailtemplate.EmailTemplateMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link EmailTemplateServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(EmailTemplateServiceImpl.class)
public class EmailTemplateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmailTemplateServiceImpl emailTemplateService;

    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    @Test
    public void testCreateEmailTemplate_success() {
        // 准备参数
        EmailTemplateCreateReqVO reqVO = randomPojo(EmailTemplateCreateReqVO.class);

        // 调用
        Long emailTemplateId = emailTemplateService.createEmailTemplate(reqVO);
        // 断言
        assertNotNull(emailTemplateId);
        // 校验记录的属性是否正确
        EmailTemplateDO emailTemplate = emailTemplateMapper.selectById(emailTemplateId);
        assertPojoEquals(reqVO, emailTemplate);
    }

    @Test
    public void testUpdateEmailTemplate_success() {
        // mock 数据
        EmailTemplateDO dbEmailTemplate = randomPojo(EmailTemplateDO.class);
        emailTemplateMapper.insert(dbEmailTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmailTemplateUpdateReqVO reqVO = randomPojo(EmailTemplateUpdateReqVO.class, o -> {
            o.setId(dbEmailTemplate.getId()); // 设置更新的 ID
        });

        // 调用
        emailTemplateService.updateEmailTemplate(reqVO);
        // 校验是否更新正确
        EmailTemplateDO emailTemplate = emailTemplateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, emailTemplate);
    }

    @Test
    public void testUpdateEmailTemplate_notExists() {
        // 准备参数
        EmailTemplateUpdateReqVO reqVO = randomPojo(EmailTemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> emailTemplateService.updateEmailTemplate(reqVO), EMAIL_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmailTemplate_success() {
        // mock 数据
        EmailTemplateDO dbEmailTemplate = randomPojo(EmailTemplateDO.class);
        emailTemplateMapper.insert(dbEmailTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmailTemplate.getId();

        // 调用
        emailTemplateService.deleteEmailTemplate(id);
       // 校验数据不存在了
       assertNull(emailTemplateMapper.selectById(id));
    }

    @Test
    public void testDeleteEmailTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> emailTemplateService.deleteEmailTemplate(id), EMAIL_TEMPLATE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmailTemplatePage() {
       // mock 数据
       EmailTemplateDO dbEmailTemplate = randomPojo(EmailTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setStatus(null);
           o.setCode(null);
           o.setName(null);
           o.setContent(null);
           o.setParams(null);
           o.setApiTemplateId(null);
           o.setMailboxId(null);
           o.setMailboxCode(null);
           o.setCreateTime(null);
       });
       emailTemplateMapper.insert(dbEmailTemplate);
       // 测试 type 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setType(null)));
       // 测试 status 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setStatus(null)));
       // 测试 code 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setCode(null)));
       // 测试 name 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setName(null)));
       // 测试 content 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setContent(null)));
       // 测试 params 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setParams(null)));
       // 测试 apiTemplateId 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setApiTemplateId(null)));
       // 测试 mailboxId 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setMailboxId(null)));
       // 测试 mailboxCode 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setMailboxCode(null)));
       // 测试 createTime 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       EmailTemplatePageReqVO reqVO = new EmailTemplatePageReqVO();
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setContent(null);
       reqVO.setParams(null);
       reqVO.setApiTemplateId(null);
       reqVO.setMailboxId(null);
       reqVO.setMailboxCode(null);
       reqVO.setCreateTime(null);

       // 调用
       PageResult<EmailTemplateDO> pageResult = emailTemplateService.getEmailTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmailTemplate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmailTemplateList() {
       // mock 数据
       EmailTemplateDO dbEmailTemplate = randomPojo(EmailTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setStatus(null);
           o.setCode(null);
           o.setName(null);
           o.setContent(null);
           o.setParams(null);
           o.setApiTemplateId(null);
           o.setMailboxId(null);
           o.setMailboxCode(null);
           o.setCreateTime(null);
       });
       emailTemplateMapper.insert(dbEmailTemplate);
       // 测试 type 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setType(null)));
       // 测试 status 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setStatus(null)));
       // 测试 code 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setCode(null)));
       // 测试 name 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setName(null)));
       // 测试 content 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setContent(null)));
       // 测试 params 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setParams(null)));
       // 测试 apiTemplateId 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setApiTemplateId(null)));
       // 测试 mailboxId 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setMailboxId(null)));
       // 测试 mailboxCode 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setMailboxCode(null)));
       // 测试 createTime 不匹配
       emailTemplateMapper.insert(cloneIgnoreId(dbEmailTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       EmailTemplateExportReqVO reqVO = new EmailTemplateExportReqVO();
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setContent(null);
       reqVO.setParams(null);
       reqVO.setApiTemplateId(null);
       reqVO.setMailboxId(null);
       reqVO.setMailboxCode(null);
       reqVO.setCreateTime(null);

       // 调用
       List<EmailTemplateDO> list = emailTemplateService.getEmailTemplateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmailTemplate, list.get(0));
    }

}
