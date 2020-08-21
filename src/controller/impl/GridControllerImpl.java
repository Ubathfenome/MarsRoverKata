package controller.impl;

import java.util.Random;

import controller.GridController;
import global.Constants;
import model.Coordinate;
import model.Grid;
import model.GridContent;

public class GridControllerImpl implements GridController{
	
	Grid grid = Grid.getInstance();

	@Override
	public String printGrid() {
		return grid.toString();
	}

	@Override
	public void randomizeContent() {
		int randomizationsNumber = Constants.NUMBER_OF_RANDOMIZATIONS;
		int randomizedValues = 0;
		Random rand = new Random();
		
		while(randomizedValues != randomizationsNumber) {
			// Select a random index of the grid array
			int randomIndex = rand.nextInt(grid.getContent().length);
			int gridContent = grid.getContent()[randomIndex];
			if(gridContent == GridContent.EMPTY.getCode()) {
				// Set that index value to GridContent.OBSTACLE
				grid.setContentIndex(randomIndex, GridContent.OBSTACLE.getCode());
				randomizedValues++;
			}
		}
				
		grid.setRandomizedContent(true);
	}

	@Override
	public void setRoverPosition(Coordinate position) {
		grid.setContent(position, GridContent.ROVER);
		
	}

}
