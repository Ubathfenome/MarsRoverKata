package model;

public enum Orientation {
	NORTH('n'), SOUTH('s'), EAST('e'), WEST('w');
	
	private final char value;
	
	Orientation(char value){
		this.value = value;
	}
	
	public char getValue() {
		return this.value;
	}
	
	public static Orientation valueOf(char value) {
		if(NORTH.getValue() == value)
			return NORTH;
		else if(SOUTH.getValue() == value)
			return SOUTH;
		else if(EAST.getValue() == value)
			return EAST;
		else
			return WEST;
	}

}
