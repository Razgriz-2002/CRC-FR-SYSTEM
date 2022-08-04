package cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 邮件模板 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class EmailTemplateExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("邮件类型")
    private Integer type;

    @ExcelProperty(value = "开启状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("模板编码")
    private String code;

    @ExcelProperty("模板名称")
    private String name;

    @ExcelProperty("模板内容")
    private String content;

    @ExcelProperty("参数数组")
    private String params;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("邮件 API 的模板编号")
    private String apiTemplateId;

    @ExcelProperty("mailbox渠道编号")
    private Long mailboxId;

    @ExcelProperty("mailbox渠道编码")
    private String mailboxCode;

    @ExcelProperty("创建时间")
    private Date createTime;

}
