package tests;

import entities.hitboxes.RectangularHitbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularHitboxTest {

    @Test
    void setXPosition() {
        RectangularHitbox hitbox = new RectangularHitbox(0, 0, 10, 10);
        hitbox.setXPosition(10);
        assert hitbox.getXPosition() == 10;
    }

    @Test
    void setYPosition() {
        RectangularHitbox hitbox = new RectangularHitbox(0, 0, 10, 10);
        hitbox.setYPosition(10);
        System.out.println(hitbox.getYPosition());
        assert hitbox.getYPosition() == 10;
    }

    @Test
    void getWidth() {
        RectangularHitbox hitbox = new RectangularHitbox(0, 0, 10, 10);
        assert hitbox.getWidth() == 10;
    }

    @Test
    void getHeight() {
        RectangularHitbox hitbox = new RectangularHitbox(0, 0, 10, 10);
        assert hitbox.getHeight() == 10;
    }
}