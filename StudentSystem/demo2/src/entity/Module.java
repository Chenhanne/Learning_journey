package entity;

/**
 * @author ghr
 * @date 2023-04-17 12:13
 */
public class Module {
    private String moduleID;
    private String name;
    private String teacher;
    private double credit;
    private double grade;
    protected String type;

    public Module() {
    }

    public Module(String moduleID, String name, String teacher, double credit, double grade) {
        this.moduleID = moduleID;
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
        this.grade = grade;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
