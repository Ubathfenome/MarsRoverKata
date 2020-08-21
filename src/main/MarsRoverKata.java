package main;

import java.util.logging.Logger;

import javax.management.InvalidAttributeValueException;

import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;
import service.RoverService;
import service.impl.RoverServiceImpl;

public class MarsRoverKata {
	
	private static final Logger LOG = Logger.getLogger(MarsRoverKata.class.getSimpleName());
	
	static RoverService roverService = new RoverServiceImpl();
	
	private static final char STARTING_ORIENTATION = 'n';
	private static final Coordinate STARTING_COORDINATE = new Coordinate(1,0);
	
	private static final String ROVER_KATA = "frfflbllfffrrflb";

	public static void main(String[] params) {
		LOG.info("Starting MarsRoverKata app");
		
		char orientation = STARTING_ORIENTATION;
		Coordinate coords = STARTING_COORDINATE;
		String kata = ROVER_KATA;
		
		if(params.length == 4) {
			boolean validated = validateParams(params);
			if(validated) {
				orientation = params[0].charAt(0);
				coords = new Coordinate(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
				kata = params[3];
			}
		} else {
			LOG.warning("Invalid number of params. Using default values");
		}
		
		LOG.info("Setting starting point and orientation");
		roverService.setStartPosition(orientation, coords);
		
		LOG.info("Sending orders kata to rover");
		int sequenceLenght = kata.length();
		for(int i = 0; i < sequenceLenght; i++) {
			char order = kata.charAt(i);
			try {
				roverService.processOrder(order);
			} catch (InvalidAttributeValueException | RoverNotInitializedException | ObstacleException e) {
				LOG.severe(e.getMessage());
				break;
			}
		}		
		
		LOG.info("Ending MarsRoverKata app");
	}

	private static boolean validateParams(String[] params) {
		if(params[0].length() != 1 ||
				params[0].chars().allMatch( Character::isDigit ) ||
				!params[1].chars().allMatch( Character::isDigit ) ||
				!params[2].chars().allMatch( Character::isDigit )) {
			return false;
		}
		return true;
	}

}
