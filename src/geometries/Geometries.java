package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents a collection of geometries
 * in 3D Cartesian coordinate system
 *
 */
public class Geometries extends Intersectable {

    /**
     * list of geometries
     */
    private final LinkedList<Intersectable> geometries = new LinkedList<Intersectable>();

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
    public Geometries(List<Intersectable> geometries) {
        this.geometries.addAll(geometries);
    }

    /**
     * find intersections of a ray with the geometries
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            this.geometries.add(geo);
        }
        calculateBoundingBox();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (boundingBox != null && !boundingBox.hasIntersection(ray)) {
            return null;
        }
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();

        for (Intersectable geo : geometries) {
            List<GeoPoint> tempIntersections = geo.findGeoIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<GeoPoint>();
                }
                for (GeoPoint geoPoint : tempIntersections) {
                    if (geoPoint.point.distance(ray.getHead()) <= maxDistance) {
                        intersections.add(geoPoint);
                    }
                }
            }

        }
        return intersections;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (boundingBox != null && !boundingBox.hasIntersection(ray)) {
            return null;
        }
        List<GeoPoint> intersections = null;

        for (Intersectable geo : geometries) {
            List<GeoPoint> tempIntersections = geo.findGeoIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<GeoPoint>();
                }
                for (GeoPoint geoPoint : tempIntersections) {
                    intersections.add(geoPoint);
                }
            }

        }
        return intersections;
    }



    @Override
    protected void calculateBoundingBox() {
        if (geometries.isEmpty()) {
            boundingBox = null;
            return;
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Intersectable geo : geometries) {
            if (geo.boundingBox == null) {
                geo.calculateBoundingBox();
            }
            if (geo.boundingBox != null) {
                minX = Math.min(minX, geo.boundingBox.getMin().getX());
                minY = Math.min(minY, geo.boundingBox.getMin().getY());
                minZ = Math.min(minZ, geo.boundingBox.getMin().getZ());
                maxX = Math.max(maxX, geo.boundingBox.getMax().getX());
                maxY = Math.max(maxY, geo.boundingBox.getMax().getY());
                maxZ = Math.max(maxZ, geo.boundingBox.getMax().getZ());
            }
        }

        boundingBox = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }


    /**
     * add geometries to the collection
     * @param geometries
     */
    public void add(List<Intersectable> geometries) {
        // this.geometries.addAll(geometries);
        for (Intersectable geo : geometries) {
            this.geometries.add(geo);
        }
        calculateBoundingBox();
    }
}
