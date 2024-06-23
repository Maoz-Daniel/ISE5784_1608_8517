package renderer;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Ray;
import primitives.Point;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import primitives.Vector;
import lighting.LightSource;
import primitives.Material;
import static primitives.Util.alignZero;
import java.util.List;
import primitives.Double3;

/**
 * SimpleRayTracer class represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase{


    /** The scene of the ray tracer */
        public SimpleRayTracer(Scene scene) {
            super(scene);
        }


        @Override
        public Color traceRay(Ray ray) {
            var intersections = scene.geometries.findGeoIntersections(ray);
            if (intersections == null) return scene.background;
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint, ray);
        }

        private Color calcColor(GeoPoint p, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(p, ray));
        }

//    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
//        Vector n = gp.geometry.getNormal(gp.point);
//        Vector v = ray.getDirection();
//        double nv = alignZero(n.dotProduct(v));
//        if (nv == 0) return gp.geometry.getEmission();
//
//        Material material = gp.geometry.getMaterial();
//        Color color = gp.geometry.getEmission();
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(gp.point);
//            double nl = alignZero(n.dotProduct(l));
//            if (nl * nv > 0) { // sign(nl) == sign(nv)
//                Color iL = lightSource.getIntensity(gp.point);
//                color = color.add(
//                        iL.scale(calcDiffusive(material, n, l, nl))
//                                .add(calcSpecular(material, n, l, nl, v))
//                );
//            }
//        }
//         return color;
//    }

    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.KD.scale(nl < 0 ? -nl : nl);
    }

//    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
//        double vr = v.dotProduct(l.mirror(n, nl));
//        return (alignZero(vr) > 0) ? Double3.ZERO : mat.KS.scale(Math.pow(-vr, mat.nShininess));
//    }


}


