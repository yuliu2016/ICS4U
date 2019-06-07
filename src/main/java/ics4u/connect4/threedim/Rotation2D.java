package ics4u.connect4.threedim;

public class Rotation2D {
    double cos;
    double sin;

    public Rotation2D(double cos, double sin) {
        this.cos = cos;
        this.sin = sin;
    }

    public Rotation2D(double radians) {
        this.cos = Math.cos(radians);
        this.sin = Math.sin(radians);
    }

    public double cos() {
        return cos;
    }

    public double sin() {
        return sin;
    }

    public Rotation2D rotateBy(Rotation2D that) {
        return new Rotation2D(cos * that.cos - sin * that.sin, sin * that.cos + cos * that.sin);
    }

    public Rotation2D inverse() {
        return new Rotation2D(cos, -sin);
    }

    public double toRadians() {
        return Math.atan2(sin, cos);
    }
}
