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

    /** Default KT transparency coefficient */
    public Double3 KT=Double3.ZERO;

    /** Default KR reflection coefficient */
    public Double3 KR=Double3.ZERO;
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

    /**
     * Setter for the material's transparency coefficient
     * @param KT
     * @return
     */
    public Material setKT(Double3 KT) {
        this.KT = KT;
        return this;
    }

    /**
     * Setter for the material's reflection coefficient
     * @param KR
     * @return
     */
    public Material setKR(Double3 KR) {
        this.KR = KR;
        return this;
    }

/**
     * Setter for the material's reflection coefficient
     * @param KR
     * @return
     */
    public Material setKR(double KR) {
        this.KR = new Double3(KR);
        return this;
    }

    /**
     * Setter for the material's transparency coefficient
     * @param KT
     * @return
     */
    public Material setKT(double KT) {
        this.KT = new Double3(KT);
        return this;
    }





}
