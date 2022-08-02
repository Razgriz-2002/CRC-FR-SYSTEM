package cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - Mailbox更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmailMailboxUpdateReqVO extends EmailMailboxBaseVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    @NotNull(message = "编号不能为空")
    private Long id;

    @ApiModelProperty(value = "邮件发送回调 URL")
    private String callbackUrl;

}
