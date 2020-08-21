package controller;

import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;

public interface RoverController {
	
	public void turnRight() throws RoverNotInitializedException;
	public void turnLeft() throws RoverNotInitializedException;
	public boolean moveForwards() throws RoverNotInitializedException, ObstacleException;
	public boolean moveBackwards() throws RoverNotInitializedException, ObstacleException;
	public String checkPosition();
	public void setStartPosition(char orientation, Coordinate coords);

}
