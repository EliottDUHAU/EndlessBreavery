package controllers;

import entities.enemies.Enemy;
import entities.enemies.Spawner;
import game.waves.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The EnemyController class is a singleton class that controls the enemies in the game.
 */
public class EnemyController {
    /**
     * The types of waves that can be created.
     */
    private enum WaveType {
        SLIME, SKELETON, BATMAN, BANDIT,MIX
    }

    /**
     * The instance of the EnemyController class.
     */
    private static EnemyController instance;
    /**
     * Reset the instance of the EnemyController class.
     */
    public static void reset() {
        instance = null;
    }
    /**
     * Get the instance of the EnemyController class.
     * @return the instance of the EnemyController class
     */
    public static EnemyController getInstance() {
        if (instance == null) {
            instance = new EnemyController();
        }
        return instance;
    }
    /**
     * The spawner of the game.
     */
    private Spawner spawner;
    /**
     * The number of enemies killed.
     */
    private int killCount = 0;
    /**
     * The number of waves completed.
     */
    private int waveCount = 0;
    /**
     * Get the number of enemies killed.
     * @return the number of enemies killed
     */
    public int getKillCount() {
        return killCount;
    }
    /**
     * Get the number of waves completed.
     * @return the number of waves completed
     */
    public int getWaveCount() {
        return waveCount;
    }
    /**
     * The current wave of the game.
     */
    private Wave wave;
    /**
     * The list of enemies currently in the game.
     */
    private List<Enemy> enemies = new ArrayList<>();
    /**
     * Get the list of enemies currently in the game.
     * @return copy of the list of enemies currently in the game
     */
    public List<Enemy> getEnemies() {
        return new ArrayList(enemies);
    }
    /**
     * Add an enemy to the list of enemies currently in the game.
     * @param enemy the enemy to add to the list of enemies currently in the game
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    /**
     * Add a list of enemies to the list of enemies currently in the game.
     * @param newEnemies the list of enemies to add to the list of enemies currently in the game
     */
    public void addEnemies(List<Enemy> newEnemies) {
        enemies.addAll(newEnemies);
    }
    /**
     * The spawner of the game.
     * @return the spawner of the game
     */
    public Spawner getSpawner() {
        return spawner;
    }
    /**
     * Private constructor for EnemyController.
     */
    private EnemyController() {
        spawner = new Spawner();
    }
    /**
     * Manually spawns enemies for testing.
     */
    public void spawnEnemies() {
        enemies.addAll(spawner.spawnSlimes(10,500, 20, 20, 7));
        enemies.addAll(spawner.spawnSkeletons(1,500, 10, 20, 10));
        enemies.addAll(spawner.spawnBandits(5,500, 8, 8, 10));
        enemies.addAll(spawner.spawnBatmans(1,500, 20, 10, 15,10));
    }
    /**
     * Propagates ticking to enemies and wave.
     */
    public void tickEnemies() {
        // Initializing first wave
        if (wave == null) {
            wave = new EmptyWave(5);
        }
        // ticking enemies
        for (Enemy enemy : enemies) {
            enemy.tick();
        }
        // removing dead enemies
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            if (iterator.next().isDead()) {
                iterator.remove();
                killCount++;
            }
        }
        // tick the wave
        wave.tick();
        if (wave.isWaveOver()) {
            if (wave instanceof EmptyWave) {
                wave = createRandomWave();
            } else {
                waveCount++;
                GameController.getInstance().getGame().setPickingCard(true);
                wave = new EmptyWave(5);
            }
        }
    }
    /**
     * Creates a random wave.
     * @return a random wave
     */
    private Wave createRandomWave() {
        WaveType[] waveTypes = WaveType.values();
        WaveType randomWaveType = waveTypes[(int) (Math.random() * waveTypes.length)];
        return switch (randomWaveType) {
            case SLIME -> new SlimeWave(waveCount);
            case SKELETON -> new SkeletonWave(waveCount);
            case BATMAN -> new BatmanWave(waveCount);
            case BANDIT -> new BanditWave(waveCount);
            case MIX -> new MixWave(waveCount);
            default -> new EmptyWave(5);
        };
    }
}
