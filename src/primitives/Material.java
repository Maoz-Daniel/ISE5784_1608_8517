package primitives;

/**
 * Class Material is the basic class representing a material in a 3D system.
 */
public class Material {

    /** Default KD diffuse coefficient */
    public Double3 KD=Double3.ZERO;
    /** Default KS specular coefficient */
    public Double3 KS= Double3.ZERO;

    /** Default nShininess */
    public int nShininess=0;

    /**
     * Constructor based on a color and a double3
     * @param KD
     * @return
     */
    public Material setKD(Double3 KD) {
        this.KD = KD;
        return this;
    }

    /**
     * Constructor based on a color and a double3
     * @param KS
     * @return
     */
    public Material setKS(Double3 KS) {
        this.KS = KS;
        return this;
    }


    /**
     * Setter for the material's diffuse coefficient
     * @param KD
     * @return
     */
   public Material setKD(double KD) {
        this.KD = new Double3(KD);
        return this;
    }


    /**
     * Setter for the material's specular coefficient
     * @param KS
     * @return
     */
    public Material setKS(double KS) {
        this.KS= new Double3(KS);
        return this;
    }


    /**
     * Setter for the material's specular coefficient
     * @param nShininess
     * @return
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }


}
