package game.buffs;

/**
 * Interface for buffs
 */
public interface Buff {
    /**
     * Ticks the buff in time
     */
    void tick();

    /**
     * Method that sets up the buff
     */
    void setup();

    /**
     * Method that cleans up the buff after it's finished
     */
    void cleanup();

    /**
     * Method to check if the buff is still active
     * @return true if the buff is still applied, false if the buff can be disposed of
     */
    boolean isActive();
}
