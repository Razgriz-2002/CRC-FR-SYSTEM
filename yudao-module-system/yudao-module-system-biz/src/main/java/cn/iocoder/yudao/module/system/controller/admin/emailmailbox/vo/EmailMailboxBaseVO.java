package cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* Mailbox Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EmailMailboxBaseVO {

    @ApiModelProperty(value = "邮件签名", required = true, example = "邮件模板1")
    @NotNull(message = "邮件签名不能为空")
    private String signature;

    @ApiModelProperty(value = "渠道编码", required = true, example = "GMAIL")
    @NotNull(message = "渠道编码不能为空")
    private String code;

    @ApiModelProperty(value = "开启状态", required = true, example = "1")
    @NotNull(message = "开启状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "备注", example = "这是一条备注")
    private String remark;

    @ApiModelProperty(value = "发件人邮箱地址", required = true, example = "1235@gmail.com")
    @NotNull(message = "发件人邮箱地址不能为空")
    private String fromEmail;

    @ApiModelProperty(value = "邮箱授权码", example = "AFSGWES")
    private String authCode;

}
