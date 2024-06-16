package renderer;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Ray;
import primitives.Point;
import scene.Scene;

import java.util.List;

/**
 * SimpleRayTracer class represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase{


    /** The scene of the ray tracer */
        public SimpleRayTracer(Scene scene) {
            super(scene);
        }


        @Override
        public Color TraceRay(Ray ray) {
            List<Point> intersections = scene.geometries.findIntersections(ray);
            if (intersections == null) {
                return scene.background;
            }
            Point closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }

        private Color calcColor(Point p) {
            return scene.ambientLight.getIntensity();
        }

}


