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
public class SimpleRayTracer extends RayTracerBase {

    /**
     * The delta for the calculations
     */
    private final double DELTA = 0.1;

    /**
     * The scene of the ray tracer
     */
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

    /**
     * Calculate the color of the point
     *
     * @param p
     * @param ray
     * @return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(p, ray));
    }

    /**
     * Calculate the local effects of the point
     *
     * @param point
     * @return the color of the point
     */
    private Color calcLocalEffects(GeoPoint point, Ray ray) {
        Color color = point.geometry.getEmission();
        Vector n = point.geometry.getNormal(point.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = point.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));
            if ((nl * nv > 0) && unshaded(point,lightSource,l,n,nl)) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(point.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;

    }

    /**
     * Calculate the specular light
     *
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return the color of the point
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double minusVR = -alignZero(v.dotProduct(r));
        return material.KS.scale(Math.pow(Math.max(0, minusVR), material.nShininess));
    }


    /**
     * Calculate the diffusive light
     *
     * @param material
     * @param nl
     * @return the color of the point
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.KD.scale(Math.abs(nl));
    }

    /**
     * Check if the point is shaded
     *
     * @param l
     * @param n
     * @param gp
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(GeoPoint gp,LightSource light, Vector l, Vector n, double nl){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray ray= new Ray(point, lightDirection);

        var intersections = scene.geometries.findGeoIntersections(ray);

       if( intersections!= null)
         {
              double lightDistance = light.getDistance(gp.point);
              for (GeoPoint geoPoint : intersections) {
                if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0)
                     return false;
              }
         }
          return true;


    }




}


