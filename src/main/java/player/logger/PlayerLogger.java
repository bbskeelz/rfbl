package player.logger;

import org.apache.log4j.Logger;

public class PlayerLogger {

    private static final Logger log = Logger.getLogger(PlayerLogger.class);

	public static void log(String str){
		log.debug(str);
	}
	
}
