package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JPanel {

    public Menu(JFrame f) {
        super(new LayoutCenter(5, 9));
        f.getContentPane().add(this);
        Font BigFont = new Font("Yu Gothic Light", Font.BOLD, 25);
        JButton b1 = new JButton("Game");
        b1.setFont(BigFont);
        JButton b2 = new JButton("Records");
        b2.setFont(BigFont);
        JButton b3 = new JButton("Exit");
        b3.setFont(BigFont);

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                NameDialog d = new NameDialog(f);

            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Records r = new Records(f, "src/Record.txt");
            }
        });

        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        add(b1);
        add(b2);
        add(b3);

    }

}
