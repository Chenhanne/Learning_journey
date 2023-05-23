package entity;

/**
 * @author ghr
 * @date 2023-04-20 12:21
 */
public class Curriculum {
    private String name;
    private String description;

    public Curriculum() {
    }

    /**
     *
     * @description This is to initialise a Curriculum with its name and description.
     * @param name
     * @param description
     */
    public Curriculum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @description The method is to get the name of the curriculum.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to get the description of the curriculum.
     */
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Name: " + getName() + ", Description: " + getDescription();
    }
}
