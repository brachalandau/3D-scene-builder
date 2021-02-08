package main.elements;

import main.primitives.Point3D;
import main.primitives.Vector;
import java.awt.Color;
import java.awt.*;
import java.util.Objects;

public class PointLight extends Light {
    Point3D position;
    double kc;
    double kj;
    double kq;
    double distance;

    // ***************** Constructors ********************** //

    public PointLight(Color color,Point3D position, double kc, double kj, double kq) {
        super(color);
        this.position = new Point3D(position);
        this.kc = kc;
        this.kj = kj;
        this.kq = kq;
    }

    public PointLight(PointLight pl)
    {
        this.color=pl.color;
        this.position = new Point3D(pl.position);
        this.kc = pl.kc;
        this.kj = pl.kj;
        this.kq = pl.kq;
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public double getKc() {
        return kc;
    }

    public void setKc(double kc) {
        this.kc = kc;
    }

    public double getKj() {
        return kj;
    }

    public void setKj(double kj) {
        this.kj = kj;
    }

    public double getKq() {
        return kq;
    }

    public void setKq(double kq) {
        this.kq = kq;
    }


    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointLight)) return false;
        PointLight that = (PointLight) o;
        return Double.compare(that.getKc(), getKc()) == 0 &&
                Double.compare(that.getKj(), getKj()) == 0 &&
                Double.compare(that.getKq(), getKq()) == 0 &&
                Double.compare(that.distance, distance) == 0 &&
                Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getKc(), getKj(), getKq(), distance);
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "position=" + position +
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

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        double d=this.position.Distance(point);

        double help= kc*kj*d*kq*d*d;
        help= 1/help;
        if (help > 1) help = 1;
        return new Color((int)(r*help),(int)(g*help),(int)(b*help));
    }

    @Override
    public Vector getL(Point3D point) {
        return new Vector(position,point);

    }
}



