package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-19 22:34
 */
public class SkillTest {
    static Skill skill = new Skill("Play Basketball", "CrossOver!");
    @Test
    public void test1(){
        assertEquals("Play Basketball", skill.getName());
        assertEquals("CrossOver!", skill.getDescription());
    }
}
