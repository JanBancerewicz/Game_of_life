package animals;
import game.*;
import static game.Constants.*;


public class Fox extends Animal {
    public Fox(Point position) {
        super(position, 'F', OrganismType.FOX, 3, 7);
    }

    @Override
    public void action() {
        System.out.println("Fox action");
        setAge(getAge() + 1);

        Point newpos = getParent().getRandomNearFriendlyPosition(getPosition());
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
}