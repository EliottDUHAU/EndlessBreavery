package tests;

import entities.hitboxes.*;
import org.junit.jupiter.api.Test;
import utils.Pair;

class VoidHitboxTest {

    @Test
    void getXPosition() {
        VoidHitbox voidHitbox = new VoidHitbox();
        assert voidHitbox.getXPosition() == 0;
    }

    @Test
    void getYPosition() {
        VoidHitbox voidHitbox = new VoidHitbox();
        assert voidHitbox.getYPosition() == 0;
    }

    @Test
    void setXPosition() {
        VoidHitbox voidHitbox = new VoidHitbox();
        voidHitbox.setXPosition(10);
        assert voidHitbox.getXPosition() == 0;
    }

    @Test
    void setYPosition() {
        VoidHitbox voidHitbox = new VoidHitbox();
        voidHitbox.setYPosition(10);
        assert voidHitbox.getYPosition() == 0;
    }

    @Test
    void intersectsVoid() {
        VoidHitbox voidHitbox = new VoidHitbox();
        assert !voidHitbox.intersects(new VoidHitbox());
    }

    @Test
    void intersectsCircle() {
        VoidHitbox voidHitbox = new VoidHitbox();
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        assert !voidHitbox.intersects(circleHitbox);
    }

    @Test
    void intersectsLine() {
        VoidHitbox voidHitbox = new VoidHitbox();
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert !voidHitbox.intersects(lineHitbox);
    }

    @Test
    void intersectsPolygone() {
        VoidHitbox voidHitbox = new VoidHitbox();
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(10, 0), new Pair<>(10, 10));
        assert !voidHitbox.intersects(polygoneHitbox);
    }

    @Test
    void intersectsRectangle() {
        VoidHitbox voidHitbox = new VoidHitbox();
        RectangularHitbox rectangleHitbox = new RectangularHitbox(0, 0, 10, 10);
        assert !voidHitbox.intersects(rectangleHitbox);
    }
}