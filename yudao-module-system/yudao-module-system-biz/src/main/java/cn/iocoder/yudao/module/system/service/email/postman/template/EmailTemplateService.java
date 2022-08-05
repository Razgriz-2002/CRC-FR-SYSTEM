package cn.iocoder.yudao.module.system.service.email.postman.template;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 邮件模板 Service 接口
 *
 * @author 芋道源码
 */
public interface EmailTemplateService {

    /**
     * 创建邮件模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmailTemplate(@Valid EmailTemplateCreateReqVO createReqVO);

    /**
     * 更新邮件模板
     *
     * @param updateReqVO 更新信息
     */
    void updateEmailTemplate(@Valid EmailTemplateUpdateReqVO updateReqVO);

    /**
     * 删除邮件模板
     *
     * @param id 编号
     */
    void deleteEmailTemplate(Long id);

    /**
     * 获得邮件模板
     *
     * @param id 编号
     * @return 邮件模板
     */
    EmailTemplateDO getEmailTemplate(Long id);

    /**
     * 获得邮件模板列表
     *
     * @param ids 编号
     * @return 邮件模板列表
     */
    List<EmailTemplateDO> getEmailTemplateList(Collection<Long> ids);

    /**
     * 获得邮件模板分页
     *
     * @param pageReqVO 分页查询
     * @return 邮件模板分页
     */
    PageResult<EmailTemplateDO> getEmailTemplatePage(EmailTemplatePageReqVO pageReqVO);

    /**
     * 获得邮件模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 邮件模板列表
     */
    List<EmailTemplateDO> getEmailTemplateList(EmailTemplateExportReqVO exportReqVO);

}
