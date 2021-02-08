package main.geometries;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Material m=new Material();

    Plane p= new Plane(Color.black,m,new Point3D(1,0,0),new Point3D(0,1,0), new Point3D(0,0,2));
    @Test
    void getNormal() {
        assertEquals(p.getNormal(new Point3D(1,0,0)), new Vector(2,2,1).normalize());
    }

    List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);
    @Test
    void  findIntersections1() {

        Ray r= new Ray(new Vector(0,8,0),new Point3D(0,0,0));
        intersectionPoints.add(new Point3D(0,1,0));
        assertEquals(p.findIntersections(r),intersectionPoints);
    }

    @Test
    void  findIntersections2() {// מקרה קצה the ray is part the plane

        Ray r= new Ray(new Vector(2,2,-8),new Point3D(0,0,0));
        intersectionPoints.add(new Point3D(Infinity,Infinity,-Infinity));
        assertEquals(p.findIntersections(r).get(0).toString(),intersectionPoints.get(0).toString());
    }

    @Test
    void  findIntersections3() {// מקרה קצה the ray is parallel the plane

        Plane p1= new Plane(Color.black,m,new Point3D(1,0,0),new Point3D(0,1,0), new Point3D(0,0,0));
        Ray r= new Ray(new Vector(0,0,8),new Point3D(0,0,1));
        assertEquals(p1.findIntersections(r),intersectionPoints);
    }


}