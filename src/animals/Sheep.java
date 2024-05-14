package animals;
import game.*;
import static game.Constants.*;

public class Sheep extends Animal {
    public Sheep(Point position) {
        super(position, 'S', OrganismType.SHEEP, 4, 4);
    }
}