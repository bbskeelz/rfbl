package player.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GenericParser extends Parser{

	@Override
	public void parsePlayers() {
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());

		try {
			identifyRankPosition(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		identifySplittingCharacter();
//		identifyMLBTeamPosition();
//		identifyElligiblePosition();
//		identifyName();
	}

	private void identifyRankPosition(File file) throws IOException {
		//looking for a numeric position:
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
			
		
		
		}
	}
	
	

}
