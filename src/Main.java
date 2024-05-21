import game.*;
import animals.*;
import plants.*;
import javax.swing.*;
import java.awt.*;

import java.util.Scanner;

import static game.Constants.*;


class Main {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.print("Is hexagonal map? (y/n): ");
        String isHex = myObj.nextLine();
        if (isHex.equals("y") || isHex.equals("Y")) {
            IS_HEX = true;
        } else {
            IS_HEX = false;
        }

        System.out.print("\nSize of X: ");
        String rozmX = myObj.nextLine();
        if(IS_HEX){
            System.out.println("rozm: " + rozmX + " " + rozmX);
            SIZE_X = Integer.parseInt(rozmX);
            SIZE_Y = Integer.parseInt(rozmX);
        }else{
            System.out.print("\nSize of Y: ");
            String rozmY = myObj.nextLine();
            System.out.println("rozm: " + rozmX + " " + rozmY);
            SIZE_X = Integer.parseInt(rozmX);
            SIZE_Y = Integer.parseInt(rozmY);
        }






        // po ustaleniu rozmiaru

        MyFrame myframe = new game.MyFrame();
        OptionPanel options = new OptionPanel(myframe);

        myframe.add(options);

        myframe.getWorld().drawWorld(myframe);

        myframe.setVisible(true);
        myframe.requestFocus();





//        while (world.getTurn() > -1 && !world.getInfo().isEmpty()) {
////            if (!DETAILED_LOGGING) {
////                System.out.print("\033[H\033[2J");  // Clears the console
////                System.out.flush();
////                System.out.println("\t\tTurn: " + (world.getTurn() + 1));
////                world.drawWorld();
////            }
//            world.playTurn();
//
//            System.out.println("\t\tTurn " + world.getTurn() + " recap:");
//            world.getLogger().printLogs();
//            new java.util.Scanner(System.in).nextLine(); // stop until user input
//        }




    }
}