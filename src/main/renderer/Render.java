package main.renderer;

import main.elements.Camera;
import main.elements.Light;
import main.geometries.FlatGeometry;
import main.geometries.Geometry;
import main.geometries.Sphere;
import main.primitives.Point3D;
import main.primitives.Ray;
import main.primitives.Vector;
import main.scenes.Scene;
import java.util.Map;
import java.util.Map.Entry;

import java.awt.Color;
import java.awt.*;
import java.util.*;

public class Render {
    public Scene scene;
    public ImageWriter imageWriter;
    private final int RECURSION_LEVEL = 3;


    // ***************** Constructors ********************** //


    public Render( ImageWriter imagewriter,Scene scene) {
        this.scene = scene;
        this.imageWriter = imagewriter;
    }

    public Render(Render copy) {
        scene=new Scene((copy.scene));
        imageWriter= new ImageWriter((copy.imageWriter));
    }

    // ***************** Getters/Setters ********************** //


    public Scene getScene() {
        return new Scene(scene) ;
    }

    public void setScene(Scene scene) {
        this.scene = new Scene(scene);
    }

    public ImageWriter getImagewriter() {
        return new ImageWriter(imageWriter);
    }

    public void setImagewriter(ImageWriter imagewriter) {
        this.imageWriter = new ImageWriter(imagewriter);
    }

