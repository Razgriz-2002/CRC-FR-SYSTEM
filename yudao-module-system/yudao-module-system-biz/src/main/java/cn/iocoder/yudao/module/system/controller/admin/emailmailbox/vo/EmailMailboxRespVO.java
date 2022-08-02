package cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - Mailbox Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmailMailboxRespVO extends EmailMailboxBaseVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "邮件发送回调 URL")
    private String callbackUrl;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
