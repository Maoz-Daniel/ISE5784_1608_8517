package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents a tube in 3D Cartesian coordinate
 * system
 */
public class Tube extends RadialGeometry {

    /** The axis of the tube*/
   final protected Ray axis;

   /** constructor receiving an axis and a radius*/
    public Tube(Ray _axis, double _radius) {
        super(_radius);
        axis = _axis;
    }

    @Override
    public Vector getNormal(Point point) {
        double t = 0;
        try {
            t = axis.getDirection().dotProduct(point.subtract(axis.getHead())); // t = V * (P - P0)
        } catch (IllegalArgumentException e) {
            return point.subtract(axis.getHead()).normalize();// P is on the head of the ray
        }
        Point o = axis.getHead().add(axis.getDirection().scale(t));// O = P0 + tV
        return point.subtract(o).normalize();// N = P - O
    }
}
