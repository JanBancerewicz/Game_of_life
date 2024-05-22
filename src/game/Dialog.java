package game;

import javax.swing.*;
import java.awt.*;

public class Dialog {

    private final JFrame frame = new JFrame();
    private final JLabel label = new JLabel("Koniec gry, czlowiek nie zyje!");

    public Dialog() {
        label.setBounds(0,0,400,100 );
        label.setFont(new Font(null, Font.PLAIN, 18));
        label.setForeground(Color.white);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);

        frame.setTitle("Koniec Gry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(400, 100);

        frame.getContentPane().setBackground(new Color(77, 143, 97));
        frame.setVisible(true);


    }
}
