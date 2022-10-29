import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{

    public GUI() {
        JLabel label = new JLabel();
        label.setText("Программа для ВУЗов");

        this.setTitle("DataBase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(640, 480);
        this.setVisible(true);

        this.add(label);
    }
}
