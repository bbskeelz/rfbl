package player.exceptions;

public class MLBTeamNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807224389839323331L;
	String team;
	String message;
	
	public MLBTeamNotFoundException(String team) {
		this.team = team;
		message = team + " was not recognized by the API as a valid MLB Team.";
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
