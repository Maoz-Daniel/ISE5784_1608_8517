package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource is an interface representing a light source in the scene
 */
public interface LightSource {

    /**
     * Getter for the intensity of the light source
     * @param p - the point
     * @return the intensity of the light source
     */
    public Color getIntensity(Point p);

    /**
     * Getter for the direction of the light source
     * @param p - the point
     * @return the direction of the light source
     */
    public Vector getL(Point p);
}
