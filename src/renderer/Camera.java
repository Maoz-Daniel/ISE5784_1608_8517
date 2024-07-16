package renderer;
import primitives.Point;
import primitives.Util;
import primitives.Vector;
import primitives.Ray;
import primitives.Color;

import java.util.MissingResourceException;

/**
 * Camera class represents a camera in 3D Cartesian coordinate
 */
public class Camera implements Cloneable {

    /**
     * The point of the camera
     */
    private Point p0;

    /**
     * The up vector of the camera
     */
    private Vector vUp;

    /**
     * The to vector of the camera
     */
    private Vector vTo;

    /**
     * The right vector of the camera
     */
    private Vector vRight;

    /**
     * The width of the view plane
     */
    private double width = 0.0;

    /**
     * The height of the view plane
     */
    private double height = 0.0;

    /**
     * The distance of the view plane
     */
    private double distance = 0.0;

    /**
     * The image writer
     */
    private ImageWriter imageWriter;

    /**
     * The ray tracer
     */
    private RayTracerBase rayTracer;

    /**
     * The aperture of the camera
     */
    private double aperture = 0.0;

    /**
     * The focal length of the camera
     */
    private double focalLength = 0.0;

    /**
     * Camera constructor
     */
    private Camera() {
    }

    /**
     * get the point of the camera
     *
     * @return the point of the camera
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get the up vector of the camera
     *
     * @return the up vector of the camera
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * get the to vector of the camera
     *
     * @return the to vector of the camera
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * get the right vector of the camera
     *
     * @return the right vector of the camera
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * get the width of the view plane
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * get the height of the view plane
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * get the distance of the view plane
     *
     * @return the distance of the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get the aperture of the camera
     *
     * @return the aperture of the camera
     */
    public double getAperture() {
        return aperture;
    }

    /**
     * get the focal length of the camera
     *
     * @return the focal length of the camera
     */
    public double getFocalLength() {
        return focalLength;
    }

    /**
     * Builder pattern for the camera
     *
     * @return the camera builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Construct a point on the view plane based on the pixel index
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    private Point constructRayPoint(int nX, int nY, int j, int i) {
        // Calculate the center point of the view plane
        Point pc = p0.add(vTo.scale(distance));

        // Calculate the height and width of a pixel
        double rY = height / (double) nY; // height of a single pixel
        double rX = width / (double) nX;  // width of a single pixel

        // Calculate the x and y offsets of the pixel from the center of the view plane
        double xJ = (j - (nX - 1) / 2.0) * rX; // Correct calculation of x coordinate
        double yI = -(i - (nY - 1) / 2.0) * rY; // Correct calculation of y coordinate

        // Start at the center of the view plane
        Point pIJ = pc;

        // Adjust the point based on the x and y offsets
        if (!Util.isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!Util.isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI)); // Typically, the y direction is inverted in image coordinates
        }
        return pIJ;
    }


    /**
     * construct a ray through a pixel in the view plane
     *
     * @param nX number of pixels in the columns
     * @param nY number of pixels in the rows
     * @param j  the column index of the pixel
     * @param i  the row index of the pixel
     * @return the ray through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Calculate the center point of the view plane
        Point pIJ = constructRayPoint(nX, nY, j, i);
        // Create the ray from the camera origin to the pixel on the view plane
        Vector direction = pIJ.subtract(p0);
        return new Ray(p0, direction);
    }



    /**
     * Camera builder class
     */
    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * Set the location of the camera
         * @param _p0 the location of the camera
         * @return the builder
         */
        public Builder setLocation(Point _p0) {
            camera.p0 = _p0;
            return this;
        }

        /**
         * Set the direction of the camera
         * @param _vTo the direction of the camera
         * @param _vUp the up vector of the camera
         * @return the builder
         */
        public Builder setDirection(Vector _vTo , Vector _vUp) {

            if(!Util.isZero(_vUp.dotProduct(_vTo)))
                throw new IllegalArgumentException("vUp and vTo are not orthogonal");

            camera.vUp = _vUp.normalize();
            camera.vTo = _vTo.normalize();
            //camera.vRight = _vTo.crossProduct(_vUp).normalize();
            return this;
        }

