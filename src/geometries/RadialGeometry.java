package geometries;

/**
 * RadialGeometry is an abstract class that represents a radial geometry in 3D Cartesian coordinate
 * system
 */
public abstract class RadialGeometry implements Geometry {
    /** The radius of the radial geometry*/
   final protected double radius;

   /** returns the radius of the radial geometry*/
    public RadialGeometry(double _radius) {
        radius = _radius;
    }

}
