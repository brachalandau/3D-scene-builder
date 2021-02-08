package main.primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    Point3D p=new Point3D(3,3,3);
    Point3D p1=new Point3D(3,3,3);
    Point3D p2=new Point3D(0,0,0);
    Vector v=new Vector(p1,p);
    @Test
    void subtract() {
        assertEquals(p.subtract(p1),v);

    }

    @Test
    void addVector() {
        assertEquals(p.addVector(v),p1);

    }
}