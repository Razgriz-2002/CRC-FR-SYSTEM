package cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * Mailbox Excel VO
 *
 * @author 芋道源码
 */
@Data
public class EmailMailboxExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("邮件签名")
    private String signature;

    @ExcelProperty("渠道编码")
    private String code;

    @ExcelProperty(value = "开启状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("发件人邮箱地址")
    private String fromEmail;

    @ExcelProperty("邮箱授权码")
    private String authCode;

    @ExcelProperty("邮件发送回调 URL")
    private String callbackUrl;

    @ExcelProperty("创建时间")
    private Date createTime;

}
