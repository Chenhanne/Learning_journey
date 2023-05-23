package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 17:31
 */
public class SkillHelperTest {
    SkillHelper skillHelper = new SkillHelper("111111");

    @Test
    public void test1(){
        assertEquals(true, skillHelper.addSkill("Test", "Test"));
    }

    @Test
    public void test2(){
        for(int i = 0; i < skillHelper.getSkillList().size(); i++){
            System.out.println(skillHelper.getSkillList().get(i));
        }
    }
}
