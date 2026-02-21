package Tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        boolean isEvenRow = row % 2 == 0;
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Font roboto = new Font("Roboto", Font.PLAIN, 14);

        if (!isSelected) {
            c.setFont(roboto);
            if (isEvenRow) {
                applyLightTheme(c);
            } else {
                applyDarkTheme(c);
            }
        } else {
            c.setBackground(new Color(145, 82, 173));
            c.setForeground(Color.WHITE);
            c.setFont(roboto.deriveFont(Font.BOLD));
        }

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            if (isSelected) {
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }

        if (value instanceof JLabel) {
            return (JLabel) value;
        }

        return c;
    }

    private void applyLightTheme(Component c) {
        c.setBackground(Color.WHITE);
        c.setForeground(Color.BLACK);
    }

    private void applyDarkTheme(Component c) {
        c.setBackground(new Color(88, 18, 121));
        c.setForeground(Color.WHITE);
    }

}
