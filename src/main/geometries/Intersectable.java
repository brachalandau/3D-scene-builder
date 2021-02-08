package main.geometries;

import main.primitives.Point3D;
import main.primitives.Ray;

import java.util.ArrayList;

public interface Intersectable {
    public ArrayList<Point3D> findIntersections(Ray r);
    public static final ArrayList<Point3D> EMPTY_LIST = new ArrayList<>();
}
