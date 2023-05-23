package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Module;
import entity.Student;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-18 14:22
 */
public class CheckHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<Module> modules;

    /**
     * @param studentNum
     * @description The method is to check modules of the student. First, the method gets the login student by student number. Then it gets the module list of the student.
     */
    public boolean check(String studentNum) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
            modules = loginedStudent.getModules();
        } catch (IOException | NullPointerException e) {
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
     * @description The method is to get the module list of the login student.
     */
    public List<Module> getModules() {
        return modules;
    }
}
