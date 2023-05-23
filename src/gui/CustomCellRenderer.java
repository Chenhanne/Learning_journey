package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ghr
 * @date 2023-04-22 8:56
 */
public class CustomCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setText((index + 1) + ". " + label.getText()); // Add the item number as a prefix to the item text.
        return label;
    }
}

