package entity;

/**
 * @author ghr
 * @date 2023-04-25 13:02
 */
public class ModuleRep extends Role{
    /**
     *
     * @description This is to initialise a ModuleRep with its name and description. And its type is "Module Rep".
     * @param name
     * @param description
     */
    public ModuleRep(String name, String description) {
        super(name, description);
        this.type = "Module Rep";
    }
}
