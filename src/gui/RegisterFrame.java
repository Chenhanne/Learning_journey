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

        setTitle("Register");
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

        // Create a REGISTER label
        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        registerLabel.setForeground(Color.BLACK);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0, getHeight() / 7, getWidth(), getHeight() / 7);
        panel.add(registerLabel);

        // Add a picture on the dark blue bar
        JLabel imageLabel = new JLabel(Data.qmplus);
        imageLabel.setBounds(0, -getHeight() / 20, getWidth() / 5, getHeight() / 4);
        panel.add(imageLabel);

        // Create a label that contains italicized underlined text
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

        // Create the Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nameLabel.setBounds(150, getHeight() / 3 + 45, 200, 30);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nameField.setBounds(350, getHeight() / 3 + 45, 200, 30);
        panel.add(nameField);

        // Create the Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emailLabel.setBounds(150, getHeight() / 3 + 90, 200, 30);
        panel.add(emailLabel);
        emailField = new JTextField();
        emailField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emailField.setBounds(350, getHeight() / 3 + 90, 200, 30);
        panel.add(emailField);

        // Create the Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordLabel.setBounds(150, getHeight() / 3 + 135, 200, 30);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordField.setBounds(350, getHeight() / 3 + 135, 200, 30);
        panel.add(passwordField);

        // Create the Confirm Password label and text field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        confirmPasswordLabel.setBounds(150, getHeight() / 3 + 180, 200, 30);
        panel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        confirmPasswordField.setBounds(350, getHeight() / 3 + 180, 200, 30);
        panel.add(confirmPasswordField);

        // Create the comparison label
        equalPasssword = new JLabel("");
        equalPasssword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        equalPasssword.setBounds(150, getHeight() / 3 + 225, 300, 30);
        panel.add(equalPasssword);

        passwordField.getDocument().addDocumentListener(this);
        confirmPasswordField.getDocument().addDocumentListener(this);

        // Create a REGISTER button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(new Color(0x189CD4));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(getWidth() / 2 - 75, getHeight() / 3 + 260, 150, 40);
        panel.add(registerButton);

        panel.setBackground(new Color(245, 249, 250, 255));

        // Add an action listener of register button
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
                if (isRegisterSuccessful) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Register Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new LoginFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Invalid input ", "Failed register", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
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

    /**
     * @description The method is to compare whether passwordField and confirmPasswordField are the same.
     */
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