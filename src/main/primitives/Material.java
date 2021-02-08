package main.primitives;

import java.util.Objects;

public class Material {
    private double Kd=1.0; // Diffusion attenuation coefficient – פיזור האור
    private double Ks=1.0; // Specular attenuation coefficient – החזרת האור
    private double n=19.0;  // Refraction index
    private double kt=0.0;
    private double kr=0.0;

// ***************** Constructors ********************** //

    public Material(double kd, double ks, double n,double kt,double kr) {
        this.Kd = kd;
        this.Ks = ks;
        this.n = n;
        this.kt=kt;
        this.kr=kr;
    }

    public Material() {
        Kd=1.0; // Diffusion attenuation coefficient – פיזור האור
        Ks=1.0; // Specular attenuation coefficient – החזרת האור
        n=1.0;  // Refraction index
        kt=0.0;
        kr=0.0;

    }

    public Material(Material m) {
        this.Kd= m.Kd;
        this.Ks=m.Ks;
        this.n=m.n;
        this.kt=m.kt;
        this.kr=m.kr;
    }

    // ***************** Getters/Setters ********************** //

    public double getKd() {
        return Kd;
    }

    public void setKd(double kd) {
        Kd = kd;
    }

    public double getKs() {
        return Ks;
    }

    public void setKs(double ks) {
        Ks = ks;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getKt() {
        return kt;
    }

    public void setKt(double kt) {
        this.kt = kt;
    }

    public double getKr() {
        return kr;
    }

    public void setKr(double kr) {
        this.kr = kr;
    }
    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return Double.compare(material.getKd(), getKd()) == 0 &&
                Double.compare(material.getKs(), getKs()) == 0 &&
                Double.compare(material.getN(), getN()) == 0 &&
                Double.compare(material.getKt(), getKt()) == 0 &&
                Double.compare(material.getKr(), getKr()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKd(), getKs(), getN(), getKt(), getKr());
    }

    @Override
    public String toString() {
        return "Material{" +
                "Kd=" + Kd +
                ", Ks=" + Ks +
                ", n=" + n +
                ", kt=" + kt +
                ", kr=" + kr +
                '}';
    }

    // ***************** Operations ******************** //







}



// ***************** Constructors ********************** //




// ***************** Getters/Setters ********************** //



// ***************** Administration  ******************** //




// ***************** Operations ******************** //





