package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
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

    @Test
    public void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray's line is inside the triangle


    }



}