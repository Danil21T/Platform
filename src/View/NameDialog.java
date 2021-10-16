package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameDialog extends JDialog {
    public NameDialog(JFrame f){
        super(f,"Entered name",true);
        setLayout(new LayoutCenter(2,6));
        setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-150,
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-100,
                300, 200);
        setResizable(false);

        JTextField t = new JTextField("Name",25);
        add(t);
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game g = new Game(f, t.getText());
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.getContentPane().removeAll();
                g.setPreferredSize(new Dimension(f.getWidth() ,f.getHeight() ));
                f.setLayout(new BorderLayout());
                f.getContentPane().add(g,BorderLayout.CENTER);
                f.pack();
                f.setVisible(true);
                g.start();
                dispose();
            }

        });
        add(ok);
        setVisible(true);
    }


}
