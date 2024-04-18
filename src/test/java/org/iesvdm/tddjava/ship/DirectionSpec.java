package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
        Direction expectedDirection = Direction.NORTH;
        Direction actualDirection = Direction.getFromShortName('N');
        assertEquals(expectedDirection, actualDirection);
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = Direction.getFromShortName('W');
        assertEquals(expectedDirection, actualDirection);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        Direction expectedDirection = Direction.NONE;
        Direction actualDirection = Direction.getFromShortName('X');
        assertEquals(expectedDirection, actualDirection);
    }

    public void givenSWhenLeftThenE() {
        Direction expectedDirection = Direction.EAST;
        Direction actualDirection = Direction.SOUTH.turnLeft();
        assertEquals(expectedDirection, actualDirection);
    }

    public void givenNWhenLeftThenW() {
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = Direction.NORTH.turnLeft();
        assertEquals(expectedDirection, actualDirection);
    }

    public void givenSWhenRightThenW() {
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = Direction.SOUTH.turnRight();
        assertEquals(expectedDirection, actualDirection);
    }

    public void givenWWhenRightThenN() {
        Direction expectedDirection = Direction.NORTH;
        Direction actualDirection = Direction.WEST.turnRight();
        assertEquals(expectedDirection, actualDirection);
    }

}
