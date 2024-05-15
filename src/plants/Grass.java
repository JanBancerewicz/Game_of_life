package plants;
import game.*;

import static game.Constants.*;

public class Grass extends Plant {
    public Grass(Point position) {
        super(position, 'G', OrganismType.GRASS, 0, 0);
    }

    @Override
    public void action() {
        super.action();
        if (Rnd.roll(100) <= CHANCE_OF_REPRODUCTION_GRASS) {
            this.reproduction();
        }
    }
}