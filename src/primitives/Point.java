package primitives;

public class Point {

    protected Double3 xyz;

    public String toString() {
        return "Point(" + xyz + ")";
    }

    public Point add(Vector v) {
        return new Point();
    }

    public Vector subtract(Point p) {
        return new Vector();
    }

    public double distance(Point p) {
        return 0.0;
    }

    public boolean equals(Object obj) {
        return false;
    }
}
