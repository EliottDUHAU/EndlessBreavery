package entities.enemies;

import controllers.MapController;
import controllers.PlayerController;
import entities.Player;
import entities.hitboxes.CircleHitbox;
import entities.hitboxes.Hitbox;
import entities.hitboxes.LineHitbox;
import entities.hitboxes.VoidHitbox;
import game.movement.MovementOverride;
import sprites.AnimatedSprite;
import sprites.enemies.SlimeDeath;
import sprites.enemies.SlimeSprite;
import utils.Pair;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The slime enemy entity
 */
public class Slime extends Enemy {
    /**
     * The movement overrides for the slime
     */
    private List<MovementOverride> movementOverrides = new ArrayList<>();
    /**
     * Add a movement override to the slime
     * @param movementOverride the movement override to add
     */
    public void addMovementOverride(MovementOverride movementOverride) {
        movementOverrides.add(movementOverride);
    }

    /**
     * Get the hitbox for the slime
     * @return the hitbox for the slime
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Get the collision box for the slime
     * @return the collision box for the slime
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
     * Set the collision box for the slime
     * @param hitbox the hitbox to set
     */
    @Override
    public void setCollisionBox(Hitbox hitbox) {
        return;
    }
    /**
     * The speed of the slime
     */
    private int speed;
    /**
     * The hitbox for the slime
     */
    private Hitbox hitbox;
    /**
     * The random number generator
     */
    Random random = new Random();
    /**
     * The sprite for the slime
     */
    private AnimatedSprite slimeSprite = new SlimeSprite(0, 0);
    /**
     * Create a new slime
     * @param x the x position of the slime
     * @param y the y position of the slime
     * @param health the health of the slime
     * @param maxHealth the max health of the slime
     * @param speed the speed of the slime
     */
    public Slime(int x, int y, int health, int maxHealth, int speed) {
        super(x, y, health, maxHealth);
        this.slimeSprite = new SlimeSprite(x, y);
        this.speed = speed;
        hitbox = new CircleHitbox(slimeSprite.getXCenter(), slimeSprite.getYCenter(), 32);
    }

    /**
     * Get the sprite for the slime
     * @return slime sprite
     */
    public AnimatedSprite getSprite() {
        return slimeSprite;
    }
    /**
     * Get the slime speed
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }
    /**
     * Move the slime toward the player
     */
    public void move(){
        int speed = random.nextInt(getSpeed()+1);
        // get the player position
        int xTarget = getPlayer().getSprite().getXCenter() - getSprite().getWidth() / 2;
        int yTarget = getPlayer().getSprite().getYCenter() - getSprite().getHeight() / 2;
        // get the distance between the slime and the player
        int xCheck = xTarget - getSprite().getXPosition();
        int yCheck = yTarget - getSprite().getYPosition();
        // the movement vector
        Vector2D vector = new Vector2D(xCheck, yCheck);
        vector.normalize();
        vector.multiply(speed);

        for (MovementOverride movementOverride : new ArrayList<>(movementOverrides)) {
            vector = movementOverride.move(vector);
        }

        if (getSprite() instanceof SlimeSprite sprite) {
            sprite.setFaceLeft(xCheck > 0);
        }
        if (getSprite() instanceof SlimeDeath sprite) {
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
     * Check if the slime is dead
     * @return true if the slime is dead
     */
    @Override
    public boolean isDead() {
        return (slimeSprite instanceof SlimeDeath) && ((SlimeDeath) slimeSprite).isDone();
    }
    /**
     * Update the slime
     */
    public void tick() {
        super.tick();
        slimeSprite.tick();
        if (getHealth() <= 0 && slimeSprite instanceof SlimeSprite) {
            slimeSprite = new SlimeDeath(getXPosition(), getYPosition());
            hitbox = new VoidHitbox();
        }
        if (getHealth() <= 0) {
            return;
        }
        Player player = PlayerController.getInstance().getPlayer();
        if (player.getHitbox().intersects(hitbox)) {
            player.damage(10);
            player.knockBack(getSprite().getXCenter(), getSprite().getYCenter());
        }
        move();
    }
}