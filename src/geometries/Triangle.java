package geometries;

import primitives.Point;
import primitives.Ray;

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
    public List<Point> findIntersections(Ray ray) {
        return null;

    }

}
