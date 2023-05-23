package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:32
 */
public class CompulsoryModule extends Module{
    /**
     *
     * @description This is to initialise a compulsory module with its id, name, teacher, credit, grade and semester. Ane the type of the module is "Compulsory".
     * @param moduleID
     * @param name
     * @param teacher
     * @param credit
     * @param grade
     * @param semester
     */
    public CompulsoryModule(String moduleID, String name, String teacher, double credit, double grade, int semester) {
        super(moduleID, name, teacher, credit, grade, semester);
        this.type = "Compulsory";
    }
}
