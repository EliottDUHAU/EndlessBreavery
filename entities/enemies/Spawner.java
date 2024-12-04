package entities.enemies;

import controllers.EnemyController;
import controllers.MapController;
import controllers.PlayerController;
import entities.Player;
import entities.hitboxes.LineHitbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The Spawner class is a class that spawns enemies in the game.
 */
public class Spawner {
    /**
     * The radius of the spawner.
     */
    private int radius;
    /**
     * The angle of the spawner.
     */
    private double angle;
    /**
     * The random object.
     */
    private Random random = new Random();
    /**
     * Create a new spawner.
     */
    public Spawner() {
    }
    /**
     * Get the player.
     * @return the player
     */
    private Player getPlayer() {
        return PlayerController.getInstance().getPlayer();
    }
    /**
     * Get the radius of the spawner.
     * @return the radius of the spawner
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Set the radius of the spawner.
     * @param newRadius the new radius of the spawner
     */
    public void setRadius(int newRadius) {
        radius = newRadius;
    }
    /**
     * Get the angle of the spawner.
     * @return the angle of the spawner
     */
    public double getRandomCosSin() {
        return random.nextDouble() * Math.PI * 2;
    }
    /**
     * Get the angle of the spawner.
     * @return the angle of the spawner
     */
    public int CircleXPosition() {
        int xCircle = getPlayer().getSprite().getXPosition();
        double adj = Math.cos(angle) * radius;
        xCircle += (int) adj;
        return xCircle;
    }

    /**
     * Get the y position of the circle
     * @return the y position of the circle
     */
    public int CircleYPosition() {
        int yCircle = getPlayer().getSprite().getYPosition();
        double opp = Math.sin(angle) * radius;
        yCircle -= (int) opp;
        return yCircle;
    }

    /**
     * Spawn a circle of a given radius
     * @param radius the radius of the circle
     * @return the radius of the circle
     */
    public int spawnCircle(int radius) {
        return radius;
    }

    /**
     * Return a list of a given number of slimes
     *
     * @param number number of slimes created
     * @return slimes
     */
    public List<Slime> spawnSlimes(int number, int radius, int health, int maxHealth, int speed) {
        this.radius = radius;
        List<Slime> slimes = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Slime slime;
            do {
                angle = getRandomCosSin();
                slime = new Slime(CircleXPosition(), CircleYPosition(), health, maxHealth, speed);
            } while (!validSpawningSpot(slime.getCollisionBox()));
            slimes.add(slime);
        }
        return slimes;
    }
    /**
     * Return a list of a given number of skeletons
     *
     * @param number number of skeletons created
     * @return skeletons
     */
    public List<Skeleton> spawnSkeletons(int number, int radius, int health, int maxHealth, int speed) {
        this.radius = radius;
        List<Skeleton> skeletons = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Skeleton skeleton;
            do {
                angle = getRandomCosSin();
                skeleton = new Skeleton(CircleXPosition(), CircleYPosition(), health, maxHealth, speed);
            } while (!validSpawningSpot(skeleton.getCollisionBox()));
            skeletons.add(skeleton);
        }
        return skeletons;
    }
    /**
     * Return a list of a given number of bandits
     *
     * @param number number of bandits created
     * @return bandits
     */
    public List<Bandit> spawnBandits(int number, int radius, int health, int maxHealth, int speed) {
        this.radius = radius;
        List<Bandit> bandits = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Bandit bandit;
            do {
                angle = getRandomCosSin();
                bandit = new Bandit(CircleXPosition(), CircleYPosition(), health, maxHealth, speed);
            } while (!validSpawningSpot(bandit.getCollisionBox()));
            bandits.add(bandit);
        }
        return bandits;
    }
    /**
     * Return a list of a given number of batmans
     *
     * @param number number of batmans created
     * @return batmans
     */
    public List<Batman> spawnBatmans(int number, int radius, int health, int maxHealth, int dashMagnitude, int dashDuration) {
        this.radius = radius;
        List<Batman> batmans = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Batman batman;
            angle = getRandomCosSin();
            batman = new Batman(CircleXPosition(), CircleYPosition(), health, maxHealth, dashMagnitude, dashDuration);
            batmans.add(batman);
        }
        return batmans;
    }

    /**
     * Check if the spawning spot is valid
     * @param collisionBox the collision box of the enemy
     * @return whether the spawning spot is valid
     */
    private boolean validSpawningSpot(LineHitbox collisionBox) {
        return !MapController.getInstance().getMap().intersects(collisionBox)
                && collisionBox.getYPosition() > 1130
                && collisionBox.getYPosition() < 2535
                && Math.min(collisionBox.getXPosition(), collisionBox.getXPosition() + collisionBox.getVectorY()) > 1210
                && Math.max(collisionBox.getXPosition(), collisionBox.getXPosition() + collisionBox.getVectorY()) < 2755;
    }
}