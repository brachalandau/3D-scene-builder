package main.elements;

import main.primitives.Point3D;
import main.primitives.Vector;

import java.awt.*;

public abstract class Light {
    protected Color color=new Color(0,0,0);
    abstract public Color getIntensity(Point3D point);
    abstract  public Vector getL(Point3D point);

    public Light() {
        this.color=new Color(0,0,0);
    }

    public Light(Color color_) {
        this.color = color_;
    }

    public Color getColor() {
        return this.color;
    }

    public Color setColor() {
        return this.color;
    }

}
