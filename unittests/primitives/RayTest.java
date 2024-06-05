package primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import  primitives.Ray;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}