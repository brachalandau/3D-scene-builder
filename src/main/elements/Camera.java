package main.elements;

import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.primitives.Util.usubtract;

public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;
    // ***************** Constructors ********************** //


    public Camera() {
        _p0 = new Point3D(0, 0, 10);
        _vUp = new Vector(1, 0, 0.0);
        _vTo = new Vector(0.0, 0.0, -1.0);

        _vRight = _vTo.crossProduct(_vUp);
    }

    public Camera(Point3D _p0, Vector _vUp, Vector _vTo, Vector _vRight) {
        this._p0 = new Point3D(_p0);
        this._vUp = new Vector(_vUp);
        this._vTo = new Vector(_vTo);
        this._vRight = new Vector(_vRight);
    }

    public Camera(Vector _vUp, Vector _vRight) {
        this._p0 = new Point3D(0, 0, 0);
        this._vUp = new Vector(_vUp);
        this._vTo = new Vector(_vTo);
        this._vRight = new Vector(_vTo.crossProduct(_vUp).normalize());
    }

    public Camera(Point3D p, Vector _vUp, Vector _vRight) {
        this._p0 = new Point3D(p);
        this._vUp = new Vector(_vUp);
        this._vTo = new Vector(_vTo);
        this._vRight = new Vector(_vTo.crossProduct(_vUp).normalize());
    }

    public Camera(Camera camera) {
        this._p0 = new Point3D(camera._p0);
        this._vUp = new Vector(camera._vUp);
        this._vTo = new Vector(camera._vTo);
        this._vRight = new Vector(camera._vRight);
    }


    // ***************** Getters/Setters ********************** //

    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    public void set_p0(Point3D _p0) {
        this._p0 = new Point3D(_p0);
    }

    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    public void set_vUp(Vector _vUp) {
        this._vUp = new Vector(_vUp);
    }

    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    public void set_vTo(Vector _vTo) {
        this._vTo = new Vector(_vTo);
    }

    public Vector get_vRight() {
        return new Vector(_vRight);
    }

    public void set_vRight(Vector _vRight) {
        this._vRight = new Vector(_vRight);
    }


    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Camera)) return false;
        Camera camera = (Camera) o;
        return Objects.equals(get_p0(), camera.get_p0()) &&
                Objects.equals(get_vUp(), camera.get_vUp()) &&
                Objects.equals(get_vTo(), camera.get_vTo()) &&
                Objects.equals(get_vRight(), camera.get_vRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_p0(), get_vUp(), get_vTo(), get_vRight());
    }

    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vUp +
                ", _vTo=" + _vTo +
                ", _vRight=" + _vRight +
                '}';
    }

    // ***************** Operations ******************** //

    public Ray constructRayThroughPixel(double Nx, double Ny, double x, double y, double screenDist, double screenWidth, double screenHeight) {
        // calculates pc:
        Vector pcHelp = new Vector(_vTo);
        pcHelp = pcHelp.scale(screenDist);
        Point3D pc = _p0.addVector(pcHelp);

        // calculates  vright - we may not need this
        _vRight = new Vector(_vTo.crossProduct(_vUp));
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;


        //from here its the big formula//
        double x7 = (((x - (Nx / 2)) * Rx) - (Rx / 2)); // ((x- [#pixelsx/ 2])Rx)+(Rx/2)
        double y7 = (((y - (Ny / 2)) * Ry) - (Ry / 2));

        Vector vR = new Vector(_vRight.scale(x7));
        Vector vU = new Vector(_vUp.scale(y7));
        Vector help = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p = new Ray(pc, _p0);
        return p;
    }

    public ArrayList<Ray> ConstructRayThroughPixelSuperSampling(double Nx, double Ny, double x, double y, double screenDist, double screenWidth, double screenHeight) {

        // calculates pc:
        Vector pcHelp = new Vector(_vTo);
        pcHelp = pcHelp.scale(screenDist);
        Point3D pc = _p0.addVector(pcHelp);

        // calculates  vright - we may not need this
        Vector _vRight = new Vector(_vTo.crossProduct(_vUp));
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;
        ArrayList<Ray> rays = new ArrayList<>();
//## we wrote -rx/2 in the formula, but in the presentations it says +rx/2. same about ry/2/


        double x7 = (((x - (Nx / 2)) * Rx));
        double y7 = (((y - (Ny / 2)) * Ry));
        Vector vR = new Vector(_vRight.scale(x7 + Rx / 2));
        Vector vU = new Vector(_vUp.scale(y7));
        Vector help = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p = new Ray(pc, _p0);
        rays.add(p);


        _vRight.scale(x7 + Rx);
        Vector _vup = new Vector(_vUp.scale(y7));
        Vector help0 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help0);

        Ray p1 = new Ray(pc, _p0);
        rays.add(p1);

        Vector vR1 = new Vector(_vRight.scale(x7));
        Vector vU1 = new Vector(_vUp.scale(y7));
        Vector help1 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help1);

        Ray p2 = new Ray(pc, _p0);
        rays.add(p2);


        Vector vR2 = new Vector(_vRight.scale(x7));
        Vector vU2 = new Vector(_vUp.scale(y7 + Ry / 2));
        Vector help2 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help2);

        Ray p3 = new Ray(pc, _p0);
        rays.add(p3);


