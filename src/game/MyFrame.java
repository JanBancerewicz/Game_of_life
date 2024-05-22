package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import static game.Constants.IS_HEX;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

    private World world;
    protected final ArrayList<TileButton> tiles = new ArrayList<>();
    private int globalItemIndex = 11;

    private int humandir = 0;
    protected OptionPanel op;

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public int getHumandir() {
        return humandir;
    }

    public void setHumandir(int humandir) {
        this.humandir = humandir;
    }


    public MyFrame() {
        world = new World(); // world declaration
        world.loadFromDefaultFile();
        world.getLogger().resetLogging();

        this.setTitle("World of Organisms");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(600, 600));
        this.setLayout(null);
        this.setSize(1230, 860);
        this.setFocusable(true);


        this.getContentPane().setBackground(new Color(25, 116, 51));

        generateBoard(Constants.SIZE_X, Constants.SIZE_Y);
        this.requestFocus();
        this.addKeyListener(this);
        this.setVisible(true);
    }

    private void generateBoard(int sizeX, int sizeY)
    {
        for(int i=0; i<sizeX; i++)
        {
            for(int j=0; j<sizeY; j++)
            {
                TileButton tile = new TileButton(i, j);
                tile.addActionListener(this);


                tiles.add(tile); // lista przyciskow (do pozniejszej iteracji)
                this.add(tile); // dodanie przycisku do frame
            }
        }


    }


    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("Key typed: "+e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(humandir==0){
            System.out.println("Key pressed: "+e.getKeyChar());
            switch(e.getKeyChar()) {
                case 'w':
                case 'W':
                    humandir = IS_HEX ? 5 : 2;
                    break;
                case 'a':
                case 'A':
                    humandir = IS_HEX ? 4 : 3;
                    break;
                case 's':
                case 'S':
                    humandir = IS_HEX ? 6 : 4;
                    break;
                case 'd':
                case 'D':
                    humandir = 1;
                    break;
                case 'q':
                case 'Q':
                    if(IS_HEX){
                        humandir = 3;
                    }else{
                        humandir = 0;
                    }
                    break;
                case 'e':
                case 'E':
                    if(IS_HEX){
                        humandir = 2;
                    }else{
                        humandir = 0;
                    }
                    break;

                default:
                    humandir = 0;
                    break;
            }
        }
        if(humandir!=0){
            op.directHuman(humandir);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Key released: "+e.getKeyChar());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.requestFocus();
        for (TileButton button : tiles) {
            if (e.getSource() == button) {
                if (globalItemIndex == 11)
                    globalItemIndex = 0;
                else
                    globalItemIndex++;
                if(globalItemIndex==10)
                    globalItemIndex = 11;

                Point p = button.clickAction(globalItemIndex);

                this.world.deleteOrganism(p);
                if(globalItemIndex != 11){
                    this.world.generateOrganism(p, OrganismType.values()[globalItemIndex]);
                    this.world.getOrganism(p).setParent(this.world);
                    System.out.println("Tile("+p.getX() +", "+ p.getY()+") clicked with organismtype: "+OrganismType.values()[globalItemIndex]);

                }

                break;
            }
        }
    }
}
