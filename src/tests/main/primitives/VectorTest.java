package main.primitives;
import static java.lang.StrictMath.sqrt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    Vector v=new Vector();
    Vector v1=new Vector(1,1,1);
    Vector v2=new Vector(2,2,2);
    Vector v3=new Vector(0,0,1);
    Vector v4=new Vector(-1,-1,-1);
    Vector v5=new Vector(-1,0,2);

    @Test
    void add1() {
        assertEquals(v.add(v1),new Vector(1,1,1));
    }

    @Test
    void subtract1() {
        assertEquals(v1.subtract(v),new Vector(1,1,1));
        assertNotEquals(v1.subtract(v),new Vector(1,-1,1));

    }

    @Test
    void scale1() {
        assertEquals(v1.scale(9),new Vector(9,9,9));

    }

    @Test
    void crossProduct1() {
        assertEquals( v1.crossProduct(v2),new Vector());

    }

    @Test
    void length1() {
        assertEquals( v3.length(),1);

    }

    @Test
    void normalize1() {
        assertEquals(v1.normalize(),new Vector(0.5773502691896258,0.5773502691896258,0.5773502691896258));
        assertNotEquals(v1.normalize(),new Vector(0,0,1));
    }

    @Test
    void dotProduct1(){
        assertEquals( v1.dotProduct(v2),6);

    }



    // **********Edge cases:*********//

    @Test
    void add2() {
        assertEquals(v4.add(v5),new Vector(-2,-1,1));
    }

    @Test
    void subtract2() {
        assertEquals(v4.subtract(v5),new Vector(0,-1,-3));

    }

    @Test
    void scale2() {
        assertEquals(v5.scale(-2),new Vector(2,0,-4));

    }

    @Test
    void crossProduct2() {
        assertEquals( v4.crossProduct(v5),new Vector(-2,3,-1));


    }

    @Test
    void length2() {
        assertEquals( v5.length(),sqrt(5));
    }

    @Test
    void normalize2() {
        double h=sqrt(5);
        assertEquals( v5.normalize(),new Vector(-1/h,0,2/h));
    }


    @Test
    void dotProduct2(){
        assertEquals( v4.dotProduct(v5),-1);

    }
}