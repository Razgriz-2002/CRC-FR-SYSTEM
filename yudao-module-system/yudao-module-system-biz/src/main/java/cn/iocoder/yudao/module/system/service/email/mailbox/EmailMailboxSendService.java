package cn.iocoder.yudao.module.system.service.email.mailbox;


import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;

/**
 * 信箱的服务接口
 * 该接口的任务是接收Postman对象的调用
 * 根据Postman对象回传的字符串以及渠道配置信息找到对应的渠道Bean
 * 并且发送邮件
 * @author : Razgriz
 * @since : 2022/8/2 10:25
 */
public interface EmailMailboxSendService {


    /**
     * 发送邮件的接口 应该传入包含mailbox的id信息和解析完成的模板内容以及标题等信息
     * @author Razgriz
     * @param
     * @return
     */
    public void send(EmailMailboxReqDTO dto) throws Exception;


}
