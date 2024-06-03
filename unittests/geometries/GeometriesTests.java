package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



public class GeometriesTests {
    Sphere sphere = new Sphere(new Point(0, -2, 0), 3);
    Triangle triangle = new Triangle(new Point(4, 0, 0), new Point(-2, 0, 0), new Point(0, 0, 4));
    Plane plane = new Plane(new Point(1, 1, 0), new Vector(1, 1, 1));
    Polygon polygon = new Polygon(new Point(0, 4, 0), new Point(6, 0, 0),
            new Point(4, -4, 0), new Point(0, -5, 0),new Point(-4, -3, 0), new Point(-5, 0, 0));

    Geometries geometries = new Geometries(sphere, triangle, plane, polygon);

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {

        /// ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the findIntersections function works correctly when ray intersects some of the geometries
        Ray ray = new Ray(new Point(0, -2, -7), new Vector(0, 0, 1));
        List<Point> result = geometries.findIntersections(ray);
        assertEquals(4, result.size(), "findIntersections() did not find the correct number of intersections");

        //// =============== Boundary Values Tests ==================

        // TC10: test to see that the findIntersections function works correctly when the ray does not intersect any of the geometries
        ray = new Ray(new Point(0, 5, 0), new Vector(0, 0, 1));
        result = geometries.findIntersections(ray);
        assertNull(result, "findIntersections() did not find the correct number of intersections");

        // TC11: test to see that the findIntersections function works correctly when the ray intersects one of the geometries
        ray = new Ray(new Point(5, 0, -1), new Vector(0, 0, 1));
        result = geometries.findIntersections(ray);
        assertEquals(1, result.size(), "findIntersections() did not find the correct number of intersections");

        // TC12: test to see that the findIntersections function works correctly when the ray intersects all the geometries
        ray = new Ray(new Point(0, -6, -4), new Vector(0, 1, 1));
        result = geometries.findIntersections(ray);
        assertEquals(5, result.size(), "findIntersections() did not find the correct number of intersections");

        // TC13: test to see that the findIntersections function works correctly when there are no geometries
        Geometries geometriesEmpty = new Geometries();
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 0, 1));
        result = geometriesEmpty.findIntersections(ray);
        assertNull(result, "findIntersections() did not find the correct number of intersections");


    }




}
