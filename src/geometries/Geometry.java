package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Util;
import primitives.Color;

/**
 * Geometry interface is the basic interface for all geometric objects
 */

public abstract class Geometry extends Intersectable {

    /**
     * @param point
     * @return the color of the geometry at the point
     */
    protected  Color emission = Color.BLACK;

    /**
     * @param point
     * @return the normal to the geometry at the point
     */
    public abstract Vector getNormal(Point point);


    /**
     * @return the emission of the geometry at the point
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @param emission
     * @return the geometry with the emission set to the given emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


}
