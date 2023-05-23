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
    private Module module;

    public boolean record(String studentNum, String moduleID, String name, String teacher, double credit, double grade, String type) {
        if (type.equals("")) {
            return false;
        } else if (type.equals("Compulsory")) {
            module = new CompulsoryModule(moduleID, name, teacher, credit, grade);
        } else if (type.equals("Elective")) {
            module = new ElectiveModule(moduleID, name, teacher, credit, grade);
        } else if (type.equals("Public")) {
            module = new PublicModule(moduleID, name, teacher, credit, grade);
        }
        //将新的Module对象追加到JSON文件中
        String json = null;
        try{
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>(){}.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
            loginedStudent.addModule(module);
            String updatedJson = gson.toJson(students);
            Files.write(Paths.get("students.json"), updatedJson.getBytes());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Student getStudent(String studentNum, List<Student> students){
        for (Student student: students){
            if (studentNum.equals(student.getStudentNum())){
                return student;
            }
        }
        return null;
    }
}
