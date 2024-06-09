package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class AmbientLight is the basic class representing an ambient light in a 3D system.
 * param intensity - the light intensity
 */
public class AmbientLight {

    /** The light intensity */
    private final Color intensity;

    /** Default Background */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0.0);

    /**
     * Constructor based on a color and a double3
     * @param Ia - the light intensity
     * @param Ka - the light intensity
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        intensity = Ia.scale(Ka);

    }
    /**
     * Constructor based on a color and a double
     * @param Ia - the light intensity
     * @param Ka - the light intensity
     */
    public AmbientLight(Color Ia, double Ka) {
        intensity = Ia.scale(Ka);
    }

    /**
     * Getter for the intensity
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }
}
