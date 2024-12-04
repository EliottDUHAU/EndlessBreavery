package utils;

public class Vector2D {
    private double x;
    private double y;

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getDoubleX() {
        return x;
    }

    public double getDoubleY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public double length() {
        return Math.sqrt((x * x) + (y * y));
    }

    public void normalize() {
        double length = length();
        if (length != 0) {
            this.x /= length;
            this.y /= length;
        }
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
