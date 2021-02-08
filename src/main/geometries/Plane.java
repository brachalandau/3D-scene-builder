package main.geometries;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plane extends  Geometry implements FlatGeometry {

    Point3D p1;
    Point3D p2;
    Point3D p3;

    // ***************** Constructors ********************** //

    public Plane() {
    }

    public Plane(Color color, Material material, Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        setEmmission(color);
        setMaterial(material);
    }
    public Plane( Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        setMaterial(new Material());
    }
    public Plane(Plane p)
    {
        p1=new Point3D(p.p1);
        p2=new Point3D(p.p2);
        p3=new Point3D(p.p3);
        this.setEmmission(p.getEmmission());
        this.setMaterial(p.getMaterial());
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
    public Vector getNormal(Point3D p) {//100

        Vector v1=new Vector(p1,p2);
        Vector v2=new Vector(p1,p3);
        Vector normal=v1.crossProduct(v2);
        normal=normal.normalize();
        return  normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return Objects.equals(p1, plane.p1) &&
                Objects.equals(p2, plane.p2) &&
                Objects.equals(p3, plane.p3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }


    @Override
    public ArrayList<Point3D> findIntersections(Ray r) {

        ArrayList<Point3D> intersectionPoints = new ArrayList<Point3D>();

        Point3D P0 = new Point3D(r.getSource());
        Point3D Q0 = new Point3D(this.getP1());
        Vector N = new Vector(this.getNormal(Q0));
        Vector V = new Vector(r.getDirection());
        Vector P0_Q0 = new Vector( Q0,P0);

        double t = ((N.dotProduct(P0_Q0)) / (N.dotProduct(V))) * (-1);

        if (t >= 0) {
            Point3D P = new Point3D(P0.addVector(V.scale(t)));
            intersectionPoints.add(P);
        }
        return new ArrayList<Point3D>(intersectionPoints);

    }
}
