package cn.iocoder.yudao.module.system.service.email.mailbox;


import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void send(EmailMailboxReqDTO dto) {

    }
}
