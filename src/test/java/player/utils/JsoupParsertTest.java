package player.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 
 * @author sean
 * the html looks are in follow format:
 * 
	  <div class="player-details">
	   <h3><a href="/players/3232/eloy-jimenez/" title="Eloy Jimenez">Eloy Jimenez</a></h3>
	   <div class="team-position-container">
	    <span class="team">White Sox</span> 
	    <span class="position">OF</span>
	   </div>
	  </div>
	  <div class="player-notes">
	   <span class="label">Notes:</span>
	   <p>Hit: 60 | Power: 70 | Speed: 40 | Fielding: 45 | Arm: 45<br><strong>Scouting Report: </strong>Scouts who saw Jimenez last season used words like "man-child," "mutant" and "Superman." More specifically, Jimenez is an intimidating, strong-bodied prospect with a whip-quick bat capable of massive home runs. More than his raw power, which approaches the top of the scale, he is a diligent, dedicated worker. One manager recalled seeing Jimenez strike out multiple times during a game, then saw him on the field early the next day for tracking drills. Rival managers lamented not being able to find many holes in his swing, even when they pitched him backwards. And here's the scary part: Jimenez might not be done developing physically. He played all of 2017 at 20 years old and still has room to sculpt his body and add more strength, possibly becoming a perennial 40-home run threat. Jimenez has spent his career flipping back and forth between right and left field, with left his likely eventual home because of his below-average arm. He's also a tick below-average runner. Defense and speed were never expected to be selling points of his game, however. Jimenez is a hitter, period, with a mix of power and ability to get to it to change a game.</p>
	  </div>
	 </div>
	 <div class="desktop-only spacer"></div></li>
 */

public class JsoupParsertTest {

	File file = new File(getClass().getClassLoader().getResource("2018-Baseball-America-September.html").getFile());
	
	public static void main(String[] args) {
		JsoupParsertTest jsoupParsertTest = new JsoupParsertTest();
		try {
			Document doc = Jsoup.parse(jsoupParsertTest.file, "UTF-8");

			Element detail = doc.getElementById("detail-list-items");
			for ( Element child : detail.children()){
				Element ranking = child.getElementsByClass("list-ranking").get(0);
				System.out.println(child.getElementsByClass("rank-number").get(0).childNode(0));
				//name
				System.out.println(child.getElementsByTag("a").get(0).getElementsByAttribute("title").get(0).childNode(0));
				//team
				System.out.println(child.getElementsByClass("team").get(0).childNode(0));
				//position
				System.out.println(child.getElementsByClass("position").get(0).childNode(0));
				//rank
				System.out.println(child.getElementsByClass("position").get(0).childNode(0));


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
