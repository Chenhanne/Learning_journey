package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:32
 */
public class ElectiveModule extends Module{
    /**
     *
     * @description This is to initialise an elective module with its id, name, teacher, credit, grade and semester. Ane the type of the module is "Elective".
     * @param moduleID
     * @param name
     * @param teacher
     * @param credit
     * @param grade
     * @param semester
     */
    public ElectiveModule(String moduleID, String name, String teacher, double credit, double grade, int semester) {
        super(moduleID, name, teacher, credit, grade, semester);
        this.type = "Elective";
    }
}
