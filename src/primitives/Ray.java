package primitives;

/**
 * Class Ray is the basic class representing a ray in a 3D system.
 * param head - the ray head
 * param direction - the ray direction
 */
public class Ray {

    /** The ray head */
    private Point head;

    /** The ray direction */
    private Vector direction;

    /** Constructor based on a point and a vector */
    public Ray(Point p, Vector v) {
        head = p;
        direction = v.normalize();
    }
@Override
   public boolean equals(Object obj) {
        if (this == obj) return true;
        return ((obj instanceof Ray other) &&
                head.equals(other.head) &&
                direction.equals(other.direction)
        );
    }

  @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}
