package exceptions;

public class RoverNotInitializedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887098716179139165L;
	private String message;
	
	public RoverNotInitializedException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
