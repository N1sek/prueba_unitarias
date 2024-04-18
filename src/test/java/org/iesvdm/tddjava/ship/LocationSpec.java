package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(location.getPoint().getX(), x);
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals(location.getPoint().getY(), y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals(location.getDirection(), direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        location.setDirection(Direction.NORTH);
        location.forward(max, obstacles);
        assertEquals(location.getPoint().getY(), y - 1);
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection(Direction.SOUTH);
        location.forward(max, obstacles);
        assertEquals(location.getPoint().getY(), y + 1);
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection(Direction.EAST);
        location.forward(max, obstacles);
        assertEquals(location.getPoint().getX(), x + 1);
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection(Direction.WEST);
        location.forward(max, obstacles);
        assertEquals(location.getPoint().getX(), x - 1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.setDirection(Direction.NORTH);
        location.backward(max, obstacles);
        assertEquals(location.getPoint().getY(), y + 1);
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection(Direction.SOUTH);
        location.backward(max, obstacles);
        assertEquals(location.getPoint().getY(), y - 1);
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection(Direction.EAST);
        location.backward(max, obstacles);
        assertEquals(location.getPoint().getX(), x - 1);
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection(Direction.WEST);
        location.backward(max, obstacles);
        assertEquals(location.getPoint().getX(), x + 1);
    }

    public void whenTurnLeftThenDirectionIsSet() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.turnLeft();
        Direction expectedDirection = Direction.WEST;
        assertEquals(expectedDirection, location.getDirection());
    }

    public void whenTurnRightThenDirectionIsSet() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        location.turnRight();
        Direction expectedDirection = Direction.EAST;
        assertEquals(expectedDirection, location.getDirection());
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x, y), direction);
        assertEquals(location, location1);
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Object object = new Object();
        assertNotEquals(location, object);
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x + 1, y), direction);
        assertNotEquals(location, location1);
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x, y + 1), direction);
        assertNotEquals(location, location1);
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x, y), Direction.SOUTH);
        assertNotEquals(location, location1);
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Direction direction = Direction.NORTH;
        location = new Location(new Point(x, y), direction);
        Location location1 = new Location(new Point(x, y), direction);
        assertEquals(location, location1);
    }

    public void whenCopyThenDifferentObject() {
        Location location1 = location.copy();
        assertNotSame(location, location1);
    }

    public void whenCopyThenEquals() {
        Location location1 = location.copy();
        assertEquals(location, location1);
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        Direction direction = Direction.EAST;
        location = new Location(new Point(x+100, y+100), direction);
        location.forward(max);
        int x =1;
        assertEquals(location.getX(), x);
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {

    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {

    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        Direction direction = Direction.SOUTH;
        location = new Location(new Point(x+100, y+100), direction);
        location.forward(max);
        int y = 1;
        assertEquals(location.getY(), y);
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        obstacles.add(new Point(x, y-1));
        assertFalse(location.forward(max, obstacles));
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        obstacles.add(new Point(x, y+1));
        assertFalse(location.backward(max, obstacles));
    }

}
