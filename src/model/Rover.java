package model;

public class Rover {

	private Orientation orientation;
	private Coordinate coords;
	private boolean initialized = false;
	
	private static Rover INSTANCE = null;
	
	private Rover(){
		
	}
	
	private synchronized static void createInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Rover();
		}			
	}
	
	public static Rover getInstance() {
		if (INSTANCE == null) { 
			createInstance();
		} 
		return INSTANCE;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		// get the Orientation value that matches the received char
		this.orientation = Orientation.valueOf(orientation);
	}

	public Coordinate getCoords() {
		return coords;
	}

	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
