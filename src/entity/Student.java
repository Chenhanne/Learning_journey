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
    private ArrayList<Role> roles;
    private ArrayList<Module> modules;
    private ArrayList<Curriculum> extraCurriculums;

    private ArrayList<Achievement> achievements;

    public Student() {
    }

    /**
     *
     * @description This is to initialise a Student with his/her student number, name, password and email.
     * @param studentNum
     * @param name
     * @param password
     * @param email
     */
    public Student(String studentNum, String name, String password, String email) {
        this.studentNum = studentNum;
        this.name = name;
        this.password = password;
        this.email = email;
        skills = new ArrayList<>();
        roles = new ArrayList<>();
        modules = new ArrayList<>();
        extraCurriculums = new ArrayList<>();
        achievements = new ArrayList<>();
    }

    /**
     *
     * @description The method is to calculate the GPA of the student.
     */
    public double calGPA() {
        double total = 0.0;
        double single_GPA = 0;

        for (Module module : modules) {
            double credit = module.getCredit();
            total += credit;
        }
        for (Module module : modules) {
            double single_grade = module.getGrade();
            double credit = module.getCredit();

            if (single_grade >= 60 && single_grade <= 100) {
                single_GPA = 4 - 3 * (Math.pow((100 - single_grade), 2) / 1600);
            } else {
                single_GPA = 0;
            }
            GPA += single_GPA * (credit / total);
        }
        return GPA;
    }

    /**
     *
     * @description The method is to calculate the semester GPA of the student.
     * @param semester
     */
    public double calGPA(int semester) {
        double semesterGPA = 0;
        double total = 0.0;
        double single_GPA = 0;

        for (Module module : modules) {
            if(module.getSemester() == semester) {
                double credit = module.getCredit();
                total += credit;
            }
        }
        for (Module module : modules) {
            if(module.getSemester() == semester) {
                double single_grade = module.getGrade();
                double credit = module.getCredit();

                if (single_grade >= 60 && single_grade <= 100) {
                    single_GPA = 4 - 3 * (Math.pow((100 - single_grade), 2) / 1600);
                } else {
                    single_GPA = 0;
                }
                semesterGPA += single_GPA * (credit / total);
            }
        }
        return semesterGPA;
    }

    /**
     *
     * @description The method is to calculate the grade of the student.
     */
    public double calGrade() {

        double total = 0.0;
        for (Module module : modules) {
            if (!module.getType().equals("Public")) {
                double credit = module.getCredit();
                total += credit;
            }
        }
        for (Module module : modules) {
            if (!module.getType().equals("Public")) {
                double single_grade = module.getGrade();
                double credit = module.getCredit();
                if (single_grade >= 60 && single_grade <= 100) {
                    grade += single_grade * (credit / total);
                } else {
                    grade += 60 * (credit / total);
                }
            }
        }
        return grade;
    }

    /**
     *
     * @description The method is to calculate the semester grade of the student.
     * @param semester
     */
    public double calGrade(int semester) {
        double semesterGrade = 0;
        double total = 0.0;
        for (Module module : modules) {
            if (!module.getType().equals("Public") && module.getSemester() == semester) {
                double credit = module.getCredit();
                total += credit;
            }
        }
        for (Module module : modules) {
            if (!module.getType().equals("Public") && module.getSemester() == semester) {
                double single_grade = module.getGrade();
                double credit = module.getCredit();
                if (single_grade >= 60 && single_grade <= 100) {
                    semesterGrade += single_grade * (credit / total);
                } else {
                    semesterGrade += 60 * (credit / total);
                }
            }
        }
        return semesterGrade;
    }

    /**
     *
     * @description The method is to add a module to the module list of the student.
     * @param module
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     *
     * @description The method is to add a skill to the skill list of the student.
     * @param skill
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    /**
     *
     * @description The method is to add an achievement to the achievement list of the student.
     * @param achievement
     */
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    /**
     *
     * @description The method is to add a role to the role list of the student.
     * @param role
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     *
     * @description The method is to add an extra curriculum to the curriculum list of the student.
     * @param curriculum
     */
    public void addExtraCul(Curriculum curriculum) {
        extraCurriculums.add(curriculum);
    }

    /**
     *
     * @description The method is to get the student number of the student.
     */
    public String getStudentNum() {
        return studentNum;
    }

    /**
     *
     * @description The method is to set the student number of the student.
     * @param studentNum
     */
    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    /**
     *
     * @description The method is to get the name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @description The method is to set the name of the student.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @description The method is to get the password of the student.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @description The method is to get the module list of the student.
     */
    public ArrayList<Module> getModules() {
        return modules;
    }

    /**
     *
     * @description The method is to get the email of the student.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @description The method is to get the GPA of the student.
     */
    public double getGPA() {
        this.calGPA();
        return GPA;
    }

    /**
     *
     * @description The method is to get the grade of the student.
     */
    public double getGrade() {
        this.calGrade();
        return grade;
    }

    /**
     *
     * @description The method is to get the skill list of the student.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     *
     * @description The method is to get the role list of the student.
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     *
     * @description The method is to get the curriculum list of the student.
     */
    public ArrayList<Curriculum> getExtraCurriculums() {
        return extraCurriculums;
    }

    /**
     *
     * @description The method is to get the achievement list of the student.
     */
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNum='" + studentNum + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", GPA=" + GPA +
                ", grade=" + grade +
                ", skills=" + skills +
                ", roles=" + roles +
                ", modules=" + modules +
                ", extraCurriculums=" + extraCurriculums +
                ", achievements=" + achievements +
                '}';
    }
}
