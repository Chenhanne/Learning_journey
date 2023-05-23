package gui;


import entity.Student;
import service.LoginHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField studentNumberField;
    private JPasswordField passwordField;

    public LoginFrame() {
        // 设置窗口标题
        setTitle("Login");
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
                // 绘制深蓝色条框
                g.setColor(new Color(26, 51, 112));
                g.fillRect(0, 0, getWidth(), getHeight() / 7);

            }
        };

        panel.setLayout(null);

        // 创建LOGIN标签
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0, getHeight() / 7, getWidth(), getHeight() / 7);
        panel.add(loginLabel);

        // 在深蓝色条上添加一个图片
        JLabel imageLabel = new JLabel(Data.qmplus);
        imageLabel.setBounds(0, -getHeight() / 20, getWidth() / 5, getHeight() / 4);
        panel.add(imageLabel);

        // 创建包含斜体下划线文本的标签
        JLabel titleLabel = new JLabel("<html><u><i>Learning<br>Journey</i></u></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(new Color(0xFFECE036, true));
        titleLabel.setBounds(getWidth()/2, 0, 350, getHeight()/7);
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

        // 创建Password标签和文本框
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordLabel.setBounds(150, getHeight() / 2, 200, 30);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordField.setBounds(350, getHeight() / 2, 200, 30);
        panel.add(passwordField);

        // 创建注册超链接
        JLabel registerLabel = new JLabel("Click to Register");
        registerLabel.setFont(new Font("微软雅黑", Font.ITALIC, 15));
        registerLabel.setForeground(new Color(0x189CD4));
        registerLabel.setBounds(500, 2 * getHeight() / 3 - 53, 300, 30);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterFrame().setVisible(true);
                dispose();
            }
        });
        panel.add(registerLabel);

        // 创建LOGIN按钮
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(0x189CD4));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(getWidth() / 2 - 75, 2 * getHeight() / 3, 150, 40);
        panel.add(loginButton);

        // 设置面板背景颜色
        panel.setBackground(new Color(245, 249, 250, 255));

        LoginHelper loginHelper = new LoginHelper();
        //添加登录按钮监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredStudentNumber = studentNumberField.getText().trim();//trim()方法删除头尾空白符
                String enteredPassword = new String(passwordField.getPassword());
                boolean isLoginSuccessful = loginHelper.login(enteredStudentNumber,enteredPassword);
                Student loginStudent = loginHelper.loginStudent();
                if(isLoginSuccessful){
                    JOptionPane.showMessageDialog(LoginFrame.this, "Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // 打开下一个页面
                    new MainPageFrame(loginStudent).setVisible(true);
                    dispose();// 关闭当前页面
                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid student number or password ","Failed login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 将面板添加到窗口中
        add(panel);
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }

}
