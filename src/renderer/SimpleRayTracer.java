package renderer;
import primitives.Color;
import primitives.Ray;
import primitives.Point;
import scene.Scene;

public class SimpleRayTracer extends RayTracerBase{

        public SimpleRayTracer(Scene scene) {
            super(scene);
        }

        @Override
        public Color traceRay(Ray ray) {
            Point closestPoint = ray.findClosestPoint(scene.geometries.findIntersections(ray));
            if(closestPoint == null) {
                return scene.background;
            }
            return this.calColor;
        }

        private Color calColor(Point point) {
            ///////////////////////////////////
        }

}
