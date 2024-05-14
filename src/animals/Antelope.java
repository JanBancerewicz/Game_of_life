package animals;
import game.*;
import static game.Constants.*;

public class Antelope extends Animal {
    public Antelope(Point position) {
        super(position, 'A', OrganismType.ANTELOPE, 4, 4);
    }

    @Override
    public void action() {
        System.out.println("Antelope action");
        setAge(getAge() + 1);

        Point newpos = getParent().getRandomNearPosition(getPosition(), 2, 1);
        if (getPosition().equals(newpos)) {
            if (DETAILED_LOGGING) {
                System.out.println("No place to move");
            }
        } else {
            Organism target = getParent().getOrganism(newpos);
            if (target != null) {
                if (target.getOrganismType() == getOrganismType()) {
                    target.reproduction();
                } else {
                    movementAction(newpos);
                }
            } else {
                move(newpos);
            }
        }
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        int result = super.collision(target, collisionPoint);
        if (result == 0) { // If the Antelope lost the collision
            if (Rnd.roll(100) <= CHANCE_OF_RETREAT_ANTELOPE) {
                return 3; // Retreat
            }
        }
        return result;
    }
}