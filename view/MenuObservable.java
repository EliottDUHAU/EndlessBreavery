package view;

/**
 * The observable of the menu.
 */
public interface MenuObservable {
    /**
     * Add an observer to the menu.
     *
     * @param observer the observer to be added.
     */
    void addObserver(MenuObserver observer);
    /**
     * Remove an observer from the menu.
     *
     * @param observer the observer to be removed.
     */
    void removeObserver(MenuObserver observer);
    /**
     * Notify all observers of the menu.
     */
    void notifyObservers();
}
