package entity;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-19 21:53
 */
public class ModuleTest {
    static Module compulsoryModule = new CompulsoryModule("EBU1234", "Python", "Li Lin", 5.0, 88.0, 1);
    static Module electiveModule = new ElectiveModule("EBU1111", "Java", "Wang Qi", 4.0, 78.0, 2);
    static Module publicModule = new PublicModule("EBU4233", "SQL", "Lil Wayne", 4.0, 99.0, 3);

    @Test
    public void test1() {
        assertEquals("EBU1234", compulsoryModule.getModuleID());
        assertEquals("Python", compulsoryModule.getName());
        assertEquals("Li Lin", compulsoryModule.getTeacher());
        assertEquals(5.0, compulsoryModule.getCredit(), 0);
        assertEquals(88.0, compulsoryModule.getGrade(), 0);
        assertEquals("Compulsory", compulsoryModule.getType());
        assertEquals(1, compulsoryModule.getSemester());
    }

    @Test
    public void test2() {
        assertEquals("EBU1111", electiveModule.getModuleID());
        assertEquals("Java", electiveModule.getName());
        assertEquals("Wang Qi", electiveModule.getTeacher());
        assertEquals(4.0, electiveModule.getCredit(), 0);
        assertEquals(78.0, electiveModule.getGrade(), 0);
        assertEquals("Elective", electiveModule.getType());
        assertEquals(2, electiveModule.getSemester());
    }
    @Test
    public void test3(){
        assertEquals("EBU4233", publicModule.getModuleID());
        assertEquals("SQL", publicModule.getName());
        assertEquals("Lil Wayne", publicModule.getTeacher());
        assertEquals(4.0, publicModule.getCredit(), 0);
        assertEquals(99.0, publicModule.getGrade(), 0);
        assertEquals("Public", publicModule.getType());
        assertEquals(3, publicModule.getSemester());
    }

}
