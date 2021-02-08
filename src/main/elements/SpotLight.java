package main.elements;

import main.primitives.Point3D;
import main.primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class SpotLight extends PointLight {
    Vector direction;

// ***************** Constructors ********************** //

    public SpotLight(Color color, Point3D position,Vector d, double kc, double kj, double kq) {

        super(color,position, kc, kj, kq);
        direction=new Vector(d);
    }

    public SpotLight(SpotLight sl) {
        super(new PointLight(sl.color,new Point3D(sl.position),sl.kc,sl.kj,sl.kq));
        this.direction = new Vector(sl.direction);
    }
    // ***************** Getters/Setters ********************** //

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
        if (!(o instanceof SpotLight)) return false;
        if (!super.equals(o)) return false;
        SpotLight spotLight = (SpotLight) o;
        return Objects.equals(getDirection(), spotLight.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDirection());
    }

    @Override
    public String toString() {
        return "SpotLight{" +
                "direction=" + direction +
                ", position=" + position +
                ", kc=" + kc +
                ", kj=" + kj +
                ", kq=" + kq +
                ", distance=" + distance +
                ", color=" + color +
                '}';
    }
    // ***************** Operations ******************** //


    @Override
    public Color getIntensity(Point3D point) {

        Color i=super.getIntensity(point);
        int r = i.getRed();
        int g = i.getGreen();
        int b = i.getBlue();

        direction=direction.normalize();
        double v=(direction.dotProduct(new Vector(getL(point)).normalize()));
        if (v<0)
            v=0;
        if (v>1) v=1;

        return new Color((int)(r * v),(int)(g*v),(int)(b*v));
    }

    @Override
    public Vector getL(Point3D point) {
        return super.getL(point);
    }



}
