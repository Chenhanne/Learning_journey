package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Achievement;
import entity.Skill;
import entity.Student;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-29 1:40
 */
public class AchieveHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<Achievement> achievementList;
    private Achievement achievement;

    /**
     * @param studentNum
     * @description This is to initialise an AchieveHelper with the student number. And it will get the login student and the student's achievement list.
     */
    public AchieveHelper(String studentNum) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            loginedStudent = getStudent(studentNum, students);
            achievementList = loginedStudent.getAchievements();

            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param name
     * @param description
     * @description The method is to add an achievement with its name and description. If adding successfully, return true; otherwise return false.
     */
    public boolean addAchievement(String name, String description) {
        if (name.equals("") || description.equals("")) {
            return false;
        }
        achievement = new Achievement(name, description);

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            loginedStudent.addAchievement(achievement);
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
     * @description The method is to get the achievement list of the login student.
     */
    public List<Achievement> getAchievementList() {
        return achievementList;
    }
}
