package game.waves;

import entities.enemies.Batman;
import game.Game;

/**
 * The Batman wave class
 */
public class BatmanWave extends Wave {
    /**
     * Create a new Batman wave
     * @param difficulty the difficulty of the wave
     */
    public BatmanWave(int difficulty) {
        super(difficultyToCooldown(difficulty));
        this.addEnemy(Batman.class, 10 + difficulty * 2);
    }
}
