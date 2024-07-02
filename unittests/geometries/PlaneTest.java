package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Plane class
 */
class PlaneTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     * Test method for {@link geometries.Plane#Plane(Point, Vector)}.

     */
    @Test
    public void testPlaneConstructor() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(2, 4, 5)), "ERROR: failed to create a plane");

        // TC02: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Plane(new Point(1, 2, 3), new Vector(1, 2, 3)), "ERROR: failed to create a plane");

        // =============== Boundary Values Tests ==================

        // TC10: test to see that plane cannot be created with 3 identical points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 2, 3)), "ERROR: plane cannot be created with 3 identical points");

        // TC11: test to see that all three points must be different
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(2, 4, 6)),
                "ERROR: all three points must be different");

        // TC12: test to see that all three points cannot be on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6, 9)),
                "ERROR: all three points cannot be on the same line");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testPlaneGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        Plane plane2 = new Plane(new Point(1, 0, 0) ,  new Point(0, 1, 0),new Point(1, 1, 0));
        // TC01: test to see that the getNormal function works correctly
        Vector normal1 = plane2.getNormal(new Point(1, 1, 0));
        assertTrue(normal1.equals(new Vector(0, 0, 1)) || normal1.equals(new Vector(0, 0, -1)), "getNormal() returned an incorrect result: " + normal1);

        // TC02: test to see that the getNormal function works correctly

        assertEquals(new Vector(0, 0, -1) , plane2.getNormal(), "getNormal() wrong result");
        Vector normal2 = plane2.getNormal(new Point(1, 1, 0));
        assertTrue( normal2.equals(new Vector(0, 0, 1)) || normal2.equals(new Vector(0, 0, -1)), "getNormal() returned an incorrect result: " + normal2);

        // TC03: test to see that the getNormal function length is 1
        assertEquals(normal1.length() , 1, DELTA , "getNormal() returned a vector with a length different than 1");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 1, 1), new Vector(0, 0, 1));
        Point p001 = new Point(0, 0, 1);

        Vector v0m11 = new Vector(0, -1, 1);
        Vector v001 = new Vector(0, 0, 1);
        // ============ Equivalence Partitions Tests ==============

        // Ray's line starts outside the plane and isn't parallel to the plane and isn't orthogonal
        // to the plane and intersects the plane (1 point)
        var result = plane.findIntersections(new Ray(new Point(0, 1, 0), v0m11));
        assertEquals(1, result.size(), "Ray's line intersects the plane");
        assertEquals(List.of(p001), result, "Ray's line intersects the plane");

        // Ray's line starts outside the plane and isn't parallel to the plane and isn't orthogonal
        // to the plane and doesn't intersect the plane (0 point)
        result = plane.findIntersections(new Ray(new Point(0, 1, 2), v0m11));
        assertNull(result, "Ray's line out of plane");

        // ============ Boundary Values Tests ==============

        // **** Group: Ray's line is parallel to the plane
        // Ray's line starts on the plane and is parallel to the plane and intersects the plane (0 point)
        result = plane.findIntersections(new Ray(p001, new Vector(0, 1, 0)));
        assertNull(result, "Ray's line out of plane");

        // Ray's line starts outside the plane and is parallel to the plane and doesn't intersects the plane (0 point)
        result = plane.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 1, 0)));
        assertNull(result, "Ray's line out of plane");


        // **** Group: Ray's line is orthogonal to the plane

        // Ray's line starts before the plane and is orthogonal to the plane and intersects the plane (1 point)
        result = plane.findIntersections(new Ray(new Point(0, 0, 0), v001));
        assertEquals(1, result.size(), "Ray's line intersects the plane");
        assertEquals(List.of(p001), result, "Ray's line intersects the plane");

        // Ray's line starts on the plane and is orthogonal to the plane  (0 point)
        result = plane.findIntersections(new Ray(p001,v001));
        assertNull(result, "Ray's line out of plane");

        // Ray's line starts after the plane and is orthogonal to the plane  (0 point)
        result = plane.findIntersections(new Ray(new Point(0, 0, 2), v001));
        assertNull(result, "Ray's line out of plane");

        // **** Group: Ray's line isn't parallel to the plane and isn't orthogonal to the plane

        // Ray's line starts on the point of the plane
        result = plane.findIntersections(new Ray(new Point(1,1,1), v0m11));
        assertNull(result, "Ray's line out of plane");

        // Ray's line starts on the plane and intersects the plane (0 point)
        result = plane.findIntersections(new Ray(p001, v0m11));
        assertNull(result, "Ray's line out of plane");
    }

}