package utils;

/**
 * A pair of elements
 * @param <T> the type of the first element
 * @param <U> the type of the second element
 */
public class Pair<T, U> {
    /**
     * The first element of the pair
     */
    private T first;
    /**
     * The second element of the pair
     */
    private U second;
    /**
     * Create a new pair
     * @param first the first element
     * @param second the second element
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    /**
     * Get the first element of the pair
     * @return the first element
     */
    public T getFirst() {
        return first;
    }
    /**
     * Get the second element of the pair
     * @return the second element
     */
    public U getSecond() {
        return second;
    }
    /**
     * Set the first element of the pair
     * @param first the first element
     */
    public void setFirst(T first) {
        this.first = first;
    }
    /**
     * Set the second element of the pair
     * @param second the second element
     */
    public void setSecond(U second) {
        this.second = second;
    }

    /**
     * Get a string representation of the pair
     * @return a string representation of the pair
     */
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
