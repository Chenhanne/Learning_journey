package gui;

/**
 * @author ghr
 * @date 2023-04-17 17:40
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldsComparison extends JFrame implements ActionListener {

    private JTextField textField1, textField2;
    private JLabel resultLabel;

    public TextFieldsComparison() {
        // 创建 JFrame 实例
        super("比较两个文本框");

        // 设置窗口大小
        setSize(300, 200);

        // 设置布局为 FlowLayout
        setLayout(new FlowLayout());

        // 创建文本框和按钮
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        JButton compareButton = new JButton("比较");

        // 给按钮添加事件监听器
        compareButton.addActionListener(this);

        // 创建结果标签
        resultLabel = new JLabel("");

        // 将组件添加到 JFrame 中
        add(textField1);
        add(textField2);
        add(compareButton);
        add(resultLabel);

        // 设置可见性
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 按钮点击时执行的逻辑

        String text1 = textField1.getText();
        String text2 = textField2.getText();

        if (text1.equals(text2)) {
            resultLabel.setText("两个文本框的内容相同");
        } else {
            resultLabel.setText("两个文本框的内容不同");
        }
    }

    public static void main(String[] args) {
        new TextFieldsComparison();
    }
}
