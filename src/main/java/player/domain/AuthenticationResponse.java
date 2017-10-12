package player.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {

    private Authentication body;
    private String statusMessage;

    public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public AuthenticationResponse() {
    }

    public Authentication getBody() {
        return body;
    }

    public void setBody(Authentication body) {
        this.body = body;
    }

}
