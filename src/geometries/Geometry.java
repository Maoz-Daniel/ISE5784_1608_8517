package geometries;
import primitives.*;

/**
 * Geometry interface is the basic interface for all geometric objects
 */

public abstract class Geometry extends Intersectable {

    /**
     * @param point
     * @return the color of the geometry at the point
     */
    private Material material = new Material();

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
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material
     * @return
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
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
