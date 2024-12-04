package game.waves;

import entities.enemies.Bandit;
import entities.enemies.Batman;
import entities.enemies.Skeleton;
import entities.enemies.Slime;
import game.Game;

/**
 * The MixWave class
 */
public class MixWave  extends Wave {
    /**
     * Create a new MixWave
     * @param difficulty the difficulty of the wave
     */
    public MixWave(int difficulty) {
        super(difficultyToCooldown(difficulty));
        int enemyCount = 10 + difficulty * 2;
        this.addEnemy(Batman.class, enemyCount/4)
                .addEnemy(Bandit.class, enemyCount/4)
                .addEnemy(Slime.class, enemyCount/4)
                .addEnemy(Skeleton.class, enemyCount/4);
    }
}