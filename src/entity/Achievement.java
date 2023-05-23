package entity;

/**
 * @author ghr
 * @date 2023-04-21 21:34
 */
public class Achievement {
    private String name;
    private String description;

    public Achievement() {
    }

    /**
     *
     * @description This is to initialise an achievement with its name and description.
     * @param name
     * @param description
     */
    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @description The method is to get the name of the achievement.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to get the description of the achievement.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Description: " + getDescription();
    }
}
