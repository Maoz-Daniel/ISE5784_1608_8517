package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Sphere#Sphere(Point, double)}.
     */
    @Test
    void testSphereConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Sphere(new Point(1, 2, 3), 5), "ERROR: failed to create a sphere");

        // =============== Boundary Values Tests ==================

        // TC10: test to see that sphere cannot be created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Sphere(new Point(1, 2, 3), -5), "ERROR: sphere cannot be created with a negative radius");

        // TC11: test to see that sphere cannot be created with a radius of 0
        assertThrows(IllegalArgumentException.class, () -> new Sphere(new Point(1, 2, 3), 0), "ERROR: sphere cannot be created with a radius of 0");
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testSphereGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the getNormal function works correctly
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1);
        Vector normal = sphere.getNormal(new Point(2,0 , 0));
        assertTrue(normal.equals(new Vector(1, 0, 0)) || normal.equals(new Vector(-1, 0, 0)), "getNormal() returned an incorrect result: " + normal);

    // TC02: test to see that the getNormal function length is 1
        assertEquals(1, normal.length(), DELTA, "getNormal() returned an incorrect result: " + normal);
          }
}