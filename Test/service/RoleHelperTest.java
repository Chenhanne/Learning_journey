package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ghr
 * @date 2023-05-20 17:19
 */
public class RoleHelperTest {
    RoleHelper roleHelper = new RoleHelper("111111");

    @Test
    public void test1(){
        assertEquals(true, roleHelper.addRole("Test","Test","Class Rep"));
        assertEquals(true, roleHelper.addRole("Test","Test","Module Rep"));
        assertEquals(true, roleHelper.addRole("Test", "Test", "Volunteer"));
    }

    @Test
    public void test2(){
        for(int i = 0;  i < roleHelper.getRoleList().size(); i++){
            System.out.println(roleHelper.getRoleList().get(i));
        }
    }
}
