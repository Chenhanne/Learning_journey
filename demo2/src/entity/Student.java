package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

/**
 * @author ghr
 * @date 2023-04-17 1:20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String studentNum;
    private String name;
    private String password;
    private String email;
    private double GPA;
    private double grade;
    private ArrayList<Skill> skills;
    private ArrayList<String> roles;
    private ArrayList<Module> modules;
    private ArrayList<String> entraCurriculums;

    public Student() {
    }

    public Student(String studentNum, String name, String password, String email) {
        this.studentNum = studentNum;
        this.name = name;
        this.password = password;
        this.email = email;
        skills = new ArrayList<>();
        roles = new ArrayList<>();
        modules = new ArrayList<>();
        entraCurriculums = new ArrayList<>();
    }

    public double calGPA(){
        return GPA;
    }

    public double calGrade(){
        return grade;
    }

    public void addModule(Module module){
        modules.add(module);
    }

    public void addSkill(){

    }

    public void addAchievement(){

    }

    public void addRole(){

    }

    public void addExtraCul(){

    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }
}
