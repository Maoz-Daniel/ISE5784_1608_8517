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
import renderer.*;
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

    private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);
    private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);
    @Test
    public void beamTest() {
        Scene sceneMine = new Scene("beam Test")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(new Color(50,70,150));

        // Add geometries to the scene
        sceneMine.geometries.add(
                new Sphere(new Point(0, 0, -170), 150)
                        .setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(0, 0, 55), 100)
                        .setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(0, 0, 215), 65)
                        .setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(-55, 25, 220), 10)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(-55, -25, 220), 10)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(-60, 0, 200), 10)
                        .setEmission(new Color(255, 140, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Plane(new Point(0, 0, -320), new Vector(0, 0, 1))
                        .setEmission(new Color(225, 50, 50))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
//                new Plane(new Point(3500, 0, 0), new Vector(1, 0, 0))
//                        .setEmission(new Color(50, 225, 100))
//                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)),
                new Sphere(new Point(2000, -700, 900), 150)
                        .setEmission(new Color(225, 225, 0))
                        .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100).setKT(0.3)),
                new Polygon(new Point(1500, 100, -320), new Point(1400, -500, -320),
                        new Point(1400, -500, 400), new Point(1500, 100, 400))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKR(0.8)),
                new Sphere(new Point(0, -250, 200), 80).setEmission(new Color(0, 0, 30)).
                        setMaterial(new Material().setKT(0.8).setNShininess(100).setKS(KS3).setKD(KD3))
        );


        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 225, 225), new Vector(1, -1, -1)));
        sceneMine.lights.add(
                new SpotLight(new Color(0, 0, 225),new Point(0, -250, 200), new Vector(-0.5, -1, -1)));


        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-1000, 0, 0))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600).setAperture(15).setFocalLength(0); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("BeamTest", 500, 500))
                .build()
                .renderImage(17)
                .writeToImage();
    }

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
                .setVpSize(500, 500).setVpDistance(600).setFocalLength(1300).setAperture(17); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("DepthTest", 500, 500))
                .build()
                .renderImage(33)
                .writeToImage();
    }

}
