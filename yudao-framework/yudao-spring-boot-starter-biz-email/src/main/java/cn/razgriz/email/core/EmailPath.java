package cn.razgriz.email.core;

import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.dto.EmailRespDTO;

/**
 * 邮件的Path接口
 *
 * @author : Razgriz
 * @since : 2022/8/1 18:42
 */
public interface EmailPath {

    /**
     *  发送邮件的函数
      * @author Razgriz
      * @param
      * @return
      */

    public EmailCommonResult<EmailRespDTO> send(EmailReqDTO emailReqDTO) throws Exception;

}
