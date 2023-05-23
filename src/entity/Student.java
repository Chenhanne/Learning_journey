package entity;

import java.util.ArrayList;

/**
 * @author ghr
 * @date 2023-04-11 17:13
 */
public class Student {
    private String BUPT_id;
    private String QMUL_id;
    private int pin;
    private String studentClass;
    private String studentName;
    private ArrayList<Module> moduleList;
    private double GPA;
    private double mark;
    private ArrayList<String> achievements;
    private ArrayList<String> roles;
    private ArrayList<String> extraCurriculum;
    private ArrayList<Skill> skills;
    private String email;

    public double calGPA(){
        // 计算GPA
        return 0;
    }
    public double calMark(){
        return 0;
    }
    public void addModule(){
        // 添加课程
    }
    public void addAchievement(){
        // 添加成就
    }
    public void addRole(){
        // 增加职务
    }
    public void addExtraCurriculum(){
        // 增加额外课程
    }






}
