package player.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponse {

    private PlayersList body;
    private String statusMessage;

    public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public PlayerResponse() {
    }

    public PlayersList getBody() {
        return body;
    }

    public void setBody(PlayersList body) {
        this.body = body;
    }

}
