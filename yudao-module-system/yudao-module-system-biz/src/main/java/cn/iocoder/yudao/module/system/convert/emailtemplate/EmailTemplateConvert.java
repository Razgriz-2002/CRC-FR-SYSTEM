package cn.iocoder.yudao.module.system.convert.emailtemplate;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;

/**
 * 邮件模板 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface EmailTemplateConvert {

    EmailTemplateConvert INSTANCE = Mappers.getMapper(EmailTemplateConvert.class);

    EmailTemplateDO convert(EmailTemplateCreateReqVO bean);

    EmailTemplateDO convert(EmailTemplateUpdateReqVO bean);

    EmailTemplateRespVO convert(EmailTemplateDO bean);

    List<EmailTemplateRespVO> convertList(List<EmailTemplateDO> list);

    PageResult<EmailTemplateRespVO> convertPage(PageResult<EmailTemplateDO> page);

    List<EmailTemplateExcelVO> convertList02(List<EmailTemplateDO> list);

}
