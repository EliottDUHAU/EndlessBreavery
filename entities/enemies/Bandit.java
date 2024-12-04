package entities.enemies;

import cards.status.Wound;
import controllers.CardController;
import controllers.MapController;
import controllers.PlayerController;
import entities.Player;
import entities.hitboxes.*;
import sprites.AnimatedSprite;
import sprites.enemies.*;
import utils.Pair;
import utils.Vector2D;

import java.awt.*;
import java.util.Random;

/**
 * The Bandit enemy entity
 */
public class Bandit extends Enemy {
    /**
     * Get the hitbox of the bandit
     * @return hitbox
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }
    /**
     * Get the collision box of the bandit
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
     * Set the collision box of the bandit
     * @param hitbox the collision box
     */
    @Override
    public void setCollisionBox(Hitbox hitbox) {
        return;
    }
    /**
     * The speed of the bandit
     */
    private int speed;
    /**
     * The hitbox of the bandit
     */
    private Hitbox hitbox;
    /**
     * The random number generator
     */
    Random random = new Random();
    /**
     * The sprite for the Bats
     */
    private AnimatedSprite banditSprite;
    /**
     * Create a new Bandit
     * @param x the x position
     * @param y the y position
     * @param health the health
     * @param maxHealth the max health
     * @param speed the speed
     */
    public Bandit(int x, int y, int health, int maxHealth, int speed) {
        super(x, y, health, maxHealth);
        this.banditSprite = new BanditSprite(x, y);
        this.speed = speed;
        hitbox = new CircleHitbox(banditSprite.getXCenter(), banditSprite.getYCenter(), 32);
    }

    /**
     * Get the sprite for the Bats
     * @return Bats sprite
     */
    public AnimatedSprite getSprite() {
        return banditSprite;
    }
    /**
     * Get the Bats speed
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }
    /**
     * Move the Bats toward the player
     */
    public void move(){
        int speed = random.nextInt(getSpeed()+1);
        // get the player position
        int xTarget = getPlayer().getSprite().getXCenter() - getSprite().getWidth() / 2;
        int yTarget = getPlayer().getSprite().getYCenter() - getSprite().getHeight() / 2;
        // get the distance between the Bats and the player
        int xCheck = xTarget - getSprite().getXPosition();
        int yCheck = yTarget - getSprite().getYPosition();
        // the movement vector
        Vector2D vector = new Vector2D(xCheck, yCheck);
        vector.normalize();
        vector.multiply(speed);
        if (getSprite() instanceof BanditSprite sprite) {
            sprite.setFaceLeft(xCheck > 0);
        }
        if (getSprite() instanceof BanditDeath sprite) {
            sprite.setFaceLeft(xCheck > 0);
        }
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
     * Check if the bandit is dead
     * @return true if the bandit is dead
     */
    @Override
    public boolean isDead() {
        return (banditSprite instanceof BanditDeath) && ((BanditDeath) banditSprite).isDone();
    }
    /**
     * Update the bandit
     */
    public void tick() {
        super.tick();
        banditSprite.tick();
        if (getHealth() <= 0 && banditSprite instanceof BanditSprite) {
            banditSprite = new BanditDeath(getXPosition(), getYPosition());
            hitbox = new VoidHitbox();
        }
        if (getHealth() <= 0) {
            return;
        }
        Player player = PlayerController.getInstance().getPlayer();
        if (player.getHitbox().intersects(hitbox)) {
            if (player.damage(5)) CardController.getInstance().getDeck().addCard(new Wound());
            player.knockBack(getSprite().getXCenter(), getSprite().getYCenter());
        }
        move();
    }
}