package primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import  primitives.Ray;
import java.util.List;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Testing Ray class
 */
public class RayTest {

    /**
     * Test method for {@link Ray#getPoint(double)}.
     */
    @Test
    public void testGetPoint() {

        Ray ray = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the getPoint function works correctly
        assertEquals(new Point(2, 2, 3), ray.getPoint(1) , "getPoint() wrong result");

        // TC02: test to see that the getPoint function works correctly with a negative t
        assertEquals(new Point(0, 2, 3), ray.getPoint(-1) , "getPoint() wrong result");

        //=============== Boundary Values Tests ==================
        // TC10: test to see that the getPoint function works correctly with t=0
        assertEquals(new Point(1, 2, 3), ray.getPoint(0) , "getPoint() wrong result");

    }

    /**
     * Test method for {@link Ray#findClosestPoint(List)}.
     */
    @Test
    public void testRayFindClosestPoint(){
        Ray ray = new Ray(new Point(1, 1, 0), new Vector(1, 1, 0));


        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the findClosestPoint function works correctly
        assertEquals(new Point(2, 2, 0), ray.findClosestPoint(List.of(new Point(3,3,0),new Point(2,2,0)
                        , new Point(5,5,0), new Point(7,7,0))),
                "findClosestPoint() wrong result");

        // ================== Boundary Values Tests ==================
        // TC10: test to see that the findClosestPoint function works correctly with an empty list
        assertNull(ray.findClosestPoint(List.of()), "findClosestPoint() wrong result");

        // TC11: test to see that the findClosestPoint function works correctly with the closest being first
        assertEquals(new Point(2, 2, 0), ray.findClosestPoint(List.of(new Point(2,2,0), new Point(3,3,0)
                        , new Point(5,5,0), new Point(7,7,0))),
                "findClosestPoint() wrong result");

        // TC12: test to see that the findClosestPoint function works correctly with the closest being last
        assertEquals(new Point(2, 2, 0), ray.findClosestPoint(List.of(new Point(3,3,0), new Point(5,5,0)
                        , new Point(7,7,0), new Point(2,2,0))),
                "findClosestPoint() wrong result");

    }
}