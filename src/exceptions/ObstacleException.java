package exceptions;

public class ObstacleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670398581042386485L;
	private String message;

	public ObstacleException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
