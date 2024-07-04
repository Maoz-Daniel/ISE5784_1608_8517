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

    /* The delta for the calculations */
    private final double DELTA = 0.1;

    /* The maximum level of the color calculation */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /* The minimum level of the color calculation */
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K = Double3.ONE;

    private  static final boolean SNELL = false;
    /**
     * The scene of the ray tracer
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray) {

        GeoPoint closestPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray)); //find the closest point
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculate the color of the point
     *
     * @param p
     * @param ray
     * @return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(p, ray);
        return 1 == level ? color
                : color.add((calcGlobalEffects(p, ray, level, k)));
    }

    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculate the global effects of the point
     *
     * @param point
     * @param ray
     * @param level
     * @param k
     * @return the color of the point
     */

        private Color calcGlobalEffects(GeoPoint point, Ray ray, int level, Double3 k) {

        Material material = point.geometry.getMaterial();
        Vector v = ray.getDirection();
        Vector n = point.geometry.getNormal(point.point);
        return calcGlobalEffect(constructRefractedRay(point,v,n ), material.KT,level, k)
                .add(calcGlobalEffect(constructReflectedRay(point,v, n), material.KR,level, k));

    }
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
    }
    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        double nv = n.dotProduct(v);
        if (nv == 0) return null;

        Vector vec = v.subtract(n.scale(2 * nv));
        return new Ray(gp.point, vec, n);
    }

    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
       Double3 kkx=kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp=ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray)); //find the closest point
        return gp==null?scene.background:calcColor(gp,ray,level-1,kkx)
                .scale(kx);
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
                  if(geoPoint.geometry.getMaterial().KT.equals(Double3.ZERO))
                      return false;
                if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0)
                     return false;
              }
         }
          return true;


    }




}


