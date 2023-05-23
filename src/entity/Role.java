package entity;

/**
 * @author ghr
 * @date 2023-04-18 22:25
 */
public class Role {
    private String name;
    private String description;
    protected String type;

    public Role() {
    }

    /**
     *
     * @description This is to initialise a Role with its name and description.
     * @param name
     * @param description
     */
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @description The method is to get the name of the role.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to get the description of the role.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @description The method is to get the type of the role.
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Description: " + getDescription()  + " Type: " + getType();

    }
}
