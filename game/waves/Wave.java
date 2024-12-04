package game.waves;

import controllers.EnemyController;
import entities.enemies.Enemy;
import entities.enemies.Spawner;
import game.Game;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The wave class
 */
public abstract class Wave {
    /**
     * The number of ticks
     */
    private int ticks;
    /**
     * The map of enemies
     */
    private Map<Class<? extends Enemy>, Integer> enemies;
    /**
     * The spawn cooldown
     */
    private int spawnCooldown;
    /**
     * Get the spawn cooldown
     * @param difficulty the difficulty
     * @return the spawn cooldown
     */
    public static int difficultyToCooldown(int difficulty) {
        int res = (int) (Game.TPS * (Math.pow(0.8, difficulty) * 3));
        System.out.println("Difficulty: " + difficulty + " Cooldown: " + res);
        return res;
    }
    /**
     * Tick the wave
     */
    public void tick() {
        ticks++;
        if (ticks >= spawnCooldown) {
            spawnNextEnemy();
            ticks = 0;
        }
    }
    /**
     * Spawn the next enemy
     */
    private void spawnNextEnemy() {
        Spawner spawner = EnemyController.getInstance().getSpawner();
        if (enemies.isEmpty()) {
            return;
        }
        Map.Entry<Class<? extends Enemy>, Integer> entry = enemies.entrySet().iterator().next();
        List<? extends Enemy> spawnedEnemies = new ArrayList<>();
        switch (entry.getKey().getSimpleName()) {
            case "Slime":
                spawnedEnemies =  spawner.spawnSlimes(1, 500, 20, 20, 6);
                break;
            case "Skeleton":
                spawnedEnemies = spawner.spawnSkeletons(1, 600, 10, 10, 4);
                break;
            case "Batman":
                spawnedEnemies = spawner.spawnBatmans(1, 500, 15,  15, 10,10);
                break;
            case "Bandit":
                spawnedEnemies = spawner.spawnBandits(1,500,25,25,7);
                break;
            default:
                System.out.println("Unknown enemy type: " + entry.getKey().getName());
        }
        enemies.put(entry.getKey(), entry.getValue() - 1);
        if (enemies.get(entry.getKey()) == 0) {
            enemies.remove(entry.getKey());
        }
        EnemyController.getInstance().addEnemies((List<Enemy>) spawnedEnemies);
    }
    /**
     * Add an enemy to the wave
     * @param enemyType the enemy type
     * @param amount the amount of enemies
     * @return the wave
     */
    protected Wave addEnemy(Class<? extends Enemy> enemyType, int amount) {
        if (enemies.containsKey(enemyType)) {
            enemies.put(enemyType, enemies.get(enemyType) + amount);
        } else {
            enemies.put(enemyType, amount);
        }
        return this;
    }
    /**
     * Constructor for the wave
     * @param spawnCooldown the spawn cooldown
     */
    public Wave(int spawnCooldown) {
        this.spawnCooldown = spawnCooldown;
        ticks = 0;
        enemies = new HashMap<>();
    }
    /**
     * Check if the wave is over
     * @return if the wave is over
     */
    public boolean isWaveOver() {
        return enemies.isEmpty() && EnemyController.getInstance().getEnemies().isEmpty();

    }
}
