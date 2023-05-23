package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Skill;
import entity.Student;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-22 8:27
 */
public class SkillHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<Skill> skillList;
    private Skill skill;

    /**
     * @param studentNum
     * @description This is to initialise a SkillHelper with student number. First, it gets the login student by student number. Then it gets the skill list of the login student.
     */
    public SkillHelper(String studentNum) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            loginedStudent = getStudent(studentNum, students);
            skillList = loginedStudent.getSkills();

            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param name
     * @param description
     * @description The method is to add a new skill to the skill list of the login student. If adding successfully, return true; otherwise return false.
     */
    public boolean addSkill(String name, String description) {
        if (name.equals("") || description.equals("")) {
            return false;
        }
        skill = new Skill(name, description);

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            loginedStudent.addSkill(skill);
            String updatedJson = gson.toJson(students);
            Files.write(Paths.get("students.json"), updatedJson.getBytes());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * @param studentNum
     * @param students
     * @description The method is to get the login student by student number.
     */
    public Student getStudent(String studentNum, List<Student> students) {
        for (Student student : students) {
            if (studentNum.equals(student.getStudentNum())) {
                return student;
            }
        }
        return null;
    }

    /**
     * @description This method is to get the skill list of the login student.
     */
    public List<Skill> getSkillList() {
        return skillList;
    }
}

