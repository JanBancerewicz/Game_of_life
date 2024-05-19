package animals;
import game.*;

import java.util.Scanner;

import static game.Constants.*;


public class Human extends Animal {
//    private int countdown = 0;
    public int humandir = 0;

    private int countdown=0;

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public int movementHandler() {
        Scanner scanner = new Scanner(System.in);
        int loop = 1;
        while (loop == 1) {
            loop = 0;
            char ch1 = scanner.next().charAt(0);
            switch (ch1) {
                case KEY_ARROW_1:
                    char ch2 = scanner.next().charAt(0);
                    switch (ch2) {
                        case KEY_ARROW_RIGHT:
                        case KEY_ARROW_right:
                            System.out.println("GOOD INPUT");
                            return 4;
                        case KEY_ARROW_UP:
                        case KEY_ARROW_up:
                            System.out.println("GOOD INPUT");
                            return 3;
                        case KEY_ARROW_LEFT:
                        case KEY_ARROW_left:
                            System.out.println("GOOD INPUT");
                            return 2;
                        case KEY_ARROW_DOWN:
                        case KEY_ARROW_down:
                            System.out.println("GOOD INPUT");
                            return 1;
                        default:
                            System.out.println("INVALID INPUT");
                            return 0;
                    }
                default:
                    switch (ch1) {
                        case KEY_ARROW_RIGHT:
                        case KEY_ARROW_right:
//                            System.out.println("Prawo");
                            return 4;
                        case KEY_ARROW_UP:
                        case KEY_ARROW_up:
//                            System.out.println("GORA");
                            return 3;
                        case KEY_ARROW_LEFT:
                        case KEY_ARROW_left:
//                            System.out.println("LEWO");
                            return 2;
                        case KEY_ARROW_DOWN:
                        case KEY_ARROW_down:
//                            System.out.println("DOL");
                            return 1;

                        case KEY_F:
                        case KEY_f:
//                            if (countdown <= 0) {
//                                System.out.println("ULTIMATE ABILITY IS ABOUT TO BE USED");
//                                this.countdown = 10;
//                            } else {
//                                System.out.println("Ultimate cannot be used again for " + countdown + " turns");
//                            }
                            loop = 1;
                            break;
                        case KEY_P:
                        case KEY_p:
//                            if (this.getParent().saveToFile()) {
//                                System.out.println("Game saved");
//                            } else {
//                                System.out.println("Failed to save game");
//                            }
                            loop = 1;
                            break;
                        case KEY_L:
                        case KEY_l:
//                            if (this.getParent().loadFromFile()) {
//                                System.out.println("Game loaded");
//                                this.getParent().drawWorld(); //todo fix
//                            } else {
//                                System.out.println("Failed to load game");
//                            }
                            break;
                        case KEY_Q:
                        case KEY_q:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("INVALID INPUT");
                            loop = 1;
                            break;
                    }
            }
        }
        return 0;
    }

    @Override
    public void reproduction() {
        System.out.println("Human cannot reproduce");
    }

    private void ultimate() {
        if (countdown > 5) {
            System.out.println("Ultimate active");
            System.out.println("Burning everything around");
            this.getParent().getLogger().addLog("Ultimate active, human burns everything around him");
            getParent().destroyEverythingAround(getPosition(), false);
        }
        if (countdown > 0 && countdown <= 5) {
            System.out.println("Ultimate ready in " + countdown + " turns");
        }
        countdown--;
        getParent().cooldown = countdown;
    }

    public Human(Point position) {
        super(position, 'H', OrganismType.HUMAN, 5, 4);
    }


    @Override
    public void action() {
//        if (SINGLE_MAP) { getParent().drawWorld(); }
//        this.getParent().getLogger().printLogs(); //todo fix logs
        System.out.println("Options:[arrows](move)  [F](Ultimate)  [L](load)  [P](save)  [Q](Quit) Autor: Jan Bancerewicz 198099");
        System.out.println("Human action");
        setAge(getAge() + 1);
//        int dir = movementHandler();
        int dir = humandir;
        ultimate();

        if (dir != 0) {
            Point newPosition = getParent().mapPoint(new Point(getPosition()), dir);
            if (getParent().isInBounds(newPosition.getX(), newPosition.getY()) && !(getPosition().getX() == newPosition.getX() && getPosition().getY() == newPosition.getY())) {
                if (getParent().getOrganism(newPosition) == null) {
                    move(newPosition);
                } else {
                    movementAction(newPosition);
                }
            } else {
                System.out.println("No place to move :(");
                this.getParent().getLogger().addLog("Human has no place to move");
            }
        } else {
            System.out.println("No human movement");
            this.getParent().getLogger().addLog("Human performs no movement");
        }
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        return super.collision(target, collisionPoint);
    }

    @Override
    public void die() {

//        this.getParent().getLogger().printLogs(); //todo fix logs
        System.out.println("   _____                          ____                 " );
        System.out.println( "  / ____|                        / __ \\                " );
        System.out.println( " | |  __  __ _ _ __ ___   ___   | |  | |_   _____ _ __ " );
        System.out.println( " | | |_ |/ _` | '_ ` _ \\ / _ \\  | |  | \\ \\ / / _ \\ '__|" );
        System.out.println( " | |__| | (_| | | | | | |  __/  | |__| |\\ V /  __/ |   " );
        System.out.println( "  \\_____|\\__,_|_| |_| |_|\\___|   \\____/  \\_/ \\___|_|   " + "\n");
        System.out.println( "Human died." );
        this.getParent().getLogger().addLog("Human died");

        this.getParent().isGameOver = true;

        Dialog dialog = new Dialog();
//        frame.dispose();


//        new java.util.Scanner(System.in).nextLine(); // stop until user input
//        System.exit(0);
    }
}