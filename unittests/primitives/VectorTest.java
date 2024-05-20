package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
  @Test
  public void testVectorConstructor(){
             // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the constructor works correctly
      assertDoesNotThrow(()->new Vector(1,2,3),"ERROR: failed to create a vector");

        // TC02: test to see that the constructor works correctly
      Double3 d3=new Double3(1,2,3);
        assertDoesNotThrow(()->new Vector(d3),"ERROR: failed to create a vector");

        // =============== Boundary Values Tests ==================
      assertThrows(IllegalArgumentException.class,()->new Vector(0,0,0),"ERROR: zero vector cant be created");

      // TC04: test to see that the constructor works correctly
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
        // TC02: test to see that the scale function works correctly
        assertDoesNotThrow(() -> v1.scale(0), "ERROR: scale by zero should not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)} (primitives.Vector)}.
     */
    @Test
   public void testVectorDotProduct() {

        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the dotProduct function works correctly
        assertEquals(6, v1.dotProduct(v2), "dotProduct() wrong result");
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
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
   public void testVectorLengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the lengthSquared function works correctly
        assertEquals(14, v1.lengthSquared(), "lengthSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
   public void testVectorLength() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: test to see that the length function works correctly
        assertEquals(Math.sqrt(14), v1.length(), "length() wrong result");
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