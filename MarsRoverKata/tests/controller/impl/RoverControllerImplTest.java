package controller.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.RoverController;
import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;
import model.Orientation;

class RoverControllerImplTest {
	
	@Test
	void testTurnRight() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.NORTH;
		Coordinate startCoordinate = new Coordinate(0,0);
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		
		try {
			roverController.turnRight();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		Orientation orientation = extractOrientation(roverController.checkPosition());
		Coordinate coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.EAST, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnRight();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.SOUTH, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnRight();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.WEST, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnRight();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.NORTH, orientation);
		assertEquals(startCoordinate, coordinate);
	}

	@Test
	void testTurnLeft() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.EAST;
		Coordinate startCoordinate = new Coordinate(0,1);
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		
		Orientation orientation = null;
		Coordinate coordinate = null;
		
		try {
			roverController.turnLeft();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.NORTH, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnLeft();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.WEST, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnLeft();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.SOUTH, orientation);
		assertEquals(startCoordinate, coordinate);
		
		try {
			roverController.turnLeft();
		} catch (RoverNotInitializedException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.EAST, orientation);
		assertEquals(startCoordinate, coordinate);
	}

	@Test
	void testMoveForwards() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.WEST;
		Coordinate startCoordinate = new Coordinate(1,0);
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		
		Orientation orientation = null;
		Coordinate coordinate = null;
		
		try {
			roverController.moveForwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(startOrientation, orientation);
		assertEquals(new Coordinate(0,0), coordinate);
		
		try {
			roverController.moveForwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(startOrientation, orientation);
		assertEquals(new Coordinate(15,0), coordinate);
		
		try {
			roverController.turnLeft();
			roverController.moveForwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.SOUTH, orientation);
		assertEquals(new Coordinate(15,7), coordinate);
		
		try {
			roverController.turnLeft();
			roverController.moveForwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.EAST, orientation);
		assertEquals(new Coordinate(0,7), coordinate);
	}

	@Test
	void testMoveBackwards() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.NORTH;
		Coordinate startCoordinate = new Coordinate(1,1);
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		
		Orientation orientation = null;
		Coordinate coordinate = null;
		
		try {
			roverController.moveBackwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.NORTH, orientation);
		assertEquals(new Coordinate(1,0), coordinate);
		
		try {
			roverController.moveBackwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.NORTH, orientation);
		assertEquals(new Coordinate(1,7), coordinate);
		
		try {
			roverController.turnRight();
			roverController.moveBackwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.EAST, orientation);
		assertEquals(new Coordinate(0,7), coordinate);
		
		try {
			roverController.moveBackwards();
		} catch (RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		orientation = extractOrientation(roverController.checkPosition());
		coordinate = extractPosition(roverController.checkPosition());
		
		assertEquals(Orientation.EAST, orientation);
		assertEquals(new Coordinate(15,7), coordinate);
	}

	@Test
	void testCheckPosition() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.NORTH;
		Coordinate startCoordinate = new Coordinate(3,5);
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		// System.out.println(roverController.checkPosition());
		Coordinate coordinate = extractPosition(roverController.checkPosition());
		Orientation orientation = extractOrientation(roverController.checkPosition());
		assertEquals(startOrientation, orientation);
		assertEquals(startCoordinate, coordinate);
	}

	private Coordinate extractPosition(String checkPosition) {
		String position = checkPosition.substring(checkPosition.indexOf("(")+2, checkPosition.indexOf(")"));
		return new Coordinate(Integer.parseInt(position.split(",")[0]), Integer.parseInt(position.split(",")[1].trim()));
	}

	private Orientation extractOrientation(String checkPosition) {
		return Orientation.valueOf(checkPosition.split("facing ")[1]);
	}

	@Test
	void testSetStartPosition() {
		RoverController roverController = new RoverControllerImpl();
		
		Orientation startOrientation = Orientation.NORTH;
		Coordinate startCoordinate = new Coordinate(3,5);
		
		String rover = roverController.checkPosition();
		// NOTE: This assert does not work when running all class tests, 
		// due to the singleton implementation of the Rover
		// It does work if the test 'testSetStartPosition' is run independently
		assertTrue(rover.split("facing ")[1].equals("null"));
		assertTrue(rover.substring(rover.indexOf("(")+1, rover.indexOf(")")).equals("null"));
		
		roverController.setStartPosition(startOrientation.getValue(), startCoordinate);
		
		Coordinate coordinate = extractPosition(roverController.checkPosition());
		Orientation orientation = extractOrientation(roverController.checkPosition());
		assertEquals(startOrientation, orientation);
		assertEquals(startCoordinate, coordinate);
	}

}
