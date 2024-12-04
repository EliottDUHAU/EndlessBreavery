package entities;

import cards.Card;
import cards.Hand;
import cards.status.Wound;
import controllers.*;
import entities.enemies.Enemy;
import entities.enemies.projectiles.Fireball;
import entities.enemies.projectiles.Projectile;
import entities.hitboxes.Hitbox;
import entities.hitboxes.LineHitbox;
import entities.hitboxes.RectangularHitbox;
import game.Camera;
import game.movement.ConserveMomentum;
import game.movement.MovementOverride;
import game.movement.ScaleSpeed;
import map.Map;
import sprites.player.*;
import utils.Pair;
import utils.Vector2D;
import view.GamePanel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 */
public class Player extends Entity {
    /**
     * The invulnerable ticks after getting hit
     */
    private int invulnerableTicks = 60;
    /**
     * The tick counter for iframes
     */
    private int iTickCounter = 0;
    /**
     * The knock back vector
     */
    private Vector2D knockBack;
    /**
     * The duration of the knock back
     */
    private int knockBackDuration = 15;
    /**
     * The counter for the knock back
     */
    private int knockbackCounter = 0;
    /**
     * The magnitude of the knock back
     */
    private double knockbackMagnitude = 1.2;
    /**
     * The maximum health of the player
     */
    private int maxHealth = 100;
    /**
     * The current health of the player
     */
    private int health = 100;
    /**
     * The speed of the player
     */
    private double speed = 10.0;
    /**
     * The current speed of the player
     */
    private double currentSpeed = 10.0;
    /**
     * The player sprite
     */
    private PlayerSprite playerSprite;
    /**
     * The hitbox of the player
     */
    private RectangularHitbox hitbox;
    /**
     * The collision box of the player
     */
    private LineHitbox collisionbox;
    /**
     * The player UI sprite
     */
    private PlayerUI playerUI;
    /**
     * The space bar controller
     */
    public final SpaceController spaceController = new SpaceController();

    private final List<MovementOverride> movementOverrides = new ArrayList<>();

    /**
     * Create a new player entity
     * z@param x x position
     * @param y y position
     */
    public Player(int x, int y) {
        super(x, y);
        this.playerUI = new PlayerUI(this);
        this.playerSprite = new PlayerSprite(this.getXPosition(), this.getYPosition());
        int hitBoxSize = (int) (playerSprite.getWidth() * 0.5f);
        this.hitbox = new RectangularHitbox(playerSprite.getXCenter(), playerSprite.getYCenter(), hitBoxSize, hitBoxSize);
        this.collisionbox = new LineHitbox(getXPosition(), getYPosition() + playerSprite.getHeight(), playerSprite.getWidth(), 0);
        SoundController soundController = SoundController.getInstance();
    }
    /**
     * Get the player sprite
     * @return player sprite
     */
    public PlayerSprite getSprite() {
        return playerSprite;
    }
    /**
     * Get the player UI
     * @return player UI
     */
    public PlayerUI getPlayerUI() {
        return playerUI;
    }
    /**
     * Get the health of the player
     * @return health
     */
    public int getHealth() {
        return health;
    }
    /**
     * Get the maximum health of the player
     * @return max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * Method to move the player and the camera if any keys are pressed during the current tick
     * Also normalises the vector to avoid diagonal movement being faster
     */
    public void move() {
        double x = 0;
        double y = 0;
        if (MovementController.getInstance().getDirections().contains(Directions.UP)) {
            y -= 1;
        }
        if (MovementController.getInstance().getDirections().contains(Directions.DOWN)) {
            y += 1;
        }
        if (MovementController.getInstance().getDirections().contains(Directions.LEFT)) {
            x -= 1;
        }
        if (MovementController.getInstance().getDirections().contains(Directions.RIGHT)) {
            x += 1;
        }
        if (x != 0 && y != 0) {
            x *= 0.7071;
            y *= 0.7071;
        }
        x *= currentSpeed;
        y *= currentSpeed;
        Vector2D movement = new Vector2D(x, y);
        for (MovementOverride movementOverride : new ArrayList<>(movementOverrides)) {
            if (movementOverride.isDone()) {
                movementOverrides.remove(movementOverride);
            }
            else {
                movement = movementOverride.move(movement);
            }
        }
        Map map = MapController.getInstance().getMap();
        // Pair<Integer, Integer> newPosition = new Pair<>(getXPosition() + (int) x, getYPosition() + playerSprite.getHeight() + (int) y);
        Pair<Integer, Integer> newPosition = map.collide(this.collisionbox, movement.getX(), movement.getY());
        int currentX = this.getXPosition();
        int currentY = this.getYPosition();
        Camera camera = CameraController.getInstance().getCamera();
        camera.setX(playerSprite.getXCenter() - camera.getWidth() / 2);
        camera.setY(playerSprite.getYCenter() - camera.getHeight() / 2);
        if (newPosition.getFirst() == currentX && newPosition.getSecond() == currentY + playerSprite.getHeight()) {
            playerSprite.setMoving(false);
            return;
        }
        playerSprite.setMoving(true);
        this.setXPosition(newPosition.getFirst());
        this.setYPosition(newPosition.getSecond() - playerSprite.getHeight());
        hitbox.setXPosition(playerSprite.getXCenter());
        hitbox.setYPosition(playerSprite.getYCenter());
        collisionbox.setXPosition(getXPosition());
        collisionbox.setYPosition(getYPosition() + playerSprite.getHeight());
        playerSprite.setFaceLeft(MovementController.getInstance().getFacing() == Directions.LEFT);
    }
    /**
     * Method to start a slash attack
     */
    public void slash() {
        playerSprite = new PlayerSlash(playerSprite);
        movementOverrides.add(new ScaleSpeed(0.0, playerSprite));
        SoundController.getInstance().play("slash");
    }

