package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:25
 */
public class ExportHelperTest {
    ExportHelper exportHelper = new ExportHelper();

    @Test
    public void test1(){
        assertEquals(true,exportHelper.export("111111"));
        assertEquals(false,exportHelper.export("000000"));
    }
}
