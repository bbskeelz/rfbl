package player.exceptions;

public class PositionNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807224389839323331L;
	String position;
	String message;
	
	public PositionNotFoundException(String position) {
		this.position = position;
		message = position + " was not recognized by the API as a valid position.";
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
