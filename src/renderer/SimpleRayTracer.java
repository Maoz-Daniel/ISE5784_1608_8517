package renderer;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Ray;
import primitives.Point;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
            if (intersections == null) {
                return scene.background;
            }
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint);
        }

        private Color calcColor(GeoPoint p) {
//            var color = p.geometry.getEmission();
//            if(p.geometry instanceof geometries.Plane)
//                return p.geometry.getEmission().add(scene.ambientLight.getIntensity());/////
            return p.geometry.getEmission().add(scene.ambientLight.getIntensity());/////
        }

}


