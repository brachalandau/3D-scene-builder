package main.geometries;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle extends Geometry implements FlatGeometry {
    Point3D p1;
    Point3D p2;
    Point3D p3;

    // ***************** Constructors ********************** //

    public Triangle() {
    }

    public Triangle(Color color, Material material, Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        setEmmission(color);
        setMaterial(material);
    }
    public Triangle(Point3D p1, Point3D p2, Point3D p3,Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        setEmmission(color);
        setMaterial(new Material());
    }
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Triangle T){
        p1=new Point3D(T.p1);
        p2=new Point3D(T.p2);
        p3=new Point3D(T.p3);
        setEmmission(T.getEmmission());
        setMaterial(T.getMaterial());
    }

    // ***************** Getters/Setters ********************** //


    public Point3D getP1() {
        return p1;
    }

    public void setP1(Point3D p1) {
        this.p1 = p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public void setP2(Point3D p2) {
        this.p2 = p2;
    }

    public Point3D getP3() {
        return p3;
    }

    public void setP3(Point3D p3) {
        this.p3 = p3;
    }
    // ***************** Administration  ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        Vector v1=new Vector(p1,p2);
        Vector v2=new Vector(p1,p3);
        Vector normal=v1.crossProduct(v2);
        normal=normal.normalize();
        normal.scale(-1);
        return  normal;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(getP1(), triangle.getP1()) &&
                Objects.equals(getP2(), triangle.getP2()) &&
                Objects.equals(getP3(), triangle.getP3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP1(), getP2(), getP3());
    }


    @Override
    public ArrayList<Point3D> findIntersections(Ray r) {

        ArrayList<Point3D> intersectionPoints = new ArrayList<Point3D>(1);

        Plane plane=new Plane(getEmmission(),getMaterial(),getP1(),getP2(),getP3());
        ArrayList<Point3D> PLANEintersectionPoints = new ArrayList<Point3D>(plane.findIntersections(r));

        if (PLANEintersectionPoints.isEmpty())
        {
            return intersectionPoints;
        }

        Point3D P=PLANEintersectionPoints.get(0);
        Point3D p0= new Point3D(r.getSource());
        Vector v1= new Vector(p0,getP1());
        Vector v2= new Vector(p0,getP2());
        Vector v3= new Vector(p0,getP3());

        // Checking if the interseculating point is bounded by the triangle
        Vector P_P0 = new Vector( P,p0);

        //first triangle
        Vector N1= new Vector(v1.crossProduct(v2).normalize());
        Vector help1=new Vector(p0,P);
        double S1= -N1.dotProduct(help1);

        //second triangle
        Vector N2= new Vector(v2.crossProduct(v3).normalize());
        Vector help2=new Vector(p0,P);
        double S2= -N2.dotProduct(help2);

        //third triangle
        Vector N3= new Vector(v3.crossProduct(v1).normalize());
        Vector help3=new Vector(p0,P);
        double S3= -N3.dotProduct(help3);


        int flag=0;
        if (S1<0 && S2<0 && S3<0)
            flag=1;
        if (S1>0 && S2>0 && S3>0)
            flag=1;

        if (flag==1)
            intersectionPoints.add(P);


        return  intersectionPoints;
    }
}
