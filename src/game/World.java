package game;

import plants.*;
import animals.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static game.Constants.*;



public class World {


    private int turn;
    private Organism[][] tab = new Organism[SIZE_X][SIZE_Y]; // organizmy na planszy
    private ArrayList<Organism> info = new ArrayList<Organism>(); // (posortowana) lista organizmow
    private Logger logger;

    public World() {
        this.initializeMap();
        this.turn = 0;
        this.logger = new Logger();
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Organism getOrganism(Point p) {
        return this.tab[p.getX()][p.getY()];
    }

    public ArrayList<Organism> getInfo() {
        return new ArrayList<>(this.info);
    }

    public int getTurn() {
        return this.turn;
    }

    public void setOrganism(Point p, Organism o) {
        this.tab[p.getX()][p.getY()] = o;
    }

    public int playTurn() {
        this.tabSort();

        int n = this.info.size();
        if (DETAILED_LOGGING_LIST) {
            System.out.println("Rozmiar: " + this.info.size());
            for (int i = 0; i < n; i++) {
                System.out.println(this.info.get(i).getInitiative() + ", " + this.info.get(i).getAge());
            }
        }

        int target = 0;

        // human action should take movement direction (wasd) + optionally skill (f)

        if (SINGLE_MAP) {
            this.drawWorld();
        }
        while (target < n) {
            this.info.get(target).action();
            int newN = this.info.size();
            if (newN < n) {
                n = newN;
            }
            if (!SINGLE_MAP) {
                this.drawWorld();
            }
            if (DETAILED_LOGGING) {
                System.out.println("Rozmiar: " + this.info.size() + " faktyczny: " + n);
            }
            target++;
        }
        return this.turn++;
    }

    public void drawWorld() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (tab[i][j] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(tab[i][j].getAscii());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y;
    }

    public Point mapPoint(Point p, int direction) {
        Point newPoint = new Point(p.getX(), p.getY());
        switch (direction) {
            case 1: // right
                if (isInBounds(p.getX() + 1, p.getY())) {
                    newPoint.setX(p.getX() + 1);
                }
                break;
            case 2: // down
                if (isInBounds(p.getX(), p.getY() - 1)) {
                    newPoint.setY(p.getY() - 1);
                }
                break;
            case 3: // left
                if (isInBounds(p.getX() - 1, p.getY())) {
                    newPoint.setX(p.getX() - 1);
                }

                break;
            case 4: // up
                if (isInBounds(p.getX(), p.getY() + 1)) {
                    newPoint.setY(p.getY() + 1);
                }
                break;
        }

        return newPoint;
    }

    public Point getRandomNearPosition(Point position, int allowCollision, int doubleMove) {
        Point[] positions = new Point[4];
        int positionsCount = 0;

        for (int i = 1; i <= 4; i++) {
            Point newPosition = (doubleMove != 0 ? mapPoint(mapPoint(position, i), i) : mapPoint(position, i));
            if (isInBounds(newPosition.getX(), newPosition.getY())) {
                if (!(position.getX() == newPosition.getX() && position.getY() == newPosition.getY())) {
                    if (this.tab[newPosition.getX()][newPosition.getY()] == null || allowCollision != 0) {
                        positions[positionsCount] = newPosition;
                        positionsCount++;
                    }
                }
            }
        }

        if (positionsCount == 0) {
            if (DETAILED_LOGGING) {
                System.out.println("Brak wolnych pozycji");
            }
            return position;
        }

        return positions[Rnd.roll(positionsCount) - 1];
    }

    public Point getRandomNearFriendlyPosition(Point position) {
        Point[] positions = new Point[4];
        int positionsCount = 0;

        for (int i = 1; i <= 4; i++) {
            Point newPosition = mapPoint(position, i);
            if (isInBounds(newPosition.getX(), newPosition.getY())) {
                if (!(position.getX() == newPosition.getX() && position.getY() == newPosition.getY())) {
                    if (this.tab[newPosition.getX()][newPosition.getY()] == null) { // allow collision but only with weaker organisms
                        positions[positionsCount] = newPosition;
                        positionsCount++;
                    } else {
                        if (this.getOrganism(newPosition).getStrength() <= this.getOrganism(position).getStrength()) {
                            positions[positionsCount] = newPosition;
                            positionsCount++;
                        }
                    }
                }
            }
        }

        if (positionsCount == 0) {
            if (DETAILED_LOGGING) {
                System.out.println("Brak wolnych pozycji");
            }
            return position;
        }

        return positions[Rnd.roll(positionsCount) - 1];
    }

    public void destroyEverythingAround(Point position, boolean animalsOnly) {
        Point[] positions = new Point[4];
        int positionsCount = 0;

        for (int i = 1; i <= 4; i++) {
            Point newPosition = mapPoint(position, i);
            if (isInBounds(newPosition.getX(), newPosition.getY())) {
                if (!(position.getX() == newPosition.getX() && position.getY() == newPosition.getY())) {
                    if (this.tab[newPosition.getX()][newPosition.getY()] != null) {
                        if (animalsOnly) {
                            if (!this.tab[newPosition.getX()][newPosition.getY()].isPlant()) {
                                positions[positionsCount] = newPosition;
                                positionsCount++;
                            }
                        } else {
                            positions[positionsCount] = newPosition;
                            positionsCount++;
                        }
                    }
                }
            }
        }
        if (DETAILED_LOGGING) {
            System.out.println(positionsCount + " to destroy");
        }

        for (int i = 0; i < positionsCount; i++) {
            if (DETAILED_LOGGING) {
                System.out.println("destroying " + this.getOrganism(positions[i]).getOrganismTypeName() + " at " + positions[i].getCoords());
            }
            this.getOrganism(positions[i]).die();
        }
    }

    public void generateOrganism(Point position, OrganismType type) {
        switch (type) {
            case GRASS:
                tab[position.getX()][position.getY()] = new Grass(position);
                break;
            case MILKWEED:
                tab[position.getX()][position.getY()] = new Milkweed(position);
                break;
            case GUARANA:
                tab[position.getX()][position.getY()] = new Guarana(position);
                break;
            case NIGHTSHADEBERRIES:
                tab[position.getX()][position.getY()] = new NightshadeBerries(position);
                break;
            case SOSNOWSKIHOGWEED:
                tab[position.getX()][position.getY()] = new SosnowskiHogweed(position);
                break;
            case WOLF:
                tab[position.getX()][position.getY()] = new Wolf(position);
                break;
            case SHEEP:
                tab[position.getX()][position.getY()] = new Sheep(position);
                break;
            case TURTLE:
                tab[position.getX()][position.getY()] = new Turtle(position);
                break;
            case ANTELOPE:
                tab[position.getX()][position.getY()] = new Antelope(position);
                break;
            case FOX:
                tab[position.getX()][position.getY()] = new Fox(position);
                break;
            case HUMAN:
                tab[position.getX()][position.getY()] = new Human(position);
                break;
        }
        info.add(getOrganism(position));
    }

    public void deleteOrganism(Point position) {
        if (DETAILED_LOGGING) {
            System.out.println(this.getOrganism(position).getOrganismType() + " dies at " + position.getCoords());
        }
        Organism toDelete = this.getOrganism(position);

        Iterator<Organism> iterator = info.iterator(); //todo fix possibly
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if (organism.equals(toDelete)) {
                iterator.remove();
                break;
            }
        }

        tab[position.getX()][position.getY()] = null;
    }

