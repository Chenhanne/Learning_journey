package entity;

/**
 * @author ghr
 * @date 2023-04-11 17:16
 */
public class Module {
    private String moduleID;
    private String moduleName;
    protected double credit; // 学分
    protected double grade; // 分数
    protected double description; // 课程描述
    protected String type; // 类型

    public Module(String moduleID, String moduleName, double credit, double grade) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credit = credit;
        this.grade = grade;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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
}
