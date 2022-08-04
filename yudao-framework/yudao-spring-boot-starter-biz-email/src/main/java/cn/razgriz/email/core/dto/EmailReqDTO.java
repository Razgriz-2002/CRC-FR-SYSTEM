package cn.razgriz.email.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求Path方法时传递的对象
 *
 * @author : Razgriz
 * @since : 2022/8/1 19:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailReqDTO {

    private String fromEmail;  //发送者的邮箱

    private String authCode;   //用户授权码

    private String content;   //要发送的内容

    private String toEmail;   //收件人邮箱

    private String title;   //标题

}
