package View;

import IO.ReaderRecords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Records extends JDialog {
    private String way;
    public Records(JFrame j, String way) {
        super(j, "Records", true);
        this.way = way;
        setLayout(new BorderLayout());
        setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 200,
                300, 300);
        setResizable(false);
        add(new ShowRecords(),BorderLayout.CENTER);
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        add(ok, BorderLayout.SOUTH);
        setVisible(true);
    }

    public class ShowRecords extends JComponent {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            try {
                Font font = new Font("Yu Gothic Light", Font.BOLD, 15);
                g2.setFont(font);
                int y = 20;
                g2.drawString("Name", 20, y);
                g2.drawString("Score", 220, y);
                y = 45;
                TreeMap<Integer, ArrayList<String>> spisok = new ReaderRecords(way).ReadRecords();
                int count = 0;
                for (Map.Entry<Integer, ArrayList<String>> entry : spisok.entrySet()) {
                    ArrayList<String> value = entry.getValue();
                    for(String element : value) {
                        count++;
                        g2.drawString(element, 20, y);
                        g2.drawString(entry.getKey().toString(),220, y);
                        y += 20;
                    }
                    if (count == 10)
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
