package main.primitives;
import java.util.Objects;

import static java.lang.StrictMath.sqrt;
import static main.primitives.Util.uscale;
import static main.primitives.Util.usubtract;

public class Vector  {

    protected Point3D head;

    // ***************** Constructors ********************** //

    public Vector() {
        head=new Point3D();
    }

    public Vector(Point3D head) {
        if (head.equals(new Point3D())) {
            throw new IllegalArgumentException("not explicit Point(0,0,0) allowed");}
        setHead(head);
    }
    public Vector(double x, double y, double z) {
        setHead(new Point3D(x,y,z));
    }

    public Vector(Vector v) {
        this.head = new Point3D(v.head);
    }

    public Vector(Point3D p1,Point3D p2) {
        setHead(new Point3D(p2.getX().subtract(p1.getX()),p2.getY().subtract(p1.getY()),p2.getZ().subtract(p1.getZ())));
    }


    // ***************** Getters/Setters ********************** //


    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D p)
    {
        head=new Point3D(p);
    }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return getHead().equals(vector.getHead());// mimcha
    }


    @Override
    public String toString() {
        return "Vector:" +
                "head=(" + head.x +","+head.y+","+head.z+")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }

    // ***************** Operations ******************** //

    public Vector add(Vector v){
        Vector c=new Vector();
         c.head.x=head.x.add(v.head.x);
         c.head.y=head.y.add(v.head.y);
         c.head.z=head.z.add(v.head.z);
         return c;
     }
     public Vector subtract(Vector v)
     {
         Vector c=new Vector();
         c.head.x=head.x.subtract(v.head.x);
         c.head.y=head.y.subtract(v.head.y);
         c.head.z=head.z.subtract(v.head.z);
         return c;
     }

     public Vector scale(double d)
     {
         Vector c=new Vector();
         c.head.x._coord=head.x._coord*d;
         c.head.y._coord=head.y._coord*d;
         c.head.z._coord=head.z._coord*d;
         return c;
     }

     public Vector crossProduct(Vector cp)
     {
         Vector c=new Vector();
         c.head.x._coord=usubtract(uscale(this.head.y._coord,cp.head.z._coord),uscale(this.head.z._coord,cp.head.y._coord));
         c.head.y._coord=usubtract(uscale(this.head.z._coord,cp.head.x._coord),uscale(this.head.x._coord,cp.head.z._coord));
         c.head.z._coord=usubtract(uscale(this.head.x._coord,cp.head.y._coord),uscale(this.head.y._coord,cp.head.x._coord));
         return c;
     }

     public double length( )
     {
         if (this.equals(new Vector(0,0,0)))
             throw new ArithmeticException("the vector that was sent equals to vector zero (0,0,0)");
         double l;
         l=head.x._coord*head.x._coord;
         l+=head.y._coord*head.y._coord;
         l+=head.z._coord*head.z._coord;
         l=sqrt(l);
         return l;
     }

     public Vector normalize()
     {
         if (head.equals(new Point3D()))
            throw new ArithmeticException("the vector that was sent equals to vector zero (0,0,0)");
         double l=this.length();
         if (l==0)
         {
             throw new ArithmeticException("vector length is zero, cant divide vector by length=0");
         }
         Vector c=new Vector();
         c.head.x._coord=  this.head.x._coord/l;
         c.head.y._coord=  this.head.y._coord/l;
         c.head.z._coord=  this.head.z._coord/l;
         return c;
     }
     public double dotProduct(Vector v)
     {
         double dp;
         dp=head.x._coord*v.head.x._coord;
         dp+=head.y._coord*v.head.y._coord;
         dp+=head.z._coord*v.head.z._coord;
         return  dp;
     }
}
