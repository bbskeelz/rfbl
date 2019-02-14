package player.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import player.domain.Player;

public class MLB_2016 extends Parser{

	public MLB_2016() {
	}

	public MLB_2016(String file){
		super(file, "201604");
	}
	
	@Override
	public void parsePlayers() {
		List<String> lines = new ArrayList<>();
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			Pattern pattern = Pattern.compile("[A-Z][-.a-zA-Z]+");
			lines = stream
					.filter(line -> line.trim().split(" ").length > 1)
//					.map(String::toUpperCase).
					.collect(Collectors.toList());
			StringBuilder sb = null;
			for (int i = 0; i < lines.size(); i++){
				String playerLine = lines.get(i);
				sb = new StringBuilder();
				Matcher m = pattern.matcher(playerLine);
				while (m.find()){
					sb.append(m.group()).append(" ");
				}
				Player player = playerFactory.getPlayer();
				player.setFullname(sb.toString().trim());
				players.add(player);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
