package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents a collection of geometries
 * in 3D Cartesian coordinate system
 *
 */
public class Geometries extends Intersectable {

    private final LinkedList<Intersectable> _geometries = new LinkedList<Intersectable>();

    /**
     *   default constructor receiving a list of geometries
     */
    public Geometries() {
    }

    /**
     * constructor receiving a list of geometries
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * find intersections of a ray with the geometries
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        }
    }

    /**
     * find intersections of a ray with the geometries
     * @param ray
     * @return list of intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections =  geo.findGeoIntersectionsHelper(ray) ;
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<GeoPoint>();
                }
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
