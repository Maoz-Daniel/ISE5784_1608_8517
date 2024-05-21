package primitives;

import static primitives.Util.isZero;

/**
 * Class Vector is the basic class representing a vector in a 3D system.
 * The class is based on class Point, which is a basic class representing a point in a 3D system.
 * param xyz - the vector coordinates
 */
public class Vector extends Point {

    /** Constructor based on three double values */
    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
        if (isZero(d1)  && isZero(d2) && isZero(d3))
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        ;
    }

    /** Constructor based on a Double3 value */
    public Vector(Double3 other) {
        super(other);
        if (other.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
    }

    /**add a vector to the vector */
    public Vector add(Vector v) {
        return new Vector(v.xyz.add(this.xyz));
    }

    /**scales a vector by a scalar */
    public Vector scale(double scalar){
        return new Vector(this.xyz.scale(scalar));
    }

    /** scalar multipication of two vectors */
    public double dotProduct(Vector v){
       Double3 temp = this.xyz.product(v.xyz);
       return temp.d1 + temp.d2 + temp.d3;
    }

    /** cross product of two vectors */
    public Vector crossProduct(Vector v){
        return new Vector(
            this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,
            this.xyz.d3*v.xyz.d1 - this.xyz.d1*v.xyz.d3,
            this.xyz.d1*v.xyz.d2 - this.xyz.d2*v.xyz.d1
        );
    }

    /** length squared of a vector */
    public double lengthSquared(){
        return this.xyz.d1*this.xyz.d1 + this.xyz.d2*this.xyz.d2 + this.xyz.d3*this.xyz.d3;
    }

    /** length of a vector */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    /** normalize a vector */
    public Vector normalize(){
        double length = this.length();
        return this.scale(1/length);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return ((obj instanceof Vector other) &&
                (super.equals(obj))
        );//////////////////
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
