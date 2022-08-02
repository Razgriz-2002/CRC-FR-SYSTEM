package cn.iocoder.yudao.module.system.dal.mysql.emailmailbox;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.*;

/**
 * Mailbox Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface EmailMailboxMapper extends BaseMapperX<EmailMailboxDO> {

    default PageResult<EmailMailboxDO> selectPage(EmailMailboxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmailMailboxDO>()
                .eqIfPresent(EmailMailboxDO::getSignature, reqVO.getSignature())
                .eqIfPresent(EmailMailboxDO::getCode, reqVO.getCode())
                .eqIfPresent(EmailMailboxDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmailMailboxDO::getFromEmail, reqVO.getFromEmail())
                .eqIfPresent(EmailMailboxDO::getAuthCode, reqVO.getAuthCode())
                .eqIfPresent(EmailMailboxDO::getCallbackUrl, reqVO.getCallbackUrl())
                .eqIfPresent(EmailMailboxDO::getCreator, reqVO.getCreator())
                .eqIfPresent(EmailMailboxDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmailMailboxDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(EmailMailboxDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(EmailMailboxDO::getId));
    }

    default List<EmailMailboxDO> selectList(EmailMailboxExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmailMailboxDO>()
                .eqIfPresent(EmailMailboxDO::getSignature, reqVO.getSignature())
                .eqIfPresent(EmailMailboxDO::getCode, reqVO.getCode())
                .eqIfPresent(EmailMailboxDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmailMailboxDO::getFromEmail, reqVO.getFromEmail())
                .eqIfPresent(EmailMailboxDO::getAuthCode, reqVO.getAuthCode())
                .eqIfPresent(EmailMailboxDO::getCallbackUrl, reqVO.getCallbackUrl())
                .eqIfPresent(EmailMailboxDO::getCreator, reqVO.getCreator())
                .eqIfPresent(EmailMailboxDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EmailMailboxDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(EmailMailboxDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(EmailMailboxDO::getId));
    }

}
