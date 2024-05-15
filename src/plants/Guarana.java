package plants;
import game.*;
import static game.Constants.*;

public class Guarana extends Plant {
    public Guarana(Point position) {
        super(position, 'U', OrganismType.GUARANA, 0, 0);
    }

    @Override
    public void action() {
        super.action();
        if (Rnd.roll(100) <= CHANCE_OF_REPRODUCTION_GUARANA) {
            this.reproduction();
        }
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        System.out.println("Guarana collision");
        target.setStrength(target.getStrength() + 3);
        return super.collision(target, collisionPoint);
    }
}