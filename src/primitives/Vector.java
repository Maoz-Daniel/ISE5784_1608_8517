package primitives;

public class Vector extends Point {

  @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector other)) return false;
        if(!super.equals(obj)) return false;
        return xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
     return super.toString();
    }


}
