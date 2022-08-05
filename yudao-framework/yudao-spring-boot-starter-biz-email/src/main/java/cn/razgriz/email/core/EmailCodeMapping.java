package cn.razgriz.email.core;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.razgriz.email.core.enums.EmailErrorCodeConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * 邮件的错误码映射
 * QQ邮箱和Gmail映射的错误码是统一的 如果以后其它渠道有不同可以在/impl/xxx文件夹下加入对应的映射表
 * 参考网址：https://support.google.com/a/answer/3726730
 * @author : Razgriz
 * @since : 2022/8/5 16:02
 */
@Slf4j
public class EmailCodeMapping {

    public static ErrorCode apply(String code){
        switch (code){

            //=============400-499错误 一般是由于邮件服务器故障引起===============//
            case "421-4.3.0":return EmailErrorCodeConstants.EMAIL_SERVICE_PROBLEM;
            case "421-4.4.5":return EmailErrorCodeConstants.EMAIL_SERVICE_BUSY;
            case "451-4.4.2":return EmailErrorCodeConstants.EMAIL_SERVICE_OVERTIME;
            //=============500以后的错误 一般是由于不适当的请求引起的==============//
            case "535-5.7.8":return EmailErrorCodeConstants.EMAIL_AUTH_FAILED;
            //TODO:更多请求之后再补充
        }
        //返回未知的时候打出来日志 以后再处理
        log.info("[email]:未知的错误码{"+code+"},请登录 https://support.google.com/a/answer/3726730 查看并补充");
        return EmailErrorCodeConstants.EMAIL_UNKNOWN;  //默认返回未知
    }


}
