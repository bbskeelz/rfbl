import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import player.domain.Player;
import player.parsers.Baseball_America_2017_Parser;
import player.parsers.Parser;

public class BaseballProspectus2017ParserTest {

	
	@Test
	public void test() {
    	Parser parser1 = new Baseball_America_2017_Parser("2017-Baseball-America.csv");

    	parser1.parsePlayers();
    	List<Player> players = parser1.getPlayers();
    	
    	assertEquals(players.size(), 100);

	}

}
