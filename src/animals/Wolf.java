package animals;
import game.*;
import static game.Constants.*;

public class Wolf extends Animal {
    public Wolf(Point position) {
        super(position, 'W', OrganismType.WOLF, 9, 5);
    }
}