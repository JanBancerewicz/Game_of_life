package animals;
import game.*;
import static game.Constants.*;


public class Animal extends Organism {
    void move(Point newpos) {
        this.getParent().moveOrganism(this.getPosition(), newpos);
        this.setPosition(newpos);
    }

    void movementAction(Point newpos) {
        String attackerinfo = this.getOrganismTypeName();
        String preyinfo = this.getParent().getOrganism(newpos).getOrganismTypeName();

        // Collision handler, newpos = collision point
        int isWinner = this.collision(this.getParent().getOrganism(newpos), newpos);

        int isLoser = 1;
        if (this.getParent().getOrganism(newpos) != null) {
            isLoser = this.getParent().getOrganism(newpos).collision(this, newpos); // Collision for attacker and prey
        } // Now one of them may not be alive

        if (isLoser == 2) {
            // Repulsion, no movement for the attacker
            String logmsg = attackerinfo + " got pushed away by " + preyinfo + " at " + newpos.getCoords();
            if (DETAILED_LOGGING) { System.out.println(logmsg); }
            this.getParent().getLogger().addLog(logmsg);
        } else if (isLoser == 3) {
            // Escape, movement of prey
            Point retreatpos = this.getParent().getRandomNearPosition(this.getParent().getOrganism(newpos).getPosition(), 0, 0);
            if (!(retreatpos.getX() == newpos.getX() && retreatpos.getY() == newpos.getY())) {
                String logmsg = preyinfo + " flees from " + attackerinfo + " to " + retreatpos.getCoords();
                if (DETAILED_LOGGING) { System.out.println(logmsg); }
                this.getParent().getLogger().addLog(logmsg);
                this.getParent().getOrganism(newpos).setPosition(retreatpos);
                this.getParent().moveOrganism(newpos, retreatpos);
                this.move(newpos);
            } else {
                String logmsg = preyinfo + " has nowhere to run away from " + attackerinfo;
                if (DETAILED_LOGGING) { System.out.println(logmsg); }
                this.getParent().getLogger().addLog(logmsg);
                isLoser = 1;
            }
        }
        if (isLoser <= 1) {
            if (isWinner == 1) {
                this.getParent().getLogger().addLog(attackerinfo + " eats " + preyinfo);
                this.getParent().getOrganism(newpos).die();
                this.move(newpos);
            } else {
                this.getParent().getLogger().addLog(attackerinfo + " is eaten by " + preyinfo);
                this.die();
            }
        }
    }


    Animal(Point position, char ascii, OrganismType organismType, int strength, int initiative) {
        super(position, ascii, organismType, strength, initiative);
    }

    @Override
    public void action() {
        System.out.println(this.getOrganismTypeName() + " action");
        this.setAge(this.getAge() + 1);

//        System.out.println("currpos: "+this.getPosition().getCoords());
        Point newpos = this.getParent().getRandomNearPosition(new Point(this.getPosition()), 1, 0);
//        System.out.println("newpos: "+newpos.getCoords());

        if (this.getPosition().getX() == newpos.getX() && this.getPosition().getY() == newpos.getY()) {
            if (DETAILED_LOGGING) { System.out.println("There is no place to move"); }
        } else {
            if (this.getParent().getOrganism(newpos) != null) {
                if (this.getParent().getOrganism(newpos).getOrganismType() == this.getOrganismType()) {
                    this.getParent().getOrganism(newpos).reproduction(); // Reproduction at the new position, no movement
                } else {
                    movementAction(newpos);
                }
            } else {
                this.move(newpos);
            }
        }
    }

    @Override
    public void reproduction() {
        Point p = this.getParent().getRandomNearPosition(new Point(this.getPosition()), 0, 0);
        if (p.getX() == this.getPosition().getX() && p.getY() == this.getPosition().getY()) {
            if (DETAILED_LOGGING) { System.out.println("No place to reproduce"); }
            // Nothing will appear because there is no space
        } else {
            this.getParent().getLogger().addLog(this.getOrganismTypeName() + " reproduces at " + p.getCoords());
            // System.out.println(this.getOrganismType() + " reproduces at " + p.getX() + ", " + p.getY());
            this.getParent().generateOrganism(p, this.getOrganismType());
            this.getParent().getOrganism(p).setParent(this.getParent());
        }
    }

    @Override
    public void die() {
        this.getParent().deleteOrganism(this.getPosition());
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        this.getParent().getLogger().addLog(this.getOrganismTypeName() + " collides with " + target.getOrganismTypeName() + " at " + collisionPoint.getCoords());

        if (this.getStrength() > target.getStrength()) {
            return 1;
        } else if (this.getStrength() == target.getStrength()) {
            if (!(this.getPosition().getX() == collisionPoint.getX() && this.getPosition().getY() == collisionPoint.getY())) {
                return 1;
            }
        }
        return 0;
    }
}
