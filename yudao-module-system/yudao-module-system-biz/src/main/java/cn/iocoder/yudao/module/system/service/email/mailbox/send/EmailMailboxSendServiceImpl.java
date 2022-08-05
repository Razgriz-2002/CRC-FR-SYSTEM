package cn.iocoder.yudao.module.system.service.email.mailbox.send;


import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.emailmailbox.EmailMailboxDO;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxService;
import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.dto.EmailReqDTO;
import cn.razgriz.email.core.factory.EmailPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EmailMailboxService的实现类
 * 在该类中 调用dao层的方法实现根据id的mailbox查询
 * 找到每个配置好的mailbox中的授权码 用户名 渠道等信息
 * @author : Razgriz
 * @since : 2022/8/2 10:35
 */
@Service
@Slf4j
public class EmailMailboxSendServiceImpl implements EmailMailboxSendService {

    @Autowired
    EmailMailboxService emailMailboxService;

    /**
     * 发送邮件的Mailbox层
     * 首先通过传入的dto获得所需mailbox的id 之后经过service层查找该id
     * 将返回的信息补充到传向path层的dto中 进行邮件发送操作
     *
     * @author Razgriz
     * @param
     * @return
     */

    @Override
    public void send(EmailMailboxReqDTO dto) throws Exception {
        Long id = dto.getMailboxId();
        //从数据库获取emailbox
        EmailMailboxDO emailMailbox = emailMailboxService.getEmailMailbox(id);
        //将该emailbox中的各个值封装到传向path的dto中
        String pathCode = emailMailbox.getCode();  //获取渠道编码
        Integer status = emailMailbox.getStatus();  //获得当前状态 若未开启 则仅在日志中打印
        String fromEmail = emailMailbox.getFromEmail();  //获得发件人邮箱
        String authCode = emailMailbox.getAuthCode();  //获得发件人邮箱授权码
        String callBackUrl = emailMailbox.getCallbackUrl();  //获得回调url
        //反射加载类
        EmailPath emailPath = EmailPathFactory.getEmailPath(pathCode);
        //封装请求参数
        EmailReqDTO reqDTO = new EmailReqDTO();
        reqDTO.setToEmail(dto.getToEmail());
        reqDTO.setFromEmail(fromEmail);
        reqDTO.setContent(dto.getContent());
        reqDTO.setTitle(dto.getTitle());
        reqDTO.setAuthCode(authCode);
        //装载dto 发送邮件
        emailPath.send(reqDTO);
    }


}
