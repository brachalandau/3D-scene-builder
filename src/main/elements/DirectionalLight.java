package main.elements;

import main.primitives.Point3D;
import main.primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class DirectionalLight extends Light {
    Vector direction=null;

    // ***************** Constructors ********************** //


    public DirectionalLight() {

    }

    public DirectionalLight(Color color,Vector direction) {
        super(color);
        this.direction = new Vector(direction);
    }

    // ***************** Getters/Setters ********************** //


    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = new Vector(direction);
    }

    // ***************** Administration  ******************** //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectionalLight)) return false;
        DirectionalLight that = (DirectionalLight) o;
        return Objects.equals(getDirection(), that.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirection());
    }

    @Override
    public String toString() {
        return "DirectionalLight{" +
                "direction=" + direction +
                ", color=" + color +
                '}';
    }

    // ***************** Operations ******************** //

    @Override
    public Color getIntensity(Point3D point) {
        return this.color;
    }

    @Override
    public Vector getL(Point3D point) {
        return this.direction;
    }
}
