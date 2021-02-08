package main.primitives;

import java.util.Objects;

public class Point2D {
    protected Coordinate x;
    protected Coordinate y;

    // ***************** Constructors ********************** //

    public Point2D() {//default con
        this.setX(new Coordinate(Coordinate.ZERO));
        this.setY(new Coordinate(Coordinate.ZERO));
    }

    public Point2D(Coordinate x, Coordinate y) { //con
        this.setX(x);
        this.setY(y);
    }

    public Point2D(double x, double y) { //con
        this.setX(new Coordinate(x));
        this.setY(new Coordinate(y));
    }

    public Point2D(Point2D p) {//copy con
        setX(p.getX());
        setY(p.getY());
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return new Coordinate(x.get());
    }

    public Coordinate getY() {
        return new Coordinate(y.get());
    }

    public void setX(Coordinate x) {
        this.x = new Coordinate(x);
    }

    public void setY(Coordinate y) {
        this.y = new Coordinate(y);
    }


    // ***************** Administration  ******************** //

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return getX().equals(point2D.getX()) &&
                getY().equals(point2D.getY());
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // ***************** Operations ******************** //

    public Point2D add(Point2D a,Point2D b)
    {
        Point2D c= new Point2D();
        c.x= a.x.add(b.x);
        c.y= a.y.add(b.y);
        return new Point2D(c);
    }
}






/*package main.primitives;

import java.util.Objects;

public class Point2D {
    protected Coordinate x;
    protected Coordinate y;

    // ***************** Constructors ********************** //

    public Point2D() {//default con
        this.setX(new Coordinate(Coordinate.ZERO));
        this.setY(new Coordinate(Coordinate.ZERO));
    }

    public Point2D(Coordinate x, Coordinate y) { //con
        this.setX(x);
        this.setY(y);
    }

    public Point2D(Point2D p) {//copy con
        setX(p.getX());
        setY(p.getY());
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return new Coordinate(x.get());
    }

    public Coordinate getY() {
        return new Coordinate(y.get());
    }

    public void setX(Coordinate x) {
        this.x = new Coordinate(x);
    }

    public void setY(Coordinate y) {
        this.y = new Coordinate(y);
    }


    // ***************** Administration  ******************** //

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return getX().equals(point2D.getX()) &&
                getY().equals(point2D.getY());
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // ***************** Operations ******************** //

    public Point2D add(Point2D a,Point2D b)
    {
        Point2D c= new Point2D();
        c.x= a.x.add(b.x);
        c.y= a.y.add(b.y);
        return new Point2D(c);
    }
}*/
