package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 17:15
 */
public class ModifyHelperTest {
    ModifyHelper modifyHelper = new ModifyHelper();

    @Test
    public void test1(){
        assertEquals(true, modifyHelper.modify("111111","EBU1234","Java","Li He",4.0, 90,"Compulsory",4));
        assertEquals(false,modifyHelper.modify("000000","EBU1234","Java","Li He",4.0, 90,"Compulsory",4));
        assertEquals(false, modifyHelper.modify("111111","EBU1234","Python","Li He",4.0, 90,"Compulsory",4));

    }
}
