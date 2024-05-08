package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate
 * system
 */
public class Cylinder extends Tube{

    /**
     * Cylinder height
     */
   final private double height;

    /**
     * Cylinder constructor receiving a radius and height
     * @param _radius
     * @param _height
     */
    public Cylinder(Ray _axis, double _radius, double _height) {
        super(_axis, _radius);
        height = _height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
