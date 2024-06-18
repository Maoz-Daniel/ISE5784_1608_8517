package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate
 * system
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor receiving 3 points
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        var intersections = plane.findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }

        Point P0 = ray.getHead();
        Point P1 = vertices.get(0);
        Point P2 = vertices.get(1);
        Point P3 = vertices.get(2);
        Vector v = ray.getDirection();

        if(P0.equals(P1) || P0.equals(P2) || P0.equals(P3) ) { // if the ray starts at one of the vertices
            return null;
        }



        Vector v1 = P1.subtract(P0);
        Vector v2 = P2.subtract(P0);
        Vector v3 = P3.subtract(P0);

        Vector N1 = v1.crossProduct(v2).normalize();
        Vector N2 = v2.crossProduct(v3).normalize();
        Vector N3 = v3.crossProduct(v1).normalize();

        double s1 = v.dotProduct(N1);
        double s2 = v.dotProduct(N2);
        double s3 = v.dotProduct(N3);

        if(Util.isZero(s1) || Util.isZero(s2) || Util.isZero(s3)) {
            return null;
        }
        if((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)){
            return intersections;
        }
        return null;
    }
}
