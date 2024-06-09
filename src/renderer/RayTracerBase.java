package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
public abstract class  RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene Scene) {
        scene = Scene;
    }
    public abstract Color RayTracerBase(Ray ray);


}
