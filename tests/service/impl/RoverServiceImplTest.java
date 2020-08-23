package service.impl;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.InvalidAttributeValueException;

import org.junit.jupiter.api.Test;

import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;
import model.Orientation;
import service.RoverService;

class RoverServiceImplTest {
	
	RoverService roverService = new RoverServiceImpl();

	@Test
	void testProcessOrder() {
		
		Orientation orientation = Orientation.NORTH;
		Coordinate position = new Coordinate(3,7);
		
		roverService.setStartPosition(orientation.getValue(), position);
		
		try {
			roverService.processOrder('f');
		} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		try {
			roverService.processOrder('b');
		} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		try {
			roverService.processOrder('r');
		} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		try {
			roverService.processOrder('l');
		} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
			fail(e.getMessage());
		}
		
		try {
			roverService.processOrder('z');
		} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
			assertEquals("Invalid order", e.getMessage());
		}
		
		
	}

	@Test
	void testSetStartPosition() {
		Orientation orientation = Orientation.NORTH;
		Coordinate position = new Coordinate(3,7);
		
		try {
			roverService.processOrder('f');
		} catch (InvalidAttributeValueException e) {
			fail(e.getMessage());
		} catch (RoverNotInitializedException e) {
			assertEquals("The rover has not been initilialized yet!", e.getMessage());
		} catch (ObstacleException e) {
			fail(e.getMessage());
		}
		
		roverService.setStartPosition(orientation.getValue(), position);
		
		try {
			roverService.processOrder('f');
		} catch (InvalidAttributeValueException e) {
			fail(e.getMessage());
		} catch (RoverNotInitializedException e) {
			assertEquals("The rover has not been initilialized yet!", e.getMessage());
		} catch (ObstacleException e) {
			fail(e.getMessage());
		}
	}

}
