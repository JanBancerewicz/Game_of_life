import game.*;
import animals.*;
import plants.*;

import java.util.Scanner;

import static game.Constants.*;


public class Main {
    public static <Scanner> void main(String[] args) {
//        System.out.println("Hello world1!");

        World world = new World(); // world declaration
        world.drawWorld();


//        System.out.println("Hello world2!");

        world.loadFromDefaultFile();
        world.getLogger().resetLogging();

        world.drawWorld();
//        java.util.Scanner scanner = new java.util.Scanner(System.in); //todo never used
        new java.util.Scanner(System.in).nextLine(); // stop until user input

        while (world.getTurn() > -1 && !world.getInfo().isEmpty()) {
            if (!DETAILED_LOGGING) {
                System.out.print("\033[H\033[2J");  // Clears the console
                System.out.flush();
                System.out.println("\t\tTurn: " + (world.getTurn() + 1));
                world.drawWorld();
            }
            world.playTurn();

            System.out.println("\t\tTurn " + world.getTurn() + " recap:");
            world.getLogger().printLogs();
            new java.util.Scanner(System.in).nextLine(); // stop until user input
        }





    }
}