package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class PointLight is the basic class representing a point light in a 3D system.
 * param intensity - the light intensity
 * param position - the position of the light
 */
public class PointLight extends Light implements LightSource {

    /** The position of the light source */
    protected Point position;
    /** The attenuation factors kc */
    private double kC = 1;
    /** The attenuation factors kl*/
    private double kL = 0;
    /** The attenuation factors kq */
    private double kQ = 0;

    /**
     * Constructor for Light
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;


    }

    /**
     * Getter for the position of the light source
     * @param kC
     * @return
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }
    /**
     * Setter for the position of the light source
     * @param kL
     * @return
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter for the position of the light source
     * @param kQ
     * @return
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }


   @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    @Override
    public Color getIntensity(Point p) {
       return intensity.scale(1/((kC + kL * p.distance(position) + kQ * p.distanceSquared(position))));

    }

    @Override
    public Vector getL(Point p) {
       return p.subtract(position).normalize();
    }
}
