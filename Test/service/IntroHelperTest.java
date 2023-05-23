package service;

import org.junit.Test;

/**
 * @author ghr
 * @date 2023-05-20 17:12
 */
public class IntroHelperTest {
    IntroHelper introHelper = new IntroHelper("111111");

    @Test
    public void test1(){
        System.out.println(introHelper.getLoginedStudent().toString());
    }
}
