package ics4u.connect4;

public class Translation3D {
    double x;
    double y;
    double z;

    public Translation3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Translation3D inverse() {
        return new Translation3D(-x, -y, -z);
    }

    public Translation3D rotate(Rotation3D r) {
        return new Translation3D(
                x * (r.z_cos * r.y_cos)
                        + y * (r.z_cos * r.y_sin * r.x_sin - r.z_sin * r.x_cos)
                        + z * (r.z_cos * r.y_sin * r.x_sin + r.z_sin * r.x_sin),
                x * (r.z_sin * r.y_cos)
                        + y * (r.z_sin * r.y_sin * r.x_sin + r.z_cos * r.x_cos)
                        + z * (r.z_sin * r.y_sin * r.x_sin - r.z_cos * r.x_sin),
                x * (-r.y_sin)
                        + y * (r.y_cos * r.x_sin)
                        + z * (r.y_cos * r.x_cos)
        );
    }
}
