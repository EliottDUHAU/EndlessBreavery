package entities.hitboxes;

import game.Camera;
import utils.Pair;

import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

/**
 * Class for a polygon hitbox
 */
public class PolygoneHitbox implements Hitbox {
    /**
     * The polygon shape object of the hitbox
     */
    private Polygon polygon;
    /**
     * The points list of the polygon
     */
    private List<Pair<Integer, Integer>> points = new ArrayList<>();
    /**
     * Create a new polygon hitbox
     * @param points the points of the polygon
     */
    public PolygoneHitbox(Pair<Integer, Integer>... points) {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xPoints[i] = points[i].getFirst();
            yPoints[i] = points[i].getSecond();
            this.points.add(points[i]);
        }
        polygon = new Polygon(xPoints, yPoints, points.length);
    }
    /**
     * Get the shape of the hitbox
     * @return the shape of the hitbox
     */
    @Override
    public Shape getShape() {
        return polygon;
    }
    /**
     * Get the x position of the hitbox
     * @return the x position of the hitbox
     */
    @Override
    public int getXPosition() {
        return (int) points.getFirst().getFirst();
    }
    /**
     * Get the y position of the hitbox
     * @return the y position of the hitbox
     */
    @Override
    public int getYPosition() {
        return (int) points.getFirst().getSecond();
    }
    /**
     * Set the x position of the hitbox
     * @param xPosition x position
     */
    @Override
    public void setXPosition(int xPosition) {
        int xOffset = xPosition - (int) points.getFirst().getFirst();
        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            Pair<Integer, Integer> point = points.get(i);
            point.setFirst((int) point.getFirst() + xOffset);
            xPoints[i] = point.getFirst();
            yPoints[i] = point.getSecond();
        }
        polygon = new Polygon(xPoints, yPoints, points.size());
    }
    /**
     * Set the y position of the hitbox
     * @param yPosition y position
     */
    @Override
    public void setYPosition(int yPosition) {
        int yOffset = yPosition - (int) points.getFirst().getSecond();
        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            Pair<Integer, Integer> point = points.get(i);
            point.setSecond((int) point.getSecond() + yOffset);
            xPoints[i] = point.getFirst();
            yPoints[i] = point.getSecond();
        }
        polygon = new Polygon(xPoints, yPoints, points.size());
    }
    /**
     * Check if the hitbox intersects with another hitbox
     * @param hitbox the hitbox to check intersection with
     * @return whether the hitboxes intersect
     */
    @Override
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof LineHitbox) {
            for (int i = 0; i < polygon.npoints; i ++) {
                int nextI = (i + 1) % polygon.npoints;
                Line2D edge = new Line2D.Double(polygon.xpoints[i], polygon.ypoints[i], polygon.xpoints[nextI], polygon.ypoints[nextI]);

                if (((Line2D) hitbox.getShape()).intersectsLine(edge)) {
                    return true;
                }
            }
            return false;
        }
        if (hitbox instanceof PolygoneHitbox polygoneHitbox) {
            for (int i = 0; i < polygon.npoints; i ++) {
                int nextI = (i + 1) % polygon.npoints;
                Line2D edge = new Line2D.Double(polygon.xpoints[i], polygon.ypoints[i], polygon.xpoints[nextI], polygon.ypoints[nextI]);

                for (int j = 0; j < polygoneHitbox.polygon.npoints; j ++) {
                    int nextJ = (j + 1) % polygoneHitbox.polygon.npoints;
                    Line2D edge2 = new Line2D.Double(polygoneHitbox.polygon.xpoints[j], polygoneHitbox.polygon.ypoints[j],
                            polygoneHitbox.polygon.xpoints[nextJ], polygoneHitbox.polygon.ypoints[nextJ]);

                    if (edge.intersectsLine(edge2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (hitbox instanceof CircleHitbox) {
            CircleHitbox circleHitbox = (CircleHitbox) hitbox;
            for (int i = 0; i < polygon.npoints; i ++) {
                int nextI = (i + 1) % polygon.npoints;
                LineHitbox edge = new LineHitbox(polygon.xpoints[i], polygon.ypoints[i],
                        polygon.xpoints[nextI] - polygon.xpoints[i], polygon.ypoints[nextI] - polygon.ypoints[i]);

                if (edge.intersects(circleHitbox)) {
                    return true;
                }
            }
            return false;
        }

        Area area = new Area(polygon);
        area.intersect(new Area(hitbox.getShape()));
        return !area.isEmpty();
    }
    /**
     * Draw the hitbox for debugging purposes
     * @param g the graphics object to draw with
     * @param camera the camera to draw relative to
     */
    @Override
    public void debugDraw(Graphics g, Camera camera) {
        for (int i = 0; i < polygon.npoints; i ++) {
            int nextI = (i + 1) % polygon.npoints;
            g.drawLine(polygon.xpoints[i] - camera.getX(), polygon.ypoints[i] - camera.getY(),
                    polygon.xpoints[nextI] - camera.getX(), polygon.ypoints[nextI] - camera.getY());
        }
    }

    /**
     * Check if the hitbox contains a point
     * @param p the point to check
     * @return whether the hitbox contains the point
     */
    public boolean containsPoint(Point2D p) {
        int n = polygon.npoints;
        if (n < 3) return false;

        int sign = collinear(new Point2D.Double(polygon.xpoints[n - 1], polygon.ypoints[n - 1]),
                new Point2D.Double(polygon.xpoints[0], polygon.ypoints[0]), p);
        if (sign == 0) return true;

        for (int i = 1; i < n; i++) {
            int newSign = collinear(new Point2D.Double(polygon.xpoints[i - 1], polygon.ypoints[i - 1]),
                    new Point2D.Double(polygon.xpoints[i], polygon.ypoints[i]), p);
            if (newSign == 0) return true;
            if (newSign != sign) return false;
        }

        return true;
    }

    /**
     * Check if three points are collinear
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return 0 if the points are collinear, -1 if c is to the right of the line, 1 if c is to the left of the line
     */
    private static int collinear(Point2D a, Point2D b, Point2D c) {
        double ax = a.getX(), ay = a.getY();
        double bx = b.getX(), by = b.getY();
        double cx = c.getX(), cy = c.getY();
        double area = (bx - ax) * (cy - ay) - (by - ay) * (cx - ax);
        return (int) signum(area);
    }
    /**
     * Get the string representation of the hitbox
     * @return the string representation of the hitbox
     */
    public String toString() {
        return "PolygoneHitbox with points: " + points;
    }
}
