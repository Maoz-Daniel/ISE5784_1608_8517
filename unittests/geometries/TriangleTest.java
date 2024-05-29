package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

class TriangleTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testTriangleGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        // TC01: test to see that the getNormal function works correctly
        assertEquals(new Point(0, 0, 1), triangle.getNormal(new Point(0, 0, 0)), "getNormal() wrong result");
        // TC02: test to see that the getNormal function returns one
        assertEquals(1, triangle.getNormal(new Point(0, 0, 0)).length(), DELTA, "getNormal() wrong result");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {

      final  Triangle triangle = new Triangle(new Point(2, 0, 0), new Point(0, 2, 0), new Point(0, 0, 2));
      final Point p100=new Point(1,0,0);

        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray's line is inside the triangle (1 point)

        var result=triangle.findIntersections(new Ray(p100, new Vector(0, 0.7, 0.3)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 0.7, 0.3)), result, "Ray's line inside the triangle");

        //TC02: Ray's line is outside the triangle outside against edge (0 points)

        result=triangle.findIntersections(new Ray(p100, new Vector(0, -1, 1)));
        assertNull(result, "Ray's line outside the triangle outside against edge");

        //TC03: Ray's line is outside the triangle outside against vertex (0 points)

        result=triangle.findIntersections(new Ray(p100, new Vector(3, -1, -1)));
        assertNull(result, "Ray's line outside the triangle outside against vertex");

        // ================= Boundary Values Tests ==================

        //TC11: Ray's line is on edge (0 points)
        result=triangle.findIntersections(new Ray(p100, new Vector(0, 0, 1)));
        assertNull(result, "Ray's line on edge");

        //TC12: Ray's line is in vertex (0 points)
        result=triangle.findIntersections(new Ray(p100, new Vector(1, 0, 0)));
        assertNull(result, "Ray's line in vertex");

        //TC13: Ray's line is on edge's continuation (0 points)
        result=triangle.findIntersections(new Ray(new Point(0,0,1), new Vector(-1, 0, 2)));
        assertNull(result, "Ray's line on edge's continuation");

    }



}