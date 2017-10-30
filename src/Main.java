import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Button b = new Button();
        JFrame frame = new JFrame("enter pin code");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);
        b.display();
        frame.getContentPane().add(b.p);
        frame.setSize(500, 600);
        frame.setResizable(true);
    }
}
