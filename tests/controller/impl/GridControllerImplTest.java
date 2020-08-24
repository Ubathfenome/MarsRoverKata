package controller.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.GridController;
import global.Constants;
import model.Coordinate;
import model.GridContent;

class GridControllerImplTest {

	GridController gridController = new GridControllerImpl();
	
	@Test
	void testPrintGrid() {
		// Check that the printed grid has nRows*nColumns+nRows size. 
		// The last added nRows stands for each EOL char
		String printedGrid = gridController.printGrid();
		assertEquals(Constants.GRID_ROWS * Constants.GRID_COLUMNS + Constants.GRID_ROWS, printedGrid.length());
	}

	@Test
	void testRandomizeContent() {
		// Check that grid content has obstacles (content.value == 2)
		String grid = gridController.printGrid();
		assertEquals(false, grid.contains(String.valueOf(GridContent.OBSTACLE.getCode())));
		gridController.randomizeContent();
		grid = gridController.printGrid();
		assertEquals(true, grid.contains(String.valueOf(GridContent.OBSTACLE.getCode())));
	}

	@Test
	void testSetRoverPosition() {
		// Check that grid content has the rover (content.value == 1) on the specified position
		String grid = gridController.printGrid();
		assertEquals(false, grid.contains(String.valueOf(GridContent.ROVER.getCode())));
		gridController.setRoverPosition(new Coordinate(0,1));
		grid = gridController.printGrid();
		assertEquals(true, grid.contains(String.valueOf(GridContent.ROVER.getCode())));
		assertEquals(true, String.valueOf(grid.charAt(103)).equals(String.valueOf(GridContent.ROVER.getCode())));
	}

}
