package gui;

import entity.Module;
import entity.Student;
import javafx.embed.swing.JFXPanel;
import service.CheckHelper;
import service.LoginHelper;
import service.RecordHelper;
import service.RegisterHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
// 应该把多个JPanel封装
public class MainPageFrame extends JFrame {

    JPanel checkPanel = new JPanel();

    public MainPageFrame(Student student) {
        // 设置窗口标题
        setTitle("Main");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 设置窗口大小和位置
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 禁止用户改变窗口大小
        setResizable(false);

        // 创建包含所有组件的面板
        JPanel panel = new JPanel();

        panel.setLayout(null);
        add(panel);

        // 创建深蓝色条框
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(26, 51, 112));
        int headerHeight = getHeight() / 7;
        headerPanel.setBounds(0, 0, getWidth(), headerHeight);
        panel.add(headerPanel);
        panel.setComponentZOrder(headerPanel, 0);  // 将深蓝色条框设置为最顶层组件

        // 在深蓝色条上添加一个图片
        JLabel imageLabel = new JLabel(Data.qmplus);
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));  // 设置左对齐，并且添加水平和垂直间距
        headerPanel.add(imageLabel);
        // 在深蓝色条上写一个占据空间的Label
        JLabel emptyLabel = new JLabel("                                         ");
        emptyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        headerPanel.add(emptyLabel);

        // 在深蓝色条右侧写一个Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome! " + student.getName() + "        ");
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0xECE036));
        headerPanel.add(welcomeLabel);

        // 在深蓝色条右侧写一个Logout Label
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


        // 创建侧栏面板
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(26, 51, 112));
        sidebar.setBounds(0, headerHeight, getWidth() / 8, getHeight() - headerHeight);
        sidebar.setLayout(new GridLayout(10, 1));
        panel.add(sidebar);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xA6A127));
        mainPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        mainPanel.setLayout(new CardLayout());//CardLayout 是一种容器布局，它允许在一个容器中切换多个子组件（卡片）的可见性。
        panel.add(mainPanel);

        // 创建按钮
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

        JButton checkButton = new JButton("CHECK");
        checkButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkButton.setBackground(new Color(0x1A3370));
        checkButton.setForeground(new Color(0xECE036));
        sidebar.add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout c3 = (CardLayout) (mainPanel.getLayout());
                c3.show(mainPanel, "check panel");
            }
        });

        JButton searchButton = new JButton("SEARCH");
        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setBackground(new Color(0x1A3370));
        searchButton.setForeground(new Color(0xECE036));
        sidebar.add(searchButton);

        JButton modifyButton = new JButton("MODIFY");
        modifyButton.setFont(new Font("Arial", Font.BOLD, 15));
        modifyButton.setBackground(new Color(0x1A3370));
        modifyButton.setForeground(new Color(0xECE036));
        sidebar.add(modifyButton);

        JButton button1 = new JButton("GPA");
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setBackground(new Color(0x1A3370));
        button1.setForeground(new Color(0xECE036));
        sidebar.add(button1);

        JButton button2 = new JButton("NEXT FUNCTION");
        button2.setFont(new Font("Arial", Font.BOLD, 10));
        button2.setBackground(new Color(0x1A3370));
        button2.setForeground(new Color(0xECE036));
        sidebar.add(button2);

        // 创建不同的Panel
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(new Color(0xF5F9FA));
        defaultPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        mainPanel.add(defaultPanel, "default panel");
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "default panel");

        JPanel recordPanel = new JPanel();
        recordPanel.setBackground(new Color(0xF5F9FA));
        recordPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        recordPanel.setLayout(null);
        mainPanel.add(recordPanel, "record panel");

        // 创建Record功能下的组件
        JLabel recordLabel = new JLabel("Add New Module");
        recordLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        recordLabel.setForeground(Color.black);
        recordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recordLabel.setBounds(0, recordPanel.getHeight() / 7 - 70, recordPanel.getWidth(), recordPanel.getHeight() / 7);
        recordPanel.add(recordLabel);

        // moduleID组件
        JLabel moduleIDLabel = new JLabel("Module ID");
        moduleIDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDLabel.setBounds(75, recordLabel.getHeight() + 10, 200, 30);
        recordPanel.add(moduleIDLabel);

        JTextField moduleIDField = new JTextField();
        moduleIDField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleIDField.setBounds(275, recordLabel.getHeight() + 10, 300, 30);
        recordPanel.add(moduleIDField);

        // moduleName组件
        JLabel moduleNameLabel = new JLabel("Module Name");
        moduleNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameLabel.setBounds(75, recordLabel.getHeight() + 60, 200, 30);
        recordPanel.add(moduleNameLabel);

        JTextField moduleNameField = new JTextField();
        moduleNameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        moduleNameField.setBounds(275, recordLabel.getHeight() + 60, 300, 30);
        recordPanel.add(moduleNameField);

        // teacher组件
        JLabel teacherLabel = new JLabel("Teacher");
        teacherLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherLabel.setBounds(75, recordLabel.getHeight() + 110, 200, 30);
        recordPanel.add(teacherLabel);

        JTextField teacherField = new JTextField();
        teacherField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        teacherField.setBounds(275, recordLabel.getHeight() + 110, 300, 30);
        recordPanel.add(teacherField);

        // credit组件
        JLabel creditLabel = new JLabel("Credit");
        creditLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditLabel.setBounds(75, recordLabel.getHeight() + 160, 200, 30);
        recordPanel.add(creditLabel);

        JTextField creditField = new JTextField();
        creditField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        creditField.setBounds(275, recordLabel.getHeight() + 160, 300, 30);
        recordPanel.add(creditField);

        // Score组件
        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreLabel.setBounds(75, recordLabel.getHeight() + 210, 200, 30);
        recordPanel.add(scoreLabel);

        JTextField scoreField = new JTextField();
        scoreField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        scoreField.setBounds(275, recordLabel.getHeight() + 210, 300, 30);
        recordPanel.add(scoreField);

        // Type组件
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

        //Add按钮组件
        JButton addButton = new JButton("ADD");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(0x189CD4));
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(275, recordLabel.getHeight() + 310, 150, 40);
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

                boolean isRecordSuccessful = recordHelper.record(student.getStudentNum(), enteredModuleID, enteredModuleName, enteredTeacher, enteredCredit, enteredScore, enteredType);
                if (isRecordSuccessful) {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Successfully", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                    checkPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(MainPageFrame.this, "Add Unsuccessfully", "Add Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //check功能界面
        checkPanel.setBackground(new Color(123123));
        checkPanel.setBounds(getWidth() / 8, headerHeight, 7 * getWidth() / 8, getHeight() - headerHeight);
        checkPanel.setLayout(new BorderLayout());
        mainPanel.add(checkPanel, "check panel");

        //课程表格创建
        CheckHelper checkHelper = new CheckHelper();

        String[] columnNames = {"Module ID", "Name", "Teacher", "Credit", "Grade", "Type"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        JTable moduleTable = new JTable(model);
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
        List<Module> modules = null;

        if(isCheckSuccessful) {
            modules = checkHelper.getModules();
        }else{
            JOptionPane.showMessageDialog(MainPageFrame.this, "Check Unsuccessfully", "Check Failure", JOptionPane.ERROR_MESSAGE);
        }

        for(Module module : modules){
            String moduleID = module.getModuleID();
            String moduleName = module.getName();
            String teacher = module.getTeacher();
            String credit = String.valueOf(module.getCredit());
            String grade = String.valueOf(module.getGrade());
            String type = module.getType();
            Object[] rowData = {moduleID, moduleName, teacher, credit, grade, type};
            model.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(moduleTable);
        checkPanel.add(scrollPane,BorderLayout.CENTER);

        sorter.setSortable(0, true); // Module ID
        sorter.setSortable(1, true); // Name
        sorter.setSortable(2, true); // Teacher
        sorter.setSortable(3, true); // Credit
        sorter.setSortable(4, true); // Grade
        sorter.setSortable(5, true); // Type

    }

    public static void main(String[] args) {
        new MainPageFrame(new Student()).setVisible(true);
    }
}