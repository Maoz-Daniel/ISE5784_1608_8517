package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate
 * system
 */
public class Sphere extends RadialGeometry{

    /**The center of the sphere*/
   final private Point center;

   /** constructor receiving a center and a radius*/
    public Sphere(Point _center, double _radius) {
        super(_radius);
        center = _center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
