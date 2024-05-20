package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {


    /**
     * Test method for {@link geometries.Sphere#Sphere(Point, double)}.
     */
    @Test
    void testSphereConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Sphere(new Point(1, 2, 3), 5), "ERROR: failed to create a sphere");

        // =============== Boundary Values Tests ==================

        // TC02: test to see that sphere cannot be created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Sphere(new Point(1, 2, 3), -5), "ERROR: sphere cannot be created with a negative radius");

        // TC03: test to see that sphere cannot be created with a radius of 0
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
        assertEquals(new Vector(1, 0, 0), sphere.getNormal(new Point(2, 0, 0)), "getNormal() wrong result");
    }
}