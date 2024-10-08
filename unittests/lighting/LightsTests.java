package  lighting;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * @author Dan
 */
public class LightsTests {
    /** First scene for some of tests */
    private final Scene          scene1                  = new Scene("Test scene");
    /** Second scene for some of tests */
    private final Scene          scene2                  = new Scene("Test scene")
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

    /** First camera builder for some of tests */
    private final Camera.Builder camera1                 = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene1))
            .setLocation(new Point(0, 0, 1000))
            .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
            .setVpSize(150, 150).setVpDistance(1000);
    /** Second camera builder for some of tests */
    private final Camera.Builder camera2                 = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene2))
            .setLocation(new Point(0, 0, 1000))
            .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
            .setVpSize(200, 200).setVpDistance(1000);

    /** Shininess value for most of the geometries in the tests */
    private static final int     SHININESS               = 301;
    /** Diffusion attenuation factor for some of the geometries in the tests */
    private static final double  KD                      = 0.5;
    /** Diffusion attenuation factor for some of the geometries in the tests */
    private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

    /** Specular attenuation factor for some of the geometries in the tests */
    private static final double  KS                      = 0.5;
    /** Specular attenuation factor for some of the geometries in the tests */
    private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

    /** Material for some of the geometries in the tests */
    private final Material       material                = new Material().setKD(KD3).setKS(KS3).setNShininess(SHININESS);
    /** Light color for tests with triangles */
    private final Color          trianglesLightColor     = new Color(800, 500, 250);
    /** Light color for tests with sphere */
    private final Color          sphereLightColor        = new Color(800, 500, 0);
    /** Color of the sphere */
    private final Color          sphereColor             = new Color(BLUE).reduce(2);

    /** Center of the sphere */
    private final Point          sphereCenter            = new Point(0, 0, -50);
    /** Radius of the sphere */
    private static final double  SPHERE_RADIUS           = 50d;

    /** The triangles' vertices for the tests with triangles */
    private final Point[]        vertices                =
            {
                    // the shared left-bottom:
                    new Point(-110, -110, -150),
                    // the shared right-top:
                    new Point(95, 100, -150),
                    // the right-bottom
                    new Point(110, -110, -150),
                    // the left-top
                    new Point(-75, 78, 100)
            };
    /** Position of the light in tests with sphere */
    private final Point          sphereLightPosition     = new Point(-50, -50, 25);
    /** Light direction (directional and spot) in tests with sphere */
    private final Vector         sphereLightDirection    = new Vector(1, 1, -0.5);
    /** Position of the light in tests with triangles */
    private final Point          trianglesLightPosition  = new Point(30, 10, -100);
    /** Light direction (directional and spot) in tests with triangles */
    private final Vector         trianglesLightDirection = new Vector(-2, -2, -2);

    /** The sphere in appropriate tests */
    private final Geometry       sphere                  = new Sphere(sphereCenter, SPHERE_RADIUS)
            .setEmission(sphereColor).setMaterial(new Material().setKD(KD).setKS(KS).setNShininess(SHININESS));
    /** The first triangle in appropriate tests */
    private final Geometry       triangle1               = new Triangle(vertices[0], vertices[1], vertices[2])
            .setMaterial(material);
    /** The first triangle in appropriate tests */
    private final Geometry       triangle2               = new Triangle(vertices[0], vertices[1], vertices[3])
            .setMaterial(material);

    /** Produce a picture of a sphere lighted by a directional light */
    @Test
    public void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(sphereLightColor, sphereLightDirection));

        camera1.setImageWriter(new ImageWriter("lightSphereDirectional", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of a sphere lighted by a point light */
    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(sphereLightColor, sphereLightPosition)
                .setKl(0.001).setKq(0.0002));

        camera1.setImageWriter(new ImageWriter("lightSpherePoint", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of a sphere lighted by a spotlight */
    @Test
    public void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(sphereLightColor, sphereLightPosition, sphereLightDirection)
                .setKl(0.001).setKq(0.0001));

        camera1.setImageWriter(new ImageWriter("lightSphereSpot", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of two triangles lighted by a directional light */
    @Test
    public void trianglesDirectional() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new DirectionalLight(trianglesLightColor, trianglesLightDirection));

        camera2.setImageWriter(new ImageWriter("lightTrianglesDirectional", 500, 500)) //
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of two triangles lighted by a point light */
    @Test
    public void trianglesPoint() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new PointLight(trianglesLightColor, trianglesLightPosition)
                .setKl(0.001).setKq(0.0002));

        camera2.setImageWriter(new ImageWriter("lightTrianglesPoint", 500, 500)) //
                .build() //
                .renderImage() //
                .writeToImage(); //
    }

    /** Produce a picture of two triangles lighted by a spotlight */
    @Test
    public void trianglesSpot() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                .setKl(0.001).setKq(0.0001));

        camera2.setImageWriter(new ImageWriter("lightTrianglesSpot", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of a sphere lighted by a narrow spotlight */
    @Test
    public void sphereSpotSharp() {
        scene1.geometries.add(sphere);
        scene1.lights
                .add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5))
                        .setKl(0.001).setKq(0.00004).setNarrowBeam(10));

        camera1.setImageWriter(new ImageWriter("lightSphereSpotSharp", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of two triangles lighted by a narrow spotlight */
    @Test
    public void trianglesSpotSharp() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                .setKl(0.001).setKq(0.00004).setNarrowBeam(10));

        camera2.setImageWriter(new ImageWriter("lightTrianglesSpotSharp", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }



    /** Produce a picture of a sphere lighted by a point light and a directional light */
    @Test
    public void sphereMultipleLights() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point(-40, -40, 20))
                .setKl(0.002).setKq(0.0004));
        scene1.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(1, -1, -0.5)));
        scene1.lights.add(new SpotLight(new Color(300, 150, 150), new Point(-60, -60, 30), new Vector(1, 1, -0.5))
                .setKl(0.002).setKq(0.0002));

        camera1.setImageWriter(new ImageWriter("lightSphereMultiple", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /** Produce a picture of two triangles lighted by a point light and a directional light */
    @Test
    public void trianglesMultipleLights() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new PointLight(new Color(500, 300, 0), new Point(20, 0, -80))
                .setKl(0.002).setKq(0.0004));
        scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(-1, -1, -1)));
        scene2.lights.add(new SpotLight(new Color(300, 150, 150), new Point(40, 20, -120), new Vector(-2, -2, -2))
                .setKl(0.002).setKq(0.0002));

        camera2.setImageWriter(new ImageWriter("lightTrianglesMultiple", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }


    /** many shapes picture*/
    @Test
    public void manyShapesPicture() {
        Scene sceneMine = new Scene("Many geometries scene ")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        // Add geometries to the scene
        int numSpheres = 100;
        double radius = 100; // Adjust the radius of the circle as needed
        double angleIncrement = 2 * Math.PI / numSpheres;

        for (int i = 0; i < numSpheres; i++) {
            double angle = i * angleIncrement;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            double z = 0; // All spheres in the same plane, z = 0

            sceneMine.geometries.add(new Sphere(new Point(x, y, z), 10).setEmission(randColor())
                    .setMaterial(new Material().setKS(KS3).setKD(KD3).setNShininess(100)));
        }

        sceneMine.geometries.add(new Polygon(new Point(-300, -300, -130), new Point(-300, 300, -130),
                new Point(300, 300, -130), new Point(300, -300, -130))
                .setEmission(new Color(0, 0, 0))
                .setMaterial(new Material().setKR(0.6).setNShininess(100)).setEmission(randColor().scale(0.1)),
                new Polygon(new Point(300, -300, -130), new Point(300, 300, -130),
                        new Point(300, 300, 300), new Point(300, -300, 300))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKR(0.6).setNShininess(100)).setEmission(randColor().scale(0.1)));

        sceneMine.lights.add(
                new DirectionalLight(new Color(225, 225, 225), new Vector(1, -1, -1)));



        // Adjust camera position and direction
        Camera.Builder cameraMine = Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(sceneMine))
                .setLocation(new Point(-600, 0, 300))
                .setDirection(new Vector(2, 0, -1), new Vector(1, 0, 2)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(500); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("ManyShapesTest", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
    private Color randColor(){
        //random number between 0-7
        int rand = (int)(Math.random()*8);
        switch (rand){
            case 0:
                return new Color(255,0,0);//red
            case 1:
                return new Color(0,255,0);//green
            case 2:
                return new Color(0,0,255);//blue
            case 3:
                return new Color(255,255,0);//yellow
            case 4:
                return new Color(255,0,255);//purple
            case 5:
                return new Color(0,255,255);//cyan
            case 6:
                return new Color(255,150,150);//pink
            case 7:
                return new Color(225,120,0);//orange
        }
        return new Color(0,0,0);
    }

    /** Produce a picture of a snow man*/
    @Test
    public void funTests2() {
        Scene sceneMine = new Scene("Test scene for fun")
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
                .setVpSize(500, 500).setVpDistance(600); // Adjusted vpSize and vpDistance

        cameraMine.setImageWriter(new ImageWriter("MyFunTestMine", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }






}
