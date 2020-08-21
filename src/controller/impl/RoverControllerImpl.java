package controller.impl;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

import controller.RoverController;
import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;
import model.Grid;
import model.GridContent;
import model.Rover;

public class RoverControllerImpl implements RoverController{
	
	private static final Logger LOG = Logger.getLogger(RoverControllerImpl.class.getSimpleName());
	
	Rover rover = Rover.getInstance();
	Grid grid = Grid.getInstance();

	@Override
	public void turnRight() throws RoverNotInitializedException {	
		if(!rover.isInitialized()) { 
			throw new RoverNotInitializedException("The rover has not been initilialized yet!"); 
		}
		
		switch(rover.getOrientation()) {
			case NORTH:
				rover.setOrientation('e');
				break;
			case SOUTH:
				rover.setOrientation('w');
				break;
			case EAST:
				rover.setOrientation('s');
				break;
			case WEST:
				rover.setOrientation('n');
				break;
		}
		
		LOG.info(checkPosition());
	}

	@Override
	public void turnLeft() throws RoverNotInitializedException {
		if(!rover.isInitialized()) { 
			throw new RoverNotInitializedException("The rover has not been initilialized yet!"); 
		}
		
		switch(rover.getOrientation()) {
			case NORTH:
				rover.setOrientation('w');
				break;
			case SOUTH:
				rover.setOrientation('e');
				break;
			case EAST:
				rover.setOrientation('n');
				break;
			case WEST:
				rover.setOrientation('s');
				break;
		}
		
		LOG.info(checkPosition());
	}

	@Override
	public boolean moveForwards() throws RoverNotInitializedException, ObstacleException {
		if(!rover.isInitialized()) { 
			throw new RoverNotInitializedException("The rover has not been initilialized yet!");
		}
		
		// try to move and check if it is possible
		moveRover(true);
				
		LOG.info(checkPosition());
		
		return true;
	}

	@Override
	public boolean moveBackwards() throws RoverNotInitializedException, ObstacleException {
		if(!rover.isInitialized()) { 
			throw new RoverNotInitializedException("The rover has not been initilialized yet!");
		}
		
		// try to move and check if it is possible
		moveRover(false);
		
		LOG.info(checkPosition());
		
		return true;
	}
	
	private void moveRover(boolean forwardsMovement) throws ObstacleException {
		Coordinate nextDestination = new Coordinate(rover.getCoords());
		Coordinate currentLocation = rover.getCoords();
		int gridContent;
		
		switch(rover.getOrientation()) {
			case NORTH:
				// Get the next Y coordinate up
				if(forwardsMovement) {
					nextDestination.setY(nextDestination.getY() == (grid.getNumRows() - 1) ? 0 : nextDestination.getY() + 1);
				}else {
					nextDestination.setY(nextDestination.getY() == 0 ? grid.getNumRows() - 1 : nextDestination.getY() - 1);
				}
				gridContent = grid.getContent(nextDestination);
				
				tryToMove(nextDestination, currentLocation, gridContent);
				break;
			case SOUTH:
				// Get the next Y coordinate down
				if(forwardsMovement) {
					nextDestination.setY(nextDestination.getY() == 0 ? grid.getNumRows() - 1 : nextDestination.getY() - 1);
				}else {
					nextDestination.setY(nextDestination.getY() == (grid.getNumRows() - 1) ? 0 : nextDestination.getY() + 1);
				}
				gridContent = grid.getContent(nextDestination);
				
				tryToMove(nextDestination, currentLocation, gridContent);
				break;
			case EAST:
				// Get the next X coordinate to the left
				if(forwardsMovement) {
					nextDestination.setX(nextDestination.getX() == (grid.getNumColumns() - 1) ? 0 : nextDestination.getX() + 1);
				}else {
					nextDestination.setX(nextDestination.getX() == 0 ? grid.getNumColumns() - 1 : nextDestination.getX() - 1);
				}
				gridContent = grid.getContent(nextDestination);
				
				tryToMove(nextDestination, currentLocation, gridContent);
				break;
			case WEST:
				// Get the next X coordinate to the right
				if(forwardsMovement) {
					nextDestination.setX(nextDestination.getX() == 0 ? grid.getNumColumns() - 1 : nextDestination.getX() - 1);
				}else {
					nextDestination.setX(nextDestination.getX() == (grid.getNumColumns() - 1) ? 0 : nextDestination.getX() + 1);
				}
				gridContent = grid.getContent(nextDestination);
				
				tryToMove(nextDestination, currentLocation, gridContent);
				break;
		}
	}

	private void tryToMove(Coordinate nextDestination, Coordinate currentLocation, int gridContent)
			throws ObstacleException {
		if(gridContent == GridContent.OBSTACLE.getCode()) {
			throw new ObstacleException("Found obstacle at (" + nextDestination + "). " + checkPosition());
		} else {
			grid.setContent(currentLocation, GridContent.EMPTY);
			grid.setContent(nextDestination, GridContent.ROVER);
			rover.setCoords(nextDestination);
		}
	}

	@Override
	public String checkPosition() {
		return "Currently I\'m at (" + rover.getCoords() + ") facing " + rover.getOrientation();
	}

	@Override
	public void setStartPosition(char orientation, Coordinate coords) {	
		// Check coordinates availability
		if(!grid.areValidCoords(coords)) {
			throw new InvalidParameterException("The given coordinates (" + coords + ") are out of the grid");
		}
		
		rover.setOrientation(orientation);
		rover.setCoords(coords);
		rover.setInitialized(true);
	}

}
