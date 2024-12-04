package game.waves;

import entities.enemies.Bandit;
import game.Game;

/**
 * A wave of bandits
 */
public class BanditWave extends Wave {
    /**
     * Create a new bandit wave
     * @param difficulty the difficulty of the wave
     */
    public BanditWave(int difficulty) {
        super(difficultyToCooldown(difficulty));
        this.addEnemy(Bandit.class, 10 + difficulty * 2 );
    }
}
