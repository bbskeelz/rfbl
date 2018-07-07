package player.utils;

import java.util.ArrayList;
import java.util.HashMap;

import player.domain.Player;
import player.parsers.Parser;

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

	public Player getPlayer(int rank){
		Player player = new Player();
		player.setMentions(new HashMap<>());
		ResourceRankPair pair = new ResourceRankPair(parser.getFile().split("\\.")[0], rank);
		player.getMentions().put(parser.getFile().split("\\.")[0], pair);
		player.setBA_grades(new ArrayList<>());
		return player;
	}

}
