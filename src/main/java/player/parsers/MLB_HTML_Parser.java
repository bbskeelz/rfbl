package player.parsers;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import player.domain.MLBTeam;
import player.domain.Player;
import player.domain.Position;
import player.exceptions.MLBTeamNotFoundException;
import player.exceptions.PositionNotFoundException;

public class MLB_HTML_Parser extends Parser{

	private static final Logger log = LoggerFactory.getLogger(MLB_HTML_Parser.class);

	
	public MLB_HTML_Parser() {
	}
	
	public MLB_HTML_Parser(String file, String date) {
		super(file, date);
	}

	@Override
	public void parsePlayers() {
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		
		try {
			Document doc = Jsoup.parse(file, "UTF-8");

			Element detail = doc.getElementById("cards");
			for ( Element child : detail.children()){
				Player player = playerFactory.getPlayer();
				
				//rank
				int rank = Integer.valueOf(child.getElementsByClass("number").get(0).childNode(0).toString().substring(1));
				player.getMentions().get(getName()).setRank(rank);
				//name
				String name = child.getElementsByClass("player-name").get(0).childNode(0).toString().substring(1);
				player.setFullname(name);
				String[] namePieces = player.getFullname().split(" ");
				player.setLastname(namePieces[namePieces.length-1]);  //most likely the last name
				//team
				for (Element childElement : child.getElementsByClass("team-logo")){
					if (childElement.attributes().get("class") != null){
						String team = childElement.attributes().get("class").split(" ")[1];
						player.setPro_team(team.trim());
						try {
							MLBTeam.getMLBTeam(player.getPro_team());
						}catch(MLBTeamNotFoundException p){
							log.debug(p.getMessage());
						}
					}
				}
				//position
				String pos = child.getElementsByClass("player-info").get(0).childNode(0).toString();
				player.setEligible_positions(pos.split("/"));
				try {
					player.setPos(Position.getPosition(player.getEligible_positions()[0].trim()));
				}catch(PositionNotFoundException p){
					log.error(p.getMessage());
				}
				players.add(player);
			}
		} catch (IOException e) {
			log.error("{} file is invalid.", file.getAbsoluteFile());
		}
	}
}
