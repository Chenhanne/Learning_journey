package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 16:28
 */
public class RecordHelperTest {
    RecordHelper recordHelper = new RecordHelper();

    @Test
    public void test1() {
        assertEquals(true, recordHelper.record("111111", "EBU1234", "Java", "LiLi", 4.0, 78, "Compulsory", 1));
        assertEquals(true, recordHelper.record("111111", "EBU1111", "Python", "Lin Hao", 5.0, 88, "Elective", 2));
        assertEquals(true, recordHelper.record("111111", "EBU2222", "SQL", "Nick", 3.0, 88.0, "Public", 3));
    }

    @Test
    public void test2(){
        assertEquals(false, recordHelper.record("111123", "EBU1234", "Java", "LiLi", 4.0, 78, "Compulsory", 1));
    }

}
