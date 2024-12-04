package entities.enemies;

import controllers.MapController;
import controllers.PlayerController;
import controllers.ProjectileController;
import entities.Player;
import entities.enemies.projectiles.Fireball;
import entities.hitboxes.CircleHitbox;
import entities.hitboxes.Hitbox;
import entities.hitboxes.LineHitbox;
import entities.hitboxes.VoidHitbox;
import sprites.AnimatedSprite;
import sprites.enemies.SkeletonDeath;
import sprites.enemies.SkeletonSprite;
import sprites.enemies.EnemyUI;
import utils.Pair;
import utils.Vector2D;

import java.util.Random;

/**
 * The skeleton enemy entity
 */
public class Skeleton extends Enemy {
    /**
     * Get the hitbox of the skeleton
     * @return hitbox
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Get the collision box of the skeleton
     * @return collision box
     */
    @Override
    public LineHitbox getCollisionBox() {
        return new LineHitbox(
                getXPosition() + getSprite().getWidth() / 4,
                getYPosition() + getSprite().getHeight(),
                getSprite().getWidth() / 2,
                0
        );
    }

    /**
     * Set the collision box of the skeleton
     * @param hitbox the collision box
     */
    @Override
    public void setCollisionBox(Hitbox hitbox) {
        return;
    }
    /**
     * The attack cooldown for the skeleton
     */
    private int ATTACK_COOLDOWN = 90;
    /**
     * The attack counter for the skeleton
     */
    private int attackCounter = 0;
    /**
     * The speed of the skeleton
     */
    private int speed;
    /**
     * The hitbox of the skeleton
     */
    private Hitbox hitbox;
    /**
     * The skeleton UI
     */
    private EnemyUI skeletonUI;
    /**
     * The sprite for the skeleton
     */
    private AnimatedSprite skeletonSprite = new SkeletonSprite(0, 0);
    /**
     * Create a new skeleton enemy
     * @param x x position
     * @param y y position
     */
    public Skeleton(int x, int y, int health, int maxHealth, int speed) {
        super(x, y, health, maxHealth);
        this.skeletonSprite = new SkeletonSprite(x, y);
        this.speed = speed;
        hitbox = new CircleHitbox(skeletonSprite.getXCenter(), skeletonSprite.getYCenter(), 32);
//        this.fireball = new Fireball(getXPosition(),getYPosition(),18,2);
    }

    /**
     * Get the skeleton UI
     * @return skeletonUI
     */
    public EnemyUI getSkeletonUI(){
        return skeletonUI;
    }
    /**
     * Get the sprite for the skeleton
     * @return skeleton sprite
     */
    public AnimatedSprite getSprite() {
        return skeletonSprite;
    }
    /**
     * Get the skeleton speed
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }
    /**
     * Move the skeleton toward the player
     */
    public void move(){
        // get the player position
        int xTarget = getPlayer().getSprite().getXCenter() - getSprite().getWidth() / 2;
        int yTarget = getPlayer().getSprite().getYCenter() - getSprite().getHeight() / 2;
        // get the distance between the skeleton and the player
        int xCheck = xTarget - getSprite().getXPosition();
        int yCheck = yTarget - getSprite().getYPosition();
        // the separation vector
        Vector2D vector = new Vector2D(xCheck, yCheck);
        // invert it
        vector.multiply(-1);
        vector.normalize();
        // multiply by the radius of the aimed range for the skeleton
        vector.multiply(300);
        // calculate the new destination
        int destX = xTarget + vector.getX();
        int destY = yTarget + vector.getY();
        xCheck = destX - getSprite().getXPosition();
        yCheck = destY - getSprite().getYPosition();
        vector = new Vector2D(xCheck, yCheck);
        vector.normalize();
        boolean faceLeft = getPlayer().getSprite().getXCenter() - this.getSprite().getXCenter()> 0;
        if (getSprite() instanceof SkeletonSprite sprite) {
            sprite.setFaceLeft(faceLeft);
        }
        // if the skeleton is fleeing, slow down and go back 2 casting steps
        if ((vector.getDoubleX() > 0 && !faceLeft) || (vector.getDoubleX() < 0 && faceLeft)) {
            vector.multiply(speed / 2);
            attackCounter--;
            attackCounter--;
        }
        else vector.multiply(speed);
        // check for collision
        Pair<Integer, Integer> position = MapController.getInstance().getMap().collide(getCollisionBox(), vector.getX(), vector.getY());
        if (position.getFirst() == getCollisionBox().getXPosition() && position.getSecond() == getCollisionBox().getYPosition()) {
            return;
        }
        // move accordingly
        setXPosition(getXPosition() + vector.getX());
        setYPosition(getYPosition() + vector.getY());
    }
    /**
     * Check if the skeleton is dead
     * @return true if the skeleton is dead
     */
    @Override
    public boolean isDead() {
        return (skeletonSprite instanceof SkeletonDeath) && ((SkeletonDeath) skeletonSprite).isDone();
    }
    /**
     * Update the skeleton
     */
    public void tick() {
        super.tick();
        skeletonSprite.tick();
        if (attackCounter < ATTACK_COOLDOWN) {
            attackCounter++;
        }
        else {
            attackCounter = 0;
            Player player = PlayerController.getInstance().getPlayer();
            int xCheck = player.getSprite().getXCenter() - getSprite().getXCenter();
            int yCheck = player.getSprite().getYCenter() - getSprite().getYCenter();
            Vector2D vector = new Vector2D(xCheck, yCheck);
            vector.normalize();
            vector.multiply(7);
            Fireball fireball = new Fireball(getXPosition(), getYPosition(), vector, 900);
            ProjectileController.getInstance().addProjectile(fireball);
        }
        if (getHealth() <= 0 && skeletonSprite instanceof SkeletonSprite) {
            skeletonSprite = new SkeletonDeath(getXPosition(), getYPosition());
            hitbox = new VoidHitbox();
        }
        if (getHealth() <= 0) {
            return;
        }
        move();
    }
}