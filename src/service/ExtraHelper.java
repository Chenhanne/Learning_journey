package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Achievement;
import entity.Curriculum;
import entity.Student;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-29 1:41
 */
public class ExtraHelper {
    private List<Student> students;
    private Student loginedStudent;
    private List<Curriculum> curriculumList;
    private Curriculum curriculum;

    /**
     * @param studentNum
     * @description This is to initialise an ExtraHelper. First it gets the login student by student number. Then it gets the curriculum list of the login student.
     */
    public ExtraHelper(String studentNum) {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("students.json");
            students = gson.fromJson(fileReader, new TypeToken<List<Student>>() {
            }.getType());
            loginedStudent = getStudent(studentNum, students);
            curriculumList = loginedStudent.getExtraCurriculums();

            fileReader.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading student data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param name
     * @param description
     * @description The method is to add a curriculum to the curriculum list of the login student. If adding successfully, return true; otherwise return false.
     */
    public boolean addCurriculum(String name, String description) {
        if (name.equals("") || description.equals("")) {
            return false;
        }
        curriculum = new Curriculum(name, description);

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            loginedStudent.addExtraCul(curriculum);
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
     * @description The method is to get the curriculum list of the login student.
     */
    public List<Curriculum> getCurriculumList() {
        return curriculumList;
    }
}
