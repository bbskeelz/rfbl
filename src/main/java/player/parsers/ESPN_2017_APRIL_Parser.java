package player.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import player.domain.MLBTeam;
import player.domain.Player;
import player.domain.Position;
import player.exceptions.MLBTeamNotFoundException;
import player.exceptions.PositionNotFoundException;
import player.logger.PlayerLogger;

public class ESPN_2017_APRIL_Parser extends Parser{

	public ESPN_2017_APRIL_Parser() {
	}

	public ESPN_2017_APRIL_Parser(String file){
		super(file);
	}
	
	@Override
	public void parsePlayers() {
		List<String> lines = new ArrayList<>();
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

			lines = stream
					.collect(Collectors.toList());
			int rank = 0;
			for (String playerLine : lines){
				Player player = playerFactory.getPlayer();
				String name = playerLine.split(",")[1].trim();
				String pos = playerLine.split(",")[2].trim();
				player.setFullname(name);
				String[] namePieces = player.getFullname().split(" ");
				player.setLastname(namePieces[namePieces.length-1]);  //most likely the last name
				player.setEligible_positions(pos.split("/"));
				try {
					player.setPos(Position.getPosition(player.getEligible_positions()[0].trim()));
				}catch(PositionNotFoundException p){
					PlayerLogger.log(p.getMessage());
				}
				player.setPro_team(playerLine.split(",")[3].trim());
				try {
					MLBTeam.getMLBTeam(player.getPro_team());
				}catch(MLBTeamNotFoundException p){
					PlayerLogger.log(p.getMessage());
				}
				player.getMentions().get(getName()).setRank(++rank);
				players.add(player);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
