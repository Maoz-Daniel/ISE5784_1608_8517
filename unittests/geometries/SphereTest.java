package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane class
 */
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


    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2); // this is list of
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Point p200= new Point(2, 0, 0);
        final Point p000= new Point(0, 0, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v0m10 = new Vector(0, -1, 0);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        var result = sphere.findIntersections(new Ray(p01, v310)); //.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01)).toList();
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere
        Point insidePoint = new Point(0.5, 0.5, 0);
        result = sphere.findIntersections(new Ray(insidePoint, new Vector(1.5,-0.5,0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(p200),result, "incorrect intersection point");

        // TC04: Ray starts after the sphere (0 points)
        Point afterPoint = new Point(3, 0, 0);
        assertNull(sphere.findIntersections(new Ray(afterPoint, v110)), "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        Point onSpherePoint = new Point(1, 1, 0);
         result = sphere.findIntersections(new Ray(onSpherePoint, new Vector(1, -1, 0))).stream()
                 .sorted(Comparator.comparingDouble(p -> p.distance(p01))) // Corrected lambda syntax
                 .collect(Collectors.toList());;
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(p200),result, "incorrect intersection point");


        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(onSpherePoint, v110));
        assertNull(result, "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(p01,v100)).stream()
                .sorted(Comparator.comparingDouble(p -> p.distance(p01))) // Corrected lambda syntax
                .collect(Collectors.toList());;
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals( List.of(p000, p200),result, "incorrect intersection point");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(p200,new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(p000),result, "incorrect intersection point");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1.5,0,0),v100));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(p200),result, "incorrect intersection point");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(p100,v100));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(p200),result, "incorrect intersection point");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(p200,v100));
        assertNull(result, "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(2.5,0,0),v100));
        assertNull(result, "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point(2,1,0),v0m10));
        assertNull(result, "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(p200,v0m10));
        assertNull(result, "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point(2, -1, 0),v0m10));
        assertNull(result, "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point(3,0,0),v0m10));
        assertNull(result, "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

        // TC23: Ray's line is inside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point(0.5,0,0),new Vector(-0.5,1,0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals( List.of(new Point(0.2,0.6,0)),result, "incorrect intersection point");


    }
}