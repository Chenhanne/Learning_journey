package service;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:38
 */
public class CheckHelperTest {

    CheckHelper checkHelper = new CheckHelper();

    @Test
    public void test1(){
        assertEquals(true, checkHelper.check("111111"));
        assertEquals(false, checkHelper.check("000000"));
    }

    public void test2(){
        for (int i = 0 ; i < checkHelper.getModules().size(); i++){
            System.out.println(checkHelper.getModules().get(i).toString());
        }
    }
}
