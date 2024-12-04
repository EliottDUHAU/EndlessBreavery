package tests;


import entities.hitboxes.*;
import utils.Pair;

class CircleHitboxTest {

    @org.junit.jupiter.api.Test
    void getXPosition() {
        CircleHitbox circleHitbox = new CircleHitbox(50, 0, 10);
        assert circleHitbox.getXPosition() == 50;
    }

    @org.junit.jupiter.api.Test
    void getYPosition() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 50, 10);
        assert circleHitbox.getYPosition() == 50;
    }

    @org.junit.jupiter.api.Test
    void setXPosition() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        circleHitbox.setXPosition(50);
        assert circleHitbox.getXPosition() == 50;
    }

    @org.junit.jupiter.api.Test
    void setYPosition() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 0);
        circleHitbox.setYPosition(50);
        assert circleHitbox.getYPosition() == 50;
    }

    @org.junit.jupiter.api.Test
    void intersectsCircle() {
        CircleHitbox circleHitbox1 = new CircleHitbox(0, 0, 10);
        CircleHitbox circleHitbox2 = new CircleHitbox(5, 0, 10);
        assert circleHitbox1.intersects(circleHitbox2);
        CircleHitbox circleHitbox3 = new CircleHitbox(20, 10, 10);
        assert !circleHitbox1.intersects(circleHitbox3);
    }

    @org.junit.jupiter.api.Test
    void intersectsLine() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        LineHitbox lineHitbox1 = new LineHitbox(0, 0, 20, 0);
        assert circleHitbox.intersects(lineHitbox1);
        LineHitbox lineHitbox2 = new LineHitbox(20, 0, 40, 0);
        assert !circleHitbox.intersects(lineHitbox2);
    }

    @org.junit.jupiter.api.Test
    void IntersectsPolygon() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        PolygoneHitbox polygoneHitbox = new PolygoneHitbox(new Pair<>(0, 0), new Pair<>(20, 0), new Pair<>(20, 20));
        assert circleHitbox.intersects(polygoneHitbox);
        PolygoneHitbox polygoneHitbox2 = new PolygoneHitbox(new Pair<>(20, 20), new Pair<>(40, 20), new Pair<>(40, 40));
        assert !circleHitbox.intersects(polygoneHitbox2);
    }

    @org.junit.jupiter.api.Test
    void intersectsRectangular() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        RectangularHitbox rectangularHitbox = new RectangularHitbox(0, 0, 20, 20);
        assert circleHitbox.intersects(rectangularHitbox);
        RectangularHitbox rectangularHitbox2 = new RectangularHitbox(20, 20, 20, 20);
        assert !circleHitbox.intersects(rectangularHitbox2);
    }

    @org.junit.jupiter.api.Test
    void intersectsVoid() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        VoidHitbox voidHitbox = new VoidHitbox();
        assert !circleHitbox.intersects(voidHitbox);
    }

    @org.junit.jupiter.api.Test
    void getRadius() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 10);
        assert circleHitbox.getRadius() == 10;
    }

    @org.junit.jupiter.api.Test
    void setRadius() {
        CircleHitbox circleHitbox = new CircleHitbox(0, 0, 0);
        circleHitbox.setRadius(10);
        assert circleHitbox.getRadius() == 10;
    }
}