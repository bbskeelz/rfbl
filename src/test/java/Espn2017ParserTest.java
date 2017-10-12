import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import player.domain.Player;
import player.parsers.ESPN_2017_Parser;
import player.parsers.Parser;

public class Espn2017ParserTest {

	
	@Test
	public void test() {
    	Parser parser1 = new ESPN_2017_Parser("2017-ESPN.csv");

    	parser1.parsePlayers();
    	List<Player> players = parser1.getPlayers();
    	
    	assertEquals(players.get(11).getLastname(), "Jimenez");

	}

}
