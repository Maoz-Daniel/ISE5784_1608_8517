package lighting;
import primitives.Color;

/**
 * Light is an abstract class representing a light source in the scene
 */
abstract class Light {
    /** The intensity of the light source */
    protected Color intensity;

    /**
     * Constructor for Light
     * @param intensity - the intensity of the light source
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the intensity of the light source
     * @return the intensity of the light source
     */
    public Color getIntensity() {
        return intensity;
    }

}
