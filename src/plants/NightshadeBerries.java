package plants;
import game.*;
import static game.Constants.*;


public class NightshadeBerries extends Plant {
    public NightshadeBerries(Point position) {
        super(position, 'N', OrganismType.NIGHTSHADEBERRIES, 99, 0);
    }

    @Override
    public void action() {
        super.action();
        if (Rnd.roll(100) <= CHANCE_OF_REPRODUCTION_NIGHTSHADEBERRIES) {
            this.reproduction();
        }
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        System.out.println("Nightshade Berries collision");
        super.collision(target, collisionPoint);
        // this.die();
        return 1;
    }
}