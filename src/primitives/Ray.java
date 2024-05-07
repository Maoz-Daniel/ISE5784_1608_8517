package primitives;


public class Ray {
    private Point head;
    private Vector direction;

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
