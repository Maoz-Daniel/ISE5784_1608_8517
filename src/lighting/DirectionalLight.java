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
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
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
