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

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    Material m=new Material();


    Triangle p= new Triangle(Color.black,m,new Point3D(1,0,0),new Point3D(0,1,0), new Point3D(0,0,2));

    @Test
    void getNormal() {
        assertEquals(p.getNormal(new Point3D(1,0,0)), new Vector(2,2,1).normalize());
    }

    List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);
    Vector vto=new Vector(0,0,-1);
    Vector vright= new Vector(1,0,0);
    Vector vup= new Vector(0,0,0);
    double  Nx=3;
    double  Ny=3;
    double screenDist=100;
    double screenWidth=9;
    double screenHeight=9;
    Point3D p0= new Point3D(0,0,0);
    Camera c= new Camera(p0,vup,vto,vright);

    @Test
    void  findIntersections1() {
        Triangle t=new Triangle(Color.black,m,new Point3D(0,4.5,-4), new Point3D(-4.5,-4.5,-4), new Point3D(4.5,-4.5,-4));
        Ray r=new Ray(c.constructRayThroughPixel(3,3,2.0,2.0,1.0,9.0,9.0));
        t.findIntersections(r);
        intersectionPoints.add(new Point3D(0.0, 0.0, -4.0));
        assertEquals(t.findIntersections(r),intersectionPoints);

    }
    @Test
    void  findIntersections2() {// mikre katze- no intersection points

        Triangle t=new Triangle(Color.black,m,new Point3D(0,100,-200), new Point3D(100,-100,-200), new Point3D(-100,-100,-200));
        Ray r= new Ray(new Vector(5,100,-200),new Point3D(0,0,0));
        t.findIntersections(r);
        assertEquals(t.findIntersections(r),intersectionPoints);

    }

}