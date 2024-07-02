package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.List;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate
 * system
 */
public class Sphere extends RadialGeometry{

    /**The center of the sphere*/
    final private Point center;

    /** constructor receiving a center and a radius*/
    public Sphere(Point _center, double _radius) {
        super(_radius);
        center = _center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        Point head = ray.getHead();
        if(ray.getHead().equals(center)){// if the ray starts at the center of the sphere
            Point p = ray.getPoint(radius);
            if(Util.alignZero(ray.getHead().distance(p)-maxDistance) <= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                return List.of(new GeoPoint(this,p ));
            }

        }

        Vector u = center.subtract(head); // u = O - P0
        double tm = ray.getDirection().dotProduct(u); // tm = V . u
        if (Util.isZero(tm)) { // if the sphere is behind the ray
           if( u.length() >= radius){
               return null;
           }
              double t = Math.sqrt(radius * radius - u.lengthSquared()); // t = sqrt(R^2 - ||u||^2)
            if(Util.alignZero(ray.getHead().distance(ray.getPoint(t))-maxDistance) >= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                return null;
            }
            return List.of(new GeoPoint(this,ray.getPoint(t))); // P = P0 + t * V
        }


        double d = Math.sqrt(u.lengthSquared() - tm * tm); // d = sqrt(||u||^2 - tm^2)
        if (d >= radius) {
            return null;
        }
        double th = Math.sqrt(radius * radius - d * d); // th = sqrt(R^2 - d^2)


        double t1 = tm - th; // t1 = tm - th
        double t2 = tm + th; // t2 = tm + th
        if (t1 > 0 && t2 > 0 ) { // if the sphere is behind the ray
            Point p1 = ray.getPoint(t1); // P1 = P0 + t1 * V
            Point p2 = ray.getPoint(t2); // P2 = P0 + t2 * V
            if(p1.subtract(head).length() > p2.subtract(head).length()){
                if(Util.alignZero(t1-maxDistance) >= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                   if(Util.alignZero(t2-maxDistance) >= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                       return null;
                   }
                    return List.of(new GeoPoint(this, p2));
                }

                if(Util.alignZero(t2-maxDistance) >= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                    return List.of(new GeoPoint(this, p1));
                }

            }
            return List.of( new GeoPoint(this, p1),new GeoPoint(this, p2));
        }
        if (t1 > 0) { // if the sphere is behind the ray
            Point p1 = ray.getPoint(t1); // P1 = P0 + t1 * V
            if(Util.alignZero(t1-maxDistance) >= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                return null;
            }
            return List.of( new GeoPoint(this, p1));
        }
        if (t2 > 0) { // if the sphere is behind the ray
            Point p2 = ray.getPoint(t2); // P2 = P0 + t2 * V
            if(Util.alignZero(t2-maxDistance)>= 0){ // if the distance between the head of the ray and the point is bigger than the max distance
                return null;
            }
            return List.of(new GeoPoint(this, p2));
        }
        return null;


    }
}
