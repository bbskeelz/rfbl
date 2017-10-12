package player.utils;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import player.domain.Player;
import player.domain.PlayerResponse;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest
@SpringBootConfiguration
public class CbsAuthenticationUtilsTest {

	@Value("${search_url}")
	private String url;

	@Test
	public void testGetAuthKey() {
		RestTemplate restTemplate = new RestTemplate();

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
		restTemplate.getMessageConverters().add(converter);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("response_format", "json")
				.queryParam("league_id", "rfbl2006")
				.queryParam("access_token",CbsAuthenticationUtils.getAuthKey())
				.queryParam("name", "David Dahl")
				.queryParam("eligible_only", "0")
				.queryParam("version", "3.0")
				.queryParam("free_agents_only", "1");

		PlayerResponse response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
		List<Player> playerList = response.getBody().getPlayers(); 
		assertEquals(playerList.get(0).getOwned_by_team_id(), "8");
	}

}
