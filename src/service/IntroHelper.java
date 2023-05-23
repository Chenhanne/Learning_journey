package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Student;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-18 22:16
 */
public class IntroHelper {
    private List<Student> students;
    private Student loginedStudent;

    /**
     * @param studentNum
     * @description This is to initialise an IntroHelper with student number. It gets the login student by student number.
     */
    public IntroHelper(String studentNum) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            loginedStudent = getStudent(studentNum, students);

            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
     * @description The method is to get the login student.
     */
    public Student getLoginedStudent() {
        return loginedStudent;
    }
}
