package view;

import javax.swing.*;
import java.awt.*;

class SeaPanel extends JPanel {
    public static Color[][] color1 = new Color[10][10];
    public static Color[][] color2 = new Color[10][10];

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1100, 600);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 11; i++) {
            g.drawLine(i * 50, 0, i * 50, 500);
            g.drawLine(0, i * 50, 500, i * 50);

        }
        for (int i = 0; i < 11; i++) {
            g.drawLine(i * 50 + 550, 0, i * 50 + 550, 500);
            g.drawLine(550, i * 50, 1050, i * 50);

        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                g.setColor(color1[x][y]);
                g.fillRect(x * 50 + 1, y * 50 + 1, 49, 49);
            }
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                g.setColor(color2[x][y]);
                g.fillRect(x * 50 + 1 + 550, y * 50 + 1, 49, 49);
            }
        }
    }
}
