package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource is an interface representing a light source in the scene
 */
public interface LightSource {

    public Color getIntensity(Point p);
    public Vector getL(Point p);
}
