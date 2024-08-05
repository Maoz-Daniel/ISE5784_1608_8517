package renderer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;

import scene.Scene;

/**
 * Integration tests for the camera and the geometries
 */
public class IntegrationTests {

    /**
     * A function that counts the number of intersections between the camera rays and the geometry
     * @param geometry the geometry to check the intersections with
     * @param camera the camera to check the intersections with
     * @return the number of intersections
     */
    public int counterPixel(Geometry geometry, Camera camera) {
        int width = (int)camera.getWidth();
        int height = (int)camera.getHeight();
        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Ray ray = camera.constructRay(width, height, i, j);
                List <Point> intersections = geometry.findIntersections(ray);
                if (intersections != null) {
                    counter+= intersections.size();
                }
            }
        }
        return counter;

    }


    /**
     * Test method for the camera and the sphere
     */
    @Test
   public void testCameraSphere() {
        Vector Vto = new Vector(0, 0, -1);
        Vector Vup = new Vector(0, 1, 0);

        //TC01: test to see that the camera rays intersect the sphere when the sphere radius is 1
        Camera camera = new Camera.Builder().setLocation(new Point(0, 0, 0))
                .setDirection( Vto, Vup)
                .setVpDistance(1)
                .setVpSize(3, 3)
                .setImageWriter(new ImageWriter("test", 800, 500))
                .setRayTracer(new SimpleRayTracer(new Scene("Test"))).build();

        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, counterPixel(sphere, camera), "ERROR: wrong number of intersections");

        //TC02: test to see that the camera rays intersect the sphere when the sphere radius is 2.5
        camera = new Camera.Builder().setLocation(new Point(0, 0, 0.5))
                .setDirection( Vto,Vup)
                .setVpDistance(1)
                .setVpSize(3, 3)
                .setRayTracer(new SimpleRayTracer(new Scene("Test")))
                .setImageWriter(new ImageWriter("test", 800, 500)).build();

        sphere = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, counterPixel(sphere, camera), "ERROR: wrong number of intersections");

        //TC03: test to see that the camera rays intersect the sphere when the sphere radius is 2

        sphere = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, counterPixel(sphere, camera), "ERROR: wrong number of intersections");

        //TC04: test to see that the camera rays intersect the sphere when the sphere radius is 4

        sphere = new Sphere(new Point(0, 0, -0.5), 4);
        assertEquals(9, counterPixel(sphere, camera), "ERROR: wrong number of intersections");

        //TC05: test to see that the camera rays intersect the sphere when the sphere radius is 0.5
        sphere = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, counterPixel(sphere, camera), "ERROR: wrong number of intersections");


    }


    /**
     * Test method for the camera and the plane
     */
    @Test
    public void testCameraPlane() {
        Camera camera = new Camera.Builder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1)
                .setVpSize(3, 3)
                .setImageWriter(new ImageWriter("test", 1, 1))
                .setRayTracer(new SimpleRayTracer(new Scene("Test"))).build();

        //TC01: test to see that the camera rays intersect the plane when the plane is parallel to the view plane
        Plane plane = new Plane(new Point(1,1,-5), new Vector(0,0,1));
        assertEquals(9, counterPixel(plane, camera), "ERROR: wrong number of intersections");

        //TC02: test to see that the camera rays intersect the plane when the plane is not parallel to the view plane
        plane = new Plane(new Point(1,1,-5), new Vector(0,1,-5));
        assertEquals(9, counterPixel(plane, camera), "ERROR: wrong number of intersections");

        //TC03: test to see that the camera rays intersect the plane when the plane is in an angle to great for all the rays
        plane = new Plane(new Point(0,0,-5), new Vector(0,6,-5));
        assertEquals(6, counterPixel(plane, camera), "ERROR: wrong number of intersections");

    }

    /**
     * Test method for the camera and the triangle
     */
    @Test
    public void testCameraTriangle() {
        Point pm1m1m2 = new Point(-1,-1,-2);
        Point p1m1m2 = new Point(1,-1,-2);

        Camera camera = new Camera.Builder().setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1) , new Vector(0, 1, 0))
                .setVpDistance(1)
                .setVpSize(3, 3)
                .setRayTracer(new SimpleRayTracer(new Scene("Test")))
                .setImageWriter(new ImageWriter("test", 800, 500)).build();

        //TC01: test to see that the camera rays intersect the triangle when the triangle is parallel to the view plane
        Triangle triangle = new Triangle(pm1m1m2, p1m1m2, new Point(0,1,-2));
        assertEquals(1, counterPixel(triangle, camera), "ERROR: wrong number of intersections");

        //TC02: test to see that the camera rays intersect the triangle when the triangle is not parallel to the view plane
        triangle = new Triangle(pm1m1m2, p1m1m2, new Point(0,20,-2));
        assertEquals(2, counterPixel(triangle, camera), "ERROR: wrong number of intersections");

    }





}
