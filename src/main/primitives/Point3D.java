package main.primitives;
import java.util.Objects;

public class Point3D extends Point2D{

    protected Coordinate z;

    // ***************** Constructors ********************** //

    public Point3D() {
        super();
        this.setZ(new Coordinate(Coordinate.ZERO));
    }
    public Point3D(Coordinate X, Coordinate Y,Coordinate Z) {
        super(X,Y);
        this.setZ(new Coordinate(Z));
    }
    public Point3D(double X, double Y,double Z) {
        super(X,Y);
        this.setZ(new Coordinate(Z));
    }

    //copy con
    public Point3D(Point3D p) {
        setX(p.getX());
        setY(p.getY());
        setZ(p.getZ());
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getZ() {
        return new Coordinate(z.get());
    }

    public void setZ(Coordinate z) {
        this.z = new Coordinate(z);
    }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null) return false;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return getZ().equals(point3D.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getZ());
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    // ***************** Operations ******************** //

    public Vector subtract( Point3D p)
    {
        return new Vector(p,this);
    }

    public Point3D addVector(Vector v)
    {
//        Point3D h=v.head;
//        Point3D result =new Point3D();
//        result.x=this.x.add(h.x);
//        result.y=this.y.add(h.y);
//        result.z=this.z.add(h.z);
//        return result;
        return new Point3D(
                v.head.x.add(x),
                v.head.y.add(y),
                v.head.z.add(z)
                );
    }



    public double Distance(Point3D a){
        Vector v=new Vector(this ,a);
        double distance=v.length();
        return distance;
    }

    public double DistancePow(Point3D a){
        double distancePow=this.Distance(a);
        distancePow=distancePow*distancePow;
        return distancePow;
    }
}

/*package main.primitives;
import java.util.Objects;

public class Point3D extends Point2D{

    protected Coordinate z;

    // ***************** Constructors ********************** //

    public Point3D() {
        this.setX(new Coordinate(Coordinate.ZERO));
        this.setY(new Coordinate(Coordinate.ZERO));
        this.setZ(new Coordinate(Coordinate.ZERO));
    }
    public Point3D(Coordinate X, Coordinate Y,Coordinate Z) {
        this.setX(new Coordinate(X));
        this.setY(new Coordinate(Y));
        this.setZ(new Coordinate(Z));
    }
    public Point3D(double X, double Y,double Z) {
        this.setX(new Coordinate(X));
        this.setY(new Coordinate(Y));
        this.setZ(new Coordinate(Z));
    }

    //copy con
    public Point3D(Point3D p) {
        setX(new Coordinate(p.getX()));
        setY(new Coordinate(p.getY()));
        setZ(new Coordinate(p.getZ()));
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getZ() {
        return new Coordinate(z.get());
    }

    public void setZ(Coordinate z) {
        this.z = new Coordinate(z);
    }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null) return false;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return getZ().equals(point3D.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getZ());
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    // ***************** Operations ******************** //

    public Vector subtract( Point3D p)
    {
        return new Vector(p,this);
    }

    public Point3D addVector(Vector v)
    {
        Point3D h=v.head;
        Point3D result =new Point3D();
        result.x=this.x.add(h.x);
        result.y=this.y.add(h.y);
        result.z=this.z.add(h.z);
        return result;
    }



    public double Distance(Point3D a){
        Vector v=new Vector(this ,a);
        double distance=v.length();
        return distance;
    }

    public double DistancePow(Point3D a){
        double distancePow=this.Distance(a);
        distancePow=distancePow*distancePow;
        return distancePow;
    }
}*/