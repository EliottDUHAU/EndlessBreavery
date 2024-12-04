package game.waves;

import entities.enemies.Slime;
import game.Game;

/**
 * The SlimeWave class is a class that represents a wave of slimes.
 */
public class SlimeWave  extends Wave {
    /**
     * Create a new SlimeWave
     * @param difficulty the difficulty of the wave
     */
    public SlimeWave(int difficulty) {
        super(difficultyToCooldown(difficulty));
        this.addEnemy(Slime.class, 10  + difficulty * 2);
    }
}
