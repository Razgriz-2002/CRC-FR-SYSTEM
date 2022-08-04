package cn.iocoder.yudao.module.system.controller.admin.emailtemplate;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.emailtemplate.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.module.system.convert.emailtemplate.EmailTemplateConvert;
import cn.iocoder.yudao.module.system.service.emailtemplate.EmailTemplateService;

@Api(tags = "管理后台 - 邮件模板")
@RestController
@RequestMapping("/system/email-template")
@Validated
public class EmailTemplateController {

    @Resource
    private EmailTemplateService emailTemplateService;

    @PostMapping("/create")
    @ApiOperation("创建邮件模板")
    @PreAuthorize("@ss.hasPermission('system:email-template:create')")
    public CommonResult<Long> createEmailTemplate(@Valid @RequestBody EmailTemplateCreateReqVO createReqVO) {
        return success(emailTemplateService.createEmailTemplate(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新邮件模板")
    @PreAuthorize("@ss.hasPermission('system:email-template:update')")
    public CommonResult<Boolean> updateEmailTemplate(@Valid @RequestBody EmailTemplateUpdateReqVO updateReqVO) {
        emailTemplateService.updateEmailTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除邮件模板")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:email-template:delete')")
    public CommonResult<Boolean> deleteEmailTemplate(@RequestParam("id") Long id) {
        emailTemplateService.deleteEmailTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得邮件模板")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:email-template:query')")
    public CommonResult<EmailTemplateRespVO> getEmailTemplate(@RequestParam("id") Long id) {
        EmailTemplateDO emailTemplate = emailTemplateService.getEmailTemplate(id);
        return success(EmailTemplateConvert.INSTANCE.convert(emailTemplate));
    }

    @GetMapping("/list")
    @ApiOperation("获得邮件模板列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:email-template:query')")
    public CommonResult<List<EmailTemplateRespVO>> getEmailTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<EmailTemplateDO> list = emailTemplateService.getEmailTemplateList(ids);
        return success(EmailTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得邮件模板分页")
    @PreAuthorize("@ss.hasPermission('system:email-template:query')")
    public CommonResult<PageResult<EmailTemplateRespVO>> getEmailTemplatePage(@Valid EmailTemplatePageReqVO pageVO) {
        PageResult<EmailTemplateDO> pageResult = emailTemplateService.getEmailTemplatePage(pageVO);
        return success(EmailTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出邮件模板 Excel")
    @PreAuthorize("@ss.hasPermission('system:email-template:export')")
    @OperateLog(type = EXPORT)
    public void exportEmailTemplateExcel(@Valid EmailTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmailTemplateDO> list = emailTemplateService.getEmailTemplateList(exportReqVO);
        // 导出 Excel
        List<EmailTemplateExcelVO> datas = EmailTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "邮件模板.xls", "数据", EmailTemplateExcelVO.class, datas);
    }

}
