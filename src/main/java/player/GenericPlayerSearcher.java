//package player;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import player.domain.Player;
//import player.domain.Resource;
//import player.parsers.BaseballAmericaParser;
//import player.parsers.Parser;
//import player.repository.PlayerRepository;
//import player.repository.ResourceRepository;
//import player.utils.CbsAuthenticationUtils;
//
////@SpringBootApplication
//public class GenericPlayerSearcher implements CommandLineRunner {
//
//    @Autowired()
//    private PlayerRepository playerRepository;
//    
//    @Autowired()
//    private ResourceRepository resourceRepository;
//    
//	
//	private static final Logger log = LoggerFactory.getLogger(GenericPlayerSearcher.class);
//    
//    @Value("${access_token}")
//    private String access_token;
//    @Value("${search_url}")
//    private String url;
//    public static void main(String args[]) {
//        SpringApplication.run(GenericPlayerSearcher.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//    	List<Parser> parsers = new ArrayList<>();
//
//    	Parser parser1 = new BaseballAmericaParser("ba/ba-latest.txt");
//    	parsers.add(parser1);
//    	
//    	for (Parser parser : parsers){
//    		parser.parsePlayers();
//    		List<Player> players = parser.getPlayers();
//    		Resource resource = new Resource();
//    		resource.setSite(parser.getName());
//    		resource.setRanks(players);
//    		log.info("Searching for players listed in {}.", resource.getSite());
//    		search(players, parser.getName());
//    		log.info("All players listed in {} cataloged.", resource.getSite());
//    		log.info("Saving player list for {}", resource.getSite());
//    		resourceRepository.save(resource);
//    		log.info("Completed saving player list for {}", resource.getSite());
//    	}
//    }
//    
//    public void search(List<Player> players, String site){
//        RestTemplate restTemplate = new RestTemplate();
//        
//        String access_token = CbsAuthenticationUtils.getAuthKey();
//        
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
//        restTemplate.getMessageConverters().add(converter);
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//     		.queryParam("response_format", "json")
//        .queryParam("league_id", "rfbl2006")
//        .queryParam("access_token",access_token)
//        .queryParam("name", "corey seager")
//        .queryParam("eligible_only", "0")
//        .queryParam("version", "3.0")
//        .queryParam("free_agents_only", "1");
//        
//        int rank = 1;
//
//        for (Player p : players){
//        	//online endpoint seeking portion
//        	List<Player> playerList = null;
//        	List<String> playerChunk = p.getMentions().get(site).getText();
//        	for (String line : playerChunk){
//        		
//        	}
//        }
////        	if (playerList == null || playerList.isEmpty()){
////        		builder.replaceQueryParam("name", p.getFullname().trim());
////        		PlayerResponse response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
////        		playerList = response.getBody().getPlayers(); 
////        		if (playerList == null || playerList.isEmpty()){
////        			builder.replaceQueryParam("name", p.getLastname().trim());
////        			response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
////        			playerList = response.getBody().getPlayers(); 
////        			if (playerList == null || playerList.isEmpty()){
////            			builder.replaceQueryParam("name", p.getFullname().split(" ")[0]);
////            			response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
////            			playerList = response.getBody().getPlayers(); 
////            			if (playerList == null || playerList.isEmpty()){
////            				log.warn(p + " was not found");
////            				rank++;
////            				continue;
////            			}
////        			}
////        		}
////        	}
////        	if (playerList != null && !playerList.isEmpty()){
////        		
////        		List<Player> filteredList = playerList.size() == 1 
////        										? playerList 
////        										: playerList.stream().filter(v -> v.equalsRelaxedVersion(p)).collect(Collectors.toList());
////        		for (Player player_candidate : filteredList){
////        			if (p.equals(player_candidate) || filteredList.size()==1){
////        				Player previously_saved_player = playerRepository.findById(player_candidate.getId());
////        				if (previously_saved_player != null) {
////        					previously_saved_player.getMentions().computeIfAbsent(site, v-> p.getMentions().get(site));
////        					previously_saved_player.copy(player_candidate);
////        					p.copy(previously_saved_player);
////        					playerRepository.save(previously_saved_player);
////        					log.debug("Saving existing player - #{}.{}.", rank, p.getFullname());
////        					rank++;
////        				}else{
////        					log.debug("Saving new player - #{}.{}.", rank, p.getFullname());
////        					rank++;
////        					p.copy(player_candidate);
////        					playerRepository.save(p);
////        				}
////        			}else{
////        				log.debug("mismatch found");
////        			}
////    				if (p.isFree_agent()){
////    					printFreeAgent(p, site, rank-1);
////    				}else{
////    					if (p.getOwned_by_team_id().equals("8")){
////    						printMyPlayer(p, site, rank-1);
////    					}
////    				}
////        			
////        		}
////        	}
////        }
//
//    	
//    }
//
//	private void printFreeAgent(Player p, String site, int rank) {
//		log.info("Free Agent alert: Ranked {} by {} :", rank, site);
//		log.info(p.toString());
//	}
//
//	private void printMyPlayer(Player p, String site, int rank) {
//		log.info(" *** Future superstar alert: Ranked {} by {} :", rank, site);
//		log.info(p.toString());
//	}
// 
//    
//}