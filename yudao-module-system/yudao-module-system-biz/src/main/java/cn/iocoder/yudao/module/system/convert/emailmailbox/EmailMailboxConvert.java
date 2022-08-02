package cn.iocoder.yudao.module.system.convert.emailmailbox;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;

/**
 * Mailbox Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface EmailMailboxConvert {

    EmailMailboxConvert INSTANCE = Mappers.getMapper(EmailMailboxConvert.class);

    EmailMailboxDO convert(EmailMailboxCreateReqVO bean);

    EmailMailboxDO convert(EmailMailboxUpdateReqVO bean);

    EmailMailboxRespVO convert(EmailMailboxDO bean);

    List<EmailMailboxRespVO> convertList(List<EmailMailboxDO> list);

    PageResult<EmailMailboxRespVO> convertPage(PageResult<EmailMailboxDO> page);

    List<EmailMailboxExcelVO> convertList02(List<EmailMailboxDO> list);

}
