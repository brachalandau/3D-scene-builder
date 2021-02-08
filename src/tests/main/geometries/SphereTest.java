package main.geometries;

import main.elements.Camera;
import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    Material m=new Material();

    Sphere s= new Sphere(Color.black,m, 5, new Point3D(2,3,4));

    @Test
    void getNormal() {
        assertEquals(s.getNormal(new Point3D(6,7,8)), new Vector(4,4,4).normalize());

    }

    List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);
    Vector vto=new Vector(0,0,-1);
    Vector vright= new Vector(1,0,0);
    Vector vup= new Vector(0,1,0);
    double  Nx=3;
    double  Ny=3;
    double screenDist=100;
    double screenWidth=150;
    double screenHeight=150;
    Point3D p0= new Point3D(0,0,0);
    Camera c= new Camera(p0,vup,vto,vright);

    @Test
    void  findIntersections1() {
        Sphere s=new Sphere( Color.black,m,5,new Point3D(0,0,-400));
        double h= 1/(sqrt(3));
        Ray r= new Ray(new Vector(h,h*-1,h*-1),new Point3D(0,0,0));
        assertEquals(s.findIntersections(r),intersectionPoints);

    }
    @Test
    void  findIntersections2() {//the view plane is inside of the sphere מקרה קצה
        List<Point3D> TESTintersectionPoints= new ArrayList<Point3D>(2);
        TESTintersectionPoints.add(new Point3D(0,0,-7));
        Sphere s=new Sphere( Color.black,m,5,new Point3D(0,0,-2));
        Ray r=new Ray(new Vector(vto),new Point3D(0,0,0));
        assertEquals(s.findIntersections(r),TESTintersectionPoints);


    }


}