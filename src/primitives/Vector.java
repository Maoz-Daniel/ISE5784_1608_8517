package primitives;

public class Vector extends Point {

    public Vector(Double d1, Double d2, Double d3) {
        super(d1, d2, d3);
        if (d1 == 0 && d2 == 0 && d3 == 0)
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        ;
    }

    public Vector(Double3 other) {
        super(other);
        if (other.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
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

    public Vector add(Vector v) {
        return new Vector(v.xyz.add(this.xyz));
    }
    public Vector scale(double scalar){
        return new Vector(this.xyz.scale(scalar));
    }
    public double dotProduct(Vector v){
       Double3 temp = this.xyz.product(v.xyz);
       return temp.d1 + temp.d2 + temp.d3;
    }
    public Vector crossProduct(Vector v){
        return new Vector(
            this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,
            this.xyz.d3*v.xyz.d1 - this.xyz.d1*v.xyz.d3,
            this.xyz.d1*v.xyz.d2 - this.xyz.d2*v.xyz.d1
        );
    }
    public double lengthSquared(){
        return this.xyz.d1*this.xyz.d1 + this.xyz.d2*this.xyz.d2 + this.xyz.d3*this.xyz.d3;
    }

    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){
        double length = this.length();
        return this.scale(1/length);
    }
}
