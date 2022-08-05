package cn.iocoder.yudao.module.system.service.email.postman.template;

import cn.iocoder.yudao.module.system.service.email.postman.template.EmailTemplateService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.emailtemplate.EmailTemplateConvert;
import cn.iocoder.yudao.module.system.dal.mysql.emailtemplate.EmailTemplateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 邮件模板 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    @Override
    public Long createEmailTemplate(EmailTemplateCreateReqVO createReqVO) {
        // 插入
        EmailTemplateDO emailTemplate = EmailTemplateConvert.INSTANCE.convert(createReqVO);
        emailTemplateMapper.insert(emailTemplate);
        // 返回
        return emailTemplate.getId();
    }

    @Override
    public void updateEmailTemplate(EmailTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEmailTemplateExists(updateReqVO.getId());
        // 更新
        EmailTemplateDO updateObj = EmailTemplateConvert.INSTANCE.convert(updateReqVO);
        emailTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmailTemplate(Long id) {
        // 校验存在
        this.validateEmailTemplateExists(id);
        // 删除
        emailTemplateMapper.deleteById(id);
    }

    private void validateEmailTemplateExists(Long id) {
        if (emailTemplateMapper.selectById(id) == null) {
            throw exception(EMAIL_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public EmailTemplateDO getEmailTemplate(Long id) {
        return emailTemplateMapper.selectById(id);
    }

    @Override
    public List<EmailTemplateDO> getEmailTemplateList(Collection<Long> ids) {
        return emailTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmailTemplateDO> getEmailTemplatePage(EmailTemplatePageReqVO pageReqVO) {
        return emailTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmailTemplateDO> getEmailTemplateList(EmailTemplateExportReqVO exportReqVO) {
        return emailTemplateMapper.selectList(exportReqVO);
    }

}
