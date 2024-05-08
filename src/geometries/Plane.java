package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane {
   final private Point q;
   final private Vector normal;

    public Plane(Point p1, Point p2, Point p3) {
         normal = (p2.subtract(p1)).crossProduct(p3.subtract(p1)).normalize();
        q=p1;
    }

    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    public Vector getNormal(Point point) {

            return normal;
    }

    public Vector getNormal() {
        return normal;

    }
}
