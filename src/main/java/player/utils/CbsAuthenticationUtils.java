package player.utils;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import player.domain.AuthenticationResponse;

public class CbsAuthenticationUtils {

	private static final Logger log = LoggerFactory.getLogger(CbsAuthenticationUtils.class);

	private static final String url = "https://api.cbssports.com/general/oauth/test/access_token?";

	public static String getAuthKey(){
        RestTemplate restTemplate = new RestTemplate();
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(converter);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
        		.queryParam("response_format", "json")
        		.queryParam("league_id", "rfbl2006")
        		.queryParam("user_id", "bbskeelz")
        		.queryParam("sport", "baseball");

		AuthenticationResponse response = restTemplate.getForObject(builder.build().toUriString(), AuthenticationResponse.class);
		return response.getBody().getAccess_token(); 
	}
}
