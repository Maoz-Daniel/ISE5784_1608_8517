package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {


    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
   public void add() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(1, 1, 1);
        Point p2 = p1.add(v);

        // TC01: test to see that the add function works correctly
        assertEquals(new Point(2, 3, 4), p2, "add() wrong result");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
   public void subtract() {
        Point p1 = new Point(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        Point p2 = new Point(1, 1, 1);
        Vector v = p1.subtract(p2);

        // TC01: test to see that the subtract function works correctly
        assertEquals(new Vector(0, 1, 2), v, "subtract() wrong result");

        // =============== Boundary Values Tests ==================

        // TC10: test subtracting the same point
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "subtract() for itself should throw an exception");
    }

}