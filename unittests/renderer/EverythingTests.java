package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

/**
 * Test rendering a basic image
 */
public class EverythingTests {
    private final Scene scene = new Scene("Test scene")
            .setAmbientLight(new AmbientLight(new Color(228, 189, 129), new Double3(0.15))).setBackground(new Color(127, 204, 224));

    /**
     * Test method for
     * {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    public void testEverything() {
        Geometries pineappleBase = new Geometries();
        Geometries pineappleLeaves = new Geometries();
        Geometries pineapple = new Geometries();
        Geometries squidward = new Geometries();
        Geometries patrick = new Geometries();
        Geometries mountain = new Geometries();
        Geometries houses = new Geometries();
        Geometries bubbles = new Geometries();

        // Define the materials once
        Material material = new Material().setKD(0.5).setKS(0.5).setNShininess(20);
        Material materialWithKr = new Material().setKD(0.5).setKS(0.5).setNShininess(20).setKR(1);

        Color c_50_50_50 = new Color(50, 50, 50);

        Point C = new Point(0, -100, 1);
        Point D = new Point(0, 100, 1);
        Point E = new Point(-2000, 100, 1);
        Point F = new Point(-2000, -100, 1);

        scene.geometries.add(
                new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))
                        .setEmission(new Color(228, 189, 129)).setMaterial(material),

                new Polygon(C, D, E, F).setEmission(new Color(134, 83, 35)).setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(30)),

                new Polygon(new Point(0, 440, 1), new Point(0, 520, 1), new Point(-160, 520, 1), new Point(-1600, 440, 1))
                        .setEmission(new Color(64, 60, 57)).setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(30)),

                new Polygon(new Point(0, -440, 1), new Point(0, -520, 1), new Point(-160, -520, 1), new Point(-1600, -440, 1))
                        .setEmission(new Color(175, 227, 68)).setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(30))


        );
        patrick = new Geometries(new Sphere(new Point(0, 480, 0), 170)
                .setEmission(new Color(153, 0, 51)).setMaterial(material));





        for (int i = 0; i < 4; i++) {
            double radius = i < 2 ? 120 + i * 20 : 120 + 60 - i * 20;
            double z = 20 + i * 60;
            pineappleBase.add(new Sphere(new Point(0, -450, z), radius).setEmission(new Color(242, 167, 69))
                    .setMaterial(material));
        }
        pineappleBase.add(new Sphere(new Point(-53, -340, 175), 30)
                        .setMaterial(materialWithKr)
                        .setEmission(c_50_50_50),
                new Sphere(new Point(-50, -562, 55), 30)
                        .setMaterial(materialWithKr)
                        .setEmission(c_50_50_50)
        );

        int numberOfLeaves = 10;
        double leafHeight = 100;
        double leafWidth = 30;
        double topOfPineapple = 320; // The z-coordinate for the base of the leaves

        for (int i = 0; i < numberOfLeaves; i++) {
            double angle = 2 * Math.PI * i / numberOfLeaves; // Angle for current leaf
            double baseX1 = leafWidth * Math.cos(angle);
            double baseY1 = leafWidth * Math.sin(angle);
            double baseX2 = leafWidth * Math.cos(angle + Math.PI / numberOfLeaves);
            double baseY2 = leafWidth * Math.sin(angle + Math.PI / numberOfLeaves);

            // Create the triangles for each leaf
            pineappleLeaves.add(new Triangle(
                            new Point(0, -450, topOfPineapple - 50),
                            new Point(baseX1 * 3, baseY1 * 3 - 450, topOfPineapple + leafHeight),
                            new Point(baseX2 * 3, baseY2 * 3 - 450, topOfPineapple + leafHeight))
                            .setEmission(new Color(7, 220, 50))
                            .setMaterial(material),
                    new Triangle(new Point(0, -450, topOfPineapple - 80),
                            new Point(baseX1 * 4, baseY1 * 4 - 450, topOfPineapple + leafHeight - 30),
                            new Point(baseX2 * 4, baseY2 * 4 - 450, topOfPineapple + leafHeight - 30))
                            .setEmission(new Color(7, 220, 50))
                            .setMaterial(material));
        }
        pineapple = new Geometries(pineappleLeaves, pineappleBase);



        // Define the points once
        Point p1 = new Point(150, 150, 0);
        Point p2 = new Point(150, -150, 0);
        Point p3 = new Point(100, -100, 550);
        Point p4 = new Point(100, 100, 550);

        Point p5 = new Point(-150, 150, 0);
        Point p6 = new Point(-150, -150, 0);
        Point p7 = new Point(-100, -100, 550);
        Point p8 = new Point(-100, 100, 550);

        Point M = new Point(-100, 75, 495);
        Point N = new Point(-100, -75, 495);
        Point I = new Point(-130, 75, 495);
        Point J = new Point(-130, -75, 495);
        Point L = new Point(-130, 75, 460);
        Point K = new Point(-130, -75, 460);
        Point O = new Point(-100, -75, 460);
        Point S = new Point(-120, 25, 460);
        Point T = new Point(-120, -25, 460);
        Point R = new Point(-100, 25, 460);
        Point Q = new Point(-100, -25, 460);
        Point P = new Point(-100, 75, 460);

        Point U = new Point(-120, -55, 175);
        Point V = new Point(-120, 55, 175);
        Point W = new Point(-160, -55, 175);
        Point Z = new Point(-160, 55, 175);

        Color c_32_93_136 = new Color(32, 93, 136);
        Color c_21_70_103 = new Color(21, 70, 103);

        Point pm110_52_430 = new Point(-110, 52, 430);
        squidward.add(
                new Polygon(p1, p2, p3, p4).setEmission(c_32_93_136)
                        .setMaterial(material),
                new Polygon(p5, p6, p7, p8).setEmission(c_32_93_136)
                        .setMaterial(material),
                new Polygon(p1, p5, p8, p4).setEmission(c_32_93_136)
                        .setMaterial(material),
                new Polygon(p2, p6, p7, p3).setEmission(c_32_93_136)
                        .setMaterial(material),
                new Polygon(p4, p3, p7, p8).setEmission(c_32_93_136)
                        .setMaterial(material),

                new Polygon(N, J, I, M).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(L, P, O, K).setEmission(c_32_93_136)
                        .setMaterial(material.setNShininess(20)).setEmission(c_21_70_103),

                new Polygon(J, I, L, K).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(M, P, O, N).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(M, I, L, P).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(J, K, O, N).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),


                new Polygon(V, R, S, Z).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(U, Q, T, W).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(U, W, Z, V).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Polygon(Z, W, T, S).setEmission(c_32_93_136)
                        .setMaterial(material).setEmission(c_21_70_103),

                new Sphere(pm110_52_430, 30) //first eye of Squidward
                        .setMaterial(materialWithKr)
                        .setEmission(c_50_50_50),

                new Sphere(new Point(-110, -52, 430), 30) //second eye of Squidward
                        .setMaterial(materialWithKr)
                        .setEmission(c_50_50_50)
        );

        Point K2 = new Point(-45, 150, 440);
        Point L2 = new Point(45, 150, 440);
        Point Q2 = new Point(45, 150, 240);
        Point R2 = new Point(-45, 150, 240);
        Point S2 = new Point(-45, -150, 440);
        Point T2 = new Point(45, -150, 440);
        Point U2 = new Point(-45, -150, 240);
        Point V2 = new Point(45, -150, 240);

        squidward.add(
                new Polygon(K2, L2, Q2, R2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material),
                new Polygon(S2, T2, V2, U2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material),
                new Polygon(T2, L2, Q2, V2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material),
                new Polygon(S2, K2, R2, U2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material),
                new Polygon(S2, T2, L2, K2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material),
                new Polygon(U2, R2, Q2, V2).setEmission(c_32_93_136).setEmission(c_21_70_103)
                        .setMaterial(material)


        );
        houses = new Geometries(squidward, patrick, pineapple);





        Color c_228_189_129 = new Color(228, 189, 129);
        mountain.add(
                new Sphere(new Point(23200, 5000, -6000), 8000)
                        .setEmission(c_228_189_129)
                        .setMaterial(material),
                new Sphere(new Point(28200, -6000, -3000), 9000)
                        .setEmission(c_228_189_129)
                        .setMaterial(material)
        );


        bubbles.add(
                new Sphere(new Point(-100, -300, 450), 60) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setKT(1).setNShininess(100).setKS(0.2).setKD(0.2)), // Material properties with transparency
                new Sphere(new Point(-100, -350, 550), 40) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setKT(1).setNShininess(100).setKS(0.2).setKD(0.2)), // Material properties with transparency
                new Sphere(new Point(-100, -230, 600), 30) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setKT(1).setNShininess(100).setKS(0.2).setKD(0.2)), // Material properties with transparency
                new Sphere(new Point(-100, -369, 650), 20) // Position of the bubble
                        .setEmission(new Color(0, 0, 10)) // Light blue color for the bubble
                        .setMaterial(new Material().setKT(1).setNShininess(100).setKS(0.2).setKD(0.2)) // Material properties with transparency

        );

        for (int i = 0; i < 5; i++) {
            int x = -900;
            int y = -300 + i * 150;
            scene.geometries.add(new Sphere(new Point(x, y, 5), 25).setEmission(new Color(150, 0, 150))
                    .setMaterial(new Material().setNShininess(20).setKT(0.6).setKS(0.5).setKD(0.5)));
            scene.lights.add(
                    new PointLight(new Color(30, 30, 30), new Point(x, y, 5)));
        }
        scene.geometries.add(houses, bubbles, mountain);

        scene.lights.add(
                new DirectionalLight(new Color(50, 50, 200), new Vector(1, -1, -1)));

        Camera.Builder cameraMine1 = (Camera.getBuilder()
                .setRayTracer(new SimpleRayTracer(scene))
                .setLocation(new Point(-1700, 0, 100))
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1)) // Look towards the positive z-axis
                .setVpSize(500, 500).setVpDistance(600).setFocalLength(1700).setAperture(10).setThreadsCount(4).setPrintInterval(0.1)); // Adjusted vpSize and vpDistance

        cameraMine1.setImageWriter(new ImageWriter("SpongeBob1", 1000, 1000))
                .build()
                .renderImage(17)
                .writeToImage();


//        Camera.Builder cameraMine2 = (Camera.getBuilder()
//                .setRayTracer(new SimpleRayTracer(scene))
//                .setLocation(new Point(-1300, 800, 600))
//                .setDirection(new Vector(5, -3, -2), new Vector(5, -3, 17)) // Look towards the positive z-axis
//                .setVpSize(500, 500).setVpDistance(600)); // Adjusted vpSize and vpDistance
//
//        cameraMine2.setImageWriter(new ImageWriter("SpongeBob2", 1000, 1000))
//                .build()
//                .renderImage()
//                .writeToImage();
//
//       Camera.Builder cameraMine3 = (Camera.getBuilder()
//               .setRayTracer(new SimpleRayTracer(scene))
//                .setLocation(new Point(0, 0, 2000))
//               .setDirection(new Vector(0, 0, -1), new Vector(1, 0, 0)) // Look towards the positive z-axis
//                .setVpSize(500, 500).setVpDistance(600)); // Adjusted vpSize and vpDistance
//
//        cameraMine3.setImageWriter(new ImageWriter("SpongeBob3", 1000, 1000))
//                .build()
//                .renderImage()
//                .writeToImage();

    }
}

