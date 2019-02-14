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

public class Baseball_America_HTML_Parser extends Parser{

	private static final Logger log = LoggerFactory.getLogger(Baseball_America_HTML_Parser.class);

	
	public Baseball_America_HTML_Parser() {
	}

	public Baseball_America_HTML_Parser(String file, String date) {
		super(file, date);
	}

	@Override
	public void parsePlayers() {
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		
		try {
			Document doc = Jsoup.parse(file, "UTF-8");

			Element detail = doc.getElementById("detail-list-items");
			for ( Element child : detail.children()){
				Player player = playerFactory.getPlayer();
				
				//rank
				int rank = Integer.valueOf(child.getElementsByClass("rank-number").get(0).childNode(0).toString());
				player.getMentions().get(getName()).setRank(rank);
				//name
				String name = child.getElementsByTag("a").get(0).getElementsByAttribute("title").get(0).childNode(0).toString();
				player.setFullname(name);
				String[] namePieces = player.getFullname().split(" ");
				player.setLastname(namePieces[namePieces.length-1]);  //most likely the last name
				//team
				String team = child.getElementsByClass("team").get(0).childNode(0).toString();
				player.setPro_team(team.trim());
				try {
					MLBTeam.getMLBTeam(player.getPro_team());
				}catch(MLBTeamNotFoundException p){
					log.debug(p.getMessage());
				}
				//position
				String pos = child.getElementsByClass("position").get(0).childNode(0).toString();
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
