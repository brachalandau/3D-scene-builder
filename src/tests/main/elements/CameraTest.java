package main.elements;

import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    Point3D p0=new Point3D(0,0,0);
    Vector vto=new Vector(0,0,-1);
    Vector vright= new Vector(1,0,0);
    Vector vup= new Vector(0,1,0);
    double  Nx=3;
    double  Ny=3;
    double screenDist=100;
    double screenWidth=150;
    double screenHeight=150;
    Camera c= new Camera(p0,vup,vto,vright);

        @Test
    void constructRayThroughPixel() {
            Ray p= new Ray(c.constructRayThroughPixel(Nx,Ny,3,3,screenDist,screenWidth,screenHeight));
            assertEquals(p,new Ray(new Vector(50,-50,-100),0,0,0));
    }
}