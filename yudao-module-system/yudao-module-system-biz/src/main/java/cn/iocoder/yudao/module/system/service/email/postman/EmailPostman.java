package cn.iocoder.yudao.module.system.service.email.postman;

import cn.iocoder.yudao.module.system.api.email.dto.EmailPostmanReqDTO;

/**
 * Postman是邮件收发时用户直接调用的接口
 * 目前提供一个send方法 用户传入包含模板信息 参数值的dto Postman调用EmailMailboxService实现基于模板的邮件收发
 *
 * @author : Razgriz
 * @since : 2022/8/4 14:12
 */
public interface EmailPostman {
    public void send(EmailPostmanReqDTO reqDTO) throws Exception;
}
