package cn.razgriz.email.core.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件渠道信息的枚举类
 * 记录了渠道的名称 类路径 代码等信息
 * @author : Razgriz
 * @since : 2022/8/4 10:46
 */
@Getter
@AllArgsConstructor
public enum EmailPathEnum {

    QQ_MAIL("QQ_MAIL","QQ邮箱","cn.razgriz.email.core.impl.qq.QQEmailPath"),
    GMAIL("GMAIL","Google邮箱","cn.razgriz.email.core.impl.google.GmailEmailPath")
    ;

    private final String pathCode;  //类对应的代码 QQ--QQ_MAIL Google--GMAIL

    private final String name;   //中文名称

    private final String classPath;  //类路径 可通过反射加载

    /**
     * 抄的芋道源码 是根据传入的类代码进行枚举的匹配
     * @author Razgriz&yudao
     * @param
     * @return
     */
    public static EmailPathEnum getByCode(String code) {
        return ArrayUtil.firstMatch(o -> o.getPathCode().equals(code), values());
    }

}
