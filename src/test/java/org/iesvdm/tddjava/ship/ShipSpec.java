package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);

    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        Location expected = new Location(new Point(21, 12), Direction.NORTH);
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = new Location(new Point(21, 14), Direction.NORTH);
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenLeft() {
        Location expected = new Location(new Point(21, 13), Direction.WEST);
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenRight() {
        Location expected = new Location(new Point(21, 13), Direction.EAST);
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsFThenForward() {
        Location expected = new Location(new Point(21, 12), Direction.NORTH);
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location expected = new Location(new Point(21, 14), Direction.NORTH);
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Location expected = new Location(new Point(21, 13), Direction.WEST);
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsRThenTurnRight() {
        Location expected = new Location(new Point(21, 13), Direction.EAST);
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Location expected = new Location(new Point(21, 12), Direction.WEST);
        ship.receiveCommands("fflff");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenInstantiatedThenPlanetIsStored() {
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);
        assertEquals(ship.getPlanet(), planet);
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(50);
        ship.receiveCommands("f");
        assertEquals(ship.getLocation().getPoint().getX(), 1);
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getPoint().getX(), 50);
    }

    public void whenReceiveCommandsThenStopOnObstacle() {

    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {

    }

}
