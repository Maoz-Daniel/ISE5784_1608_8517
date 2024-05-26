package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

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

        // TC10: test to see that the constructor does not work with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Tube( new Ray(new Point(1,1,1),
                new Vector( new Double3(2,2,2))), -1), "ERROR: tube cannot be created with a negative radius");

        // TC11: test to see that the constructor does not work with a radius of 0
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
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(1, 1, 0)), "getNormal() wrong result");

        // TC02: test to see that the getNormal function works with a point on the head of the ray
        assertEquals(new Vector(0, 1, 0), tube.getNormal(new Point(1, 1, 0)), "getNormal() wrong result");
    }
}