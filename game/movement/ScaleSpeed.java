package game.movement;

import sprites.AnimatedSprite;
import utils.Vector2D;

public class ScaleSpeed extends MovementOverride {
    private double scale;

    public ScaleSpeed(double scale, AnimatedSprite sprite) {
        super(sprite);
        this.scale = scale;
    }

    @Override
    public Vector2D move(Vector2D velocity) {
        velocity.multiply(scale);
        return velocity;
    }
}
