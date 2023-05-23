package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:32
 */
public class CompulsoryModule extends Module{
    public CompulsoryModule(String moduleID, String name, String teacher, double credit, double grade) {
        super(moduleID, name, teacher, credit, grade);
        this.type = "Compulsory";
    }
}
