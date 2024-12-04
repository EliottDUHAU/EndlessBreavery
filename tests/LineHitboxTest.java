package tests;

import entities.hitboxes.CircleHitbox;
import entities.hitboxes.LineHitbox;
import entities.hitboxes.PolygoneHitbox;
import org.junit.jupiter.api.Test;
import utils.Pair;

class LineHitboxTest {

    @Test
    void getXPosition() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.getXPosition() == 0;
    }

    @Test
    void getYPosition() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.getYPosition() == 0;
    }

    @Test
    void setXPosition() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        lineHitbox.setXPosition(10);
        assert lineHitbox.getXPosition() == 10;
    }

    @Test
    void setYPosition() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        lineHitbox.setYPosition(10);
        assert lineHitbox.getYPosition() == 10;
    }

    @Test
    void intersectsLine() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.intersects(new LineHitbox(0, 10, 10, -10));
        assert !lineHitbox.intersects(new LineHitbox(0, 10, 10, 10));
    }

    @Test
    void intersectsCircle() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.intersects(new CircleHitbox(15, 15, 10));
        assert !lineHitbox.intersects(new CircleHitbox(20, 20, 5));
    }

    @Test
    void intersectsPolygone() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.intersects(new PolygoneHitbox(new Pair<>(5, 0), new Pair<>(5, 10), new Pair<>(20, 20)));
        assert !lineHitbox.intersects(new PolygoneHitbox(new Pair<>(20, 20), new Pair<>(25, 30), new Pair<>(40, 40)));
    }

    @Test
    void getVectorX() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.getVectorX() == 10;
    }

    @Test
    void getVectorY() {
        LineHitbox lineHitbox = new LineHitbox(0, 0, 10, 10);
        assert lineHitbox.getVectorY() == 10;
    }
}