    public void moveOrganism(Point oldPos, Point newPos) {
        tab[newPos.getX()][newPos.getY()] = tab[oldPos.getX()][oldPos.getY()];
        tab[oldPos.getX()][oldPos.getY()] = null;
    }

    public boolean saveToFile() {
        try (FileWriter fileWriter = new FileWriter("save.txt")) {
            for (Organism organism : info) {
                fileWriter.write(organism.getOrganismType() + " " + organism.getPosition().getX() + " " + organism.getPosition().getY() + " ");
            }
            return true;
        } catch (IOException e) {
            System.out.println("File not opened");
            return false;
        }
    }

    public boolean loadFromFile() {
        try (Scanner scanner = new Scanner(new File("save.txt"))) {
            initializeMap();
            info.clear();
            while (scanner.hasNextInt()) {
                int type = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                generateOrganism(new Point(x, y), OrganismType.values()[type]);
                getOrganism(new Point(x, y)).setParent(this);
            }
            return true;
        } catch (IOException e) {
            System.out.println("File not opened");
            return false;
        }
    }

    public boolean loadFromDefaultFile() {
        try (Scanner scanner = new Scanner(new File("savedefault.txt"))) {
            initializeMap();
            info.clear();
            while (scanner.hasNextInt()) {
                int type = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                generateOrganism(new Point(x, y), OrganismType.values()[type]);
                getOrganism(new Point(x, y)).setParent(this);
            }
            return true;
        } catch (IOException e) {
            System.out.println("File not opened");
            return false;
        }
    }

    private void initializeMap() {
//        for (int i = 0; i < SIZE_X; i++) {
//            for (int j = 0; j < SIZE_Y; j++) {
//                Point p = new Point(i, j);
//                tab[i][j] = new Plant(p, '0', OrganismType.GRASS, 0, 0); // You should define GRASS constant
//            }
//        }

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                tab[i][j] = null;
            }
        }
    }

    private void tabSort() {
        int n = this.info.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (info.get(j).getInitiative() < info.get(j + 1).getInitiative() || (info.get(j).getInitiative() == info.get(j + 1).getInitiative() && info.get(j).getAge() < info.get(j + 1).getAge())) { // sort in descending order
                    Collections.swap(this.info, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

}

