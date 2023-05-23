package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Student;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-17 1:15
 */
public class LoginHelper {
    private List<Student> students;
    private Student loginStudent;

    /**
     * @description This is to initialise a LoginHelper. It gets the student list from students.json
     */
    public LoginHelper() {

        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param studentNum
     * @param password
     * @description The method is to get the login student from the student list by student number and password. If logging in successfully, return true; otherwise return false.
     */
    public boolean login(String studentNum, String password) {
        for (Student student : students) {
            if (student.getStudentNum().equals(studentNum) && student.getPassword().equals(password)) {
                loginStudent = student;
                return true;
            }
        }
        return false;
    }

    /**
     * @description The method is to get the login student.
     */
    public Student loginStudent() {
        return loginStudent;
    }


}
