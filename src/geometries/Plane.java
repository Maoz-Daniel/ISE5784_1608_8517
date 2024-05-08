package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a plane in 3D Cartesian coordinate
 * system
 */
public class Plane {
    /**Point on the plane*/
   final private Point q;
   /**Normal to the plane*/
   final private Vector normal;

    /**
     * Plane constructor receiving 3 points
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
         normal = (p2.subtract(p1)).crossProduct(p3.subtract(p1)).normalize();
        q=p1;
    }

    /**
     * Plane constructor receiving a point and a normal
     * @param q
     * @param normal
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /** getter for the point on the plane* @return normal*/
    public Vector getNormal(Point point) {

            return normal;
    }

    /** getter for the normal to the plane* @return normal*/
    public Vector getNormal() {
        return normal;

    }
}
