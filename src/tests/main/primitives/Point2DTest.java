package main.primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {

    Point2D p = new Point2D
            (new Coordinate(1.678), new Coordinate(0.009876)) ;
    Point2D p1 =new Point2D(new Coordinate(3), new Coordinate(3));
    Point2D p2 =new Point2D(new Coordinate(6), new Coordinate(6));
    @Test
    void getX() {
        assertEquals(p.getX(), new Coordinate(1.678));
    }

    @Test
    void getY() {
        assertEquals(p.getY(), new Coordinate(0.009876));
    }

    @Test
    void setX() {
        p.setX(new Coordinate(3));
        assertEquals(p.getX(),new Coordinate(3));
    }

    @Test
    void setY() {
        p.setY(new Coordinate(3));
        assertEquals(p.getY(),new Coordinate(3));
    }

    @Test
    void equals() {
        assertEquals(new Point2D(p),p);
    }

    @Test
    void toStringTest() {
        assertEquals(p.toString(),"Point2D{" +
                "x=" + p.getX() +
                ", y=" + p.getY()+
                '}');
    }

    @Test
    void add() {
        this.p.setY(new Coordinate(3));
        this.p.setX(new Coordinate(3));
        assertEquals(p.add(p1,p),p2 );
    }


}



