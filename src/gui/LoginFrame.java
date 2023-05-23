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

        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);// The window size cannot be changed

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(26, 51, 112));
                g.fillRect(0, 0, getWidth(), getHeight() / 7);
            }
        };

        panel.setLayout(null);

        // Create the LOGIN label
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0, getHeight() / 7, getWidth(), getHeight() / 7);
        panel.add(loginLabel);

        // Add a picture on the dark blue bar
        JLabel imageLabel = new JLabel(Data.qmplus);
        imageLabel.setBounds(0, -getHeight() / 20, getWidth() / 5, getHeight() / 4);
        panel.add(imageLabel);

        // Creates a label that contains italicized underlined text
        JLabel titleLabel = new JLabel("<html><u><i>Learning<br>Journey</i></u></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(new Color(0xFFECE036, true));
        titleLabel.setBounds(getWidth() / 2, 0, 350, getHeight() / 7);
        titleLabel.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(titleLabel);

        // Create the Student Number label and text field
        JLabel studentNumberLabel = new JLabel("Student Number:");
        studentNumberLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        studentNumberLabel.setBounds(150, getHeight() / 3, 200, 30);
        panel.add(studentNumberLabel);
        studentNumberField = new JTextField();
        studentNumberField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        studentNumberField.setBounds(350, getHeight() / 3, 200, 30);
        panel.add(studentNumberField);

        // Create the Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordLabel.setBounds(150, getHeight() / 2, 200, 30);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordField.setBounds(350, getHeight() / 2, 200, 30);
        panel.add(passwordField);

        // Create a registration hyperlink
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

        // Create a LOGIN button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(0x189CD4));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(getWidth() / 2 - 75, 2 * getHeight() / 3, 150, 40);
        panel.add(loginButton);

        panel.setBackground(new Color(245, 249, 250, 255));

        LoginHelper loginHelper = new LoginHelper();
        //Add an action listener of login button
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredStudentNumber = studentNumberField.getText().trim();// The trim() method removes header and tail whitespace
                String enteredPassword = new String(passwordField.getPassword());
                boolean isLoginSuccessful = loginHelper.login(enteredStudentNumber, enteredPassword);
                Student loginStudent = loginHelper.loginStudent();
                if (isLoginSuccessful) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new MainPageFrame(loginStudent).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid student number or password ", "Failed login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

}
