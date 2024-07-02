package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

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

        t = axis.getDirection().dotProduct(point.subtract(axis.getHead())); // t = V * (P - P0)

        if (Util.isZero(t))// P is on the head of the ray
            return point.subtract(axis.getHead()).normalize();
        Point o = axis.getHead().add(axis.getDirection().scale(t));// O = P0 + tV
        return point.subtract(o).normalize();// N = P - O
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
       return null;
    }

}
