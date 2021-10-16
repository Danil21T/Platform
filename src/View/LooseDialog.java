package View;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LooseDialog extends JDialog {
    private Player p;
    public LooseDialog(JFrame f, Player player) {
        super(f, "You have lost", true);
        p = player;
        setLayout(new BorderLayout());
        setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 100,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 75,
                200, 150);
        setResizable(false);
        add(new ShowScore(),BorderLayout.CENTER);
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        Menu m = new Menu(f);
        add(ok, BorderLayout.SOUTH);
        setVisible(true);
    }

public class ShowScore extends JComponent {
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Yu Gothic Light", Font.BOLD, 15);
        g2.setFont(font);
        g2.drawString("Name: " + p.getName(), 20, 20);
        g2.drawString("Score: " + p.getScore(), 20, 50);

    }
}

}
