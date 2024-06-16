package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase class represents a ray tracer base
 */
public abstract class  RayTracerBase {

    /** The scene of the ray tracer */
    protected Scene scene;

    /**
     * Constructor based on a scene
     * @param Scene - the scene of the ray tracer
     */
    public RayTracerBase(Scene Scene) {
        scene = Scene;
    }

    /**
     * Trace a ray
     * @param ray - the ray to trace
     * @return the color of the ray
     */
    public abstract Color TraceRay(Ray ray);


}
