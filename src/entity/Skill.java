package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:13
 */
public class Skill {
    private String name;
    private String description;

    public Skill() {
    }

    /**
     *
     * @description This is to initialise a Skill with its name and description.
     * @param name
     * @param description
     */
    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @description The method is to get the name of the skill
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to get the description of the skill.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Description: " + getDescription();
    }
}
