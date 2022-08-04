package cn.iocoder.yudao.module.system.service.email.postman;

import cn.iocoder.yudao.module.system.api.email.dto.EmailMailboxReqDTO;
import cn.iocoder.yudao.module.system.api.email.dto.EmailPostmanReqDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.emailtemplate.EmailTemplateDO;
import cn.iocoder.yudao.module.system.service.email.mailbox.EmailMailboxSendService;
import cn.iocoder.yudao.module.system.service.emailtemplate.EmailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Postman接口的实现类
 * 引入MailboxSendService实现对下层发送接口的调用
 * 引入TemplateService查询邮件信息模板并将参数补全
 * @author : Razgriz
 * @since : 2022/8/4 14:13
 */
@Service
@Slf4j
public class EmailPostmanImpl implements EmailPostman{

    @Autowired
    EmailMailboxSendService sendService;  //Mailbox层的发送接口

    @Autowired
    EmailTemplateService templateService;  //template的数据库查询接口

    /**
     * 根据传入Postman信息找到对应的模板
     * 随后根据该模板以及传入的参数进行参数匹配
     * 最后将组合后的各个参数传入Mailbox层查询对应的Mailbox以及发送邮件
     * @author Razgriz
     * @param
     * @return
     */
    @Override
    public void send(EmailPostmanReqDTO reqDTO) throws Exception {
        String toEmail = reqDTO.getToEmail();  //获取收件人信息
        String title = reqDTO.getTitle();  //获取标题
        Long templateId = reqDTO.getTemplateId();  //获取模板id
        Map<String,Object> params = reqDTO.getParams();  //获取传入参数信息

        EmailTemplateDO emailTemplate = templateService.getEmailTemplate(templateId);  //通过模板id获取该模板的各种信息
        String rawTemplate = emailTemplate.getContent();  //获取原生模板内容 如 “验证码是{code}”
        String content = templateParse(rawTemplate,params);  //替换模板中的关键字
        EmailMailboxReqDTO mailboxReqDTO = new EmailMailboxReqDTO();
        mailboxReqDTO.setMailboxId(emailTemplate.getMailboxId());  //获取使用的mailbox的id
        mailboxReqDTO.setContent(content);
        mailboxReqDTO.setTitle(title);
        mailboxReqDTO.setToEmail(toEmail);
        mailboxReqDTO.setMailboxCode(emailTemplate.getMailboxCode());
        sendService.send(mailboxReqDTO);
    }

    /**
     * 根据模板的格式以及参数字典生成对应的字符串并返回
     * 之后如果业务复杂可以考虑抽象出工具类
     * 这是一个非常笨拙的方法 通过多次遍历实现字符串的全部替换
     * 以后可以尝试优化该方法
     * @author Razgriz
     * @param
     * @return
     */
    private String templateParse(String raw,Map<String,Object> params){
        StringBuilder sb = new StringBuilder(raw);  //利用Stringbuilder减少String常量池多次创建
        //该函数的思想
        //获取params中的所有key 将这些key变为{key}形式 之后找到这些字符串在模板中的位置 并且以value代替
        //如果params中有模板未匹配完 证明出现错误 应抛出异常
        Set<String> keySet = params.keySet();
        for(String str:keySet){
            //首先获取其值
            String value = (String) params.get(str);
            //之后查找
            int index;
            while ((index = sb.indexOf("{"+str+"}"))!=-1){
                //如果index不是-1 说明有未替换的字符 这时继续进行替换
                //从index开始 到长度加2（两个花括号）结束 替换成value
                sb.delete(index,str.length()+2+index);
                sb.insert(index,value);
            }
        }
        return sb.toString();
    }


}
