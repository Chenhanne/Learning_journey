package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * @author ghr
 * @date 2023-05-20 1:09
 */
public class StudentTest {
    Student student = new Student("2020213468", "Guo Haoran", "123456", "1163694580@qq.com");

    @Test
    public void test1() {
        assertEquals("2020213468", student.getStudentNum());
        assertEquals("Guo Haoran", student.getName());
        assertEquals("123456", student.getPassword());
        assertEquals("1163694580@qq.com", student.getEmail());
    }

    @Test
    public void test2() {
        student.addModule(ModuleTest.compulsoryModule);
        student.addModule(ModuleTest.electiveModule);
        student.addModule(ModuleTest.publicModule);

        for (int i = 0; i < student.getModules().size(); i++) {
            System.out.println(student.getModules().get(i).getModuleID() + " " + student.getModules().get(i).getName());
        }

        System.out.println("GPA: " + String.format("%.4f", student.getGPA()) + ", Grade: " + String.format("%.4f", student.getGrade()));

        for (int i = 1; i <= 8; i++) {
            System.out.println(i + " Semester: GPA: " + String.format("%.4f", student.calGPA(i)) + ", Grade: " + String.format("%.4f", student.calGrade(i)));
        }

        student.addRole(RoleTest.classRep);
        student.addRole(RoleTest.moduleRep);
        student.addRole(RoleTest.volunteer);

        for (int i = 0; i < student.getRoles().size(); i++) {
            System.out.println(student.getRoles().get(i).getName() + " " + student.getRoles().get(i).getDescription());
        }

        student.addSkill(SkillTest.skill);
        System.out.println(student.getSkills().get(0));

        student.addAchievement(AchievementTest.achievement);
        System.out.println(student.getAchievements().get(0));

        student.addExtraCul(CurriculumTest.curriculum);
        System.out.println(student.getExtraCurriculums().get(0));


    }
}