        /**
         * Set the view plane size
         * @param _width the width of the view plane
         * @param _height the height of the view plane
         * @return the builder
         */
        public Builder setVpSize(double _width, double _height) {
            camera.width = _width;
            camera.height = _height;
            return this;
        }

        /**
         * Set the distance of the view plane
         * @param _distance the distance of the view plane
         * @return the builder
         */
        public Builder setVpDistance(double _distance) {
            camera.distance = _distance;
            return this;
        }

        /**
         * Set the image writer
         * @param _imageWriter the image writer
         * @return the builder
         */
        public Builder setImageWriter(ImageWriter _imageWriter) {
            camera.imageWriter = _imageWriter;
            return this;
        }

        /**
         * Set the ray tracer
         * @param _rayTracer the ray tracer
         * @return the builder
         */
        public Builder setRayTracer(RayTracerBase _rayTracer) {
            camera.rayTracer = _rayTracer;
            return this;
        }

        /**
         * Set the aperture of the camera
         * @param _aperture the aperture of the camera
         * @return the builder
         */
        public Builder setAperture(double _aperture) {
            camera.aperture = _aperture;
            return this;
        }

        /**
         * Set the focal length of the camera
         * @param _focalLength the focal length of the camera
         * @return the builder
         */
        public Builder setFocalLength(double _focalLength) {
            camera.focalLength = _focalLength;
            return this;
        }

