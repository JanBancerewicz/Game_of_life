package game;

import java.util.Objects;

public abstract class Organism {
    private Point position;
    private int strength;
    private final int initiative;
    private final OrganismType organismType;
    private final char ascii;
    private int age;
    private World parent;


    public Organism(Point position, char ascii, OrganismType organismType, int strength, int initiative) {
        this.position = position;
        this.ascii = ascii;
        this.organismType = organismType;
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
    }

    public Point getPosition() {
        return position;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public OrganismType getOrganismType() { //edited
        return organismType;
    }

    public String getOrganismTypeName() {
        switch (this.organismType) {
            case GRASS:
                return "Grass";
            case MILKWEED:
                return "Milkweed";
            case GUARANA:
                return "Guarana";
            case NIGHTSHADEBERRIES:
                return "Nightshade Berries";
            case SOSNOWSKIHOGWEED:
                return "Sosnowski Hogweed";
            case WOLF:
                return "Wolf";
            case SHEEP:
                return "Sheep";
            case TURTLE:
                return "Turtle";
            case ANTELOPE:
                return "Antelope";
            case FOX:
                return "Fox";
            case HUMAN:
                return "Human";
        }
        return "Unknown";
    }

    public char getAscii() {
        return ascii;
    }

    public int getAge() {
        return age;
    }

    public World getParent() {
        return parent;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setParent(World parent) {
        this.parent = parent;
    }

    public boolean isPlant() {
        if (this.getOrganismType().ordinal() < 5) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void action();

    public abstract void reproduction();

    public abstract void die();

    public abstract int collision(Organism target, Point collisionPoint);
}


