package plants;
import game.*;
import static game.Constants.*;

public class SosnowskiHogweed extends Plant {
    public SosnowskiHogweed(Point position) {
        super(position, '$', OrganismType.SOSNOWSKIHOGWEED, 10, 0);
    }

    @Override
    public void action() {
        super.action();
        if (Rnd.roll(100) <= CHANCE_OF_REPRODUCTION_SOSNOWSKIHOGWEED) {
            this.reproduction();
        }
        Point p = this.getPosition();
        System.out.println("Sosnowski hogweed poisons everything around " + p.getCoords());
        this.getParent().getLogger().addLog("Sosnowski Hogweed poisons everything around " + p.getCoords());
        this.getParent().destroyEverythingAround(p, true);
    }

    @Override
    public int collision(Organism target, Point collisionPoint) {
        System.out.println("Sosnowski Hogweed collision");
        super.collision(target, collisionPoint);
        // this.die();
        return 1;
    }
}
