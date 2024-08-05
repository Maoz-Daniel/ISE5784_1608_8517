package scene;


import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;
import lighting.LightSource;
import primitives.Point;

/**
 * Scene class represents a scene in 3D Cartesian coordinate
 */
public class Scene {

    /** The name of the scene */
    public String name;

    /** The background color of the scene */
    public Color background = Color.BLACK;

    /** The ambient light of the scene */
    public AmbientLight ambientLight =  AmbientLight.NONE;

    /** The geometries of the scene */
    public Geometries geometries = new Geometries();

    /** The lights of the scene */
    public List<LightSource> lights = new LinkedList<>();

    public Point CenterScene = null;

    /**
     * Constructor based on a name
     * @param name - the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Getter for the name
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Getter for the name
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Getter for the name
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Getter for the name
     * @param lights
     * @return
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}
