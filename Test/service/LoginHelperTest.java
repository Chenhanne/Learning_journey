package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 15:59
 */
public class LoginHelperTest {

    LoginHelper loginHelper = new LoginHelper();

    @Test
    public void test1(){
        assertEquals(true, loginHelper.login("111111", "111111"));
    }

    @Test
    public void test2(){
        assertEquals(false, loginHelper.login("111111", "111123"));
    }

}
