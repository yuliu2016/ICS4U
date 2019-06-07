package ics4u.connect4.threedim;

public class Translation3D {
    private double x;
    private double y;
    private double z;

    public Translation3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public Translation3D inverse() {
        return new Translation3D(-x, -y, -z);
    }

    public Translation3D cross(Translation3D t) {
        return new Translation3D(
                y * t.z - z * t.y,
                z * t.x - x * t.z,
                x * t.y - y * t.x
        );
    }

    public Translation3D add(Translation3D t) {
        return new Translation3D(x + t.x, y + t.y, z + t.z);
    }

    public Translation3D rotateX(Rotation2D r) {
        // right hand y = cos; z = sin
        return new Translation3D(x, y * r.cos - z * r.sin, z * r.cos + y * r.sin);
    }

    public Translation3D rotateY(Rotation2D r) {
        return new Translation3D(x * r.cos + z * r.sin, y, z * r.cos - x * r.sin);
    }

    public Translation3D rotateZ(Rotation2D r) {
        // right hand x = cos; y = sin
        return new Translation3D(x * r.cos - y * r.sin, y * r.cos + x * r.sin, z);
    }
}
