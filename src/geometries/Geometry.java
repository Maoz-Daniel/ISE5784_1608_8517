package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface is the basic interface for all geometric objects
 */

public interface Geometry {
    public Vector getNormal(Point point);

}
