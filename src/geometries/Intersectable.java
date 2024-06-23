package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface Intersectable is the basic interface for all geometries that are intersectable by a ray.
 */
public abstract class  Intersectable {

    /**
     * Function findGeoIntersections finds the intersection points of a ray with the geometry.
     * @param ray - the ray that intersects the geometry
     * @return a list of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

   /**
    * Class GeoPoint is a helper class that represents a point on a geometry.
    */
   public static class GeoPoint {

       /** The geometry that the point is on */
       public Geometry geometry;

       /** The point on the geometry */
       public Point point;

       /**
        * Constructor for GeoPoint
        * @param geometry - the geometry that the point is on
        * @param point - the point on the geometry
        */
       public GeoPoint(Geometry geometry, Point point) {
           this.geometry = geometry;
           this.point = point;
       }

       @Override
         public boolean equals(Object obj) {
              if (this == obj) return true;
              if (obj == null) return false;
              if (!(obj instanceof GeoPoint)) return false;
              GeoPoint other = (GeoPoint)obj;
              return this.geometry.equals(other.geometry) && this.point.equals(other.point);
         }

            @Override
            public String toString() {
                return "GeoPoint{" +
                        "geometry=" + geometry +
                        ", point=" + point +
                        '}';
            }
   }


   public List<GeoPoint> findGeoIntersections(Ray ray){
    return findGeoIntersectionsHelper(ray);
   }

   protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
