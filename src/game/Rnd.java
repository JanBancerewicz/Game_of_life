package game;

import java.util.Random;

public class Rnd {
    public static int roll(int maxval) {
        return 1 + new Random().nextInt(maxval);
    }
}

