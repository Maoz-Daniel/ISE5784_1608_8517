package primitives;
/**
 * Class Point is the basic class representing a point in a 3D system.
 * The class is based on class Double3, which is a basic class representing a point in a 3D system.
 * param xyz - the point coordinates
 */
public class Point {
    /** The point coordinates */
    final protected Double3 xyz;

    /** Zero point (0,0,0) */
    final public static Point ZERO = new Point(0, 0, 0);

    /** Constructor based on three double values */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /** Constructor based on a Double3 value */
   public Point(Double3 other) {
        xyz = other;
    }

   @Override
    public String toString() {
        return xyz.toString();
    }

    /** adds a vector to the point  */
    public Point add(Vector v) {
        return new Point( v.xyz.add(this.xyz));
    }

    /**  subtraction of a point from our point and return a vector */
    public Vector subtract(Point p) {
        return new Vector( xyz.subtract(p.xyz));
    }

    /** Calculate the squared distance between two points */
    public double distanceSquared(Point p) {
        Double3 temp = (p.xyz.subtract(this.xyz)).product(p.xyz.subtract(this.xyz));
        return temp.d1 + temp.d2 + temp.d3;
    }

    /** Calculate the distance between two points */
    public double distance(Point p) {
        return Math.sqrt(this.distanceSquared(p));
    }
@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return  ((obj instanceof Point other)&&
                xyz.equals(other.xyz));
    }
}
