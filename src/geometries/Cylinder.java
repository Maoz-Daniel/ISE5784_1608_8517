package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
   final private double height;

    public Cylinder(Ray _axis, double _radius, double _height) {
        super(_axis, _radius);
        height = _height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
