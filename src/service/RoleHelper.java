package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-25 13:27
 */
public class RoleHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<Role> roleList;
    private Role role;

    /**
     * @param studentNum
     * @description This is to initialise a RoleHelper with student number. First, it gets the login student by student number. Then it gets the role list of the login student.
     */
    public RoleHelper(String studentNum) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            loginedStudent = getStudent(studentNum, students);
            roleList = loginedStudent.getRoles();
            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param name
     * @param description
     * @param type
     * @description The method is to add a new role to the role list of the login student. If adding successfully, return true; otherwise return false.
     */
    public boolean addRole(String name, String description, String type) {
        if (name.equals("") || description.equals("") || type.equals("")) {
            return false;
        }
        if (type.equals("Class Rep")) {
            role = new ClassRep(name, description);
        } else if (type.equals("Module Rep")) {
            role = new ModuleRep(name, description);
        } else if (type.equals("Volunteer")) {
            role = new Volunteer(name, description);
        }

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            loginedStudent.addRole(role);
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
     * @description The method is to get the role list of the login student.
     */
    public List<Role> getRoleList() {
        return roleList;
    }
}
