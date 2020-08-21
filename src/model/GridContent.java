package model;

public enum GridContent {
	EMPTY(0), ROVER(1), OBSTACLE(2);
	
	private final int code;
	
	GridContent(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
	
	

}
