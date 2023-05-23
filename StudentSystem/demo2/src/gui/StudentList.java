package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentList extends JPanel {
    private JTable table;

    public StudentList() {
        setLayout(new BorderLayout());

        // Create the data model and populate with student data
        String[] columnNames = {"Name", "Age", "Grade"};
        Object[][] rowData = {
                {"Alice", 16, "A"},
                {"Bob", 17, "B"},
                {"Charlie", 15, "C"}
        };
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

        // Create the JTable with the data model
        table = new JTable(model);

        // Add the JTable to the panel
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Student List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel to the frame
        StudentList panel = new StudentList();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
