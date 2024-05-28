package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface is the basic interface for all geometric objects
 */

public interface Geometry extends Intersectable {
    /**
     * @param point
     * @return the normal to the geometry at the point
     */
    public Vector getNormal(Point point);

}
