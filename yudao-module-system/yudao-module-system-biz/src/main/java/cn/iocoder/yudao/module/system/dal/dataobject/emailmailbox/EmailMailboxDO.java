package cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * Mailbox DO
 *
 * @author 芋道源码
 */
@TableName("system_email_mailbox")
@KeySequence("system_email_mailbox_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMailboxDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 邮件签名
     */
    private String signature;
    /**
     * 渠道编码
     */
    private String code;
    /**
     * 开启状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 发件人邮箱地址
     */
    private String fromEmail;
    /**
     * 邮箱授权码
     */
    private String authCode;
    /**
     * 邮件发送回调 URL
     */
    private String callbackUrl;

}
