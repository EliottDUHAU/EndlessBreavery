package game.waves;

import game.Game;

/**
 * The EmptyWave class is a class that represents an empty wave in the game.
 */
public class EmptyWave extends Wave {
    /**
     * The constructor for the EmptyWave class.
     * @param timer How long the wave should last in seconds.
     */
    public EmptyWave(double timer) {
        super(0);

        this.timer = (int) (Game.TPS * timer);
    }
    /**
     * The timer for the wave.
     */
    private int timer;

    /**
     * Ticks the wave.
     */
    @Override
    public void tick() {
        if (timer > 0) timer--;
    };
    /**
     * Checks if the wave is over.
     * @return true if the wave is over, false otherwise
     */
    @Override
    public boolean isWaveOver() {
        return timer <= 0;
    }
}
