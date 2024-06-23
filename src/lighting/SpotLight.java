package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

public class SpotLight extends PointLight {

    public Vector direction;

    /**
     * Constructor for Light
     * @param intensity
     * @param position
     * @param direction
     */
    protected SpotLight(Color intensity,Point position, Vector direction) {
          super(intensity, position);
            this.direction = direction;

    }


    @Override
    public SpotLight setKc(double kC) {
        super.setKc(kC);
        return this;
    }

    @Override
    public SpotLight setKl(double kL) {
        super.setKl(kL);
        return this;
    }

    @Override
    public SpotLight setKq(double kQ) {
        super.setKq(kQ);
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double cos = direction.dotProduct(getL(p));
        return super.getIntensity(p).scale(Math.max(0, cos));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }


}
