package cn.razgriz.email.core.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 邮件收发部分的错误码枚举
 *
 * 使用 2-001-003-000段 （框架-用户系统-Mobile-错误码）
 *
 * @author : Razgriz
 * @since : 2022/8/5 10:52
 */
public interface EmailErrorCodeConstants {

    ErrorCode EMAIL_UNKNOWN = new ErrorCode(2001003000,"未知错误");

    // ========== 权限 / 限流等相关 20010031xx ==========

    ErrorCode EMAIL_AUTH_FAILED = new ErrorCode(2001003100,"用户认证失败！");

    // =========== 邮件服务器相关 20010032xx  ===========

    ErrorCode EMAIL_SERVICE_BUSY = new ErrorCode(2001003200,"邮件服务器繁忙");
    ErrorCode EMAIL_SERVICE_PROBLEM = new ErrorCode(2001003201,"邮件服务器错误");
    ErrorCode EMAIL_SERVICE_OVERTIME = new ErrorCode(2001003202,"邮件服务器链接超时");


}
