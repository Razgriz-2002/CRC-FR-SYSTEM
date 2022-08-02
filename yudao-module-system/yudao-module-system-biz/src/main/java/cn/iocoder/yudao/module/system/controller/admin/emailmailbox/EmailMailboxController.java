package cn.iocoder.yudao.module.system.controller.admin.emailmailbox;

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

import cn.iocoder.yudao.module.system.controller.admin.emailmailbox.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;
import cn.iocoder.yudao.module.system.convert.emailmailbox.EmailMailboxConvert;
import cn.iocoder.yudao.module.system.service.emailmailbox.EmailMailboxService;

@Api(tags = "管理后台 - Mailbox")
@RestController
@RequestMapping("/system/email-mailbox")
@Validated
public class EmailMailboxController {

    @Resource
    private EmailMailboxService emailMailboxService;

    @PostMapping("/create")
    @ApiOperation("创建Mailbox")
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:create')")
    public CommonResult<Long> createEmailMailbox(@Valid @RequestBody EmailMailboxCreateReqVO createReqVO) {
        return success(emailMailboxService.createEmailMailbox(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新Mailbox")
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:update')")
    public CommonResult<Boolean> updateEmailMailbox(@Valid @RequestBody EmailMailboxUpdateReqVO updateReqVO) {
        emailMailboxService.updateEmailMailbox(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除Mailbox")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:delete')")
    public CommonResult<Boolean> deleteEmailMailbox(@RequestParam("id") Long id) {
        emailMailboxService.deleteEmailMailbox(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得Mailbox")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:query')")
    public CommonResult<EmailMailboxRespVO> getEmailMailbox(@RequestParam("id") Long id) {
        EmailMailboxDO emailMailbox = emailMailboxService.getEmailMailbox(id);
        return success(EmailMailboxConvert.INSTANCE.convert(emailMailbox));
    }

    @GetMapping("/list")
    @ApiOperation("获得Mailbox列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:query')")
    public CommonResult<List<EmailMailboxRespVO>> getEmailMailboxList(@RequestParam("ids") Collection<Long> ids) {
        List<EmailMailboxDO> list = emailMailboxService.getEmailMailboxList(ids);
        return success(EmailMailboxConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得Mailbox分页")
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:query')")
    public CommonResult<PageResult<EmailMailboxRespVO>> getEmailMailboxPage(@Valid EmailMailboxPageReqVO pageVO) {
        PageResult<EmailMailboxDO> pageResult = emailMailboxService.getEmailMailboxPage(pageVO);
        return success(EmailMailboxConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出Mailbox Excel")
    @PreAuthorize("@ss.hasPermission('system:email-mailbox:export')")
    @OperateLog(type = EXPORT)
    public void exportEmailMailboxExcel(@Valid EmailMailboxExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmailMailboxDO> list = emailMailboxService.getEmailMailboxList(exportReqVO);
        // 导出 Excel
        List<EmailMailboxExcelVO> datas = EmailMailboxConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "Mailbox.xls", "数据", EmailMailboxExcelVO.class, datas);
    }

}
