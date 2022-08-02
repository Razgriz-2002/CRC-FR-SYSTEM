package cn.iocoder.yudao.module.system.service.emailmailbox;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;
import cn.iocoder.yudao.module.system.dal.mysql.emailmailbox.EmailMailboxMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link EmailMailboxServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(EmailMailboxServiceImpl.class)
public class EmailMailboxServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmailMailboxServiceImpl emailMailboxService;

    @Resource
    private EmailMailboxMapper emailMailboxMapper;

    @Test
    public void testCreateEmailMailbox_success() {
        // 准备参数
        EmailMailboxCreateReqVO reqVO = randomPojo(EmailMailboxCreateReqVO.class);

        // 调用
        Long emailMailboxId = emailMailboxService.createEmailMailbox(reqVO);
        // 断言
        assertNotNull(emailMailboxId);
        // 校验记录的属性是否正确
        EmailMailboxDO emailMailbox = emailMailboxMapper.selectById(emailMailboxId);
        assertPojoEquals(reqVO, emailMailbox);
    }

    @Test
    public void testUpdateEmailMailbox_success() {
        // mock 数据
        EmailMailboxDO dbEmailMailbox = randomPojo(EmailMailboxDO.class);
        emailMailboxMapper.insert(dbEmailMailbox);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmailMailboxUpdateReqVO reqVO = randomPojo(EmailMailboxUpdateReqVO.class, o -> {
            o.setId(dbEmailMailbox.getId()); // 设置更新的 ID
        });

        // 调用
        emailMailboxService.updateEmailMailbox(reqVO);
        // 校验是否更新正确
        EmailMailboxDO emailMailbox = emailMailboxMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, emailMailbox);
    }

    @Test
    public void testUpdateEmailMailbox_notExists() {
        // 准备参数
        EmailMailboxUpdateReqVO reqVO = randomPojo(EmailMailboxUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> emailMailboxService.updateEmailMailbox(reqVO), EMAIL_MAILBOX_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmailMailbox_success() {
        // mock 数据
        EmailMailboxDO dbEmailMailbox = randomPojo(EmailMailboxDO.class);
        emailMailboxMapper.insert(dbEmailMailbox);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmailMailbox.getId();

        // 调用
        emailMailboxService.deleteEmailMailbox(id);
       // 校验数据不存在了
       assertNull(emailMailboxMapper.selectById(id));
    }

    @Test
    public void testDeleteEmailMailbox_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> emailMailboxService.deleteEmailMailbox(id), EMAIL_MAILBOX_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmailMailboxPage() {
       // mock 数据
       EmailMailboxDO dbEmailMailbox = randomPojo(EmailMailboxDO.class, o -> { // 等会查询到
           o.setSignature(null);
           o.setCode(null);
           o.setStatus(null);
           o.setFromEmail(null);
           o.setAuthCode(null);
           o.setCallbackUrl(null);
           o.setCreator(null);
           o.setCreateTime(null);
           o.setUpdater(null);
           o.setDeleted(null);
       });
       emailMailboxMapper.insert(dbEmailMailbox);
       // 测试 signature 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setSignature(null)));
       // 测试 code 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCode(null)));
       // 测试 status 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setStatus(null)));
       // 测试 fromEmail 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setFromEmail(null)));
       // 测试 authCode 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setAuthCode(null)));
       // 测试 callbackUrl 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCallbackUrl(null)));
       // 测试 creator 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCreator(null)));
       // 测试 createTime 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCreateTime(null)));
       // 测试 updater 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setUpdater(null)));
       // 测试 deleted 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setDeleted(null)));
       // 准备参数
       EmailMailboxPageReqVO reqVO = new EmailMailboxPageReqVO();
       reqVO.setSignature(null);
       reqVO.setCode(null);
       reqVO.setStatus(null);
       reqVO.setFromEmail(null);
       reqVO.setAuthCode(null);
       reqVO.setCallbackUrl(null);
       reqVO.setCreator(null);
       reqVO.setCreateTime(null);
       reqVO.setUpdater(null);
       reqVO.setDeleted(null);

       // 调用
       PageResult<EmailMailboxDO> pageResult = emailMailboxService.getEmailMailboxPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmailMailbox, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmailMailboxList() {
       // mock 数据
       EmailMailboxDO dbEmailMailbox = randomPojo(EmailMailboxDO.class, o -> { // 等会查询到
           o.setSignature(null);
           o.setCode(null);
           o.setStatus(null);
           o.setFromEmail(null);
           o.setAuthCode(null);
           o.setCallbackUrl(null);
           o.setCreator(null);
           o.setCreateTime(null);
           o.setUpdater(null);
           o.setDeleted(null);
       });
       emailMailboxMapper.insert(dbEmailMailbox);
       // 测试 signature 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setSignature(null)));
       // 测试 code 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCode(null)));
       // 测试 status 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setStatus(null)));
       // 测试 fromEmail 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setFromEmail(null)));
       // 测试 authCode 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setAuthCode(null)));
       // 测试 callbackUrl 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCallbackUrl(null)));
       // 测试 creator 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCreator(null)));
       // 测试 createTime 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setCreateTime(null)));
       // 测试 updater 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setUpdater(null)));
       // 测试 deleted 不匹配
       emailMailboxMapper.insert(cloneIgnoreId(dbEmailMailbox, o -> o.setDeleted(null)));
       // 准备参数
       EmailMailboxExportReqVO reqVO = new EmailMailboxExportReqVO();
       reqVO.setSignature(null);
       reqVO.setCode(null);
       reqVO.setStatus(null);
       reqVO.setFromEmail(null);
       reqVO.setAuthCode(null);
       reqVO.setCallbackUrl(null);
       reqVO.setCreator(null);
       reqVO.setCreateTime(null);
       reqVO.setUpdater(null);
       reqVO.setDeleted(null);

       // 调用
       List<EmailMailboxDO> list = emailMailboxService.getEmailMailboxList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmailMailbox, list.get(0));
    }

}
