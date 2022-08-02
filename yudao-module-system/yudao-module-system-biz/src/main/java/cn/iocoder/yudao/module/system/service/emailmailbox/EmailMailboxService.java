package cn.iocoder.yudao.module.system.service.emailmailbox;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * Mailbox Service 接口
 *
 * @author 芋道源码
 */
public interface EmailMailboxService {

    /**
     * 创建Mailbox
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmailMailbox(@Valid EmailMailboxCreateReqVO createReqVO);

    /**
     * 更新Mailbox
     *
     * @param updateReqVO 更新信息
     */
    void updateEmailMailbox(@Valid EmailMailboxUpdateReqVO updateReqVO);

    /**
     * 删除Mailbox
     *
     * @param id 编号
     */
    void deleteEmailMailbox(Long id);

    /**
     * 获得Mailbox
     *
     * @param id 编号
     * @return Mailbox
     */
    EmailMailboxDO getEmailMailbox(Long id);

    /**
     * 获得Mailbox列表
     *
     * @param ids 编号
     * @return Mailbox列表
     */
    List<EmailMailboxDO> getEmailMailboxList(Collection<Long> ids);

    /**
     * 获得Mailbox分页
     *
     * @param pageReqVO 分页查询
     * @return Mailbox分页
     */
    PageResult<EmailMailboxDO> getEmailMailboxPage(EmailMailboxPageReqVO pageReqVO);

    /**
     * 获得Mailbox列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return Mailbox列表
     */
    List<EmailMailboxDO> getEmailMailboxList(EmailMailboxExportReqVO exportReqVO);

}
