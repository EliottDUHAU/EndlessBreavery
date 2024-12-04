package utils;

import controllers.GameController;

public class Timer {
    private long tickCount;
    private long duration;

    /**
     * Creates a timer with a duration in game ticks
     * @param duration the duration of the timer in game ticks
     */
    public Timer(int duration) {
        this.duration = duration;
        this.tickCount = 0;
        GameController.getInstance().getGame().addTimer(this);
    }

    public void tick() {
        tickCount++;
    }

    public boolean isDone() {
        return tickCount >= duration;
    }
}
