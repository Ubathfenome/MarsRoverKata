package model;

import java.util.Arrays;

import global.Constants;

public class Grid {
	
	private int numRows = Constants.GRID_ROWS;
	private int numColumns = Constants.GRID_COLUMNS;
	
	private int[] content;
	
	private boolean randomizedContent = false;
	
	private static Grid INSTANCE = null;
	
	private Grid() {
		
		content = new int[numRows * numColumns];
		Arrays.fill(content, GridContent.EMPTY.getCode());
		
	}
	
	private synchronized static void createInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Grid();
		}			
	}
	
	public static Grid getInstance() {
		if (INSTANCE == null) { 
			createInstance();
		} 
		return INSTANCE;
	}

	public int[] getContent() {
		return content;
	}

	public int getContent(Coordinate nextDestination) {
		return content[getIndex(nextDestination)];
	}
	
	public void setContent(Coordinate coords, GridContent value) {
		this.content[getIndex(coords)] = value.getCode();
	}
	
	public void setContentIndex(int index, int value) {
		this.content[index] = value;		
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	private int getIndex(Coordinate coords) {
		// Translate x,y coordinates to a valid array index & return the value of that position
		// Coords (0,2) = index (32)		Coords (1,2) = index (33)
		// Coords (0,1) = index (16)		Coords (1,1) = index (17)
		// Coords (0,0) = index (0)			Coords (1,0) = index (1)
		
		return coords.getY() * numColumns + coords.getX();
	}
	
	public boolean areValidCoords(Coordinate coords) {
		if(coords.getX() < 0 || coords.getX() >= numColumns || coords.getY() < 0 || coords.getY() >= numRows) {
			return false;
		}
		return true;
	}	

	public boolean isRandomizedContent() {
		return randomizedContent;
	}

	public void setRandomizedContent(boolean randomizedContent) {
		this.randomizedContent = randomizedContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int row = numRows-1; row >= 0; row--) {
			for(int column = 0; column < numColumns; column++) {
				builder.append(content[getIndex(new Coordinate(column, row))]);
			}
			builder.append("\n");
		}
		/*return "Grid has " + numRows + " rows and " + numColumns + " columns.\n"
				+ "It\'s content has" + (randomizedContent ? "" : " NOT") + " been randomized.\n"
						+ "Content: " + builder.toString();*/
		return builder.toString();
	}

}
