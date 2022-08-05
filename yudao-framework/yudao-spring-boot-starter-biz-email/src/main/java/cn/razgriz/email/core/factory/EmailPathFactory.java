package cn.razgriz.email.core.factory;

import cn.razgriz.email.core.EmailPath;
import cn.razgriz.email.core.enums.EmailPathEnum;

/**
 * 利用反射加载EmailPath的工厂类接口
 * @author : Razgriz
 * @since : 2022/8/5 10:18
 */
public interface EmailPathFactory {

    /**
     * 根据渠道编码通过反射加载EmailPath实现类
     * 这种方法每次请求都会重新加载并创建一个类 后续可利用池化等方法进行优化
     * 此外由于邮件收发代码比较一致 因此后续可以尝试通过配置方式进行新渠道的增加
     * @author Razgriz
     * @param
     * @return
     */
    public static EmailPath getEmailPath(String pathCode) throws Exception {
        //获取枚举中配置的类路径
        String classPath = EmailPathEnum.getByCode(pathCode).getClassPath();
        //根据类路径反射加载类 并且以接口的形式返回
        Class emailPathClass = Class.forName(classPath);
        Object obj = emailPathClass.newInstance();  //获取实例
        //返回Object实例
        return (EmailPath) obj;
    }

}
