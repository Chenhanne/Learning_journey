package gui;

import entity.Student;
import org.junit.Test;

/**
 * @author ghr
 * @date 2023-05-20 17:38
 */
public class MainPageFrameTest {
    @Test
    public void test1() {
        new MainPageFrame(new Student()).setVisible(true);
    }
}
