package main.geometries;

import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class Cylinder extends Tube {

    protected  double hight;

    // ***************** Constructors ********************** //
    public Cylinder() {
    }

    public Cylinder(Color color , Material material, double _radius, Point3D center, Vector axisDirection, double hight) {
        super(color,material,_radius, center, axisDirection);
        this.hight = hight;
    }

    public Cylinder(Cylinder c)
    {
        super(c.getEmmission(),c.getMaterial(),c._radius, c.center, c.axisDirection);
        this.hight=c.hight;

    }

    // ***************** Getters/Setters ********************** //


    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    // ***************** Administration  ******************** //

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + hight +
                ", center=" + center +
                ", axisDirection=" + axisDirection +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cylinder)) return false;
        if (!super.equals(o)) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder.getHight(), getHight()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHight());
    }
}
