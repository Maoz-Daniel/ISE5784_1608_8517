package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import geometries.Polygon;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * @author Dan
 */
public class RenderTests {
    /** Scene of the tests */
    private final Scene          scene  = new Scene("Test scene");
    /** Camera builder of the tests */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(Point.ZERO).setDirection(new Vector (0,0,-1), new Vector (0,1,0))
            .setVpDistance(100)
            .setVpSize(500, 500);

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void renderTwoColorTest() {
        scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                        new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE))
                .setBackground(new Color(75, 127, 90));

        // right
        camera
                .setImageWriter(new ImageWriter("base render test", 1000, 1000))
                .build()
                .renderImage()
                .printGrid(100, new Color(YELLOW))
                .writeToImage();
    }

    // For stage 6 - please disregard in stage 5
    /**
     * Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid
     */
    @Test
    public void renderMultiColorTest() {
        scene.geometries.add( // center
                new Sphere(new Point(0, 0, -100), 50),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2))); //

        camera
                .setImageWriter(new ImageWriter("color render test", 1000, 1000))
                .build()
                .renderImage()
                .printGrid(100, new Color(WHITE))
                .writeToImage();
    }

    /**
     * Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid
     */
    private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);
    private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);


    /**
     * Produce a scene with basic 3D model - with depth of field
     */
    @Test
    public void DepthOfField() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);

        // Add geometries to the scene
        sceneMine.geometries.add(
                new Sphere(new Point(-200, 200, 0), 100)
                        .setEmission(new Color(0, 0, 225))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(100, 50, 0), 100)
                        .setEmission(new Color(0, 225, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(400, -100, 0), 100)
                        .setEmission(new Color(225, 0, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(800, -300, 0), 100)
                        .setEmission(new Color(150, 0, 150))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(1200, -500, 0), 100)
                        .setEmission(new Color(150, 150, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100))

        );


        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 225, 225), new Vector(1, -1, -1)));



        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-1000, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600).setFocalLength(2200).setAperture(21).setPrintInterval(0.1).setThreadsCount(4); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("DepthTest", 500, 500))
                .build()
                .renderImage(33)
                .writeToImage();
    }

    /**
     * Produce a scene with basic 3D mode - without depth of field
     */
    @Test
    public void DepthOfFieldWithout() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);

        // Add geometries to the scene
        sceneMine.geometries.add(
                new Sphere(new Point(-200, 200, 0), 100)
                        .setEmission(new Color(0, 0, 225))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(100, 50, 0), 100)
                        .setEmission(new Color(0, 225, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(400, -100, 0), 100)
                        .setEmission(new Color(225, 0, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(800, -300, 0), 100)
                        .setEmission(new Color(150, 0, 150))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(1200, -500, 0), 100)
                        .setEmission(new Color(150, 150, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100))

        );


        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 225, 225), new Vector(1, -1, -1)));



        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-1000, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("DepthTestWithout", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a scene with basic 3D model - with depth of field
     */
    @Test
    public void DepthOfField2() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);

        int[][] xy = {{0, 70}, {700, -200}, {-300, -200}, {-350, 350}, {1400, 500}};
        // Add geometries to the scene
        for (int[] ints : xy) {
            int x = ints[0];
            int y = ints[1];
            sceneMine.geometries.add(
                    new Sphere(new Point(x, y, -200), 150)
                            .setEmission(new Color(100, 100, 100))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                    new Sphere(new Point(x, y, 25), 100)
                            .setEmission(new Color(100, 100, 100))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                    new Sphere(new Point(x, y, 185), 65)
                            .setEmission(new Color(100, 100, 100))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                    new Sphere(new Point(-55 + x, 25 + y, 190), 10)
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                    new Sphere(new Point(-55 + x, -25 + y, 190), 10)
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                    new Sphere(new Point(-60 + x, y, 170), 10)
                            .setEmission(new Color(255, 140, 0))
                            .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100))
            );

        }



        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 225, 225), new Vector(1, -1, -1)));



        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-1000, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600).setFocalLength(1000).setAperture(10); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("DepthTest2", 500, 500))
                .build()
                .renderImage(17)
                .writeToImage();
    }


    /**
     * Produce a scene with basic 3D model - with anti-aliasing
     */
    @Test
    public void AntiAliasingTestWithout() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);


        // Add geometries to the scene
        sceneMine.geometries.add(
               new Plane(new Point(0, 0, -20), new Vector(0, 0, 1))
                        .setEmission(new Color(225, 50, 50))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100).setKR(0.3)),
                new Triangle(new Point(0, -100, 0), new Point(300, 0, 500),
                        new Point(100, 300, 200))
                        .setEmission(new Color(225, 150, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50)),
                new Triangle(new Point(-30, -100, 0), new Point(150, 20, 300),
                        new Point(30, 400, 140))
                        .setEmission(new Color(0, 150, 220))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50)),
                new Triangle(new Point(-60, -100, 0), new Point(0, 100, 200),
                        new Point(-100, -100, 200))
                        .setEmission(new Color(10, 225, 100))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50))

        );

        sceneMine.lights.add(
                new DirectionalLight(new Color(0, 0, 225), new Vector(-2, 0, -1)));
        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 0, 0), new Vector(5,-1, -2)));
        sceneMine.lights.add(
                new DirectionalLight(new Color(0, 225, 0), new Vector(-1, 3, -1)));




        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-700, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("Anti-Aliasing Test Without", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
    /**
     * Produce a scene with basic 3D model - without anti-aliasing
     */
    @Test
    public void AntiAliasingTest() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(Color.BLACK);


        // Add geometries to the scene
        sceneMine.geometries.add(
                new Plane(new Point(0, 0, -20), new Vector(0, 0, 1))
                        .setEmission(new Color(225, 50, 50))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100).setKR(0.3)),
                new Triangle(new Point(0, -100, 0), new Point(300, 0, 500),
                        new Point(100, 300, 200))
                        .setEmission(new Color(225, 150, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50)),
                new Triangle(new Point(-30, -100, 0), new Point(150, 20, 300),
                        new Point(30, 400, 140))
                        .setEmission(new Color(0, 150, 220))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50)),
                new Triangle(new Point(-60, -100, 0), new Point(0, 100, 200),
                        new Point(-100, -100, 200))
                        .setEmission(new Color(10, 225, 100))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(50))

        );

        sceneMine.lights.add(
                new DirectionalLight(new Color(0, 0, 225), new Vector(-2, 0, -1)));
        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 0, 0), new Vector(5,-1, -2)));
        sceneMine.lights.add(
                new DirectionalLight(new Color(0, 225, 0), new Vector(-1, 3, -1)));




        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-700, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("Anti-Aliasing Test", 500, 500))
                .build()
                .renderImage(17)
                .writeToImage();
    }

}
