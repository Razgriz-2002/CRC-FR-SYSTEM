package cn.razgriz.test;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.razgriz.email.core.enums.EmailErrorCodeConstants;
import org.junit.jupiter.api.Test;

/**
 * @author : Razgriz
 * @since : 2022/8/5 11:37
 */
public class ExceptionTest {

    @Test
    public void exceptionTest01(){
        try{
            throw ServiceExceptionUtil.exception(EmailErrorCodeConstants.EMAIL_UNKNOWN);
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
