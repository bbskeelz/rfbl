package player.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import player.PlayerSearcher;
import player.domain.Player;
import player.exceptions.OutOfSequenceException;

public class BaseballAmericaParser extends Parser{

	private static final Logger log = LoggerFactory.getLogger(PlayerSearcher.class);

	
	public BaseballAmericaParser() {
	}

	public BaseballAmericaParser(String file){
		super(file);
	}
	
	@Override
	public void parsePlayers() {
	/*
	 * each chunk of text for an individual player looks like this:  I will look for lines that starts with a ^[0-9]
  		2 –
		Last: 2
		ronald_acuna.jpg
		Ronald Acuna Jr.
		Braves OF
		Notes:
		Hit: 60 | Power: 70 | Speed: 70 | Fielding: 70 | Arm: 60
		Scouting Report: Acuna has a wide range of strengths and few glaring weaknesses. Multiple scouts predicted multiple all-star appearances in his future. He's the rare prospect who actually carries future 60 (or better) grades on the 20-80 scale for all five tools. Acuna is a 70 runner with 70 defense who has a 60 arm and 60 hit tool. He already uses the whole field, and he went deep six times in 2017 to right or right-center field. Acuna used the opposite field more often as the season progressed. Not coincidentally he became tougher to strike out. Scouts looking for flaws noted that his strong arm is sometimes inaccurate and he could sometimes be stymied by quality fastballs up and in. But he already shows an ability to lay off breaking balls and velocity out of the zone. When he gets a pitch to hit, Acuna has extremely fast hands with strong wrists that whip the bat through the zone with excellent bat speed. He already generates exceptional exit velocities, which should pay off with 25-30 home runs once he matures.
		
		VIDEO
		3 –
		Last: 3
		JuanSoto.jpg
		Juan Soto
		Nationals OF
		Notes:
		Hit: 70 | Power: 60 | Speed: 50 | Fielding: 55 | Arm: 50
		Scouting Report: Soto impressed evaluators with his advanced feel at the plate. He made adjustments within at-bats and displayed impressive hand-eye coordination that should allow him to be an above-average hitter. While he's still growing into it, Soto should have above-average power, thanks to strong hands and a simple swing. He is just an average runner, and profiles as a corner outfielder because of that, which put additional pressure on his bat-his best tool. He currently has fringe-average arm strength that is better suited to left field than right, but he uses his legs efficiently on throws and is young enough to safely project an average arm as he continues to mature.

	 * 
	 */
		List<String> lines = new ArrayList<>();
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

			lines = stream
					.collect(Collectors.toList());
			int rank = 0;
			Iterator<String> playerIterator = lines.iterator();
			String playerLine = "";
			Player player = null;
			List<String> playerChunk = null;
			while (playerIterator.hasNext()){
				playerLine = playerIterator.next();
				if (playerLine.matches("^[0-9]+.*")){
					//this is the rank line of a player, starts the "chunk"
					player = playerFactory.getPlayer();
					player.getMentions().get(getName()).setRank(++rank);
					playerChunk = player.getMentions().get(getName()).getText();
					players.add(player);
				}
				if (playerChunk == null){
					throw new OutOfSequenceException("A numeric number did not lead this chunk.");
				}
				playerChunk.add(playerLine);
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (OutOfSequenceException e) {
			log.error(e.getMessage());
		}
	}

}
