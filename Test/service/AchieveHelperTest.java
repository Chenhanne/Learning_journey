package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:10
 */
public class AchieveHelperTest {
    AchieveHelper achieveHelper = new AchieveHelper("111111");

    @Test
    public void test1(){

        assertEquals(true, achieveHelper.addAchievement("Test","Test"));
        assertEquals(false, achieveHelper.addAchievement("",""));

    }

    @Test
    public void test2(){
        for (int i = 0; i < achieveHelper.getAchievementList().size(); i++){
            System.out.println(achieveHelper.getAchievementList().get(i));
        }
    }
}
