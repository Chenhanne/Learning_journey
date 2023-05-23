package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-19 22:42
 */
public class RoleTest {
    static Role classRep = new ClassRep("Monitor", "I am a monitor");
    static Role moduleRep = new ModuleRep("Math representative", "I am good at math");
    static Role volunteer = new Volunteer("Basketball volunteer", "I love basketball");

    @Test
    public void test1(){
        assertEquals("Monitor", classRep.getName());
        assertEquals("I am a monitor", classRep.getDescription());
    }

    @Test
    public void test2(){
        assertEquals("Math representative", moduleRep.getName());
        assertEquals("I am good at math", moduleRep.getDescription());
    }

    @Test
    public void test3(){
        assertEquals("Basketball volunteer", volunteer.getName());
        assertEquals("I love basketball", volunteer.getDescription());
    }

}
