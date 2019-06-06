package ics4u.connect4;

public class Rotation3D {
    double x_sin;
    double x_cos;
    double y_sin;
    double y_cos;
    double z_sin;
    double z_cos;

    public Rotation3D(double x_sin, double x_cos, double y_sin, double y_cos, double z_sin, double z_cos) {
        this.x_sin = x_sin;
        this.x_cos = x_cos;
        this.y_sin = y_sin;
        this.y_cos = y_cos;
        this.z_sin = z_sin;
        this.z_cos = z_cos;
    }

    public Rotation3D rotateBy(Rotation3D other) {
        return null;
    }
}
