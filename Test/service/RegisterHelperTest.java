package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:19
 */
public class RegisterHelperTest {
    RegisterHelper registerHelper = new RegisterHelper();

    @Test
    public void test1(){
        assertEquals(true, registerHelper.register("111111","Test","11111@qq.com", "111111","111111"));
        assertEquals(false,registerHelper.register("111111","Test","11111@qq.com", "111111","000000"));
    }
}
