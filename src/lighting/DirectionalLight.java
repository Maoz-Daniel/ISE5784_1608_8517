package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class DirectionalLight is the basic class representing a directional light in a 3D system.
 * param intensity - the light intensity
 * param direction - the direction of the light
 */
public class DirectionalLight extends Light implements LightSource{

    /** The direction of the light source */
    private Vector direction;


    /**
     * Constructor for Light
     * @param intensity
     * @param direction
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
