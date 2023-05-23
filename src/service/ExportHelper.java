package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;
import org.apache.poi.xwpf.usermodel.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author ghr
 * @date 2023-04-19 17:16
 */
public class ExportHelper {

    private List<Student> students;
    private Student loginedStudent;

    /**
     * @param studentNum
     * @description The method is to export the information to a doc file including his/her basic information, modules and scores, skills, achievements, roles and extra curriculums.
     */
    public boolean export(String studentNum) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("students.json")));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Student>>() {
            }.getType();
            students = gson.fromJson(json, listType);
            loginedStudent = getStudent(studentNum, students);
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph title = document.createParagraph();
            XWPFRun run = title.createRun();
            run.setText("Student Information for" + loginedStudent.getName());
            run.setBold(true);
            run.setFontSize(16);

            XWPFParagraph basic = document.createParagraph();
            run = basic.createRun();
            run.setText("Student Number: " + loginedStudent.getStudentNum());
            run.addBreak();
            run.setText("Name: " + loginedStudent.getName());
            run.addBreak();
            run.setText("Email: " + loginedStudent.getEmail());
            run.addBreak();
            run.setText("GPA: " + String.format("%.4f", loginedStudent.getGPA()));
            run.addBreak();
            run.setText("Grade:" + String.format("%.4f", loginedStudent.getGrade()));

            XWPFParagraph modules = document.createParagraph();
            run = modules.createRun();
            run.setText("Modules:");
            run.setBold(true);

            List<entity.Module> moduleList = loginedStudent.getModules();
            for (entity.Module module : moduleList) {
                XWPFParagraph moduleInfo = document.createParagraph();
                run = moduleInfo.createRun();
                run.setText(module.getName());
                run.addBreak();
                run.setText("Module ID: " + module.getModuleID());
                run.addBreak();
                run.setText("Teacher: " + module.getTeacher());
                run.addBreak();
                run.setText("Credit: " + module.getCredit());
                run.addBreak();
                run.setText("Grade: " + module.getGrade());
                run.addBreak();
                run.setText("Type: " + module.getType());
                run.addBreak();
            }

            XWPFParagraph skills = document.createParagraph();
            run = skills.createRun();
            run.setText("Skills:");
            run.setBold(true);

            List<Skill> skillList = loginedStudent.getSkills();
            for (Skill skill : skillList) {
                XWPFParagraph skillinfo = document.createParagraph();
                run = skillinfo.createRun();
                run.setText("Skill Name: " + skill.getName());
                run.addBreak();
                run.setText("Skill Description: " + skill.getDescription());
                run.addBreak();
            }

            XWPFParagraph roles = document.createParagraph();
            run = roles.createRun();
            run.setText("Roles:");
            run.setBold(true);

            List<Role> roleList = loginedStudent.getRoles();
            for (Role role : roleList) {
                XWPFParagraph roleinfo = document.createParagraph();
                run = roleinfo.createRun();
                run.setText("Role Name: " + role.getName());
                run.addBreak();
                run.setText("Role Description: " + role.getDescription());
                run.addBreak();
                run.setText("Role Type: " + role.getType());
                run.addBreak();
            }

            XWPFParagraph curriculums = document.createParagraph();
            run = curriculums.createRun();
            run.setText("Extra Curriculums:");
            run.setBold(true);

            List<Curriculum> curriculumList = loginedStudent.getExtraCurriculums();
            for (Curriculum curriculum : curriculumList) {
                XWPFParagraph curriculuminfo = document.createParagraph();
                run = curriculuminfo.createRun();
                run.setText("Curriculum Name: " + curriculum.getName());
                run.addBreak();
                run.setText("Curriculum Description: " + curriculum.getDescription());
                run.addBreak();
            }

            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\11636\\Desktop\\" + loginedStudent.getName() + ".docx"));
            document.write(fos);
            fos.close();
            document.close();

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
