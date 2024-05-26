package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Cylinder#Cylinder(Ray, double, double)}.
     */
    @Test
    void testCylinderConstructor() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Cylinder(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)), 1, 1), "ERROR: failed to create a cylinder");

        // =============== Boundary Values Tests ==================

        // TC10: test to see that cylinder cannot be created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)), -1, 1), "ERROR: cylinder cannot be created with a negative radius");

        // TC11: test to see that cylinder cannot be created with a radius of 0
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)), 0, 1), "ERROR: cylinder cannot be created with a radius of 0");

        // TC12: test to see that cylinder cannot be created with a negative height
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)), 1, -1), "ERROR: cylinder cannot be created with a negative height");

        // TC13: test to see that cylinder cannot be created with a height of 0
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)), 1, 0), "ERROR: cylinder cannot be created with a height of 0");
    }

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testCylinderGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 2, 2);

        // TC01: test to see that the getNormal function works correctly on side
        Vector normalSide = cylinder.getNormal(new Point(1, 2, 0));
        assertTrue(normalSide.equals(new Vector(0, -1, 0)) || normalSide.equals(new Vector(0, 1, 0)), "getNormal() returned an incorrect result");

        // TC02: test to see that the getNormal function works correctly on base
        Vector normalBase1 = cylinder.getNormal(new Point(0, 0.5, 0));
      assertTrue(normalBase1.equals(new Vector(1, 0, 0)) || normalBase1.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");

      // TC03: test to see that the getNormal function works correctly on other base
        Vector normalBase2 = cylinder.getNormal(new Point(2, 0.5, 0));
        assertTrue(normalBase2.equals(new Vector(1, 0, 0)) || normalBase2.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");

        // TC04: test to see that the getNormal function length is 1
        assertEquals(normalBase1.length() , 1, DELTA , "getNormal() returned a vector with a length different than 1");

     // =============== Boundary Values Tests ==================

        // TC10: test to see that the getNormal function works correctly on head
        Vector normalHead = cylinder.getNormal(new Point(0, 0, 0));
        assertTrue(normalHead.equals(new Vector(1, 0, 0)) || normalHead.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");

        // TC11: test to see that the getNormal function works correctly on center of other base
        Vector normalCenter = cylinder.getNormal(new Point(2, 0, 0));
        assertTrue(normalCenter.equals(new Vector(1, 0, 0)) || normalCenter.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");

        // TC12: test to see that the getNormal function works correctly on the axis
        Vector normalAxis1 = cylinder.getNormal(new Point(0, 2, 0));
        assertTrue(normalAxis1.equals(new Vector(1, 0, 0)) || normalAxis1.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");

        // TC13: test to see that the getNormal function works correctly on the other axis
        Vector normalAxis2 = cylinder.getNormal(new Point(2, 2, 0));
        assertTrue(normalAxis2.equals(new Vector(1, 0, 0)) || normalAxis2.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result");
    }
}