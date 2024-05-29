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

    /** Getter for the head */
    public Point getHead() {
        return head;
    }
    /** Getter for the direction */
    public Vector getDirection() {
        return direction;
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

    /**
     * Get a point on the ray at a distance t from the head
     * @param t
     * @return
     */
    public Point getPoint(double t) {
        if (Util.isZero(t)) {
             return head;
        }
        return head.add(direction.scale(t));
    }
}
