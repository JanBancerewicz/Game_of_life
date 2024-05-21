package plants;

import game.*;
import static game.Constants.*;

public class Plant extends Organism {
    Plant(Point position, char ascii, OrganismType organismType, int strength, int initiative) {
        super(position, ascii, organismType, strength, initiative);
    }


    @Override
    public void action() {
        System.out.println(this.getOrganismTypeName() + " action");
        this.setAge(this.getAge() + 1);
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        this.getParent().getLogger().addLog(this.getOrganismTypeName() + " collides with " + target.getOrganismTypeName() + " at " + collisionPoint.getCoords());
        return 0;
    }

    @Override
    public void reproduction() {
        Point p = this.getParent().getRandomNearPosition(this.getPosition(), 0, 0);
        if (p.getX() == this.getPosition().getX() && p.getY() == this.getPosition().getY()) {
            if (DETAILED_LOGGING) {
                System.out.println("No place to reproduce"); // only for debug
            }
            // nothing happens because there is no place
        } else {
            if (DETAILED_LOGGING) {
                System.out.println(this.getOrganismType() + " reproduces at " + p.getCoords());
            }
            this.getParent().getLogger().addLog(this.getOrganismTypeName() + " reproduces at " + p.getCoords());
            this.getParent().generateOrganism(p, this.getOrganismType());
            this.getParent().getOrganism(p).setParent(this.getParent());
        }
    }

    @Override
    public void die() {
        this.getParent().deleteOrganism(this.getPosition());
    }

}