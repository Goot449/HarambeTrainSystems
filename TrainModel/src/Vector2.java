/**
 * Created by Adam1 on 10/28/2016.
 */
public class Vector2 {

    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this(v.x, v.y);
    }

    public Vector2() {
        this(0, 0);
    }

    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 subtract(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public double dot(Vector2 v) {
        return this.x * v.x + this.y * v.y;
    }

    public Vector2 scalarMultiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 scalarDivide(double scalar) {
        return scalarMultiply(1 / scalar);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double angle() {
        return Math.tan(y / x);
    }


}
