package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.jar.JarEntry;

public class OptionPanel extends JPanel {

    MyFrame myframe;

    JTextArea loggingSpace;

    JButton b1, b5, b6, b7, b8, b9, b10;

    public OptionPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.setBackground(new Color(100, 100, 100));
        this.setBounds(860, 0, 360, 860);


//        JLabel label = new JLabel();
//        label.setText("hii, this is a label");
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setHorizontalAlignment(JLabel.LEFT);

        b1 = ButtonFactory("Wykonaj turę", 0, 50, 180, 50);
        b1.setEnabled(false);
        b5 = ButtonFactory("A", 0, 100, 60, 30);
        b6 = ButtonFactory("W", 60, 100, 60, 30);
        b7 = ButtonFactory("S", 120, 100, 60, 30);
        b8 = ButtonFactory("D", 180, 100, 60, 30);
        b9 = ButtonFactory("Q", 260, 100, 60, 30);
        b10 = ButtonFactory("E", 300, 100, 60, 30);
        b1.addActionListener(e -> {
            myframe.requestFocus();
            b1.setEnabled(false);
            startTurn();
            myframe.world.drawWorld(myframe);
        });
        this.add(b1);
        JButton b2 = ButtonFactory("Wczytaj", 120, 0, 180, 50);
        b2.addActionListener(e -> {
            myframe.requestFocus();
            loadGame();
        });
        this.add(b2);
        JButton b3 = ButtonFactory("Zapisz", 0, 0, 180, 50);
        b3.addActionListener(e -> {
            myframe.requestFocus();
            saveGame();
        });
        this.add(b3);
        JButton b4 = ButtonFactory("Umiejętność", 120, 50, 180, 50);
        b4.addActionListener(e -> {
            myframe.requestFocus();
            specialAbility();
        });
        this.add(b4);
        b5.addActionListener(e -> {

            directHuman(2);
        });
        this.add(b5);
        b6.addActionListener(e -> {

            directHuman(3);
        });
        this.add(b6);
        b7.addActionListener(e -> {

            directHuman(1);
        });
        this.add(b7);
        b8.addActionListener(e -> {

            directHuman(4);
        });
        this.add(b8);
        b9.addActionListener(e -> {

            directHuman(5);
        });
        b9.setEnabled(false); //todo if hex
        this.add(b9);
        b10.addActionListener(e -> {

            directHuman(6);
        });
        b10.setEnabled(false);
        this.add(b10);


        loggingSpace = new JTextArea();
        JPanel panel = LoggerFactory(loggingSpace);

        this.add(panel);
        b1.setVisible(true);

    }

    public OptionPanel(MyFrame mf) {
        this();
        this.myframe = mf;
        myframe.op = this;
    }


    JButton ButtonFactory(String text, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setText(text);
//        button.setBounds(x, y, width, height);
        button.setPreferredSize(new Dimension(width, height));

        return button;
    }

    JPanel LoggerFactory(JTextArea textArea) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(new Color(78, 237, 121));
//        panel.setBounds(0, 130, 240, 660);
        panel.setPreferredSize(new Dimension(350, 660));

        textArea.setLineWrap(true);
        textArea.setEditable(false);
        panel.add(textArea, BorderLayout.CENTER);


        JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp);
        return panel;
    }


    void saveGame() {

        if(myframe.world.saveToFile()){
            System.out.println("zapisano gre");
            loggingSpace.append(" zapisano gre\n");
        } else {
            System.out.println("nie zapisano gry");
            loggingSpace.append(" nie zapisano gry\n");
        }

    }
    void loadGame() {

        if(myframe.world.loadFromFile())
        {
            System.out.println("wczytano gre");
            loggingSpace.append(" wczytano gre\n");
        } else{
            System.out.println("nie wczytano gry");
            loggingSpace.append(" nie wczytano gry\n");
        }
        myframe.world.drawWorld(myframe);



    }
    void startTurn() {
        if(myframe.world.isGameOver) {
            System.out.println("koniec gry");
            System.exit(0);
        }
//        System.out.println("tura rozpoczeta");
        loggingSpace.append(" \tTurn: " + (myframe.world.getTurn() + 1)+"\n");
        myframe.world.playTurn(myframe);


        System.out.println("\t\tTurn " + myframe.world.getTurn() + " recap:");
//        myframe.world.getLogger().printLogs();
        myframe.world.getLogger().printLogs(loggingSpace);
//        loggingSpace.append(" tura zakonczona\n");
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b1.setEnabled(false);
    }
    void specialAbility() {
        //TODO
//        System.out.println("umiejetnosc");
//        loggingSpace.append(" umiejetnosc\n");

        if (myframe.world.cooldown <= 0) {
            System.out.println("ULTIMATE ABILITY IS ABOUT TO BE USED");
            loggingSpace.append(" ULTIMATE ABILITY IS ABOUT TO BE USED\n");
            myframe.world.cooldown = 10;
        } else {
            System.out.println("Ultimate cannot be used again for " + myframe.world.cooldown + " turns");
            loggingSpace.append(" Ultimate cannot be used again for " + myframe.world.cooldown + " turns\n");
        }

    }
    void directHuman(int dir) {
        myframe.requestFocus();
        b1.setEnabled(true);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        String dirname= "";
        switch (dir) {
            case 1:
                dirname = "S";
                break;
            case 2:
                dirname = "A";
                break;
            case 3:
                dirname = "W";
                break;
            case 4:
                dirname = "D";
                break;
            case 5:
                dirname = "Q";
                break;
            case 6:
                dirname = "E";
                break;
        }



        loggingSpace.append(" Chosen direction: "+dirname+"\n");
        myframe.humandir = dir;

    }


}
