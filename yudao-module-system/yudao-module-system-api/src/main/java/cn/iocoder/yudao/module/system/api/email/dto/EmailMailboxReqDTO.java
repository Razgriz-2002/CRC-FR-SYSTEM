package cn.iocoder.yudao.module.system.api.email.dto;


import lombok.Data;

/**
 * 该DTO类即为Postman层向Mailbox层传送要发送字符串的内容/标题以及采用的Mailbox信息
 * @author : Razgriz
 * @since : 2022/8/2 10:29
 */
@Data
public class EmailMailboxReqDTO {

    private Long mailboxId;  //采用哪种mailbox

    private String content;   //发送的内容是什么

    private String title;   //发送的标题是什么

    private String mailboxCode;   //发送渠道的编码

    private String toEmail;   //收件人邮箱地址

}
