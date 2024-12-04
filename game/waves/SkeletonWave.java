package game.waves;

import entities.enemies.Skeleton;
import game.Game;

/**
 * The SkeletonWave class is a class that represents a wave of skeletons.
 */
public class SkeletonWave extends Wave {
    /**
     * Create a new SkeletonWave
     * @param difficulty the difficulty of the wave
     */
    public SkeletonWave(int difficulty) {
        super(difficultyToCooldown(difficulty));
        this.addEnemy(Skeleton.class, 10 + difficulty * 2);
    }
}
