package main.geometries;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import static java.lang.StrictMath.sqrt;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sphere extends RadialGeometry {

    protected Point3D center;

    // ***************** Constructors ********************** //

    public Sphere() {
    }

    public Sphere(Color color, Material material, double _radius, Point3D center) {
        super(_radius);
        this.center = center;
        setEmmission(color);
        setMaterial(material);

    }
    public Sphere(Color color, double _radius, Point3D center) {
        super(_radius);
        this.center = center;
        setEmmission(color);
        this.setMaterial(new Material());

    }


    public Sphere(double radius, Point3D _center)
    {
        super( radius);
        this.center = new Point3D(_center);
        this.setEmmission(new Color(0,0,0));
        this.setMaterial(new Material());
    }

    public Sphere(Sphere s)
    {
        center= new Point3D(s.center);
        _radius= s._radius;
        this.setEmmission(s.getEmmission());
        this.setMaterial(s.getMaterial());
    }
    // ***************** Getters/Setters ********************** //


    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        this.center = center;
    }

    // ***************** Administration  ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        Vector noraml=new Vector(p.subtract(center));
        noraml=noraml.normalize();
        return noraml;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sphere)) return false;
        Sphere sphere = (Sphere) o;
        return Objects.equals(getCenter(), sphere.getCenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter());
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray r) {

        ArrayList<Point3D> intersectionPoints= new ArrayList<Point3D>();

        Point3D p0=new Point3D(r.getSource());
        Vector L =new Vector(p0,this.getCenter());
        Vector V= new Vector(r.getDirection());
        V=V.normalize();
        double  tm= L.dotProduct(V);
        double d= sqrt((L.length()*L.length())-(tm*tm));

        if (d>this.get_radius())
        {
            return new ArrayList<Point3D>(intersectionPoints);
        }

        double th= sqrt((this.get_radius()*this.get_radius())-(d*d));
        double t1= tm-th;
        double t2= tm+th;
        if (t1>=0) {
            Point3D P1 = new Point3D(r.getSource().addVector(V.scale(t1)));
            intersectionPoints.add(P1);
        }
        if (d<this.get_radius())
        {
//            double t2= tm+th;
            if(t2>=0) {
                Point3D P2 = new Point3D(r.getSource().addVector(V.scale(t2)));
                intersectionPoints.add(P2);
            }
        }
        return intersectionPoints;

    }

}

