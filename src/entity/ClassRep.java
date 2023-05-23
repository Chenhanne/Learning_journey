package entity;

/**
 * @author ghr
 * @date 2023-04-25 0:25
 */
public class ClassRep extends Role{
    /**
     *
     * @description This is to initialise a classRep with its name and description. And its type is "Class Rep".
     * @param name
     * @param description
     */
    public ClassRep(String name, String description) {
        super(name, description);
        this.type = "Class Rep";
    }
}
