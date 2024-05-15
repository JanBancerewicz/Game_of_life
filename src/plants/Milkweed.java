package plants;
import game.*;
import static game.Constants.*;

public class Milkweed extends Plant {
    public Milkweed(Point position) {
        super(position, 'M', OrganismType.MILKWEED, 0, 0);
    }


    @Override
    public void action() {
        super.action();
        for (int i = 0; i < 3; i++) {
            if (Rnd.roll(100) <= CHANCE_OF_REPRODUCTION_MILKWEED) {
                this.reproduction();
            }
        }
    }

}
