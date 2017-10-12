package player.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Resource {
	
	@Id
	private String site;
	private String date;
	
	List<Player> ranks;
	
	public Resource(){
		this.ranks = new ArrayList<Player>();
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Player> getRanks() {
		return ranks;
	}
	public void setRanks(List<Player> ranks) {
		this.ranks = ranks;
	}
	
}
