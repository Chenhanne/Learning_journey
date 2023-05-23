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

    public LoginHelper() {
        // 读取 JSON 文件并将其转为 Student 对象列表
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>(){}.getType());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean login(String studentNum, String password){
        for(Student student: students){
            if (student.getStudentNum().equals(studentNum) && student.getPassword().equals(password)){
                loginStudent = student;
                return true;
            }
        }
        return false;
    }

    public Student loginStudent(){
        return loginStudent;
    }


}
