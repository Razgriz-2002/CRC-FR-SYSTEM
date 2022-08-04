package cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 邮件模板 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EmailTemplateBaseVO {

    @ApiModelProperty(value = "邮件类型", required = true, example = "2")
    @NotNull(message = "邮件类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "开启状态", required = true, example = "1")
    @NotNull(message = "开启状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "模板编码", required = true, example = "邮件模板1")
    @NotNull(message = "模板编码不能为空")
    private String code;

    @ApiModelProperty(value = "模板名称", required = true, example = "发送验证码模板")
    @NotNull(message = "模板名称不能为空")
    private String name;

    @ApiModelProperty(value = "模板内容", required = true, example = "您的验证码为：{code}")
    @NotNull(message = "模板内容不能为空")
    private String content;

    @ApiModelProperty(value = "参数数组", required = true)
    @NotNull(message = "参数数组不能为空")
    private String params;

    @ApiModelProperty(value = "备注", example = "这是一条备注")
    private String remark;

    @ApiModelProperty(value = "邮件 API 的模板编号", required = true)
    @NotNull(message = "邮件 API 的模板编号不能为空")
    private String apiTemplateId;

    @ApiModelProperty(value = "mailbox渠道编号", required = true)
    @NotNull(message = "mailbox渠道编号不能为空")
    private Long mailboxId;

    @ApiModelProperty(value = "mailbox渠道编码", required = true)
    @NotNull(message = "mailbox渠道编码不能为空")
    private String mailboxCode;

}
