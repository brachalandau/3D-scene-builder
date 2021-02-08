package main.scenes;
import main.elements.AmbientLight;
import main.elements.Camera;
import main.elements.Light;
import main.geometries.Geometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scene{
    String sceneName;
    Color sceneColor;
    AmbientLight ambientLight;
    List<Geometry> geometries;
    Camera camera;
    double screen_distance;
    private ArrayList<Light> lights = new ArrayList<Light>();

    // ***************** Constructors ********************** //

    public Scene() {
        sceneName="";
        sceneColor=Color.black;
        geometries=new ArrayList<Geometry>();
        camera=new Camera();
        screen_distance=100;
        this.ambientLight=new AmbientLight();
    }
    public Scene(String name) {
        sceneName=name;
        sceneColor=Color.black;
        geometries=new ArrayList<Geometry>();
        camera=new Camera();
        screen_distance=100;
        this.ambientLight= new AmbientLight();
    }
    public Scene(AmbientLight aLight, Color background, Camera camera, double screenDistance) {

        this.sceneColor = background;
        this.ambientLight = new AmbientLight(aLight);
        setCamera(new Camera(camera));
        this.screen_distance = screenDistance;
    }


    public Scene(String sceneName, Color sceneColor, AmbientLight ambientLight, Camera camera, double screen_distance) {
        this.sceneName = sceneName;
        this.sceneColor = sceneColor;
        this.ambientLight =new AmbientLight(ambientLight);
        this.camera = new Camera(camera);
        this.screen_distance = screen_distance;
        geometries=new ArrayList<Geometry>();
    }

    public Scene(Scene toCopy)
    {
        this.sceneName = sceneName;
        this.sceneColor = sceneColor;
        this.ambientLight = new AmbientLight(ambientLight);
        this.camera = new Camera(camera);
        this.screen_distance = screen_distance;
        geometries=new ArrayList<Geometry>();
        lights = new ArrayList<Light>(toCopy.lights);

    }

    // ***************** Getters/Setters ********************** //

    public String getSceneName() {
        return new String(sceneName);
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Color getSceneColor() {
        return sceneColor;
    }

    public void setSceneColor(Color sceneColor) {
        this.sceneColor = sceneColor;
    }

    public List<Geometry> getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return new AmbientLight(ambientLight);
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = new AmbientLight(ambientLight);
    }

    public void setGeometries(List<Geometry> geometries) {
        this.geometries = geometries;
    }

    public Camera getCamera() {
        return new Camera(camera);
    }

    public void setCamera(Camera camera) {
        this.camera = new Camera(camera);
    }

    public double getScreen_distance() {
        return screen_distance;
    }

    public void setScreen_distance(double screen_distance) {
        this.screen_distance = screen_distance;
    }
    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return "scenes{" +
                "sceneName='" + sceneName + '\'' +
                ", sceneColor=" + sceneColor +
                ", ambientLight=" + ambientLight +
                ", geometries=" + geometries +
                ", camera=" + camera +
                ", screen_distance=" + screen_distance +
                '}';
    }


    // ***************** Operations ******************** //


    public void addGeometry(Geometry shape){
        geometries.add(shape);
    }

    public Iterator<Geometry> getGeometriesIterator(){
        return geometries.iterator();
    }

    public void addLightSource(Light light) {
        this.lights.add(light);
    }

    public Iterator<Light> getLightsIterator() {
        return lights.iterator();
    }

}
