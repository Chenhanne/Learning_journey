package entity;

/**
 * @author ghr
 * @date 2023-04-15 14:49
 */
public class Skill {
    private String name;
    private String description;
    private String BUPT_ID;

    public Skill(String name, String description, String BUPT_ID) {
        this.name = name;
        this.description = description;
        this.BUPT_ID = BUPT_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBUPT_ID() {
        return BUPT_ID;
    }

    public void setBUPT_ID(String BUPT_ID) {
        this.BUPT_ID = BUPT_ID;
    }
}
