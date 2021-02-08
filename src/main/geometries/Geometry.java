package main.geometries;
import java.awt.Color;
import java.util.ArrayList;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

import static java.awt.Color.*;

public abstract class Geometry implements Intersectable {

    protected Color emmission = Color.BLACK;
    protected Material material;

    public abstract Vector getNormal(Point3D p);
    public abstract ArrayList<Point3D> findIntersections(Ray ray);

    // ***************** Constructors ********************** //


    public Geometry() {
        this.emmission = new Color(0);
        this.material=new Material();
    }

    public Geometry(int color,double kd,double ks,double nShininess)
    {
        this.emmission = new Color(color);
        this.material= new Material(kd,ks,nShininess,0,0);
    }

    public Geometry(Color emmission, Material material) {
        this.emmission = emmission;
        this.material = material;
    }


    // ***************** Getters/Setters ********************** //

    public void setMaterial(Material _material) {

        this.material = new Material(_material);
    }

    public Material getMaterial() {

        return new Material(material);
    }

    public void setEmmission(Color emmission) {

        this.emmission = emmission;
    }
    public Color getEmmission() {

        return emmission;
    }



}

