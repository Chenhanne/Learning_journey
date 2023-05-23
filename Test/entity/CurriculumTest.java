package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 1:02
 */
public class CurriculumTest {
    static Curriculum curriculum = new Curriculum("Research", "I am working on NLP.");
    @Test
    public void test1(){
        assertEquals("Research", curriculum.getName());
        assertEquals("I am working on NLP.", curriculum.getDescription());
    }

}
