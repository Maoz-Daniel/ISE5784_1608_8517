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
        return null;
    }
}
