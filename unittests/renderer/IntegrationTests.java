package renderer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import geometries.Geometry;
import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
import geometries.Sphere;
import geometries.Plane;






import org.junit.jupiter.api.Test;

public class IntegrationTests {

    public int counterPixel(Geometry geometry, Camera camera) {
        double width = camera.getWidth();
        double height = camera.getHeight();
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


    @Test
   public void testCameraSphere() {
        Camera camera = new Camera();
        camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirections(new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVpDistance(1)
                .setVpSize(3, 3).build();

        Sphere sphere = new Sphere(new Point (0,0,-3),1);
        assertEquals(2, counterPixel(sphere, camera), "ERROR: wrong number of intersections");
    }

    @Test
    public void testCameraPlane() {
        Camera camera = new Camera();
        camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirections(new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVpDistance(1)
                .setVpSize(3, 3).build();

        Plane plane = new Plane(new Point(1,1,-5), new Vector(0,1,-5));
        assertEquals(9, counterPixel(plane, camera), "ERROR: wrong number of intersections");
    }



}
