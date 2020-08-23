package service.impl;

import java.util.logging.Logger;

import javax.management.InvalidAttributeValueException;

import controller.GridController;
import controller.RoverController;
import controller.impl.GridControllerImpl;
import controller.impl.RoverControllerImpl;
import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;
import service.RoverService;

public class RoverServiceImpl implements RoverService{
	private static final Logger LOG = Logger.getLogger(RoverServiceImpl.class.getSimpleName());
	
	RoverController roverController = new RoverControllerImpl();
	GridController gridController = new GridControllerImpl();

	@Override
	public void processOrder(char order) throws InvalidAttributeValueException, RoverNotInitializedException, ObstacleException {
		Boolean movementDone = null;
		switch(order) {
			case 'f':
				movementDone = roverController.moveForwards();
				break;
			case 'b':
				movementDone = roverController.moveBackwards();
				break;
			case 'r':
				roverController.turnRight();
				break;
			case 'l':
				roverController.turnLeft();
				break;
			default:
				throw new InvalidAttributeValueException("Invalid order");
		}
		
		if(movementDone != null) {
			LOG.info(movementDone ? "The rover moved successfully" : "There was an error on the last movement");
		}
		System.out.println(gridController.printGrid());
	}

	@Override
	public void setStartPosition(char orientation, Coordinate position) {
		LOG.info("Starting rover location");
		roverController.setStartPosition(String.valueOf(orientation).toLowerCase().charAt(0), position);
		gridController.setRoverPosition(position);
		LOG.info("Rover location set");
		LOG.info(roverController.checkPosition());
		LOG.info("Randomizing grid content");
		gridController.randomizeContent();
		LOG.info("Grid content randomized");
		System.out.println(gridController.printGrid());
	}

}