    private void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SoundController.getInstance().play("slash");
    }
    /**
     * Method to heal the player
     * @param heal amount to heal
     */
    public void heal(int heal) {
        playerSprite = new PlayerHeal(playerSprite);
        health += heal;
        SoundController.getInstance().play("heal");
        if (health > maxHealth) {
            health = maxHealth;
        }
        Hand hand = CardController.getInstance().getHand();
        for (Card card: hand.getCards()) {
            if (card instanceof Wound) hand.removeCard(card);
        }
    }

    public void rush() {
        playerSprite = new PlayerRush(playerSprite);
        movementOverrides.add(new ScaleSpeed(2.0, playerSprite));
        movementOverrides.add(new ConserveMomentum(playerSprite));
    }
    /**
     * Advances player state by one tick
     */
    @Override
    public void tick() {
        playerSprite.tick();
        if (playerSprite instanceof PlayerDeath) {
            return;
        }
        playerUI.tick();
        move();
        if (playerSprite instanceof PlayerSpriteDecorator) {
            if (playerSprite.isDone()) {
                playerSprite = ((PlayerSpriteDecorator) playerSprite).getPlayerSprite();
            }
        }
        if (playerSprite instanceof PlayerSlash slashSprite) {
            List<Enemy> enemies = EnemyController.getInstance().getEnemies();
            for (Enemy enemy : enemies) {
                if (slashSprite.getHitbox().intersects(enemy.getHitbox())) {
                    if (enemy.damage(10)) enemy.knockBack();
                }
            }
            List<Projectile> projectiles = ProjectileController.getInstance().getProjectiles();
            for (Projectile projectile : projectiles) {
                if (slashSprite.getHitbox().intersects(projectile.getHitbox()) && projectile instanceof Fireball fireball) {
                    fireball.setAlive(false);
                }
            }
        }
        if (playerSprite instanceof PlayerRush rushSprite) {
            List<Enemy> enemies = EnemyController.getInstance().getEnemies();
            for (Enemy enemy : enemies) {
                if (rushSprite.getHitbox().intersects(enemy.getHitbox())) {
                    if (enemy.damage(20)) enemy.knockBack();
                }
            }
        }
        if (iTickCounter > 0) {
            iTickCounter--;
        }
        if (knockbackCounter > 0 && getCollisionBox() instanceof LineHitbox collisionBox) {
            Pair<Integer, Integer> position = MapController.getInstance().getMap().collide(collisionBox, knockBack.getX(), knockBack.getY());
            if (position.getFirst() != collisionBox.getXPosition() || position.getSecond() != collisionBox.getYPosition()) {
                this.setXPosition(getXPosition() + knockBack.getX());
                this.setYPosition(getYPosition() + knockBack.getY());
            }
            knockbackCounter--;
        }
        if (health <= 0 && !(playerSprite instanceof PlayerDeath)) {
            speed = 0;
            playerSprite = new PlayerDeath(playerSprite);
        }
    }
    /**
     * Set the x position of the player
     * Also updates the collision box
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
        super.setXPosition(xPosition);
        hitbox.setXPosition(playerSprite.getXCenter());
        hitbox.setYPosition(playerSprite.getYCenter());
        collisionbox = new LineHitbox(getXPosition(), getYPosition() + playerSprite.getHeight(), playerSprite.getWidth(), 0);
    }
    /**
     * Set the y position of the player
     * Also updates the collision box
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        super.setYPosition(yPosition);
        collisionbox = new LineHitbox(getXPosition(), getYPosition() + playerSprite.getHeight(), playerSprite.getWidth(), 0);
    }
    /**
     * Get the collision box of the player
     * @return collision box
     */
    public LineHitbox getCollisionBox() {
        return collisionbox;
    }
    /**
     * Set the collision box of the player
     * @param hitbox the collision box, must be a line hitbox
     */
    public void setCollisionBox(Hitbox hitbox) {
        if (hitbox instanceof LineHitbox) {
            this.collisionbox = (LineHitbox) hitbox;
        }
    }
    /**
     * Get the hitbox of the player
     * @return hitbox
     */
    public RectangularHitbox getHitbox() {
        return hitbox;
    }

    /**
     * Method to knock back the player
     * @param sourceX source x
     * @param sourceY source y
     */
    public void knockBack(int sourceX, int sourceY) {
        // calculate knock back
        knockBack = new Vector2D(sourceX - getSprite().getXCenter(), sourceY - getSprite().getYCenter());
        knockBack.multiply(-knockbackMagnitude / knockBackDuration);
        knockbackCounter = knockBackDuration;
    }
    /**
     * Method to damage the player
     * @param damage amount of damage
     * @return true if the player was damaged
     */
    public boolean damage(int damage) {
        if (playerSprite instanceof PlayerRush) return false;
        if (iTickCounter == 0) {
            health -= damage;
            iTickCounter = invulnerableTicks;
            knockBack = new Vector2D(0, 0);
            SoundController.getInstance().play("damage");
            return true;

        }
        return false;
    }
    /**
     * Method to check if the player is alive
     * @return true if the player is alive
     */
    public boolean isAlive() {

        if (playerSprite instanceof PlayerDeath playerDeath) {
            System.out.println("Player dying");
            if (playerDeath.isDone()) {
                SoundController.getInstance().play("death");
                System.out.println("Player dead");
                return false;
            }
        }
        return true;
    }
}
