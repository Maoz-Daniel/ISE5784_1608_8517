package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
   final private Point center;

    public Sphere(Point _center, double _radius) {
        super(_radius);
        center = _center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
