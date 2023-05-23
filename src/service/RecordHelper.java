package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-17 22:51
 */
public class RecordHelper {
    private List<Student> students;
    private Student loginedStudent;
    private entity.Module module;

    /**
     * @param studentNum
     * @param moduleID
     * @param name
     * @param teacher
     * @param credit
     * @param grade
     * @param type
     * @param semester
     * @description The method first instantiates a module with its id, name, teacher, credit, grade, type and semester. Then it gets the login student by student number.
     * And it add the module into the module list of the login student. if adding successfully, return true; otherwise, return false.
     */
    public boolean record(String studentNum, String moduleID, String name, String teacher, double credit, double grade, String type, int semester) {
        if (type.equals("")) {
            return false;
        } else if (type.equals("Compulsory")) {
            module = new CompulsoryModule(moduleID, name, teacher, credit, grade, semester);
        } else if (type.equals("Elective")) {
            module = new ElectiveModule(moduleID, name, teacher, credit, grade, semester);
        } else if (type.equals("Public")) {
            module = new PublicModule(moduleID, name, teacher, credit, grade, semester);
        }
        //将新的Module对象追加到JSON文件中
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
            loginedStudent.addModule(module);
            String updatedJson = gson.toJson(students);
            Files.write(Paths.get("students.json"), updatedJson.getBytes());
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
}
