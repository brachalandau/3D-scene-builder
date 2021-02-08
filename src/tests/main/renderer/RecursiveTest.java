package main.renderer;
import main.elements.*;
import main.geometries.*;
import main.primitives.Material;
import main.primitives.Vector;
import main.scenes.Scene;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
public class RecursiveTest {


    @Test
    public void recursiveTest(){

        Scene scene = new Scene();
        scene.setScreen_distance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.setN(20);
        m.setKt(0.5);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(new Color(100, 20, 20),250, new Point3D(0.0, 0.0, -1000));
        Material m2=new Material();
        m2.setN(20);
        m2.setKt(0);
        sphere2.setMaterial(m2);
        scene.addGeometry(sphere2);

        scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        imageWriter.writeToimage();

    }


    @Test
    public void recursiveTest2(){

        Scene scene = new Scene();
        scene.setScreen_distance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(-550, -500, -1000));
        Material m=new Material();
        m.setN(20);
        m.setKt(0.5);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(-550, -500, -1000));
        Material m2=new Material();
        m2.setN(20);
        m2.setKt(0);
        sphere2.setMaterial(m2);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1500,  1500, -1500),
                new Point3D(  200,  200, -375),
                new Color(20, 20, 20));

        Triangle triangle2 = new Triangle(new Point3D(  1500, -1500, -1500),
                new Point3D( -1400,  1500, -1500),
                new Point3D( -1500, -1500, -1500),
                new Color(20, 20, 20));

        Material m3=new Material();
        Material m4=new Material();
        m3.setKr(1);
        m4.setKr(0.5);
        triangle.setMaterial(m3);
        triangle2.setMaterial(m4);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLightSource(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        imageWriter.writeToimage();


    }

    @Test
    public void recursiveTest3(){

        Scene scene = new Scene();
        scene.setScreen_distance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(0, 0, -1000));
        Material m=new Material();
        m.setN(20);
        m.setKt(0.5);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(0, 0, -1000));
        Material m1=new Material();
        m1.setN(20);
        m1.setKt(0);
        sphere2.setMaterial(m1);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  2000, -1000, -1500),
                new Point3D( -1000,  2000, -1500),
                new Point3D(  700,  700, -375),
                new Color(20, 20, 20));

        Triangle triangle2 = new Triangle(new Point3D(  2000, -1000, -1500),
                new Point3D( -1000,  2000, -1500),
                new Point3D( -1000, -1000, -1500),
                new Color(20, 20, 20));

        Material m2=new Material();
        m2.setKr(1);
        triangle.setMaterial(m2);

        Material m3=new Material();
        m3.setKr(0.5);
        triangle2.setMaterial(m3);


        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLightSource(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        imageWriter.writeToimage();


    }
    @Test
    public void areTest(){

        Scene scene = new Scene();
        scene.setScreen_distance(500);
        scene.setSceneColor(new Color(255,255,200));

       // Sphere s3=new Sphere(new Color(210,183,106),new Material(),120,new Point3D(60,100,-210));

        Sphere sphere = new Sphere(new Color(255,210,0),500, new Point3D(0.0, 0.0, -2000));
        Material M=new Material();
        M.setN(20);
        M.setKt(0);//28.5
        M.setKr(85);
        sphere.setMaterial(M);
        scene.addGeometry(sphere);

        Sphere eyeb1 = new Sphere(new Color(0,100,200),100, new Point3D(-150, 80, -1500));
        Sphere eyeb2 = new Sphere(new Color(0,100,200),100, new Point3D(150, 80, -1500));
        Sphere eye1 = new Sphere(new Color(0,0,0),50, new Point3D(-150, 80, -1400));
        Sphere eye2 = new Sphere(new Color(0,0,0),50, new Point3D(150, 80, -1400));
        Sphere eyes1 = new Sphere(new Color(255,255,255),20, new Point3D(-120, 70, -1300));
        Sphere eyes2 = new Sphere(new Color(255,255,255),20, new Point3D(155, 70, -1300));

        Sphere mouth = new Sphere(new Color(255,0,0) ,60, new Point3D(-100, -100, -1500));
        Sphere mouth1 = new Sphere(new Color(255,0,0) ,60, new Point3D(100, -100, -1500));
        Sphere mouth2 = new Sphere(new Color(255,0,0) ,60, new Point3D(0, -150, -1500));

        Material m=new Material();
        m.setN(50);
        m.setKt(0.5);//28.5
        m.setKr(0.0);

        scene.addGeometry(eye1);
        scene.addGeometry(eye2);
        scene.addGeometry(eyeb1);
        scene.addGeometry(eyeb2);
        scene.addGeometry(eyes1);
        scene.addGeometry(eyes2);
        scene.addGeometry(mouth);
        scene.addGeometry(mouth1);
        scene.addGeometry(mouth2);

      scene.addLightSource(new SpotLight(new Color(255, 100, 0),  new Point3D(0, 0, -1400),
              new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("AreTest", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void finalTest(){
        Scene scene = new Scene();
        scene.setScreen_distance(10000);

        Sphere sphere = new Sphere(50, new Point3D(30, 30, -3850));
        sphere.getMaterial().setN(19);
        sphere.setEmmission(new Color(0, 0, 255));
        scene.addGeometry(sphere);

        Sphere sphere1 = new Sphere(100, new Point3D(10, 20, -4000));
        sphere1.getMaterial().setN(19);
        sphere1.setEmmission(new Color(50, 205, 50));
       scene.addGeometry(sphere1);

        Sphere sphere2 = new Sphere(30, new Point3D(-50, 30, -3870));
        sphere2.getMaterial().setN(19);
        sphere2.setEmmission(new Color(255, 0, 100));
        scene.addGeometry(sphere2);

        Light l=(new SpotLight(new Color(255, 230, 173), new Point3D(-200, -200, -3000),
                new Vector(-2, -2, -3), 1, 0.0000001, 0.0000005));

        Light l1=(new DirectionalLight(new Color(255, 230, 173), new Vector(-2, -2, -3)));

        scene.addLightSource(l);
        //scene.addLightSource(l1);


        ImageWriter imageWriter = new ImageWriter("final test", 500, 500, 500, 500);
        Render render = new Render( imageWriter,scene);
        render.renderImage();
        imageWriter.writeToimage();
    }
}