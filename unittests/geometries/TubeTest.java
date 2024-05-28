package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;
    /**
     * Test method for {@link geometries.Tube#Tube(Ray, double)}.
     */
    @Test
    public void testTubeConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Tube( new Ray(new Point(1,1,1),
                new Vector( new Double3(2,2,2))), 1), "ERROR: failed to create a tube");

        // =============== Boundary Values Tests ==================
        // TC02: test to see that the constructor does not work with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Tube( new Ray(new Point(1,1,1),
                new Vector( new Double3(2,2,2))), -1), "ERROR: tube cannot be created with a negative radius");

        // TC03: test to see that the constructor does not work with a radius of 0
        assertThrows(IllegalArgumentException.class, () -> new Tube( new Ray(new Point(1,1,1),
                new Vector( new Double3(2,2,2))), 0), "ERROR: tube cannot be created with a radius of 0");

    }


    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testTubeGetNormal() {
        Tube tube = new Tube(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)), 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the getNormal function works correctly
        Vector normal1 = tube.getNormal(new Point(2, 1, 0));
        assertTrue( normal1.equals(new Vector(0, 1, 0)) || normal1.equals(new Vector(0, -1, 0)), "getNormal() returned an incorrect result: " + normal1);

        // TC02: test to see that the getNormal function works with a point on the head of the ray
        Vector normal2 = tube.getNormal(new Point(1, 1, 0));
        assertTrue( normal2.equals(new Vector(0, 1, 0)) || normal2.equals(new Vector(0, -1, 0)), "getNormal() returned an incorrect result: " + normal2);

        // TC03: test to see that the getNormal length is 1
        assertEquals(1, normal1.length(), DELTA, "getNormal() returned an incorrect result: " + normal1);

    }
}