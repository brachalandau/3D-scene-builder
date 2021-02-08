package main.elements;

import main.geometries.Sphere;
import main.geometries.Triangle;
import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Vector;
import main.renderer.ImageWriter;
import main.renderer.Render;
import main.scenes.Scene;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {


    @Test
    public void shadowTest(){

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.getMaterial().setN(19);
        sphere.setEmmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3600, -3500, -1000),
                new Point3D(  3500, -3500, -2000));
        Material m= new Material(1,1,1,0,0);
        triangle.setMaterial(m);

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

        Material m2= new Material(1,1,1,0,0);
        triangle2.setMaterial(m2);

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        Light l=(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 1, 0.0000001, 0.0000005));

        scene.addLightSource(l);


        ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);

        Render render = new Render( imageWriter,scene);

        render.renderImage();

        imageWriter.writeToimage();

    }


    @Test
    public void shadowTestAfterPresentationChanges(){

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.getMaterial().setN(19);
        sphere.setEmmission(new Color(0, 0, 100));
        AmbientLight a= new AmbientLight(new Color(255, 100, 100),0.5);
        scene.setAmbientLight(a);

        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3600, -3500, -1000),
                new Point3D(  3500, -3500, -2000));
        Material m= new Material(1,1,1,0,0);
        triangle.setMaterial(m);

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

        Material m2= new Material(1,1,1,0,0);
        triangle2.setMaterial(m2);

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        Light l=(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                new Vector(-2, -2, -3), 1, 0.0000001, 0.0000005));

        scene.addLightSource(l);


        ImageWriter imageWriter = new ImageWriter("shadow test after presentation changes", 500, 500, 500, 500);

        Render render = new Render( imageWriter,scene);

        render.renderImage();

        imageWriter.writeToimage();

    }

    @Test
    public void pointLightTest1(){

        Scene scene = new Scene();
        scene.setScreen_distance(100);
        Sphere sphere = new Sphere (new Color(0,0,100),new Material(), 800, new Point3D(0,0,-1000));
        Material m=new Material(1,1,20,0,0);
        m.setN(20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);
        scene.addLightSource(new PointLight(new Color(255,100,100), new Point3D(-200, -200,-100),//-200, -200, -100),
                0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Point Test1 new", 500, 500, 500, 500);

        Render render = new Render( imageWriter,scene);

        render.renderImage();
        //render.printGrid(50);
        imageWriter.writeToimage();

    }


    @Test
    public void pointLightTest2(){
        Scene scene = new Scene();
        scene.setScreen_distance(100);
        Sphere sphere = new Sphere (new Color(0,0,100),new Material(), 800, new Point3D(0,0, -1000));
        Material m=new Material();
        m.setN(20);
        sphere.setMaterial(m);


        Triangle triangle = new Triangle(new Color(0,0,0), new Material(), new Point3D(  3500, 3540, -2000),
                new Point3D( -3500, -3500, -1000),  new Point3D(3500, -3600, -2000) );
        Triangle triangle2 = new Triangle(new Color(0,0,0),new Material(), new Point3D(   3500, 3500, -2000),
                new Point3D(   -3500, 3500, -1000), new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLightSource(new PointLight(new Color(255,100,100), new Point3D(-200,200, -100),
                0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Point Test2 new", 500, 500, 500, 500);
        Render render = new Render( imageWriter,scene);

        render.renderImage();
        //	render.printGrid(50);
        imageWriter.writeToimage();
    }




    @Test
    public void spotLightTest1(){

        Scene scene = new Scene();
        scene.setScreen_distance(100);
        Sphere sphere = new Sphere (new Color(0,0,100),new Material(),800, new Point3D(0,0, -1000));

        Material m=new Material();
        m.setN(20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);
        sphere.setEmmission(new Color(0, 0, 100));


        scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),  new Vector(2, 2, -3),0, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("Spot Test1 new", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        //	render.printGrid(50);
        imageWriter.writeToimage();
    }

    @Test
    public void spotLightTest2(){

        Scene scene = new Scene();
        scene.setScreen_distance(200);

        Sphere sphere = new Sphere (new Color(0,0,100),500, new Point3D(0,0,-1000));

        Material m=new Material();
        m.setN(20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Color (0, 0, 100),
                new Material(), new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270)
        );

        Material m1=new Material();
        m1.setN(4);
        triangle.setMaterial(m);
        scene.addGeometry(triangle);

        scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3),   0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot Test2 new", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        //render.printGrid(50);
        imageWriter.writeToimage();
    }

    @Test
    public void spotLightTest3(){
        Scene scene = new Scene();
        scene.setScreen_distance(100);

        Triangle triangle = new Triangle(new Color(0,0,0),new Material(),
                new Point3D(  3500,  3500, -2000),
                new Point3D( -3600, -3500, -1000),
                new Point3D(  3500, -3500, -2000)
        );

        Triangle triangle2 = new Triangle(new Color(0,0,0),new Material(),
                new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000)
        );

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3),0, 0.000001, 0.0000005));

        ImageWriter imageWriter = new ImageWriter("Spot Test3 new", 500, 500, 500, 500);

        Render render = new Render(imageWriter,scene);

        render.renderImage();
        //	render.printGrid(50);
        imageWriter.writeToimage();

    }

}






