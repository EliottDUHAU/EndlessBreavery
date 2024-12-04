package game;

/**
 * The observable of the game.
 */
public interface GameObservable {
    /**
     * Add an observer to the game.
     *
     * @param observer the observer to be added.
     */
    void addObserver(GameObserver observer);
    /**
     * Remove an observer from the game.
     *
     * @param observer the observer to be removed.
     */
    void removeObserver(GameObserver observer);
    /**
     * Notify all observers of the game.
     */
    void notifyObservers();
}
