package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:58
 */
public class ExtraHelperTest {
    ExtraHelper extraHelper = new ExtraHelper("111111");

    @Test
    public void test1(){
        assertEquals(true, extraHelper.addCurriculum("Test", "Test"));
        assertEquals(false,extraHelper.addCurriculum("",""));
    }

    @Test
    public void test2(){
        for (int i = 0; i < extraHelper.getCurriculumList().size(); i++){
            System.out.println(extraHelper.getCurriculumList().get(i));
        }
    }
}
