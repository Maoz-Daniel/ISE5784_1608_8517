package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    protected Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructor for Light
     * @param intensity
     * @param position
     */
    protected PointLight(Color intensity, Point position) {
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
    public Color getIntensity(Point p) {
       return intensity.scale(1/((kC + kL * p.distance(position) + kQ * p.distanceSquared(position))));

    }

    @Override
    public Vector getL(Point p) {
       return p.subtract(position).normalize();
    }
}
