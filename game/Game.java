package game;

import cards.Card;
import controllers.*;
import controllers.AudioController;
import entities.Player;
import sprites.*;
import map.Map;
import entities.enemies.Spawner;
import sprites.menus.PauseUI;
import entities.enemies.Slime;

import java.util.*;

import utils.Timer;
import view.GamePanel;


/**
 * The main game class
 */
public class Game implements GameObservable {
    /**
     * The frames per second constant
     */
    public static final int FPS = 100;
    /**
     * The ticks per second constant
     */
    public static final int TPS = 60;

    private Spawner spawner;
    /**
     * The map of the game
     */
    private Map map;
    /**
     * The pause UI of the pause menu
     */
    private PauseUI pauseUI = new PauseUI();
    private AudioController audioController;
    /**
     * Get the pause UI of the game
     * @return the pause UI of the game
     */
    public PauseUI getPauseUI() {
        return pauseUI;
    }
    /**
     * Whether the game is paused
     */
    private boolean paused = false;
    /**
     * Whether the game is paused because player is picking a card
     */
    private boolean pickingCard = false;
    /**
     * Get the pause UI of the game
     * @return the pause UI of the game
     */
    public boolean isPaused() {
        return paused;
    }
    /**
     * Check if the player is picking a card
     * @return whether the player is picking a card
     */
    public boolean isPickingCard() {
        return pickingCard;
    }
    /**
     * Set whether the player is picking a card
     * @param pickingCard whether the player is picking a card
     */
    public void setPickingCard(boolean pickingCard) {
        this.pickingCard = pickingCard;
    }
    /**
     * The list of observers in the game
     */
    private List<GameObserver> observers = new ArrayList<>();
    /**
     * The camera offset manager of the game
     */
    private Camera camera;
    /**
     * Get the camera of the game
     * @return the camera of the game
     */
    public Camera getCamera() {
        return camera;
    }
    /**
     * The list of timers in the game
     */
    private List<Timer> timers = new ArrayList<>();
    /**
     * Adds a timer to the game
     * @param timer
     */
    public void addTimer(Timer timer) {
        timers.add(timer);
    }
    /**
     * Constructor of the game
     */
    public Game() {
        this.map = MapController.getInstance().getMap();
        this.camera = CameraController.getInstance().getCamera();
        TileSprite tileSprite = map.getBackground();
        Player player = PlayerController.getInstance().getPlayer();
        int startX = (tileSprite.getWidth() / 2) - (player.getSprite().getWidth() / 2);
        int startY = (tileSprite.getHeight() / 2) - (player.getSprite().getHeight() / 2);
        player.setXPosition(startX);
        player.setYPosition(startY);
//        EnemyController.getInstance().spawnEnemies();
        audioController = AudioController.getInstance("resources/audio/audio.wav");
        CardController.getInstance().drawCard();
        CardController.getInstance().drawCard();
        CardController.getInstance().drawCard();
        CardController.getInstance().drawCard();
    }
    /**
     * Get the player entity
     * @return the player entity
     */
    public Player getPlayer() {
        return PlayerController.getInstance().getPlayer();
    }
    /**
     * Adding a view observer to the game
     */
    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    /**
     * Removing a view observer from the game
     */
    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    /**
     * Notifying all observers of the game
     */
    @Override
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }
    /**
     * Main game loop
     */
    public void run() {
        audioController.play();


        double timePerFrame = 1000000000.0 / FPS;
        double timePerTick = 1000000000.0 / TPS;

        long now = System.nanoTime();
        long previousFrame = now;
        long lastTime = now;

        int frames = 0;
        int ticks = 0;
        long timer = System.currentTimeMillis();
        PauseController pauseController = PauseController.getInstance();
        boolean previousPaused = pauseController.getEscape();

        double delta = 0;

        do {
            now = System.nanoTime();
            long currentTime = now;

            if (!paused) {
                delta += (now - lastTime) / timePerTick;
                if (delta >= 1) {
                    tick();
                    ticks++;
                    delta--;
                }
            }

            lastTime = currentTime;

            if (now - previousFrame > timePerFrame) {
                if (pauseController.getEscape() && !previousPaused) {
                    paused = !paused;
                }
                notifyObservers();
                previousPaused = pauseController.getEscape();
                previousFrame = System.nanoTime();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer = System.currentTimeMillis();
                System.out.println("FPS: " + frames + ", TPS: " + ticks);
                //System.out.println("player: " + player.getXPosition() + ", " + (player.getYPosition() + player.getSprite().getHeight()));
                frames = 0;
                ticks = 0;
            }
        } while (getPlayer().isAlive());
        notifyObservers();
    }
    /**
     * Method to be run on each game step
     */
    private void tick() {
        if (isPickingCard()) {
            CardController cardController = CardController.getInstance();
            Card card =  cardController.getCardPicker().getChosenCard();
            if (card != null) {
                cardController.getDeck().addCard(card);
                cardController.resetCardPicker();
                pickingCard = false;
            }
        }
        else {
            Player player = PlayerController.getInstance().getPlayer();
            EnemyController.getInstance().tickEnemies();
            CardController.getInstance().tick();
            BuffController.getInstance().tick();
            ProjectileController.getInstance().tick();
            player.tick();
            for (Timer timer : new ArrayList<>(timers)) {
                timer.tick();
                if (timer.isDone()) {
                    timers.remove(timer);
                }
            }
        }
    }
}
