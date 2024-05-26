package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    /** Delta value for accuracy when comparing the numbers of type 'double' in assertEquals */
    private final double DELTA = 0.000001;

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    @Test
  public void testVectorConstructor(){

             // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the constructor works correctly
      assertDoesNotThrow(()->new Vector(1,2,3),"ERROR: failed to create a vector");

        // TC02: test to see that the second constructor works correctly
      Double3 d3=new Double3(1,2,3);
        assertDoesNotThrow(()->new Vector(d3),"ERROR: failed to create a vector");

        // =============== Boundary Values Tests ==================

      // TC03: test to see that the first constructor don't create a zero vector
      assertThrows(IllegalArgumentException.class,()->new Vector(0,0,0),"ERROR: zero vector cant be created");

      // TC04: test to see that the second constructor don't create a zero vector
      Double3 d0=new Double3(0,0,0);
        assertThrows(IllegalArgumentException.class,()->new Vector(d0),"ERROR: zero vector cant be created");

  }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    public void testVectorAdd() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the add function works correctly
        assertEquals(new Vector(2, 3, 4), v1.add(v2), "add() wrong result");

        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(-1, -2, -3);
        // TC02: test to see that the add function works correctly
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3),
                "add() cannot add with opposite vector");


    }

    @Test
    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    public void testVectorSubtract() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the subtract function works correctly
        assertEquals(new Vector(0, 1, 2), v1.subtract(v2), "subtract() wrong result");

        // =============== Boundary Values Tests ==================

        // TC02: test to see that the subtract vector from itself is zero vector
        assertThrows(IllegalArgumentException.class, ()-> v1.subtract(v1), " cannot subtract() vector by itself ");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)} (primitives.Vector)}.
     */
    @Test
   public void testVectorScale() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the scale function works correctly
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "scale() wrong result");

        // =============== Boundary Values Tests ==================

        // TC02: test to see that the scale by zero should throw an exception
        assertThrows(IllegalArgumentException.class,() -> v1.scale(0), "scale() by 0 should throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)} (primitives.Vector)}.
     */
    @Test
   public void testVectorDotProduct() {

        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the dotProduct function works correctly with positive values
        assertEquals(6, v1.dotProduct(v2), DELTA, "dotProduct() wrong result");

        //Tc02: test to see that the dotProduct function works correctly with negative values
        Vector v3 = new Vector(-1, -1, -1);
        assertEquals(-6, v1.dotProduct(v3),DELTA, "dotProduct() wrong result");

        // =============== Boundary Values Tests ==================

        // TC03: test to see that  vector product with the unit vector work correctly
        Vector v4 = new Vector(1, 0, 0);
        assertEquals(1, v1.dotProduct(v4), DELTA, "dotProduct() wrong result");

        // TC04: test to see that  vector product with 90 degrees vector work correctly
        Vector v5 = new Vector(2, -3, 0);
        assertEquals(-4, v1.dotProduct(v5), DELTA, "dotProduct() wrong result");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)} (primitives.Vector)}.
     */
    @Test
   public void testVectorCrossProduct() {
    Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the crossProduct function works correctly
        assertEquals(new Vector(-1, 2, -1), v1.crossProduct(v2), "crossProduct() wrong result");

        // =============== Boundary Values Tests ==================

        // TC02: test to see that vector with the same direction should throw an exception
        Vector v3 = new Vector(2, 4, 6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors should throw an exception");

        // TC03: test to see that vector with the opposite direction should throw an exception
        Vector v4 = new Vector(-1, -2, -3);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v4),
                "crossProduct() for opposite vectors should throw an exception");

        // TC04: test to see that vector with itself should throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1),
                "crossProduct() for same vector should throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
   public void testVectorLengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the lengthSquared function works correctly
        assertEquals(14, v1.lengthSquared(), DELTA, "lengthSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
   public void testVectorLength() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the length function works correctly
        assertEquals(Math.sqrt(14), v1.length(), DELTA, "length() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
   public void testVectorNormalize() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the normalize function works correctly
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)), v1.normalize(), "normalize() wrong result");
    }
}