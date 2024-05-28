package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface Intersectable is the basic interface for all geometries that are intersectable by a ray.
 */
public interface Intersectable {
    /**
     * Function findGeoIntersections finds the intersection points of a ray with the geometry.
     * @param ray - the ray that intersects the geometry
     * @return a list of intersection points
     */
   public List<Point> findIntersections(Ray ray);
}
