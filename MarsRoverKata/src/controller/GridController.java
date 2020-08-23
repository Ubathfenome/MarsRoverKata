package controller;

import model.Coordinate;

public interface GridController {
	public String printGrid();
	public void randomizeContent();
	public void setRoverPosition(Coordinate position);
}
