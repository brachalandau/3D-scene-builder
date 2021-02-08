package main.geometries;
import main.primitives.Material;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tube extends RadialGeometry {

    protected  Point3D center;
    protected  Vector axisDirection;


    // ***************** Constructors ********************** //

    public Tube() {
    }

    public Tube(Color color, Material material, double _radius, Point3D center, Vector axisDirection) {
        super(_radius);
        this.center = center;
        this.axisDirection = axisDirection;
        setEmmission(color);
        setMaterial(material);
    }

    public Tube (Tube t)
    {
      center=new Point3D(t.center);
      axisDirection=new Vector(axisDirection);
      _radius=t._radius;
      this.setEmmission(t.getEmmission());
      this.setMaterial(t.getMaterial());
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        this.center = center;
    }

    public Vector getAxisDirection() {
        return axisDirection;
    }

    public void setAxisDirection(Vector axisDirection) {
        this.axisDirection = axisDirection;
    }

    // ***************** Administration  ******************** //

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tube)) return false;
        Tube tube = (Tube) o;
        return Objects.equals(getCenter(), tube.getCenter()) &&
                Objects.equals(getAxisDirection(), tube.getAxisDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter(), getAxisDirection());
    }

    @Override
    public String toString() {
        return "Tube{" +
                "center=" + center +
                ", axisDirection=" + axisDirection +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray r) {
        return null;
    }
}
