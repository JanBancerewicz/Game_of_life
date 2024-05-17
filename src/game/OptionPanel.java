package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.jar.JarEntry;

public class OptionPanel extends JPanel {



    public OptionPanel() {
        this.setLayout(null);
        this.setBackground(new Color(100, 100, 100));
        this.setBounds(860, 0, 250, 860);

//        JLabel label = new JLabel();
//        label.setText("hii, this is a label");
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setHorizontalAlignment(JLabel.LEFT);

        JButton b1 = ButtonFactory("Wykonaj turę", 0, 0, 120, 50);
        b1.addActionListener(e -> {
            startTurn();
        });
        this.add(b1);
        JButton b2 = ButtonFactory("Wczytaj", 120, 0, 120, 50);
        b2.addActionListener(e -> {
            loadGame();
        });
        this.add(b2);
        JButton b3 = ButtonFactory("Zapisz", 0, 50, 120, 50);
        b3.addActionListener(e -> {
            saveGame();
        });
        this.add(b3);
        JButton b4 = ButtonFactory("Umiejętność", 120, 50, 120, 50);
        b4.addActionListener(e -> {
            specialAbility();
        });
        this.add(b4);
        JButton b5 = ButtonFactory("A", 0, 100, 60, 30);
        b5.addActionListener(e -> {
            directHuman(1);
        });
        this.add(b5);
        JButton b6 = ButtonFactory("W", 60, 100, 60, 30);
        b6.addActionListener(e -> {
            directHuman(2);
        });
        this.add(b6);
        JButton b7 = ButtonFactory("S", 120, 100, 60, 30);
        b7.addActionListener(e -> {
            directHuman(3);
        });
        this.add(b7);
        JButton b8 = ButtonFactory("D", 180, 100, 60, 30);
        b8.addActionListener(e -> {
            directHuman(4);
        });
        this.add(b8);
        JTextArea textArea = new JTextArea();
        JPanel panel = LoggerFactory(textArea);

        this.add(panel);




    }

    JButton ButtonFactory(String text, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setText(text);
        button.setBounds(x, y, width, height);

        return button;
    }

    JPanel LoggerFactory(JTextArea textArea) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(new Color(78, 237, 121));
        panel.setBounds(0, 130, 240, 660);

        textArea.setLineWrap(true);
        textArea.setEditable(true);
        panel.add(textArea, BorderLayout.CENTER);


        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp);
        return panel;
    }


    void saveGame() {
        //TODO
        System.out.println("zapisz gre");
    }
    void loadGame() {
        //TODO
        System.out.println("wczytaj");
    }
    void startTurn() {
        //TODO
        System.out.println("tura rozpoczeta");
    }
    void specialAbility() {
        //TODO
        System.out.println("umiejetnosc");
    }
    void directHuman(int dir) {
        //TODO
        System.out.println("dir: "+dir);
    }


}
