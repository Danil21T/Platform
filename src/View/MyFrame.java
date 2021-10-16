package View;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(String name){
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 500,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 300,
                1000, 600);
        View.Menu m = new Menu(this);
        setResizable(false);
        setVisible(true);
    }
}
