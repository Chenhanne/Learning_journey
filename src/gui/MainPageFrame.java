package gui;


import entity.*;
import service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class MainPageFrame extends JFrame {

    // check function requirement
    JPanel checkPanel = new JPanel();
    CheckHelper checkHelper = new CheckHelper();
    DefaultTableModel checkModel = new DefaultTableModel();// In a member variable of the class (?) Can't update in the listener?

    // skill function requirement
    JPanel skillPanel = new JPanel();

    // role function requirement
    JPanel rolePanel = new JPanel();

    // achievement function requirement
    JPanel achievePanel = new JPanel();

    // extra function requirement
    JPanel extraPanel = new JPanel();

    public MainPageFrame(Student student) {

        setTitle("Main");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);// The window size cannot be changed

        // Create a panel that contains all the components
        JPanel panel = new JPanel();

        panel.setLayout(null);
        add(panel);

        // Create the dark blue bar
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(26, 51, 112));
        int headerHeight = getHeight() / 7;
        headerPanel.setBounds(0, 0, getWidth(), headerHeight);
        panel.add(headerPanel);
        panel.setComponentZOrder(headerPanel, 0);  // Set the dark blue bar to the topmost component

        // Create a sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(26, 51, 112));
        sidebar.setBounds(0, headerHeight, getWidth() / 8, getHeight() - headerHeight);
        sidebar.setLayout(new GridLayout(10, 1));
        panel.add(sidebar);

        // Create a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xA6A127));
        mainPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        mainPanel.setLayout(new CardLayout());
        panel.add(mainPanel);

        // Create a default panel to display basic information
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(new Color(0xF5F9FA));
        defaultPanel.setLayout(null);
        defaultPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);

        // Create a default image
        JLabel defaultLabel = new JLabel();
        Image image = Data.defaultImage.getImage().getScaledInstance(7 * getWidth() / 8, getHeight() - headerHeight, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        defaultLabel.setIcon(icon);
        defaultLabel.setBounds(0, 0, defaultPanel.getWidth(), defaultPanel.getHeight());
        defaultPanel.add(defaultLabel);

        mainPanel.add(defaultPanel, "default panel");
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "default panel");

        // Add a picture on the dark blue bar
        JLabel imageLabel = new JLabel(Data.qmplus);
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Set the left alignment, and add horizontal and vertical spacing
        headerPanel.add(imageLabel);

        // Add a space-taking Label on the dark blue bar
        JLabel emptyLabel = new JLabel("                                ");
        emptyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        headerPanel.add(emptyLabel);

        // Add a Welcome Label to the right of the dark blue bar
        JLabel welcomeLabel = new JLabel("Welcome! " + student.getName() + "        ");
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0xECE036));
        welcomeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        welcomeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "default panel");

                defaultPanel.removeAll();

                IntroHelper introHelper = new IntroHelper(student.getStudentNum());

                JLabel informationLabel = new JLabel("BASIC INFORMATION");
                informationLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                informationLabel.setForeground(new Color(0x0E3474));
                informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                informationLabel.setBounds(0, defaultPanel.getHeight() / 7 - 55, defaultPanel.getWidth(), defaultPanel.getHeight() / 7);
                defaultPanel.add(informationLabel);

                JLabel studentNumLabel = new JLabel("Student Number:");
                studentNumLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
                studentNumLabel.setBounds(100, informationLabel.getHeight() + 30, 200, 30);
                studentNumLabel.setForeground(new Color(0x0E3474));
                defaultPanel.add(studentNumLabel);
                JLabel studentNum = new JLabel(introHelper.getLoginedStudent().getStudentNum());
                studentNum.setFont(new Font("微软雅黑", Font.BOLD, 20));
                studentNum.setBounds(400, informationLabel.getHeight() + 30, 300, 30);
                studentNum.setForeground(new Color(0x0E3474));
                defaultPanel.add(studentNum);

                JLabel nameLabel = new JLabel("Student Name:");
                nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
                nameLabel.setBounds(100, informationLabel.getHeight() + 80, 200, 30);
                nameLabel.setForeground(new Color(0x0E3474));
                defaultPanel.add(nameLabel);
                JLabel name = new JLabel(introHelper.getLoginedStudent().getName());
                name.setFont(new Font("微软雅黑", Font.BOLD, 20));
                name.setBounds(400, informationLabel.getHeight() + 80, 300, 30);
                name.setForeground(new Color(0x0E3474));
                defaultPanel.add(name);

                JLabel emailLabel = new JLabel("Email:");
                emailLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
                emailLabel.setBounds(100, informationLabel.getHeight() + 130, 200, 30);
                emailLabel.setForeground(new Color(0x0E3474));
                defaultPanel.add(emailLabel);
                JLabel email = new JLabel(introHelper.getLoginedStudent().getEmail());
                email.setFont(new Font("微软雅黑", Font.BOLD, 20));
                email.setBounds(400, informationLabel.getHeight() + 130, 300, 30);
                email.setForeground(new Color(0x0E3474));
                defaultPanel.add(email);

                JLabel GPALabel = new JLabel("GPA:");
                GPALabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
                GPALabel.setBounds(100, informationLabel.getHeight() + 180, 200, 30);
                GPALabel.setForeground(new Color(0x0E3474));
                defaultPanel.add(GPALabel);
                JLabel GPA = new JLabel(String.format("%.4f", introHelper.getLoginedStudent().getGPA()));
                GPA.setFont(new Font("微软雅黑", Font.BOLD, 20));
                GPA.setBounds(400, informationLabel.getHeight() + 180, 300, 30);
                GPA.setForeground(new Color(0x0E3474));
                defaultPanel.add(GPA);

                JLabel gradeLabel = new JLabel("Grade:");
                gradeLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
                gradeLabel.setBounds(100, informationLabel.getHeight() + 230, 300, 30);
                gradeLabel.setForeground(new Color(0x0E3474));
                defaultPanel.add(gradeLabel);
                JLabel grade = new JLabel(String.format("%.4f", introHelper.getLoginedStudent().getGrade()));
                grade.setFont(new Font("微软雅黑", Font.BOLD, 20));
                grade.setBounds(400, informationLabel.getHeight() + 230, 300, 30);
                grade.setForeground(new Color(0x0E3474));
                defaultPanel.add(grade);

                JButton exportButton = new JButton("EXPORT DATA");
                exportButton.setFont(new Font("Arial", Font.BOLD, 20));
                exportButton.setBackground(new Color(0x189CD4));
                exportButton.setForeground(Color.WHITE);
                exportButton.setBounds(240, informationLabel.getHeight() + 280, 200, 40);
                defaultPanel.add(exportButton);
                exportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ExportHelper exportHelper = new ExportHelper();
                        boolean isExportSuccessful = exportHelper.export(student.getStudentNum());
                        if (isExportSuccessful) {
                            JOptionPane.showMessageDialog(MainPageFrame.this, "Export Successfully", "Export Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainPageFrame.this, "Export Unsuccessfully", "Export Failure", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

            }
        });
        headerPanel.add(welcomeLabel);

        // Add a Logout Label to the right of the dark blue bar
        JLabel logoutLabel = new JLabel("<html><body><label><u>LOG OUT</u></label></body></html>");
        logoutLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        logoutLabel.setForeground(new Color(0xECE036));
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
        headerPanel.add(logoutLabel);


        // Create a RECORD button
        JButton recordButton = new JButton("RECORD");
        recordButton.setFont(new Font("Arial", Font.BOLD, 15));
        recordButton.setBackground(new Color(0x1A3370));
        recordButton.setForeground(new Color(0xECE036));
        sidebar.add(recordButton);

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c2 = (CardLayout) (mainPanel.getLayout());
                c2.show(mainPanel, "record panel");
            }
        });

        // Create a CHECK button
        JButton checkButton = new JButton("CHECK");
        checkButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkButton.setBackground(new Color(0x1A3370));
        checkButton.setForeground(new Color(0xECE036));
        sidebar.add(checkButton);

        // check function requirement
        checkPanel.setBackground(new Color(123123));
        checkPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        checkPanel.setLayout(new BorderLayout());
        mainPanel.add(checkPanel, "check panel");

        checkButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //切换到checkPanel
                CardLayout c3 = (CardLayout) (mainPanel.getLayout());
                c3.show(mainPanel, "check panel");

                checkPanel.removeAll();

                JPanel searchPanel = new JPanel();
                searchPanel.setLayout(new FlowLayout());
                JTextField searchField = new JTextField(20);
                searchField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                JButton searchButton = new JButton("Search");
                searchButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
                searchButton.setBackground(new Color(0x189CD4));
                searchButton.setForeground(Color.WHITE);
                searchPanel.add(searchField);
                searchPanel.add(searchButton);

                String[] columnNames = {"Module ID", "Name", "Teacher", "Credit", "Grade", "Type", "Semester"};
                checkModel.setColumnIdentifiers(columnNames);

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(checkModel);
                JTable moduleTable = new JTable(checkModel);
                moduleTable.setFont(new Font("微软雅黑", Font.PLAIN, 17));
                moduleTable.setForeground(new Color(0xECE036));
                moduleTable.setBackground(new Color(0x1A3370));
                moduleTable.setRowHeight(25);
                JTableHeader header = moduleTable.getTableHeader();
                header.setFont(new Font("微软雅黑", Font.BOLD, 17));
                header.setForeground(new Color(0xECE036));
                header.setBackground(new Color(0x1A3370));
                moduleTable.setRowSorter(sorter);

                boolean isCheckSuccessful = checkHelper.check(student.getStudentNum());
                List<entity.Module> modules = null;

                if (isCheckSuccessful) {
                    modules = checkHelper.getModules();
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Check Unsuccessfully", "Check Failure", JOptionPane.ERROR_MESSAGE);
                }

                checkModel.setRowCount(0);

                assert modules != null;
                for (entity.Module module : modules) {
                    String moduleID = module.getModuleID();
                    String moduleName = module.getName();
                    String teacher = module.getTeacher();
                    String credit = String.valueOf(module.getCredit());
                    String grade = String.valueOf(module.getGrade());
                    String type = module.getType();
                    int semester = module.getSemester();
                    Object[] rowData = {moduleID, moduleName, teacher, credit, grade, type, semester};
                    checkModel.addRow(rowData);
                }

                JScrollPane scrollPane = new JScrollPane(moduleTable);
                scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 200));
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                checkPanel.add(searchPanel, BorderLayout.NORTH);
                checkPanel.add(scrollPane, BorderLayout.CENTER);


                sorter.setSortable(0, true);
                sorter.setSortable(1, true);
                sorter.setSortable(2, true);
                sorter.setSortable(3, true);
                sorter.setSortable(4, true);
                sorter.setSortable(5, true);
                sorter.setSortable(6, true);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String keyword = searchField.getText();
                        if (keyword.length() == 0) {
                            sorter.setRowFilter(null);
                        } else {
                            RowFilter<DefaultTableModel, Object> rowFilter = null;

                            try {
                                rowFilter = RowFilter.regexFilter(keyword);
                            } catch (PatternSyntaxException ex) {
                                return;
                            }
                            sorter.setRowFilter(rowFilter);
                        }
                    }
                });

            }
        });

        // Create a MODIFY button
        JButton modifyButton = new JButton("MODIFY");
        modifyButton.setFont(new Font("Arial", Font.BOLD, 15));
        modifyButton.setBackground(new Color(0x1A3370));
        modifyButton.setForeground(new Color(0xECE036));
        sidebar.add(modifyButton);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) (mainPanel.getLayout());
                c4.show(mainPanel, "modify panel");
            }
        });

        // skill function requirement

        // Create a skill panel
        skillPanel.setBackground(new Color(0xF5F9FA));
        skillPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        skillPanel.setLayout(new BorderLayout());
        mainPanel.add(skillPanel, "skill panel");

        // Create an add-skill panel
        JPanel addSkillPanel = new JPanel(null);
        addSkillPanel.setBackground(new Color(0xF5F9FA));
        addSkillPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        addSkillPanel.setLayout(null);
        mainPanel.add(addSkillPanel, "add skill panel");

        // Create an Add Skill button
        JLabel addSkillLabel = new JLabel("Add Skill");
        addSkillLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        addSkillLabel.setForeground(Color.black);
        addSkillLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addSkillLabel.setBounds(0, addSkillPanel.getHeight() / 7 - 70, addSkillPanel.getWidth(), addSkillPanel.getHeight() / 7);
        addSkillPanel.add(addSkillLabel);

        // Create the skill name label and text field
        JLabel skillNameLabel = new JLabel("Name");
        skillNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        skillNameLabel.setBounds(75, addSkillLabel.getHeight() + 50, 200, 30);
        addSkillPanel.add(skillNameLabel);

        JTextField skillNameField = new JTextField();
        skillNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        skillNameField.setBounds(275, addSkillLabel.getHeight() + 50, 300, 30);
        addSkillPanel.add(skillNameField);

        // Create the skill description label and text field
        JLabel skillDescriptionLabel = new JLabel("Description");
        skillDescriptionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        skillDescriptionLabel.setBounds(75, addSkillLabel.getHeight() + 150, 200, 30);
        addSkillPanel.add(skillDescriptionLabel);

        JTextField skillDescriptionField = new JTextField();
        skillDescriptionField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        skillDescriptionField.setBounds(275, addSkillLabel.getHeight() + 150, 300, 30);
        addSkillPanel.add(skillDescriptionField);

        // Create an ADD button
        JButton addSkillButton = new JButton("ADD");
        addSkillButton.setFont(new Font("Arial", Font.BOLD, 20));
        addSkillButton.setBackground(new Color(0x189CD4));
        addSkillButton.setForeground(Color.WHITE);
        addSkillButton.setBounds(275, addSkillLabel.getHeight() + 250, 150, 40);
        addSkillPanel.add(addSkillButton);

        addSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillHelper skillHelper = new SkillHelper(student.getStudentNum());

                String enteredSkillName = skillNameField.getText().trim();
                String enteredSkillDescription = skillDescriptionField.getText().trim();
                boolean isAddSuccessful = skillHelper.addSkill(enteredSkillName, enteredSkillDescription);
                if (isAddSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
                skillNameField.setText(null);
                skillDescriptionField.setText(null);
            }
        });


        // Create a SKILL button
        JButton skillButton = new JButton("SKILL");
        skillButton.setFont(new Font("Arial", Font.BOLD, 15));
        skillButton.setBackground(new Color(0x1A3370));
        skillButton.setForeground(new Color(0xECE036));
        sidebar.add(skillButton);

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c5 = (CardLayout) (mainPanel.getLayout());
                c5.show(mainPanel, "skill panel");

                skillPanel.removeAll();
                // Create a Your Skill label
                JLabel skillLabel = new JLabel("Your Skills");
                skillLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                skillLabel.setForeground(Color.black);
                skillLabel.setHorizontalAlignment(SwingConstants.CENTER);
                skillLabel.setBounds(0, skillPanel.getHeight() / 7 - 70, skillPanel.getWidth(), skillPanel.getHeight() / 7);
                skillLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                skillPanel.add(skillLabel, BorderLayout.NORTH);

                skillLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CardLayout c6 = (CardLayout) (mainPanel.getLayout());
                        c6.show(mainPanel, "add skill panel");
                    }
                });

                SkillHelper skillHelper = new SkillHelper(student.getStudentNum());
                List<Skill> skillList = skillHelper.getSkillList();
                JList<Skill> skillJList = new JList<>(skillList.toArray(new Skill[skillList.size()]));
                skillJList.setCellRenderer(new CustomCellRenderer());
                skillJList.setFont(new Font("微软雅黑", Font.BOLD, 17));
                skillJList.setBackground(new Color(0xFFF5F9FA, true));
                skillJList.setForeground(new Color(0x0E3474));
                JScrollPane scrollPane = new JScrollPane(skillJList);
                skillPanel.add(scrollPane, BorderLayout.CENTER);

            }
        });

        // role function requirement
        rolePanel.setBackground(new Color(0xF5F9FA));
        rolePanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        rolePanel.setLayout(new BorderLayout());
        mainPanel.add(rolePanel, "role panel");

        // Create a role panel
        JPanel addRolePanel = new JPanel(null);
        addRolePanel.setBackground(new Color(0xF5F9FA));
        addRolePanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        addRolePanel.setLayout(null);
        mainPanel.add(addRolePanel, "add role panel");

        // Create a Add Role requirement
        JLabel addRoleLabel = new JLabel("Add Role");
        addRoleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        addRoleLabel.setForeground(Color.black);
        addRoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addRoleLabel.setBounds(0, addRolePanel.getHeight() / 7 - 70, addRolePanel.getWidth(), addRolePanel.getHeight() / 7);
        addRolePanel.add(addRoleLabel);

        // Create the role name label and text field
        JLabel roleNameLabel = new JLabel("Name");
        roleNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleNameLabel.setBounds(75, addRoleLabel.getHeight() + 50, 200, 30);
        addRolePanel.add(roleNameLabel);

        JTextField roleNameField = new JTextField();
        roleNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleNameField.setBounds(275, addRoleLabel.getHeight() + 50, 300, 30);
        addRolePanel.add(roleNameField);

        // Create the role description label and text field
        JLabel roleDescriptionLabel = new JLabel("Description");
        roleDescriptionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleDescriptionLabel.setBounds(75, addRoleLabel.getHeight() + 130, 200, 30);
        addRolePanel.add(roleDescriptionLabel);

        JTextField roleDescriptionField = new JTextField();
        roleDescriptionField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleDescriptionField.setBounds(275, addRoleLabel.getHeight() + 130, 300, 30);
        addRolePanel.add(roleDescriptionField);

        //Create the role type label and combo box
        JLabel roleTypeLabel = new JLabel("Type");
        roleTypeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleTypeLabel.setBounds(75, addRoleLabel.getHeight() + 210, 200, 30);
        addRolePanel.add(roleTypeLabel);

        JComboBox roleTypeBox = new JComboBox();
        roleTypeBox.addItem("");
        roleTypeBox.addItem("Class Rep");
        roleTypeBox.addItem("Module Rep");
        roleTypeBox.addItem("Volunteer");
        roleTypeBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        roleTypeBox.setBounds(275, addRoleLabel.getHeight() + 210, 300, 30);
        addRolePanel.add(roleTypeBox);

        // Create a ADD button
        JButton addRoleButton = new JButton("ADD");
        addRoleButton.setFont(new Font("Arial", Font.BOLD, 20));
        addRoleButton.setBackground(new Color(0x189CD4));
        addRoleButton.setForeground(Color.WHITE);
        addRoleButton.setBounds(275, addRoleLabel.getHeight() + 290, 150, 40);
        addRolePanel.add(addRoleButton);

        addRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleHelper roleHelper = new RoleHelper(student.getStudentNum());

                String enteredRoleName = roleNameField.getText().trim();
                String enteredRoleDescription = roleDescriptionField.getText().trim();
                String enteredRoleType = roleTypeBox.getSelectedItem().toString().trim();
                boolean isAddSuccessful = roleHelper.addRole(enteredRoleName, enteredRoleDescription, enteredRoleType);
                if (isAddSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
                roleNameField.setText(null);
                roleDescriptionField.setText(null);
                roleTypeBox.setSelectedIndex(0);
            }
        });

        // Create a ROLE button
        JButton roleButton = new JButton("ROLE");
        roleButton.setFont(new Font("Arial", Font.BOLD, 15));
        roleButton.setBackground(new Color(0x1A3370));
        roleButton.setForeground(new Color(0xECE036));
        sidebar.add(roleButton);

        roleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c7 = (CardLayout) (mainPanel.getLayout());
                c7.show(mainPanel, "role panel");

                rolePanel.removeAll();

                // Create a Your Role label
                JLabel roleLabel = new JLabel("Your Roles");
                roleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                roleLabel.setForeground(Color.black);
                roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                roleLabel.setBounds(0, rolePanel.getHeight() / 7 - 70, rolePanel.getWidth(), rolePanel.getHeight() / 7);
                roleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rolePanel.add(roleLabel, BorderLayout.NORTH);

                roleLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CardLayout c8 = (CardLayout) (mainPanel.getLayout());
                        c8.show(mainPanel, "add role panel");
                    }
                });

                RoleHelper roleHelper = new RoleHelper(student.getStudentNum());
                List<Role> roleList = roleHelper.getRoleList();
                JList<Role> roleJList = new JList<>(roleList.toArray(new Role[roleList.size()]));
                roleJList.setCellRenderer(new CustomCellRenderer());
                roleJList.setFont(new Font("微软雅黑", Font.BOLD, 17));
                roleJList.setBackground(new Color(0xFFF5F9FA, true));
                roleJList.setForeground(new Color(0x0E3474));
                JScrollPane scrollPane = new JScrollPane(roleJList);
                rolePanel.add(scrollPane, BorderLayout.CENTER);
            }
        });

        // achievement function requirement
        achievePanel.setBackground(new Color(0xF5F9FA));
        achievePanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        achievePanel.setLayout(new BorderLayout());
        mainPanel.add(achievePanel, "achieve panel");

        // Create an achievement panel
        JPanel addAchievePanel = new JPanel(null);
        addAchievePanel.setBackground(new Color(0xF5F9FA));
        addAchievePanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        addAchievePanel.setLayout(null);
        mainPanel.add(addAchievePanel, "add achieve panel");

        // Create an Add Achievement Label
        JLabel addAchieveLabel = new JLabel("Add Achievement");
        addAchieveLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        addAchieveLabel.setForeground(Color.black);
        addAchieveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addAchieveLabel.setBounds(0, addAchievePanel.getHeight() / 7 - 70, addAchievePanel.getWidth(), addAchievePanel.getHeight() / 7);
        addAchievePanel.add(addAchieveLabel);

        // Create the achievement name label and text field
        JLabel achieveNameLabel = new JLabel("Name");
        achieveNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        achieveNameLabel.setBounds(75, addAchieveLabel.getHeight() + 50, 200, 30);
        addAchievePanel.add(achieveNameLabel);

        JTextField achieveNameField = new JTextField();
        achieveNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        achieveNameField.setBounds(275, addAchieveLabel.getHeight() + 50, 300, 30);
        addAchievePanel.add(achieveNameField);

        // Create the achievement description label and text field
        JLabel achieveDescriptionLabel = new JLabel("Description");
        achieveDescriptionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        achieveDescriptionLabel.setBounds(75, addAchieveLabel.getHeight() + 150, 200, 30);
        addAchievePanel.add(achieveDescriptionLabel);

        JTextField achieveDescriptionField = new JTextField();
        achieveDescriptionField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        achieveDescriptionField.setBounds(275, addAchieveLabel.getHeight() + 150, 300, 30);
        addAchievePanel.add(achieveDescriptionField);

        // Create an ADD button
        JButton addAchieveButton = new JButton("ADD");
        addAchieveButton.setFont(new Font("Arial", Font.BOLD, 20));
        addAchieveButton.setBackground(new Color(0x189CD4));
        addAchieveButton.setForeground(Color.WHITE);
        addAchieveButton.setBounds(275, addAchieveLabel.getHeight() + 250, 150, 40);
        addAchievePanel.add(addAchieveButton);

        addAchieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AchieveHelper achieveHelper = new AchieveHelper(student.getStudentNum());

                String enteredAchieveName = achieveNameField.getText().trim();
                String enteredAchieveDescription = achieveDescriptionField.getText().trim();

                boolean isAddSuccessful = achieveHelper.addAchievement(enteredAchieveName, enteredAchieveDescription);
                if (isAddSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
                achieveNameField.setText(null);
                achieveDescriptionField.setText(null);
            }
        });

        // Create an ACHIEVE button
        JButton achievementButton = new JButton("ACHIEVE");
        achievementButton.setFont(new Font("Arial", Font.BOLD, 15));
        achievementButton.setBackground(new Color(0x1A3370));
        achievementButton.setForeground(new Color(0xECE036));
        sidebar.add(achievementButton);

        achievementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c10 = (CardLayout) (mainPanel.getLayout());
                c10.show(mainPanel, "achieve panel");

                achievePanel.removeAll();

                // Create a Your Achievement label
                JLabel achieveLabel = new JLabel("Your Achievements");
                achieveLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                achieveLabel.setForeground(Color.black);
                achieveLabel.setHorizontalAlignment(SwingConstants.CENTER);
                achieveLabel.setBounds(0, achievePanel.getHeight() / 7 - 70, achievePanel.getWidth(), achievePanel.getHeight() / 7);
                achieveLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                achievePanel.add(achieveLabel, BorderLayout.NORTH);

                achieveLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CardLayout c11 = (CardLayout) (mainPanel.getLayout());
                        c11.show(mainPanel, "add achieve panel");
                    }
                });

                AchieveHelper achieveHelper = new AchieveHelper(student.getStudentNum());
                List<Achievement> achieveList = achieveHelper.getAchievementList();
                JList<Achievement> achieveJList = new JList<>(achieveList.toArray(new Achievement[achieveList.size()]));
                achieveJList.setCellRenderer(new CustomCellRenderer());
                achieveJList.setFont(new Font("微软雅黑", Font.BOLD, 17));
                achieveJList.setBackground(new Color(0xFFF5F9FA, true));
                achieveJList.setForeground(new Color(0x0E3474));
                JScrollPane scrollPane = new JScrollPane(achieveJList);
                achievePanel.add(scrollPane, BorderLayout.CENTER);
            }
        });

        // extra function requirement
        extraPanel.setBackground(new Color(0xF5F9FA));
        extraPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        extraPanel.setLayout(new BorderLayout());
        mainPanel.add(extraPanel, "extra panel");

        // Create a extra curriculum panel
        JPanel addExtraPanel = new JPanel(null);
        addExtraPanel.setBackground(new Color(0xF5F9FA));
        addExtraPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        addExtraPanel.setLayout(null);
        mainPanel.add(addExtraPanel, "add extra panel");

        // Create an Add Extra Label
        JLabel addExtraLabel = new JLabel("Add Extra Curriculum");
        addExtraLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        addExtraLabel.setForeground(Color.black);
        addExtraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addExtraLabel.setBounds(0, addExtraPanel.getHeight() / 7 - 70, addExtraPanel.getWidth(), addExtraPanel.getHeight() / 7);
        addExtraPanel.add(addExtraLabel);

        // Create the extra name label and text field
        JLabel extraNameLabel = new JLabel("Name");
        extraNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        extraNameLabel.setBounds(75, addExtraLabel.getHeight() + 50, 200, 30);
        addExtraPanel.add(extraNameLabel);

        JTextField extraNameField = new JTextField();
        extraNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        extraNameField.setBounds(275, addExtraLabel.getHeight() + 50, 300, 30);
        addExtraPanel.add(extraNameField);

        // Create the extra description label and text field
        JLabel extraDescriptionLabel = new JLabel("Description");
        extraDescriptionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        extraDescriptionLabel.setBounds(75, addExtraLabel.getHeight() + 150, 200, 30);
        addExtraPanel.add(extraDescriptionLabel);

        JTextField extraDescriptionField = new JTextField();
        extraDescriptionField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        extraDescriptionField.setBounds(275, addExtraLabel.getHeight() + 150, 300, 30);
        addExtraPanel.add(extraDescriptionField);

        // Create an ADD button
        JButton addExtraButton = new JButton("ADD");
        addExtraButton.setFont(new Font("Arial", Font.BOLD, 20));
        addExtraButton.setBackground(new Color(0x189CD4));
        addExtraButton.setForeground(Color.WHITE);
        addExtraButton.setBounds(275, addExtraLabel.getHeight() + 250, 150, 40);
        addExtraPanel.add(addExtraButton);

        addExtraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExtraHelper extraHelper = new ExtraHelper(student.getStudentNum());

                String enteredExtraName = extraNameField.getText().trim();
                String enteredExtraDescription = extraDescriptionField.getText().trim();

                boolean isAddSuccessful = extraHelper.addCurriculum(enteredExtraName, enteredExtraDescription);
                if (isAddSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
                extraNameField.setText(null);
                extraDescriptionField.setText(null);
            }
        });

        // Create an EXTRA button
        JButton extraButton = new JButton("EXTRA");
        extraButton.setFont(new Font("Arial", Font.BOLD, 15));
        extraButton.setBackground(new Color(0x1A3370));
        extraButton.setForeground(new Color(0xECE036));
        sidebar.add(extraButton);

        extraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c12 = (CardLayout) (mainPanel.getLayout());
                c12.show(mainPanel, "extra panel");

                extraPanel.removeAll();

                // Create a Your Extra Curriculum label
                JLabel extraCurriculumsLabel = new JLabel("Your Extra Curriculums");
                extraCurriculumsLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                extraCurriculumsLabel.setForeground(Color.black);
                extraCurriculumsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                extraCurriculumsLabel.setBounds(0, extraPanel.getHeight() / 7 - 70, extraPanel.getWidth(), extraPanel.getHeight() / 7);
                extraCurriculumsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                extraPanel.add(extraCurriculumsLabel, BorderLayout.NORTH);

                extraCurriculumsLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CardLayout c13 = (CardLayout) (mainPanel.getLayout());
                        c13.show(mainPanel, "add extra panel");
                    }
                });

                ExtraHelper extraHelper = new ExtraHelper(student.getStudentNum());
                List<Curriculum> curriculumList = extraHelper.getCurriculumList();
                JList<Curriculum> curriculumJList = new JList<>(curriculumList.toArray(new Curriculum[curriculumList.size()]));
                curriculumJList.setCellRenderer(new CustomCellRenderer());
                curriculumJList.setFont(new Font("微软雅黑", Font.BOLD, 17));
                curriculumJList.setBackground(new Color(0xFFF5F9FA, true));
                curriculumJList.setForeground(new Color(0x0E3474));
                JScrollPane scrollPane = new JScrollPane(curriculumJList);
                extraPanel.add(scrollPane, BorderLayout.CENTER);
            }
        });

        // GPA function requirement

        // Create a GPA Panel
        JPanel GPAPanel = new JPanel();
        GPAPanel.setBackground(new Color(0xF5F9FA));
        GPAPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        GPAPanel.setLayout(null);
        mainPanel.add(GPAPanel, "GPA panel");

        JButton GPAButton = new JButton("GPA");
        GPAButton.setFont(new Font("Arial", Font.BOLD, 15));
        GPAButton.setBackground(new Color(0x1A3370));
        GPAButton.setForeground(new Color(0xECE036));
        sidebar.add(GPAButton);

        GPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c9 = (CardLayout) (mainPanel.getLayout());
                c9.show(mainPanel, "GPA panel");

                // Create a MY GPA label
                JLabel myGPALabel = new JLabel("MY GPA");
                myGPALabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
                myGPALabel.setForeground(Color.black);
                myGPALabel.setHorizontalAlignment(SwingConstants.CENTER);
                myGPALabel.setBounds(0, GPAPanel.getHeight() / 7 - 70, GPAPanel.getWidth(), GPAPanel.getHeight() / 7);
                GPAPanel.add(myGPALabel);

                // Create the semester label and combo box
                JLabel semesterLabel = new JLabel("Semester");
                semesterLabel.setForeground(new Color(0x0E3474));
                semesterLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
                semesterLabel.setBounds(75, myGPALabel.getHeight() + 20, 150, 30);
                GPAPanel.add(semesterLabel);

                JComboBox semesterBox = new JComboBox();
                semesterBox.addItem("1");
                semesterBox.addItem("2");
                semesterBox.addItem("3");
                semesterBox.addItem("4");
                semesterBox.addItem("5");
                semesterBox.addItem("6");
                semesterBox.addItem("7");
                semesterBox.addItem("8");
                semesterBox.setFont(new Font("微软雅黑", Font.PLAIN, 25));
                semesterBox.setBounds(235, myGPALabel.getHeight() + 20, 200, 30);
                GPAPanel.add(semesterBox);

                // Create a Confirm Button
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setFont(new Font("Arial", Font.BOLD, 15));
                confirmButton.setBackground(new Color(0x189CD4));
                confirmButton.setForeground(Color.WHITE);
                confirmButton.setBounds(475, myGPALabel.getHeight() + 20, 125, 30);
                GPAPanel.add(confirmButton);

                // Create the GPA label
                JLabel GPA = new JLabel();
                GPA.setForeground(new Color(0x0E3474));
                GPA.setFont(new Font("微软雅黑", Font.BOLD, 25));
                GPA.setBounds(275, myGPALabel.getHeight() + 130, 300, 30);
                GPAPanel.add(GPA);

                //// Create the Grade label
                JLabel Grade = new JLabel();
                Grade.setForeground(new Color(0x0E3474));
                Grade.setFont(new Font("微软雅黑", Font.BOLD, 25));
                Grade.setBounds(275, myGPALabel.getHeight() + 240, 300, 30);
                GPAPanel.add(Grade);

                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GPAHelper gpaHelper = new GPAHelper(student.getStudentNum());
                        int semester = Integer.parseInt(semesterBox.getSelectedItem().toString());
                        GPA.setText(String.format("%.4f", gpaHelper.getGPA(semester)));
                        Grade.setText(String.format("%.4f", gpaHelper.getGrade(semester)));
                    }
                });

                // Create the "GPA" label
                JLabel GPALabel = new JLabel("GPA");
                GPALabel.setForeground(new Color(0x0E3474));
                GPALabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
                GPALabel.setBounds(75, myGPALabel.getHeight() + 130, 200, 30);
                GPAPanel.add(GPALabel);

                // Create the "Grade" label
                JLabel GradeLabel = new JLabel("Grade");
                GradeLabel.setForeground(new Color(0x0E3474));
                GradeLabel.setFont(new Font("微软雅黑", Font.BOLD, 25));
                GradeLabel.setBounds(75, myGPALabel.getHeight() + 240, 150, 30);
                GPAPanel.add(GradeLabel);

            }
        });


        // Create a Record Panel
        JPanel recordPanel = new JPanel();
        recordPanel.setBackground(new Color(0xF5F9FA));
        recordPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        recordPanel.setLayout(null);
        mainPanel.add(recordPanel, "record panel");

        // Create an Add New Module label
        JLabel recordLabel = new JLabel("Add New Module");
        recordLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        recordLabel.setForeground(Color.black);
        recordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recordLabel.setBounds(0, recordPanel.getHeight() / 7 - 70, recordPanel.getWidth(), recordPanel.getHeight() / 7);
        recordPanel.add(recordLabel);

        // Create the moduleID label and text field
        JLabel moduleIDLabel = new JLabel("Module ID");
        moduleIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDLabel.setBounds(75, recordLabel.getHeight() + 10, 200, 30);
        recordPanel.add(moduleIDLabel);

        JTextField moduleIDField = new JTextField();
        moduleIDField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDField.setBounds(275, recordLabel.getHeight() + 10, 300, 30);
        recordPanel.add(moduleIDField);

        // Create the module name label and text field
        JLabel moduleNameLabel = new JLabel("Module Name");
        moduleNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameLabel.setBounds(75, recordLabel.getHeight() + 60, 200, 30);
        recordPanel.add(moduleNameLabel);

        JTextField moduleNameField = new JTextField();
        moduleNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameField.setBounds(275, recordLabel.getHeight() + 60, 300, 30);
        recordPanel.add(moduleNameField);

        // Create the teacher label and text field
        JLabel teacherLabel = new JLabel("Teacher");
        teacherLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherLabel.setBounds(75, recordLabel.getHeight() + 110, 200, 30);
        recordPanel.add(teacherLabel);

        JTextField teacherField = new JTextField();
        teacherField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherField.setBounds(275, recordLabel.getHeight() + 110, 300, 30);
        recordPanel.add(teacherField);

        // Create the credit label and text field
        JLabel creditLabel = new JLabel("Credit");
        creditLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditLabel.setBounds(75, recordLabel.getHeight() + 160, 200, 30);
        recordPanel.add(creditLabel);

        JTextField creditField = new JTextField();
        creditField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditField.setBounds(275, recordLabel.getHeight() + 160, 300, 30);
        recordPanel.add(creditField);

        // Create the score label and text field
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreLabel.setBounds(75, recordLabel.getHeight() + 210, 200, 30);
        recordPanel.add(scoreLabel);

        JTextField scoreField = new JTextField();
        scoreField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreField.setBounds(275, recordLabel.getHeight() + 210, 300, 30);
        recordPanel.add(scoreField);

        // Create the type label and combo box
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        typeLabel.setBounds(75, recordLabel.getHeight() + 260, 200, 30);
        recordPanel.add(typeLabel);

        JComboBox typeBox = new JComboBox();
        typeBox.addItem("");
        typeBox.addItem("Compulsory");
        typeBox.addItem("Elective");
        typeBox.addItem("Public");
        typeBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        typeBox.setBounds(275, recordLabel.getHeight() + 260, 300, 30);
        recordPanel.add(typeBox);

        // Create the semester label and combo box
        JLabel SemesterLabel = new JLabel("Semester");
        SemesterLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        SemesterLabel.setBounds(75, recordLabel.getHeight() + 310, 200, 30);
        recordPanel.add(SemesterLabel);

        JComboBox semesterBox = new JComboBox();
        semesterBox.addItem("1");
        semesterBox.addItem("2");
        semesterBox.addItem("3");
        semesterBox.addItem("4");
        semesterBox.addItem("5");
        semesterBox.addItem("6");
        semesterBox.addItem("7");
        semesterBox.addItem("8");
        semesterBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        semesterBox.setBounds(275, recordLabel.getHeight() + 310, 300, 30);
        recordPanel.add(semesterBox);

        // Create an ADD button
        JButton addButton = new JButton("ADD");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(0x189CD4));
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(275, recordLabel.getHeight() + 360, 150, 30);
        recordPanel.add(addButton);

        RecordHelper recordHelper = new RecordHelper();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredModuleID = moduleIDField.getText().trim();
                String enteredModuleName = moduleNameField.getText().trim();
                String enteredTeacher = teacherField.getText().trim();
                double enteredCredit = Double.parseDouble(creditField.getText().trim());
                double enteredScore = Double.parseDouble(scoreField.getText().trim());
                String enteredType = typeBox.getSelectedItem().toString();
                int enteredSemester = Integer.parseInt(semesterBox.getSelectedItem().toString());

                boolean isRecordSuccessful = recordHelper.record(student.getStudentNum(), enteredModuleID, enteredModuleName, enteredTeacher, enteredCredit, enteredScore, enteredType, enteredSemester);
                if (isRecordSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
                moduleIDField.setText(null);
                moduleNameField.setText(null);
                teacherField.setText(null);
                creditField.setText(null);
                scoreField.setText(null);
                typeBox.setSelectedIndex(0);
                semesterBox.setSelectedIndex(0);
            }
        });

        // Create a modify panel
        JPanel modifyPanel = new JPanel();
        modifyPanel.setBackground(new Color(0xF5F9FA));
        modifyPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        modifyPanel.setLayout(null);
        mainPanel.add(modifyPanel, "modify panel");

        // Create a Modify Module label
        JLabel modifyLabel = new JLabel("Modify Module");
        modifyLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        modifyLabel.setForeground(Color.black);
        modifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modifyLabel.setBounds(0, modifyPanel.getHeight() / 7 - 70, modifyPanel.getWidth(), modifyPanel.getHeight() / 7);
        modifyPanel.add(modifyLabel);

        // Create the moduleID label and text field
        JLabel moduleIDLabel_modify = new JLabel("Module ID");
        moduleIDLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDLabel_modify.setBounds(75, modifyLabel.getHeight() + 10, 200, 30);
        modifyPanel.add(moduleIDLabel_modify);

        JTextField moduleIDField_modify = new JTextField();
        moduleIDField_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDField_modify.setBounds(275, modifyLabel.getHeight() + 10, 300, 30);
        modifyPanel.add(moduleIDField_modify);

        // Create the module name label and text field
        JLabel moduleNameLabel_modify = new JLabel("Module Name");
        moduleNameLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameLabel_modify.setBounds(75, modifyLabel.getHeight() + 60, 200, 30);
        modifyPanel.add(moduleNameLabel_modify);

        JTextField moduleNameField_modify = new JTextField();
        moduleNameField_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameField_modify.setBounds(275, modifyLabel.getHeight() + 60, 300, 30);
        modifyPanel.add(moduleNameField_modify);

        // Create the teacher label and text field
        JLabel teacherLabel_modify = new JLabel("Teacher");
        teacherLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherLabel_modify.setBounds(75, modifyLabel.getHeight() + 110, 200, 30);
        modifyPanel.add(teacherLabel_modify);

        JTextField teacherField_modify = new JTextField();
        teacherField_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherField_modify.setBounds(275, modifyLabel.getHeight() + 110, 300, 30);
        modifyPanel.add(teacherField_modify);

        // Create the credit label and text field
        JLabel creditLabel_modify = new JLabel("Credit");
        creditLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditLabel_modify.setBounds(75, modifyLabel.getHeight() + 160, 200, 30);
        modifyPanel.add(creditLabel_modify);

        JTextField creditField_modify = new JTextField();
        creditField_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditField_modify.setBounds(275, modifyLabel.getHeight() + 160, 300, 30);
        modifyPanel.add(creditField_modify);

        // Create the score label and text field
        JLabel scoreLabel_modify = new JLabel("Score");
        scoreLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreLabel_modify.setBounds(75, modifyLabel.getHeight() + 210, 200, 30);
        modifyPanel.add(scoreLabel_modify);

        JTextField scoreField_modify = new JTextField();
        scoreField_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreField_modify.setBounds(275, modifyLabel.getHeight() + 210, 300, 30);
        modifyPanel.add(scoreField_modify);

        // Create the type label and combo box
        JLabel typeLabel_modify = new JLabel("Type");
        typeLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        typeLabel_modify.setBounds(75, modifyLabel.getHeight() + 260, 200, 30);
        modifyPanel.add(typeLabel_modify);

        JComboBox typeBox_modify = new JComboBox();
        typeBox_modify.addItem("");
        typeBox_modify.addItem("Compulsory");
        typeBox_modify.addItem("Elective");
        typeBox_modify.addItem("Public");
        typeBox_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        typeBox_modify.setBounds(275, modifyLabel.getHeight() + 260, 300, 30);
        modifyPanel.add(typeBox_modify);

        // Create the semester label and combo box
        JLabel SemesterLabel_modify = new JLabel("Semester");
        SemesterLabel_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        SemesterLabel_modify.setBounds(75, modifyLabel.getHeight() + 310, 200, 30);
        modifyPanel.add(SemesterLabel_modify);

        JComboBox semesterBox_modify = new JComboBox();
        semesterBox_modify.addItem("");
        semesterBox_modify.addItem("1");
        semesterBox_modify.addItem("2");
        semesterBox_modify.addItem("3");
        semesterBox_modify.addItem("4");
        semesterBox_modify.addItem("5");
        semesterBox_modify.addItem("6");
        semesterBox_modify.addItem("7");
        semesterBox_modify.addItem("8");
        semesterBox_modify.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        semesterBox_modify.setBounds(275, modifyLabel.getHeight() + 310, 300, 30);
        modifyPanel.add(semesterBox_modify);

        // Create a MODIFY button
        JButton modifyButton1 = new JButton("MODIFY");
        modifyButton1.setFont(new Font("Arial", Font.BOLD, 20));
        modifyButton1.setBackground(new Color(0x189CD4));
        modifyButton1.setForeground(Color.WHITE);
        modifyButton1.setBounds(275, modifyLabel.getHeight() + 360, 150, 30);
        modifyPanel.add(modifyButton1);

        ModifyHelper modifyHelper = new ModifyHelper();
        modifyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String enteredModuleID = moduleIDField_modify.getText().trim();
                String enteredModuleName = moduleNameField_modify.getText().trim();
                String enteredTeacher = teacherField_modify.getText().trim();
                double enteredCredit = Double.parseDouble(creditField_modify.getText().trim());
                double enteredScore = Double.parseDouble(scoreField_modify.getText().trim());
                String enteredType = typeBox_modify.getSelectedItem().toString();
                int enteredSemester = Integer.parseInt(semesterBox_modify.getSelectedItem().toString());
                boolean isModifySuccessful = modifyHelper.modify(student.getStudentNum(), enteredModuleID, enteredModuleName, enteredTeacher, enteredCredit, enteredScore, enteredType, enteredSemester);
                if (isModifySuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Modify Successfully", "Modify Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Modify Unsuccessfully", "Modify Failure", JOptionPane.ERROR_MESSAGE);
                }
                moduleIDField_modify.setText(null);
                moduleNameField_modify.setText(null);
                teacherField_modify.setText(null);
                creditField_modify.setText(null);
                scoreField_modify.setText(null);
                typeBox_modify.setSelectedIndex(0);
            }
        });

    }

}