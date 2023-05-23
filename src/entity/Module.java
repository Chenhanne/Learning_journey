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
    protected int semester;

    public Module() {
    }

    /**
     *
     * @description This is to initialise a module with its id, name, teacher, credit, grade and semester.
     * @param moduleID
     * @param name
     * @param teacher
     * @param credit
     * @param grade
     * @param semester
     */
    public Module(String moduleID, String name, String teacher, double credit, double grade, int semester) {
        this.moduleID = moduleID;
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
        this.grade = grade;
        this.semester = semester;
    }

    /**
     *
     * @description The method is to get the id of the module.
     */
    public String getModuleID() {
        return moduleID;
    }

    /**
     *
     * @description The method is to set the id of the module.
     * @param moduleID
     */
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    /**
     *
     * @description The method is to get the name of the module.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to set the name of the module.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @description The method is to get the credit of the module.
     */
    public double getCredit() {
        return credit;
    }

    /**
     *
     * @description The method is to set the credit of the module.
     * @param credit
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     *
     * @description The method is to get the grade of the module.
     */
    public double getGrade() {
        return grade;
    }

    /**
     *
     * @description The method is to set the grade of the module.
     * @param grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     *
     * @description The method is to get the teacher of the module.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     *
     * @description The method is to get the type of the module.
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @description The method is to set the teacher of the module.
     * @param teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     *
     * @description The method is to get the semester of the module.
     */
    public int getSemester() {
        return semester;
    }

    /**
     *
     * @description The method is to set the semester of the module.
     * @param semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleID='" + moduleID + '\'' +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", credit=" + credit +
                ", grade=" + grade +
                ", type='" + type + '\'' +
                ", semester=" + semester +
                '}';
    }
}
