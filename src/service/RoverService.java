package service;

import javax.management.InvalidAttributeValueException;

import exceptions.ObstacleException;
import exceptions.RoverNotInitializedException;
import model.Coordinate;

public interface RoverService {
	public void processOrder(char order) throws InvalidAttributeValueException, RoverNotInitializedException, ObstacleException;
	public void setStartPosition(char orientation, Coordinate position);
}
