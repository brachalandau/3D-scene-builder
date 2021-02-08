package main.elements;
import main.primitives.Point3D;
import main.primitives.Vector;

import java.awt.Color;
import java.util.Objects;

import static javafx.scene.paint.Color.color;

public class AmbientLight extends Light  {
    Color color;
    double  ka;// 0<=ka<=1
    // ***************** Constructors ********************** //


    public AmbientLight() {
        color=Color.white;
        ka=0.1;

    }

    public AmbientLight(AmbientLight ambientLight) {
        color= new Color(ambientLight.getColor().getRGB());
        ka = ambientLight.getKa();
    }

    public AmbientLight(Color color, double ka) {
        this.color = new Color(color.getRGB());
        this.ka = ka;
    }
    public AmbientLight(int r, int g,int b) {
        color=new Color(r, g, b);
        ka=1;
    }
    // ***************** Getters/Setters ********************** //


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    public double getKa() {
        return ka;
    }

    public void setKa(double ka) {
        this.ka = ka;
    }
    // ***************** Administration  ******************** //


    @Override
    public String toString() {
        return "AmbientLight{" +
                "color=" + color +
                ", ka=" + ka +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmbientLight)) return false;
        AmbientLight that = (AmbientLight) o;
        return Double.compare(that.getKa(), getKa()) == 0 &&
                getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getKa());
    }

    // ***************** Operations ******************** //

    public Color getIntensity(Point3D p)
    {
        float r=(float) ka*color.getRed();
        float g=(float) ka*color.getGreen();
        float b= (float) ka*color.getBlue();
        return (new Color((int)r,(int)g,(int)b));

    }

    @Override
    public Vector getL(Point3D point) {
        return null;
    }
}
