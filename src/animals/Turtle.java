package animals;
import game.*;
import static game.Constants.*;


public class Turtle extends Animal {
    public Turtle(Point position) {
        super(position, 'T', OrganismType.TURTLE, 2, 1);
    }

    @Override
    public void action() {
        System.out.println("Turtle action");
        if (Rnd.roll(100) <= CHANCE_OF_MOVEMENT_TURTLE) {
            super.action();
        }
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        int result = super.collision(target, collisionPoint);
        if (result == 0 && target.getStrength() < 5) {
            return 2; // retreat
        } else {
            return result;
        }
    }
}