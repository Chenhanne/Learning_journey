package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Student;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-28 17:00
 */
public class GPAHelper {
    private List<Student> students;
    private Student loginedStudent;

    /**
     * @param studentNum
     * @description This is to initialise a GPAHelper with the student number. First, it gets the login student by student number.
     */
    public GPAHelper(String studentNum) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param semester
     * @description The method is to get the semester GPA of the login student.
     */
    public double getGPA(int semester) {
        return loginedStudent.calGPA(semester);
    }

    /**
     * @param semester
     * @description The method is to get the semester grade of the login student.
     */
    public double getGrade(int semester) {
        return loginedStudent.calGrade(semester);
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

}
