import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import player.domain.Player;
import player.parsers.Baseball_Prospectus_2017_Parser;
import player.parsers.Parser;

public class BaseballAmerica2017ParserTest {

	
	@Test
	public void test() {
    	Parser parser1 = new Baseball_Prospectus_2017_Parser("2017-Baseball-Prospectus.csv");

    	parser1.parsePlayers();
    	List<Player> players = parser1.getPlayers();
    	
    	assertEquals(players.get(58).getFullname(), "A.J. Puk");

	}

}
