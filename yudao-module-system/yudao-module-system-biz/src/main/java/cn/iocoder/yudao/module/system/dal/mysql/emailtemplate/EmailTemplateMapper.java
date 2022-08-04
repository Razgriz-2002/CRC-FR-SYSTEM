package cn.iocoder.yudao.module.system.dal.mysql.emailtemplate;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;

/**
 * 邮件模板 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface EmailTemplateMapper extends BaseMapperX<EmailTemplateDO> {

    default PageResult<EmailTemplateDO> selectPage(EmailTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmailTemplateDO>()
                .eqIfPresent(EmailTemplateDO::getType, reqVO.getType())
                .eqIfPresent(EmailTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmailTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(EmailTemplateDO::getName, reqVO.getName())
                .eqIfPresent(EmailTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(EmailTemplateDO::getParams, reqVO.getParams())
                .eqIfPresent(EmailTemplateDO::getApiTemplateId, reqVO.getApiTemplateId())
                .eqIfPresent(EmailTemplateDO::getMailboxId, reqVO.getMailboxId())
                .eqIfPresent(EmailTemplateDO::getMailboxCode, reqVO.getMailboxCode())
                .eqIfPresent(EmailTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmailTemplateDO::getId));
    }

    default List<EmailTemplateDO> selectList(EmailTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmailTemplateDO>()
                .eqIfPresent(EmailTemplateDO::getType, reqVO.getType())
                .eqIfPresent(EmailTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmailTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(EmailTemplateDO::getName, reqVO.getName())
                .eqIfPresent(EmailTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(EmailTemplateDO::getParams, reqVO.getParams())
                .eqIfPresent(EmailTemplateDO::getApiTemplateId, reqVO.getApiTemplateId())
                .eqIfPresent(EmailTemplateDO::getMailboxId, reqVO.getMailboxId())
                .eqIfPresent(EmailTemplateDO::getMailboxCode, reqVO.getMailboxCode())
                .eqIfPresent(EmailTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmailTemplateDO::getId));
    }

}
