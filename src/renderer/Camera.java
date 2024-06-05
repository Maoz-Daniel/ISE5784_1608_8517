package renderer;
import primitives.Point;
import primitives.Util;
import primitives.Vector;
import primitives.Ray;
import java.util.Comparator;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

/*
    * Camera class represents a camera in 3D Cartesian coordinate
 */
public class Camera implements Cloneable {

    /** The point of the camera */
    private Point p0;

    /** The up vector of the camera */
    private Vector vUp;

    /** The to vector of the camera */
    private Vector vTo;

    /** The right vector of the camera */
    private Vector vRight;

    /** The width of the view plane */
    private double width= 0.0;

    /** The height of the view plane */
    private double height= 0.0;

    /** The distance of the view plane */
    private double distance= 0.0;

    public Camera() {

    }


    /**
     * get the point of the camera
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get the up vector of the camera
     * @return
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * get the to vector of the camera
     * @return
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * get the right vector of the camera
     * @return
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * get the width of the view plane
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     * get the height of the view plane
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * get the distance of the view plane
     * @return
     */
    public double getDistance() {
        return distance;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * construct a ray through a pixel in the view plane
     * @param nX number of pixels in the columns
     * @param nY number of pixels in the rows
     * @param j the column index of the pixel
     * @param i the row index of the pixel
     * @return the ray through the pixel
     */
    public Ray constructRay(double nX, double nY, int j, int i) { //nx is the number of pixels in the columns, ny is the number of pixels in the rows
        Point pIJ = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        double yI = (i - nY / 2d) * rY + rY / 2d;
        double xJ = (j - nX / 2d) * rX + rX / 2d;
        if (!Util.isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!Util.isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }
        return new Ray(p0, pIJ.subtract(p0).normalize());
    }

    /**
     * Camera builder
     */
    public static class Builder {
        private final Camera camera = new Camera();

//        public Builder(Point _p0, Vector _vUp, Vector _vTo) {
//            camera.p0 = _p0;
//          camera.vUp = _vUp;
//            camera.vTo = _vTo;
//           camera.vRight = _vTo.crossProduct(_vUp).normalize();
//       }

        public Builder setLocation(Point _p0) {
            camera.p0 = _p0;
            return this;
        }

        public Builder setDirections( Vector _vUp, Vector _vTo) {

            if(!Util.isZero(_vUp.dotProduct(_vTo)))
                throw new IllegalArgumentException("vUp and vTo are not orthogonal");

            camera.vUp = _vUp.normalize();
            camera.vTo = _vTo.normalize();
            //camera.vRight = _vTo.crossProduct(_vUp).normalize();
            return this;
        }

        public Builder setVpSize(double _width, double _height) {
            camera.width = _width;
            camera.height = _height;
            return this;
        }

        public Builder setVpDistance(double _distance) {
            camera.distance = _distance;
            return this;
        }

        public Camera build() {
            if(camera.p0== null) {
                throw new MissingResourceException("missing camera parameters", "Camera", "p0 is missing");
            }
            if(camera.vUp==null) {
                throw new MissingResourceException("missing camera parameters", "Camera", "vUp is missing");
            }
            if(camera.vTo==null) {
                throw new MissingResourceException("missing camera parameters", "Camera", "vTo is missing");
            }
            if(camera.width==0.0) {
                throw new MissingResourceException("missing camera parameters", "Camera", "width is missing");
            }
            if(camera.height==0.0) {
                throw new MissingResourceException("missing camera parameters", "Camera", "height is missing");
            }
            if(camera.distance==0.0) {
                throw new MissingResourceException("missing camera parameters", "Camera", "distance is missing");
            }
            // maybe add invalid check for the vectors

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            return (Camera) camera.clone();
        }

    }

    @Override
    public Camera clone() {
        try {
            return (Camera) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
