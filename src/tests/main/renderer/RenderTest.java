package main.renderer;

import main.elements.AmbientLight;
import main.elements.Camera;
import main.elements.PointLight;
import main.elements.SpotLight;
import main.geometries.Geometry;
import main.primitives.Material;
import main.primitives.Vector;
import main.scenes.Scene;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import main.geometries.Sphere;
import main.geometries.Triangle;
import main.primitives.Point3D;
import main.renderer.ImageWriter;
import main.renderer.Render;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class RenderTest {

    @Test


    public void basicRendering(){
        Material m=new Material();
        Scene scene = new Scene();

        scene.addGeometry(new Sphere(Color.blue,m, 50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(Color.red,m,new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(Color.green,m,new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(Color.orange,m,new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(Color.pink,m,
                new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render( imageWriter,scene);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();


    }
    @Test
    public void basicRendering1()
    {
        Scene scene = new Scene();
        ImageWriter imageWriter = new ImageWriter("Render test2", 500, 500, 10, 10);

        Render render = new Render( imageWriter,scene);
        render.printGrid(50);
        imageWriter.writeToimage();

    }


}
