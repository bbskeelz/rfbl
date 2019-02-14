//package player.parsers;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import player.PlayerSearcher;
//import player.domain.Player;
//
//public class Baseball_America_2018_May_Parser extends Parser{
//
//	public Baseball_America_2018_May_Parser() {
//	}
//
//	public Baseball_America_2018_May_Parser(String file){
//		super(file);
//	}
//
//	private static final Logger log = LoggerFactory.getLogger(Baseball_America_2018_May_Parser.class);
//
//	
//	@Override
//	public void parsePlayers() {
//		List<String> lines = new ArrayList<>();
//		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
//		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
//
//			//1. filter line 3
//			//2. convert all content to upper case
//			//3. convert it into a List
//			lines = stream
//					.collect(Collectors.toList());
//			Player player = null;
//			int i = 0;
//			int index = 0;
//			while (i < lines.size()){
//				String line = lines.get(i++);
//				if (line.split(" ")[0].matches("\\d+")){
//					index = 1;
//					if (player != null){
//						players.add(player);
//						player = playerFactory.getPlayer(Integer.valueOf(line.split(" ")[0]));
//					}else{
//						player = playerFactory.getPlayer(Integer.valueOf(line.split(" ")[0]));
//					}
//				}else if (index == 1){
//					player.setFullname(line);
//					index = 2;
//				}else if (index == 2){
//					log.debug(player.getFullname());
//					String[] secondLine = line.split(" ");
//					String pos = secondLine[secondLine.length-1].split("/")[0];
//					player.setPosition(pos);
//					String mlbTeam = line.substring(0, (line.length() - secondLine[secondLine.length-1].length()) -1);
//					player.setPro_team(mlbTeam);
//					index = 3;
//				}else if (index == 3){
//					player.getBA_grades().add(line);
//				}
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
