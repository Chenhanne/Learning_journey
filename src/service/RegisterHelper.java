package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Student;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-17 17:32
 */
public class RegisterHelper {

    private List<Student> students;

    /**
     * @param studentNum
     * @param name
     * @param email
     * @param password
     * @param confirmPassword
     * @description The method is to instantiate a student with his/her student number, name, email, password, confirmPassword. Then it adds the student to the student list of students.json.
     */
    public boolean register(String studentNum, String name, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        if (studentNum.equals("") || name.equals("") || email.equals("") || password.equals("")) {
            return false;
        }

        // 创建一个新的 Student 对象，设置其属性
        Student student = new Student(studentNum, name, password, email);

        // 将新的 Student 对象追加到 JSON 文件中
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // 格式化，缩进格式
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            List<Student> students = gson.fromJson(json, listType);
            students.add(student);
            String updatedJson = gson.toJson(students);
            Files.write(Paths.get("students.json"), updatedJson.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
