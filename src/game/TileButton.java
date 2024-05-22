package game;

import javax.swing.*;

import static game.Constants.IS_HEX;
import static game.Constants.SIZE_X;


class TileButton extends JButton {

    private int posX=0;
    private int posY=0;

    private TileButton() {
        ImageIcon icon = new ImageIcon("img/res/empty.jpg");
        this.setIcon(icon);
        this.setBounds(0, 0, 40, 40);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 2));
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
    }

    TileButton(int x, int y)
    {
        this(); // call default constructor (for design)
        this.posX = x;
        this.posY = y;
        if(IS_HEX){
            int size=SIZE_X;
            this.setBounds(40*(size-y-1) + x*40 +1, 20*x + y*20 +1, 40, 40);
        }else{
            this.setBounds(x*40 +1, y*40 +1, 40, 40);
        }

    }

    void setImg(int imgIndex) {
        String imgPath = IS_HEX ? "img/res/emptyhex.jpg" :"img/res/empty.jpg";

        switch(imgIndex)
        {
            case 0:
                imgPath = "img/res/grass.jpg";
                break;
            case 1:
                imgPath = "img/res/milkweed.jpg";
                break;
            case 2:
                imgPath = "img/res/guarana.jpg";
                break;
            case 3:
                imgPath = "img/res/berries.jpg";
                break;
            case 4:
                imgPath = "img/res/sosnowski.jpg";
                break;
            case 5:
                imgPath = "img/res/wolf.jpg";
                break;
            case 6:
                imgPath = "img/res/sheep.jpg";
                break;
            case 7:
                imgPath = "img/res/turtle.jpg";
                break;
            case 8:
                imgPath = "img/res/antelope.jpg";
                break;
            case 9:
                imgPath = "img/res/fox.jpg";
                break;
            case 10:
                imgPath = "img/res/human.jpg";
                break;
            case 11:
                imgPath = IS_HEX ? "img/res/emptyhex.jpg": "img/res/empty.jpg";
                break;
            default:
                imgPath = IS_HEX ? "img/res/emptyhex.jpg":  "img/res/empty.jpg";
                break;
        }

        ImageIcon icon = new ImageIcon(imgPath);
        this.setIcon(icon);
//        this.setVerticalAlignment(JLabel.TOP);
//        this.setHorizontalAlignment(JLabel.LEFT);

    }

    Point clickAction(int newIndex)

    {
        this.setImg(newIndex);
        return new Point(posX, posY); //todo check if correct
    }
}
