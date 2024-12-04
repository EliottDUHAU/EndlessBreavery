package entities.enemies.projectiles;

import controllers.PlayerController;
import entities.Entity;
import entities.Player;
import entities.hitboxes.*;
import sprites.AnimatedSprite;
import sprites.Sprite;
import sprites.enemies.FireballSprite;
import utils.Vector2D;

/**
 * The fireball projectile
 */
public class Fireball extends Projectile {
    /**
     * The hitbox of the fireball
     */
    private Hitbox hitbox;
    /**
     * The sprite of the fireball
     */
    private AnimatedSprite fireballSprite;
    /**
     * The fireball lifetime
     */
    private int fireballCooldown;
    /**
     * The tick counter
     */
    private int tickCounter = 0;
    /**
     * The direction of the fireball
     */
    private Vector2D direction;
    /**
     * The alive status of the fireball
     */
    private boolean alive = true;
    /**
     * Create a new fireball
     * @param x the x position
     * @param y the y position
     * @param direction the direction of the fireball
     * @param fireballCooldown the fireball lifetime
     */
    public Fireball(int x, int y, Vector2D direction, int fireballCooldown){
        super(x,y);
        this.fireballSprite = new FireballSprite(getXPosition(), getYPosition());
        this.fireballCooldown = fireballCooldown;
        this.hitbox = new CircleHitbox(fireballSprite.getXCenter(),fireballSprite.getYCenter(),fireballSprite.getWidth()/2);
        this.direction = direction;
    }
    /**
     * Get the collision box of the fireball
     * @return the collision box
     */
    @Override
    public Hitbox getCollisionBox() {
        return new VoidHitbox();
    }
    /**
     * Set the collision box of the fireball
     * @param hitbox the collision box
     */
    @Override
    public void setCollisionBox(Hitbox hitbox) {

    }
    /**
     * Get the x position of the fireball
     * @return the x position
     */
    @Override
    public Sprite getSprite() {
        return fireballSprite;
    }
    /**
     * Get the y position of the fireball
     * @return the y position
     */
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }
    /**
     * Get the direction of the fireball
     * @return the direction
     */
    @Override
    public Vector2D getDirection() {
        return direction;
    }
    /**
     * Check if the fireball is alive
     * @return true if the fireball is alive
     */
    @Override
    public boolean isAlive() {
        return tickCounter < fireballCooldown && alive;
    }
    /**
     * Set the alive status of the fireball
     * @param alive the alive status
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**
     * Update the fireball
     */
    @Override
    public void tick() {
        tickCounter++;
        super.tick();
        Player player = PlayerController.getInstance().getPlayer();
        if (hitbox.intersects(player.getHitbox())){
            player.damage(8);
            Vector2D knockback = new Vector2D(- player.getSprite().getXCenter() + getHitbox().getXPosition(), - player.getYPosition() + getHitbox().getYPosition());
            knockback.normalize();
            knockback.multiply(40);
            player.knockBack(knockback.getX() + player.getSprite().getXCenter(), knockback.getY() + player.getSprite().getYCenter());
            alive = false;
        }
    }
}