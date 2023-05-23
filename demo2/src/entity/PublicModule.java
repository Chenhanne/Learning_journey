package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:32
 */
public class PublicModule extends Module{
    public PublicModule(String moduleID, String name, String teacher, double credit, double grade) {
        super(moduleID, name, teacher, credit, grade);
        this.type = "Public";
    }
}
