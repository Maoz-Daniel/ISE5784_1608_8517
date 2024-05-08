package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
   final protected Ray axis;

    public Tube(Ray _axis, double _radius) {
        super(_radius);
        axis = _axis;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
