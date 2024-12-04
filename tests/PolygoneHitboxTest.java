package tests;

import entities.hitboxes.*;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.awt.geom.Point2D;

class PolygoneHitboxTest {

    @Test
    void getXPosition() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        assert polygoneHitbox.getXPosition() == 0;
    }

    @Test
    void getYPosition() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        assert polygoneHitbox.getYPosition() == 0;
    }

    @Test
    void setXPosition() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        polygoneHitbox.setXPosition(10);
        assert polygoneHitbox.getXPosition() == 10;
    }

    @Test
    void setYPosition() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        polygoneHitbox.setYPosition(10);
        assert polygoneHitbox.getYPosition() == 10;
    }

    @Test
    void intersectsPolygon() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        PolygoneHitbox polygoneHitbox2 = new PolygoneHitbox(new Pair<>(0, 10), new Pair<>(20, 10), new Pair<>(10, 30));
        PolygoneHitbox polygoneHitbox3 = new PolygoneHitbox(new Pair<>(0, 50), new Pair<>(20, 50), new Pair<>(10, 70));
        assert polygoneHitbox.intersects(polygoneHitbox2);
        assert !polygoneHitbox.intersects(polygoneHitbox3);
    }

    @Test
    void intersectsCircle() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        CircleHitbox circleHitbox = new CircleHitbox(10, 10, 5);
        CircleHitbox circleHitbox2 = new CircleHitbox(10, 30, 5);
        assert polygoneHitbox.intersects(circleHitbox);
        assert !polygoneHitbox.intersects(circleHitbox2);
    }

    @Test
    void intersectsLine() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        LineHitbox lineHitbox = new LineHitbox(0, 10, 20, 0);
        LineHitbox lineHitbox2 = new LineHitbox(0, 20, 20, 20);
        assert polygoneHitbox.intersects(lineHitbox);
        assert !polygoneHitbox.intersects(lineHitbox2);
    }

    @Test
    void intersectsRectangle() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        RectangularHitbox rectangleHitbox = new RectangularHitbox(0, 0, 10, 10);
        RectangularHitbox rectangleHitbox2 = new RectangularHitbox(0, 50, 20, 20);
        assert polygoneHitbox.intersects(rectangleHitbox);
        assert !polygoneHitbox.intersects(rectangleHitbox2);
    }

    @Test
    void intesectsVoid() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        VoidHitbox voidHitbox = new VoidHitbox();
        assert !polygoneHitbox.intersects(voidHitbox);
    }

    @Test
    void containsPoint() {
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(10, 20));
        assert polygoneHitbox.containsPoint(new Point2D.Double(10, 10));
        assert !polygoneHitbox.containsPoint(new Point2D.Double(10, 30));
    }

}