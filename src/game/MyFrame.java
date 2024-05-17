package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class MyFrame extends JFrame implements ActionListener {

    public ArrayList<TileButton> tiles = new ArrayList<>();
    public int globalItemIndex = 11;
//    TileButton button;
//        button.setText("Click me");
//        button.setSize(200, 200);

    MyFrame() {
        this.setTitle("Imie nazwisko nr indeksu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(600, 600));
        this.setLayout(null);
        this.setSize(1120, 860);

        this.getContentPane().setBackground(new Color(25, 116, 51));

        generateBoard(Constants.SIZE_X, Constants.SIZE_Y, Constants.IS_HEX);
        this.setVisible(true);
    }

    void generateBoard(int sizeX, int sizeY, boolean isHex)
    {
        for(int i=0; i<sizeX; i++)
        {
            for(int j=0; j<sizeY; j++)
            {
                TileButton tile = new TileButton(i, j);
                tile.addActionListener(this);

                tiles.add(tile);
                this.add(tile);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Button clicked");
        for (TileButton button : tiles) {
            if (e.getSource() == button) {
                System.out.println("Button clicked");
                if (globalItemIndex == 11)
                    globalItemIndex = 0;
                else
                    globalItemIndex++;
                if(globalItemIndex==10)
                    globalItemIndex = 11;

                button.clickAction(globalItemIndex);
                break;
            }
        }
    }
}
