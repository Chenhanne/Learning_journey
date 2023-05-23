package entity;

/**
 * @author ghr
 * @date 2023-04-25 13:15
 */
public class Volunteer extends Role{
    /**
     *
     * @description This is to initialise a Volunteer with its name and description. And its type is "Volunteer".
     * @param name
     * @param description
     */
    public Volunteer(String name, String description) {
        super(name, description);
        this.type = "Volunteer";
    }
}
