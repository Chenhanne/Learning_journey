package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-19 15:07
 */
public class ModifyHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<entity.Module> modules;
    private entity.Module new_module;

    public boolean modify(String studentNum, String moduleID, String name, String teacher, double credit, double grade, String type, int semester) {
        if (type.equals("")) {
            return false;
        }

        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
            modules = loginedStudent.getModules();
            for (entity.Module module : modules) {
                if (module.getModuleID().equals(moduleID) && module.getName().equals(name)) {
                    modules.remove(module);
                    if (type.equals("Compulsory")) {
                        new_module = new CompulsoryModule(moduleID, name, teacher, credit, grade, semester);
                    } else if (type.equals("Elective")) {
                        new_module = new ElectiveModule(moduleID, name, teacher, credit, grade, semester);
                    } else if (type.equals("Public")) {
                        new_module = new PublicModule(moduleID, name, teacher, credit, grade, semester);
                    }
                    loginedStudent.addModule(new_module);
                    String updatedJson = gson.toJson(students);
                    Files.write(Paths.get("students.json"), updatedJson.getBytes());
                    return true;
                }
            }
            return false;

        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public Student getStudent(String studentNum, List<Student> students) {
        for (Student student : students) {
            if (studentNum.equals(student.getStudentNum())) {
                return student;
            }
        }
        return null;
    }
}
