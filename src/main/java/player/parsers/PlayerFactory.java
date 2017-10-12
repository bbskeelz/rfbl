package player.parsers;

import java.util.HashMap;

import player.domain.Player;
import player.utils.ResourceRankPair;

public class PlayerFactory {

	private Parser parser;
	
	public PlayerFactory(Parser parser){
		this.parser = parser;
	}
	
	public Player getPlayer(){
		Player player = new Player();
		player.setMentions(new HashMap<>());
		ResourceRankPair pair = new ResourceRankPair(parser.getFile().split("\\.")[0], -1);
		player.getMentions().put(parser.getFile().split("\\.")[0], pair);
		return player;
	}
	
}
