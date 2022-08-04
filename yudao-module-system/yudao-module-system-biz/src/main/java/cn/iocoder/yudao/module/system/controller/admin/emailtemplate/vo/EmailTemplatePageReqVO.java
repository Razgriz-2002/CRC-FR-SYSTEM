package cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 邮件模板分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmailTemplatePageReqVO extends PageParam {

    @ApiModelProperty(value = "邮件类型", example = "2")
    private Integer type;

    @ApiModelProperty(value = "开启状态", example = "1")
    private Integer status;

    @ApiModelProperty(value = "模板编码", example = "邮件模板1")
    private String code;

    @ApiModelProperty(value = "模板名称", example = "发送验证码模板")
    private String name;

    @ApiModelProperty(value = "模板内容", example = "您的验证码为：{code}")
    private String content;

    @ApiModelProperty(value = "参数数组")
    private String params;

    @ApiModelProperty(value = "邮件 API 的模板编号")
    private String apiTemplateId;

    @ApiModelProperty(value = "mailbox渠道编号")
    private Long mailboxId;

    @ApiModelProperty(value = "mailbox渠道编码")
    private String mailboxCode;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