        /**
         * Build the camera
         * @return the camera
         */
        public Camera build() {

            String errorMessage = "missing camera parameters";
            String errorSource = "Camera";

            if(camera.p0== null) {
               throw new MissingResourceException(errorMessage, errorSource, "p0 is missing");
            }
            if(camera.vUp==null) {
                throw new MissingResourceException(errorMessage, errorSource, "vUp is missing");
                    }
            if(camera.vTo==null) {
                throw new MissingResourceException(errorMessage, errorSource, "vTo is missing");
            }
            if(camera.width==0.0) {
                throw new MissingResourceException(errorMessage, errorSource, "width is missing");
            }
            if(camera.height==0.0) {
                throw new MissingResourceException(errorMessage, errorSource, "height is missing");
                }
            if(camera.distance==0.0) {
                throw new MissingResourceException(errorMessage, errorSource, "distance is missing");
            }
            if(camera.imageWriter==null) {
                throw new MissingResourceException(errorMessage, errorSource, "imageWriter is missing");
            }
            if(camera.rayTracer==null) {
                throw new MissingResourceException(errorMessage, errorSource, "rayTracer is missing");
            }

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

   /**
     * Render the image without sample Size
     * @return the camera
     */
    public Camera renderImage() {

        // Calculate the number of pixels in the rows and columns
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        // Render the image
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRays(nX, nY, j, i);
            }
        }
        return this;
    }

    /**
     * Render the image with beam
     * @param sampleSize
     * @return the camera
     */
    public Camera renderImage(int sampleSize) {
        if (sampleSize == 1)
        {
            return renderImage();
        }
        // Calculate the number of pixels in the rows and columns
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        if (focalLength != 0.0 && aperture != 0.0) {

            // Render the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    depthOfField(nX, nY, j, i, sampleSize);
                }
            }
            return this;
        }
        else {

            // Render the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    antiAliasing(nX, nY, j, i, sampleSize);
                }
            }
            return this;
        }
    }




    /**
     * Print a grid on the view plane
     * @param interval the interval between the grid lines
     * @param color the color of the grid lines
     */
    public Camera printGrid(int interval, Color color) {


        // Calculate the number of pixels in the rows and columns
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        // Render the grid
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Write the image to a file
     */
    public Camera writeToImage() {

        imageWriter.writeToImage();
        return this;
    }

    /**
     * Cast rays through a pixel in the view plane
     * @param nX number of pixels in the columns
     * @param nY number of pixels in the rows
     * @param column the column index of the pixel
     * @param row the row index of the pixel
     */
    private void castRays(int nX, int nY, int column, int row) {
        Ray ray = constructRay(nX, nY, column, row);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(column, row, color);
    }

    /**
     * Calculate the focal point of the camera
     * @param v the direction of the camera
     * @return the focal point of the camera
     */
    private Point focalPoint(Vector v) {

        return p0.add(v.scale(v.dotProduct(vTo.scale(focalLength))));

    }

    /**
     * Cast rays through a pixel in the view plane with depth of field
     * @param nX number of pixels in the columns
     * @param nY number of pixels in the rows
     * @param column the column index of the pixel
     * @param row the row index of the pixel
     * @param sampleSize the number of rays to cast through the pixel
     */
    private void depthOfField(int nX, int nY, int column, int row, int sampleSize) {
        Point pIJ = constructRayPoint(nX, nY, column, row);
        Vector v =  pIJ.subtract(p0).normalize();
        Point focalPoint = focalPoint(v);
        double rY = (height / (double) nY); // height of a single pixel
        double rX = (width / (double) nX);  // width of a single pixel

        pIJ = pIJ.add(vRight.scale(-aperture/2));
        pIJ = pIJ.add(vUp.scale(aperture/2));
        Color color =new Color(0,0,0);
        double count = (sampleSize+1)*(sampleSize+1);

        for (int i = 0; i < sampleSize +1; i++) {
            for (int j = 0; j < sampleSize+1; j++) {
                Point p = pIJ;
                double xrand = -0.5 + (Math.random() * (0.5 - (-0.5)));
                double yrand = -0.5 + (Math.random() * (0.5 - (-0.5)));
                // Adjust the point based on the x and y offsets
                if (!Util.isZero(j)) {
                    p = p.add(vRight.scale(j * aperture / sampleSize));
                }
                if (!Util.isZero(i)) {
                    p = p.add(vUp.scale(-i * aperture / sampleSize)); // Typically, the y direction is inverted in image coordinates
                }
                if (!Util.isZero(xrand)) {
                    p = p.add(vRight.scale(xrand*rX/sampleSize)); // Typically, the y direction is inverted in image coordinates
                }
                if (!Util.isZero(yrand)) {
                    p = p.add(vUp.scale(yrand*rY/sampleSize)); // Typically, the y direction is inverted in image coordinates
                }
                if(p.distance(pIJ) <= aperture) {
                    Ray ray = new Ray(p, focalPoint.subtract(p));
                    color = color.add(rayTracer.traceRay(ray));
                }
                else {
                    count--;
                }
            }
        }
        color = color.reduce(count);
        imageWriter.writePixel(column, row, color);
    }

    /**
     * Cast rays through a pixel in the view plane with anti-aliasing
     * @param nX number of pixels in the columns
     * @param nY number of pixels in the rows
     * @param column the column index of the pixel
     * @param row the row index of the pixel
     * @param sampleSize the number of rays to cast through the pixel
     */
    private void antiAliasing(int nX, int nY, int column, int row, int sampleSize) {
        Point pIJ = constructRayPoint(nX, nY, column, row);
        double rY = (height / (double) nY); // height of a single pixel
        double rX = (width / (double) nX);  // width of a single pixel
        pIJ = pIJ.add(vRight.scale(-rX/2));
        pIJ = pIJ.add(vUp.scale(rY/2));
        double xrand = -0.5 + (Math.random() * (0.5 - (-0.5)));
        double yrand = -0.5 + (Math.random() * (0.5 - (-0.5)));

        Color color =new Color(0,0,0);

        for (int i = 0; i < sampleSize +1; i++) {
            for (int j = 0; j < sampleSize+1; j++) {
                Point p = pIJ;
                if (!Util.isZero(j)) {
                    p = p.add(vRight.scale(j * rX / sampleSize));
                }
                if (!Util.isZero(i)) {
                    p = p.add(vUp.scale(-i * rY / sampleSize)); // Typically, the y direction is inverted in image coordinates
                }
                if (!Util.isZero(xrand)) {
                    p = p.add(vRight.scale(xrand*rX/sampleSize)); // Typically, the y direction is inverted in image coordinates
                }
                if (!Util.isZero(yrand)) {
                    p = p.add(vUp.scale(yrand*rY/sampleSize)); // Typically, the y direction is inverted in image coordinates
                }

                Ray ray = new Ray(p0, p.subtract(p0));
                color = color.add(rayTracer.traceRay(ray));

            }
        }
        color = color.reduce((sampleSize+1)*(sampleSize+1));
        imageWriter.writePixel(column, row, color);

    }

}
