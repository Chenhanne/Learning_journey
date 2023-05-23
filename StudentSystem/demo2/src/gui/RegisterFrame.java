package gui;

import service.RegisterHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ghr
 * @date 2023-04-17 16:01
 */
public class RegisterFrame extends JFrame implements DocumentListener {

    private JTextField studentNumberField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel equalPasssword;

    public RegisterFrame() {
        // 设置窗口标题
        setTitle("Register");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置窗口大小和位置
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 禁止用户改变窗口大小
        setResizable(false);

        // 创建包含所有组件的面板
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 绘制浅黄色背景
                g.setColor(new Color(26, 51, 112));
                g.fillRect(0, 0, getWidth(), getHeight() / 7);

            }
        };
        panel.setLayout(null);

        // 创建REGISTER标签
        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        registerLabel.setForeground(Color.BLACK);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0, getHeight() / 7, getWidth(), getHeight() / 7);
        panel.add(registerLabel);

        // 在深蓝色条上添加一个图片
        JLabel imageLabel = new JLabel(Data.qmplus);
        imageLabel.setBounds(0, -getHeight() / 20, getWidth() / 5, getHeight() / 4);
        panel.add(imageLabel);

        // 创建包含斜体下划线文本的标签
        JLabel titleLabel = new JLabel("<html><u><i>Learning<br>Journey</i></u></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(new Color(0xFFECE036, true));
        titleLabel.setBounds(getWidth() / 2, 0, 350, getHeight() / 7);
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(titleLabel);

        // 创建Student Number标签和文本框
        JLabel studentNumberLabel = new JLabel("Student Number:");
        studentNumberLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        studentNumberLabel.setBounds(150, getHeight() / 3, 200, 30);
        panel.add(studentNumberLabel);
        studentNumberField = new JTextField();
        studentNumberField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        studentNumberField.setBounds(350, getHeight() / 3, 200, 30);
        panel.add(studentNumberField);

        // 创建Name标签和文本框
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nameLabel.setBounds(150, getHeight() / 3 + 45, 200, 30);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nameField.setBounds(350, getHeight() / 3 + 45, 200, 30);
        panel.add(nameField);

        // 创建Email标签和文本框
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emailLabel.setBounds(150, getHeight() / 3 + 90, 200, 30);
        panel.add(emailLabel);
        emailField = new JTextField();
        emailField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emailField.setBounds(350, getHeight() / 3 + 90, 200, 30);
        panel.add(emailField);

        // 创建Password标签和文本框
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordLabel.setBounds(150, getHeight() / 3 + 135, 200, 30);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordField.setBounds(350, getHeight() / 3 + 135, 200, 30);
        panel.add(passwordField);

        // 创建Confirm Password标签和文本框
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        confirmPasswordLabel.setBounds(150, getHeight() / 3 + 180, 200, 30);
        panel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        confirmPasswordField.setBounds(350, getHeight() / 3 + 180, 200, 30);
        panel.add(confirmPasswordField);

        // 创建比较标签
        equalPasssword = new JLabel("");
        equalPasssword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        equalPasssword.setBounds(150, getHeight() / 3 + 225, 300, 30);
        panel.add(equalPasssword);

        passwordField.getDocument().addDocumentListener(this);
        confirmPasswordField.getDocument().addDocumentListener(this);

        // 创建REGISTER按钮
        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(new Color(0x189CD4));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(getWidth() / 2 - 75, getHeight() / 3 + 260, 150, 40);
        panel.add(registerButton);

        // 设置面板背景颜色
        panel.setBackground(new Color(245, 249, 250, 255));

        // 添加注册按钮监听器
        RegisterHelper registerHelper = new RegisterHelper();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredStudentNum = studentNumberField.getText().trim();
                String enteredName = nameField.getText().trim();
                String enteredEmail = emailField.getText().trim();
                String enteredPassword = new String(passwordField.getPassword());
                String enteredConfirmpassword = new String(confirmPasswordField.getPassword());
                boolean isRegisterSuccessful = registerHelper.register(enteredStudentNum, enteredName, enteredEmail, enteredPassword, enteredConfirmpassword);
                if (isRegisterSuccessful){
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Register Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new LoginFrame().setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Invalid input ","Failed register", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 将面板添加到窗口中
        add(panel);
    }

    public static void main(String[] args) {
        new RegisterFrame().setVisible(true);

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        compare();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        compare();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        compare();
    }

    private void compare() {
        if (new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
            equalPasssword.setForeground(new Color(0x79D934));
            equalPasssword.setText("Passwords are equal");
        } else {
            equalPasssword.setForeground(new Color(0xC42424));
            equalPasssword.setText("Passwords are not equal");
        }
    }

}