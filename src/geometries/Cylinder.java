package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
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
        if(_height <= 0)
            throw new IllegalArgumentException("height must be positive");
        height = _height;
    }


    @Override
    public Vector getNormal(Point point) {
        if (point.equals(axis.getHead())) // if the point is on the head
            return axis.getDirection().normalize();
        else if (point.equals(axis.getHead().add(axis.getDirection().scale(height)))) // if the point is in the center of the other base
            return axis.getDirection().normalize();
        if(Util.isZero(point.subtract(axis.getHead()).dotProduct(axis.getDirection()))) // if the point is on the base
            return axis.getDirection().normalize();
        else if (Util.isZero((point.subtract(axis.getHead().add(axis.getDirection().scale(height)))).dotProduct(axis.getDirection()))) // if the point is on the other base
            return axis.getDirection().normalize();

        else // if the point is on the side
            return super.getNormal(point);
    }
}