//###left top


        Vector vR3 = new Vector(_vRight.scale(x7));
        Vector vU3 = new Vector(_vUp.scale(y7 + Ry));
        Vector help3 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p4 = new Ray(pc, _p0);
        rays.add(p4);


//##top middle


        Vector vR4 = new Vector(_vRight.scale(x7 + Rx / 2));
        Vector vU4 = new Vector(_vUp.scale(y7 + Ry));
        Vector help4 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p5 = new Ray(pc, _p0);
        rays.add(p5);

//##top right

        Vector vR5 = new Vector(_vRight.scale(x7 + Rx));
        Vector vU5 = new Vector(_vUp.scale(y7 + Ry));
        Vector help5 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p6 = new Ray(pc, _p0);
        rays.add(p6);


//###right middle

        Vector vR6 = new Vector(_vRight.scale(x7 + Rx));
        Vector vU6 = new Vector(_vUp.scale(y7 + Ry / 2));
        Vector help6 = new Vector(vR.subtract(vU));
        pc = pc.addVector(help);

        Ray p7 = new Ray(pc, _p0);
        rays.add(p7);
        return rays;

    }
}

/*





        ArrayList<Ray> rays=new ArrayList<Ray>();
        double rx=screenWidth/Nx;
        double ry=screenHeight/Ny;
        double tempX=(x-(Nx/2.0))*rx;
        double tempY=(y-(Ny/2.0))*ry;

        //point up  right
        double urX=tempX+rx;
        double urY=tempY;
        Vector vec1 = new Vector(_p0.addVector(_vTo.scale(screenDist)).addVector(_vRight.scale(urX).subtract(_vUp.scale(urY))));
        Point3D tempPoint1 = new Point3D(vec1.getHead());
        Ray rayUR = new Ray( vec1,_p0);
        rays.add(rayUR);

        //point down left
        double dlX=tempX;
        double dlY=tempY+ry;
        Vector vec2 = new Vector(_p0.addVector(_vTo.scale(screenDist)).addVector(_vRight.scale(dlX).subtract(_vUp.scale(dlY))));
        Point3D tempPoint2= new Point3D(vec2.getHead());

        Ray rayDL = new Ray( vec2,_p0);
        rays.add(rayDL);

        //point up left
        double ulX=tempX;
        double ulY=tempY;
        Vector vec3 = new Vector(_p0.addVector(_vTo.scale(screenDist)).addVector(_vRight.scale(ulX).subtract(_vUp.scale(ulY))));
        Point3D tempPoint3= new Point3D(vec3.getHead());
        Ray rayUL = new Ray( vec3,_p0);
        rays.add(rayUL);

        //point down right
        double drX=tempX;
        double drY=tempY;
        Vector vec4 = new Vector(_p0.addVector(_vTo.scale(screenDist)).addVector(_vRight.scale(drX).subtract(_vUp.scale(drY))));
        Point3D tempPoint4= new Point3D(vec4.getHead());
        Ray rayDR = new Ray( vec4,_p0);
        rays.add(rayDR);

        return rays;

    }*/



