    // ***************** Administration  ******************** //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Render)) return false;
        Render render = (Render) o;
        return Objects.equals(getScene(), render.getScene()) &&
                Objects.equals(getImagewriter(), render.getImagewriter());
    }

    @Override
    public String toString() {
        return "Render{" +
                "scene=" + scene +
                ", imagewriter=" + imageWriter +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScene(), getImagewriter());
    }
    // ***************** Operations ******************** //

    public void writeToImage() {
        imageWriter.writeToimage();
    }

    public void printGrid(int interval) {

        int height = imageWriter.getHeight();
        int width = imageWriter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, Color.WHITE);

            }
        }
    }

    private Map<Geometry,ArrayList<Point3D>> getSceneRayIntersections(Ray ray){
        Iterator<Geometry> geometryIterator=scene.getGeometriesIterator();
        Map<Geometry,ArrayList<Point3D>> intersectionPoints= new HashMap<Geometry, ArrayList<Point3D>>();

        while (geometryIterator.hasNext())
        {
            Geometry geometry=geometryIterator.next();
            ArrayList<Point3D> geometryIntesectionPoints = new ArrayList<Point3D>(geometry.findIntersections(ray));
            if (!geometryIntesectionPoints.isEmpty())
            {
                intersectionPoints.put(geometry,geometryIntesectionPoints);
            }
        }
        return intersectionPoints;
    }

    private Map<Geometry, Point3D> getClosestPoint(
            Map<Geometry, ArrayList<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Point3D P0 = scene.getCamera().get_p0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();


        for (Map.Entry<Geometry, ArrayList<Point3D>> entry : intersectionPoints.entrySet()) {
            for (Point3D point : entry.getValue()) {
                double pointDistance = P0.Distance(point);
                if (pointDistance < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = pointDistance;
                }
            }
        }

        return minDistancePoint;

    }
    private Color addColors(Color a, Color b) {
        int R = a.getRed() + b.getRed();
        if (R > 255) R = 255;

        int G = a.getGreen() + b.getGreen();
        if (G > 255) G = 255;

        int B = a.getBlue() + b.getBlue();
        if (B > 255) B = 255;

        Color result = new Color(R, G, B);

        return result;
    }
    private Color calcColor(Geometry geometry,Point3D point,Ray inRay ){
        return(calcColor(geometry,point,inRay,0));//calls the recursive function

    }

    private Color calcColor(Geometry geometry,Point3D point,Ray inRay,int level ){//the recursive calcColor

        if (level==RECURSION_LEVEL)
            return new Color(0,0,0);
        Color ambientLight= scene.getAmbientLight().getIntensity(point);
        //Color I0=new Color(ambientLight.getRed(),ambientLight.getGreen(),ambientLight.getBlue());
        Color emission=geometry.getEmmission();

        Color inherentColors= addColors(ambientLight,emission );

        Iterator<Light> lights= scene.getLightsIterator();
        Color lightReflected = new Color(0, 0, 0);
        while (lights.hasNext()) {
            Light light = lights.next();
            if (!occluded(light, point, geometry))
            {
                Vector v = new Vector(point, scene.getCamera().get_p0());
                Color diffusiveLight = calcDiffusiveComp(geometry.getMaterial().getKd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point));
                Color specularLight = calcSpecularComp(geometry.getMaterial().getKs(), new Vector(v), geometry.getNormal(point), light.getL(point), geometry.getMaterial().getN(), light.getIntensity(point));
                lightReflected = (addColors(diffusiveLight , specularLight));

            }
        }
        Color I0 = (addColors(lightReflected, inherentColors));

        // Recursive calls

        //reflected

        Ray reflectedRay = new Ray(constructReflectedRay(geometry.getNormal(point), point, inRay));
        Entry<Geometry,Point3D> reflectedEntry= findClosesntIntersection(reflectedRay);
        Color reflected = new Color(0,0,0);
        if (reflectedEntry!=null)
        {
            reflected= calcColor(reflectedEntry.getKey(),reflectedEntry.getValue(),reflectedRay,level+1);
            double kr= geometry.getMaterial().getKr();
            int a=reflected.getRed();
            int b=reflected.getGreen();
            int c=reflected.getBlue();
            a=(int) (a*kr);
            if (a>255)
                a=255;
            b= (int) (b*kr);
            if (b>255)
                b=255;
            c=(int) (c*kr);
            if (c>255)
                c=255;
            reflected=new Color(a,b,c);
        }

        //refracted
        Ray refractedRay= new Ray (constructRefractedRay(geometry,point,inRay));
        Entry<Geometry,Point3D> refractedEntry= findClosesntIntersection(refractedRay);
        Color refracted = new Color(0,0,0);
        if (refractedEntry!=null)
        {
            refracted= calcColor(refractedEntry.getKey(),refractedEntry.getValue(),refractedRay,level+1);
            double kt= geometry.getMaterial().getKt();
            int a=refracted.getRed();
            int b=refracted.getGreen();
            int c=refracted.getBlue();
            a=(int) (a*kt);
            if (a>255)
                a=255;
            b= (int) (b*kt);
            if (b>255)
                b=255;
            c=(int) (c*kt);
            if (c>255)
                c=255;
            refracted=new Color(a,b,c);
        }

        Color envColors = addColors(reflected, refracted);
        Color finalColor = addColors(envColors, I0);

        return finalColor;
    }
    private Ray constructRefractedRay(Geometry g, Point3D point, Ray inRay) {//we got help from a friend
        Ray continuation=new Ray(inRay);
        Point3D point1=new Point3D(point);
        Vector v=new Vector(inRay.getDirection().normalize());
        v=v.scale(3);
        point1=point1.addVector(v);
        continuation.setSource(point1);
        continuation.setDirection(inRay.getDirection().normalize());
        return continuation;
    }

    private Ray constructReflectedRay(Vector n, Point3D point,Ray inRay)//we got help from a friend
    {
        Ray d=new Ray(inRay);
        Vector n1= new Vector(n);
        double dn=d.getDirection().normalize().dotProduct(n1)*2;
        n1=new Vector(n1.scale(dn));
        Vector d2dn= d.getDirection().normalize();
        d2dn=d2dn.subtract(n1);
        d.setDirection(d2dn);
        Point3D p1=new Point3D(point);
        Vector v= new Vector(d.getDirection().normalize());
        v=v.scale(2);
        Point3D p2= new Point3D(p1.addVector(v));
        d.setSource(p2);
        return  d;
    }

    public boolean occluded(Light light,Point3D point,Geometry geometry){
        Vector lightDirection= light.getL(point);
        lightDirection=lightDirection.scale(-1);
        lightDirection=lightDirection.normalize();

        Point3D geometryPoint=(new Point3D(point));
        Vector epsilonVector=new Vector(geometry.getNormal(point));
        epsilonVector=epsilonVector.scale(2);
        geometryPoint=geometryPoint.addVector(epsilonVector);
        Ray lightRay=new Ray(lightDirection,geometryPoint);

        Map<Geometry,ArrayList<Point3D>> intersectionPoints=getSceneRayIntersections(lightRay);

        if (geometry instanceof FlatGeometry){
            intersectionPoints.remove(geometry);
        }

        for (Entry<Geometry,ArrayList<Point3D>> entry: intersectionPoints.entrySet()){
            if (entry.getKey().getMaterial().getKt()==0)//its blocked
                return true;

        }
        return false;
    }



    Color calcDiffusiveComp(double kd, Vector normal,Vector L,Color intensity){
        L=L.normalize();
        normal=normal.normalize();
        double NL= Math.abs(normal.dotProduct(L));
        NL=NL*kd;
        if (NL>1)
            NL=1;
        int a=intensity.getRed();
        int b=intensity.getGreen();
        int c=intensity.getBlue();
        a=(int) (a*NL);
        a=a>255?255:a;
        b= (int) (b*NL);
        b=b>255?255:b;
        c=(int) (c*NL);
        c=c>255?255:c;
        Color defuse= new Color(a,b,c);
        return defuse;

    }

    Color calcSpecularComp(double ks,Vector v,Vector normal,Vector l,double shininess, Color intensity ){
        //D=l
        //calculating R:
        v=v.normalize();
        l=l.normalize();
        normal=normal.normalize();
        normal=normal.scale(-2*l.dotProduct(normal));
        Vector r= new Vector(l.add(normal));
        r=r.normalize();

        //calculating specular:
        double k=0;
        if (v.dotProduct(r) > 0)
            k=ks*(Math.pow(v.dotProduct(r),shininess));

        int a=intensity.getRed();
        int b=intensity.getGreen();
        int c=intensity.getBlue();
        a=(int) (a*k);
        if (a>255)
            a=255;
        b= (int) (b*k);
        if (b>255)
            b=255;
        c=(int) (c*k);
        if (c>255)
            c=255;
        Color specular= new Color(a,b,c);
        return specular;
    }

    public void renderImage() {

      /*  Camera camera = scene.getCamera();
        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {

                Ray ray =camera .constructRayThroughPixel(
                        imageWriter.getNx(), imageWriter.getNy(), j, i,
                        scene.getScreen_distance(),
                        imageWriter.getWidth(), imageWriter.getHeight());

                Entry<Geometry, Point3D> entry = findClosesntIntersection(ray);

                if (entry == null) {
                    imageWriter.writePixel(j, i, scene.getSceneColor());
                } else {
                    imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(),new Ray(ray)));
                    //imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray));

                }
            }
        }*/





            Camera camera = scene.getCamera();
            for (int i = 0; i < imageWriter.getHeight(); i++)
                for (int j = 0; j < imageWriter.getWidth(); j++) {

                    Ray ray = camera.constructRayThroughPixel(
                            imageWriter.getNx(), imageWriter.getNy(), j, i,
                            scene.getScreen_distance(),
                            imageWriter.getWidth(), imageWriter.getHeight());

                    Entry<Geometry, Point3D> intersectionPoints = findClosesntIntersection(ray);

                    if (intersectionPoints == null) {
                        imageWriter.writePixel(j, i, scene.getSceneColor());
                    } else {
                        Color color = new Color(0, 0, 0);
                        ArrayList<Ray> rays = this.scene.getCamera().ConstructRayThroughPixelSuperSampling(
                                imageWriter.getNx(), imageWriter.getNy(), j, i,
                                scene.getScreen_distance(),
                                imageWriter.getWidth(), imageWriter.getHeight
                                        ());
                        for (Iterator<Ray> iterator = rays.iterator(); iterator.hasNext(); ) {
                            Ray help = iterator.next();
                            Map<Geometry, ArrayList<Point3D>> intersectionPointsSuper = getSceneRayIntersections(help);
                            if (intersectionPointsSuper.isEmpty()) {
                                color = addColors(color, scene.getSceneColor());
                            } else {
                                Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPointsSuper);
                                Iterator it = closestPoint.entrySet().iterator();
                                while (it.hasNext()) {
                                    Entry<Geometry, Point3D> entry = (Entry) it.next(); //current entry in a loop
                                    color = addColors(color, calcColor(entry.getKey(), entry.getValue(), help));
                                }
                            }
                        }

                        int red = color.getRed() / 8;
                        int green = color.getGreen() / 8;
                        int blue = color.getBlue() / 8;

                        //middle


                        //Map<Geometry, ArrayList<Point3D>> geometryPoint3DMap = ((Map) intersectionPoints);
                        //Map<Geometry, Point3D> closestPoint = getClosestPoint(geometryPoint3DMap);
                        Color middle = calcColor((intersectionPoints).getKey(), (intersectionPoints).getValue(), ray);
                        red += middle.getRed();
                        red = red / 2;
                        green += middle.getGreen();
                        green = green / 2;
                        blue += middle.getBlue();
                        blue = blue / 2;
                        Color superSampling = new Color(red, green, blue);
                        imageWriter.writePixel(i, j, superSampling);
                    }
                }








        }

    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray) {

        Map<Geometry, ArrayList<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if (intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;

    }

}
