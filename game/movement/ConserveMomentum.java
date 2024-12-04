package game.movement;

import sprites.AnimatedSprite;
import utils.Vector2D;

public class ConserveMomentum extends MovementOverride {
    private Vector2D velocity;

    public ConserveMomentum(AnimatedSprite sprite) {
        super(sprite);
        this.velocity = new Vector2D(0, 0);
    }

    @Override
    public Vector2D move(Vector2D velocity) {
        if (velocity.length() > this.velocity.length()) {
            this.velocity = velocity;
        }
        return this.velocity;
    }
}
