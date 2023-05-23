package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:32
 */
public class ElectiveModule extends Module{
    public ElectiveModule(String moduleID, String name, String teacher, double credit, double grade) {
        super(moduleID, name, teacher, credit, grade);
        this.type = "Elective";
    }
}
