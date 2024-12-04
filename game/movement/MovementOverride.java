package game.movement;

import sprites.AnimatedSprite;
import utils.Timer;
import utils.Vector2D;

public abstract class MovementOverride {
    private AnimatedSprite sprite;
    private Timer timer;


    public MovementOverride(AnimatedSprite sprite) {
        this.sprite = sprite;
    }
    public MovementOverride(int duration) {
        this.timer = new Timer(duration);
    }

    public abstract Vector2D move(Vector2D velocity);

    public boolean isDone() {
        if (sprite != null) {
            return sprite.isDone();
        }
        if (timer != null) {
            return timer.isDone();
        }
        return false;
    }
}
