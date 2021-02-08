
package main.primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
class CoordinateTest {
    Coordinate c=new Coordinate(9.9);
    @org.junit.jupiter.api.Test
    public void get() {
        assertEquals(c.get(),9.9);
    }
    @org.junit.jupiter.api.Test

    public void subtract() {
        Coordinate temp1=new Coordinate(5);
        Coordinate temp2=new Coordinate(3);
        Coordinate temp3=new Coordinate(0);
        temp3=temp2.subtract(temp1);
        Coordinate temp4=new Coordinate(-2);
        assertEquals(temp3,temp4);
    }

    @org.junit.jupiter.api.Test
    public void add() {
        Coordinate temp1=new Coordinate(5);
        Coordinate temp2=new Coordinate(3);
        Coordinate temp3=new Coordinate(0);
        temp3=temp2.add(temp1);
        Coordinate temp4=new Coordinate(8);
        assertEquals(temp4,temp3);
    }

    @org.junit.jupiter.api.Test
    public void scale() {
    }

    @org.junit.jupiter.api.Test
    public void multiply() {
    }
}
