package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 1:40
 */
public class AchievementTest {
    static Achievement achievement = new Achievement("first prize","I made it!");

    @Test
    public void test1(){
        assertEquals("first prize", achievement.getName());
        assertEquals("I made it!", achievement.getDescription());
    }
}
