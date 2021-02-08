package main.primitives;

import java.util.Objects;

public class Ray extends Vector {

    Point3D source;
    Vector direction;

    // ***************** Constructors ********************** //
    public Ray() {
    }

    public Ray(Point3D Direction, Point3D Source) {
        this.direction=new Vector(Direction);
        this.source= new Point3D(Source);
    }

    public Ray(Vector v,double x, double y, double z) {
        direction = new Vector(v);
        source=new Point3D(x,y,z);
    }

    public Ray(Vector v, Point3D p) {
        direction=new Vector(v);
        source=new Point3D(p);
    }

    public Ray(Ray toCopy ) {
        this.source= new Point3D((toCopy.source));
        this.direction=new Vector(toCopy.direction);
    }
    // ***************** Getters/Setters ********************** //

    public Point3D getSource() {
        return source;
    }

    public void setSource(Point3D source) {
        this.source = source;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
    // ***************** Administration  ******************** //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        if (!super.equals(o)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(getSource(), ray.getSource()) &&
                Objects.equals(getDirection(), ray.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSource(), getDirection());
    }

    @Override
    public String toString() {
        return "Ray{" +
                "source=" + source +
                ", direction=" + direction +
                '}';
    }
}
