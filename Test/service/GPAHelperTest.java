package service;

import org.junit.Test;

/**
 * @author ghr
 * @date 2023-05-20 17:06
 */
public class GPAHelperTest {
    GPAHelper gpaHelper = new GPAHelper("111111");

    @Test
    public void test1() {
        for (int i = 1; i <= 8; i++){
            System.out.println(i + "Semester GPA: " + gpaHelper.getGPA(i) + ", Grade: " + gpaHelper.getGrade(i));
        }
    }
}
