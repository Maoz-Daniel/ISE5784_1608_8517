package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

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
        boolean isCorrectNormalWithPoint = normal1.equals(new Vector(0, 0, 1)) || normal1.equals(new Vector(0, 0, -1));
        assertTrue(isCorrectNormalWithPoint, "getNormal() returned an incorrect result: " + normal1);

        // TC02: test to see that the getNormal function works correctly

        assertEquals(new Vector(0, 0, -1) , plane2.getNormal(), "getNormal() wrong result");
        Vector normal2 = plane2.getNormal(new Point(1, 1, 0));
        boolean isCorrectNormalWithoutPoint = normal2.equals(new Vector(0, 0, 1)) || normal2.equals(new Vector(0, 0, -1));
        assertTrue(isCorrectNormalWithoutPoint, "getNormal() returned an incorrect result: " + normal2);
    }

}