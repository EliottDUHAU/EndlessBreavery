package entities.enemies;

import controllers.MapController;
import controllers.PlayerController;
import entities.Player;
import entities.hitboxes.*;
import sprites.AnimatedSprite;
import sprites.enemies.*;
import utils.Pair;
import utils.Vector2D;

import java.util.Random;

/**
 * The Bats enemy class
 */
public class Batman extends Enemy {
    /**
     * Get the hitbox of the Bats
     * @return hitbox box
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Get the collision box of the Bats
     * @return hitbox box
     */
    @Override
    public Hitbox getCollisionBox() {
        return new VoidHitbox();
    }
    /**
     * Set the collision box of the Bats
     * @param hitbox box
     */
    @Override
    public void setCollisionBox(Hitbox hitbox) {
        return;
    }

    /**
     * The speed of the Bats
     */
    private int speed;
    /**
     * The hitbox of the Bats
     */
    private Hitbox hitbox;
    /**
     * The UI of the Bats
     */
    private EnemyUI batmanUI;
    /**
     * The random number generator
     */
    Random random = new Random();
    /**
     * The health of the Bats
     */
    private int maxHealth;
    /**
     * The duration of the dash
     */
    private int dashDuration;
    /**
     * The magnitude of the dash
     */
    private int dashMagnitude;
    /**
     * The cooldown of the dash
     */
    int dashCooldown = 0;

    /**
     * The sprite for the Bats
     */
    private AnimatedSprite batmanSprite = new BatmanSprite(0, 0);
    /**
     * Create a new Bats enemy
     * @param x x position
     * @param y y position
     */
    public Batman(int x, int y, int health, int maxHealth, int dashMagnitude, int dashDuration) {
        super(x, y, health, maxHealth);
        this.batmanSprite = new BatmanSprite(x, y);
        this.batmanUI = new EnemyUI(this);
        this.dashMagnitude = dashMagnitude;
        this.dashDuration = dashDuration;
        hitbox = new CircleHitbox(batmanSprite.getXCenter(), batmanSprite.getYCenter(), 32);
    }

    /**
     * Get the sprite for the Bats
     * @return Bats sprite
     */
    public EnemyUI getEnemyUI(){
        return batmanUI;
    }
    /**
     * Get the sprite for the slime
     * @return slime sprite
     */
    public AnimatedSprite getSprite() {
        return batmanSprite;
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
    public void move(){;
        int speed = dashMagnitude;
        //set time restrain on the dash system
        if (dashCooldown <= 60){
            dashCooldown++;
        }
        //
        if(  dashCooldown<= 60 && dashCooldown >= 60 - dashDuration){
            int xTarget = getPlayer().getSprite().getXCenter() - getSprite().getWidth() / 2;
            int yTarget = getPlayer().getSprite().getYCenter() - getSprite().getHeight() / 2;
            // get the distance between the skeleton and the player
            int xCheck = xTarget - getSprite().getXPosition();
            int yCheck = yTarget - getSprite().getYPosition();
            // the movement vector
            Vector2D vector = new Vector2D(xCheck, yCheck);
            vector.normalize();
            vector.multiply(speed);
            if (getSprite() instanceof BatmanSprite sprite) {
                sprite.setFaceLeft(xCheck > 0);
            }
            if (getSprite() instanceof BatmanDeath sprite) {
                sprite.setFaceLeft(xCheck > 0);
            }
            // move accordingly
            setXPosition(getXPosition() + vector.getX());
            setYPosition(getYPosition() + vector.getY());
        }
        if(dashCooldown >= 60){
            dashCooldown = 0;
        }
    }

    /**
     * Check if the Bats is dead
     * @return true if the Bats is dead
     */
    @Override
    public boolean isDead() {
        return (batmanSprite instanceof BatmanDeath) && ((BatmanDeath) batmanSprite).isDone();
    }

    /**
     * Tick the Bats
     */
    public void tick() {
        move();
        super.tick();
        batmanSprite.tick();
        if (getHealth() <= 0 && batmanSprite instanceof BatmanSprite) {
            batmanSprite = new BatmanDeath(getXPosition(), getYPosition());
            hitbox = new VoidHitbox();
        }
        if (getHealth() <= 0) {
            return;
        }
        Player player = PlayerController.getInstance().getPlayer();
        if (player.getHitbox().intersects(hitbox)) {
            player.damage(8);
            player.knockBack(getSprite().getXCenter(), getSprite().getYCenter());
        }
    }
}