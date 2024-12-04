package view;

import controllers.*;
import entities.Entity;
import entities.Player;
import entities.enemies.Enemy;
import entities.enemies.projectiles.Projectile;
import game.Camera;
import game.Game;
import game.GameObserver;
import sprites.TileSprite;
import sprites.menus.GameOver;
import sprites.player.PlayerDeath;
import sprites.player.PlayerRush;
import sprites.player.PlayerSlash;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The java swing game panel of the game.
 */
public class GamePanel extends JPanel implements GameObserver {
    /**
     * The initial width of the game.
     */
    public static final int GAME_WIDTH = 1000;
    /**
     * The initial height of the game.
     */
    public static final int GAME_HEIGHT = 800;
    /**
     * The background sprite.
     */
    private TileSprite background = new TileSprite(0, 0);
    private controllers.MouseController mouseController;
    /**
     * The show hitboxes flag.
     */
    private boolean showHitboxes = false;
    /**
     * The previous show hitboxes flag.
     */
    private boolean previousShowHitboxes = false;
    /**
     * The show collision flag.
     */
    private boolean showCollision = false;
    /**
     * The previous show collision flag.
     */
    private boolean previousShowCollision = false;
    /**
     * Constructor of the game panel.
     */
    public GamePanel() {
        super();
        DebugController debugController = DebugController.getInstance();
        setSize();
        setVisible(true);
        setFocusable(true);
        this.mouseController = new controllers.MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        addKeyListener(MovementController.getInstance());
        addKeyListener(PauseController.getInstance());
        addKeyListener(debugController);
        addMouseListener(CardInputController.getInstance());
        addMouseListener(PauseController.getInstance());
        addMouseListener(ResetController.getInstance());
    }
    /**
     * Set the size of the game panel.
     */
    private void setSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }
    /**
     * Get the center x position of the game panel.
     * @return the center x position of the game panel.
     */
    public int getCenterX() {
        return this.getSize().height / 2;
    }
    /**
     * Get the center y position of the game panel.
     * @return the center y position of the game panel.
     */
    public int getCenterY() {
        return this.getSize().width / 2;
    }
    /**
     * Update the game panel.
     *
     */
    @Override
    public void update() {
        repaint();
    }
    /**
     * Paint the game panel.
     *
     * @param g the graphics object to paint the game panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Game game = GameController.getInstance().getGame();
        super.paintComponent(g);
        // draw background
        drawBackground(g);
        // drawing the player sprite
        drawEntities(g);
        // draw foreground
        drawForeground(g);
        CardController.getInstance().drawGraphics(g, game.getCamera());
        // draw debug
        drawDebug(g);

        if (game.isPickingCard()) {
            CardController.getInstance().getCardPicker().drawGraphics(g, game.getCamera());
        }

        if (! PlayerController.getInstance().getPlayer().isAlive()) {
            new GameOver().draw(g, game.getCamera());
            GameController.getInstance().getGame().removeObserver(this);
        }

        if (game.isPaused()) {
            PauseController.getInstance().getPauseUI().draw(g, game.getCamera());
        }
    }
    /**
     * Draw the background of the game.
     * @param g the graphics object to draw the background.
     */
    private void drawBackground(Graphics g) {
        background.draw(g, CameraController.getInstance().getCamera());
    }
    /**
     * Draw the entities of the game.
     * @param g the graphics object to draw the entities.
     */
    private void drawEntities(Graphics g) {
        List<Enemy>enemies = EnemyController.getInstance().getEnemies();
        for(Enemy enemy : enemies){
            enemy.getSprite().draw(g, CameraController.getInstance().getCamera());
            if(enemy.getHealth()!=enemy.getMaxHealth()) {
                enemy.drawUI(g, CameraController.getInstance().getCamera());
            }
        }
        PlayerController.getInstance().getPlayer().getSprite().draw(g, CameraController.getInstance().getCamera());
        for (Projectile projectile : ProjectileController.getInstance().getProjectiles()) {
            projectile.getSprite().draw(g, CameraController.getInstance().getCamera());
        }
    }
    /**
     * Draw the UI in the foreground of the game.
     * @param g the graphics object to draw the foreground.
     */
    private void drawForeground(Graphics g) {
        // Draw the player UI
        PlayerController.getInstance().getPlayer().getPlayerUI().draw(g, CameraController.getInstance().getCamera());
    }
    /**
     * Draw the debug information of the game.
     * @param g the graphics object to draw the debug information.
     */
    private void drawDebug(Graphics g) {
        DebugController debugController = DebugController.getInstance();
        if (debugController.getHPressed() && !previousShowHitboxes) {
            showHitboxes = !showHitboxes;
            previousShowHitboxes = true;
        } else if (!debugController.getHPressed()) {
            previousShowHitboxes = false;
        }
        if (debugController.getCPressed() && !previousShowCollision) {
            showCollision = !showCollision;
            previousShowCollision = true;
        } else if (!debugController.getCPressed()) {
            previousShowCollision = false;
        }
        Player player = PlayerController.getInstance().getPlayer();
        Camera camera = CameraController.getInstance().getCamera();
        if (showHitboxes) {
            g.setColor(Color.RED);
            player.getHitbox().debugDraw(g, camera);
            List<Enemy> enemies = EnemyController.getInstance().getEnemies();
            List<Projectile> projectiles = ProjectileController.getInstance().getProjectiles();
            for (Enemy enemy : enemies) {
                enemy.getHitbox().debugDraw(g, camera);
            }
            for (Projectile projectile : projectiles) {
                projectile.getHitbox().debugDraw(g, camera);
            }
            if (player.getSprite() instanceof PlayerSlash) {
                ((PlayerSlash) player.getSprite()).debugDraw(g, camera);
            }
            if (player.getSprite() instanceof PlayerRush) {
                ((PlayerRush) player.getSprite()).debugDraw(g, camera);
            }
            g.setColor(Color.PINK);
            CardController.getInstance().getHand().debugDraw(g, camera);
            g.setColor(Color.BLACK);
        }
        if (showCollision) {
            g.setColor(Color.BLUE);
            List<Enemy> enemies = EnemyController.getInstance().getEnemies();
            for (Enemy enemy : enemies) {
                enemy.getCollisionBox().debugDraw(g, camera);
            }
            player.getCollisionBox().debugDraw(g, camera);
            MapController.getInstance().getMap().debugDraw(g, camera);
            g.setColor(Color.BLACK);
        }
    }
}