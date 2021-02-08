package main.geometries;

public abstract class RadialGeometry extends Geometry {

    protected double _radius;

    // ***************** Constructors ********************** //

    public RadialGeometry() {
    }

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }


    // ***************** Getters/Setters ********************** //
    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }


}
