package player.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import player.exceptions.MLBTeamNotFoundException;
import player.exceptions.PositionNotFoundException;
import player.utils.ResourceRankPair;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

	private static final Logger log = LoggerFactory.getLogger(Player.class);

	@Resource(name="elias_id")
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String fullname;
	private String position;
	private String pro_team;
	@Indexed
	private String fantasy_team_name;
	private String owned_by_team_id;
	private boolean free_agent;
	private String[] eligible_positions;
	private Map<String, ResourceRankPair> mentions; 
	private List<String> BA_grades;
	transient private MLBTeam mlbTeam;
	transient private Position pos;
	
	public Player(){
	}

	public Player(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
		this.free_agent = true;
	}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
		String[] namePieces = this.fullname.split(" ");
		setLastname(namePieces[namePieces.length-1]);  //most likely the last name

	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
		try{
			pos = Position.getPosition(position);
		}catch(PositionNotFoundException e){
			log.error(e.getMessage());
		}
		this.eligible_positions = new String[]{position};
	}

	public String getPro_team() {
		return pro_team;
	}

	public void setPro_team(String pro_team) {
		this.pro_team = pro_team;
		try{
			this.mlbTeam = MLBTeam.getMLBTeam(pro_team);
		}catch(MLBTeamNotFoundException e){
			log.error(e.getMessage());
		}
	}

	public String getFantasy_team_name() {
		return fantasy_team_name;
	}

	public void setFantasy_team_name(String fantasy_team_name) {
		this.fantasy_team_name = fantasy_team_name;
		this.free_agent = false;
	}

	public boolean isFree_agent() {
		return free_agent;
	}

	public void setFree_agent(boolean free_agent) {
		this.free_agent = free_agent;
	}

	public String[] getEligible_positions() {
		return eligible_positions;
	}

	public void setEligible_positions(String[] eligible_positions) {
		this.eligible_positions = eligible_positions;
	}

	public MLBTeam getMlbTeam() {
		return mlbTeam;
	}

	public void setMlbTeam(MLBTeam mlbTeam) {
		this.mlbTeam = mlbTeam;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Map<String, ResourceRankPair> getMentions() {
		if (mentions == null){
			mentions = new HashMap<>();
		}
		return mentions;
	}

	public void setMentions(Map<String, ResourceRankPair> mentions) {
		this.mentions = mentions;
	}

	@Override
	public String toString() {
		return "Player [fullname=" + fullname + ", fantasy_team_name=" + fantasy_team_name 
				+ ", mlb_team=" + pro_team
				+ ", free_agent="
				+ free_agent + ", eligible_positions=" + Arrays.toString(eligible_positions) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (mlbTeam == null){
			if (other.mlbTeam != null)
				return false;
		}else if (!mlbTeam.equals(other.mlbTeam))
				return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equalsIgnoreCase(other.fullname)){
			if (lastname == null) {
				if (other.lastname != null)
					return false;
			} else if (!lastname.equalsIgnoreCase(other.lastname))
				return false;
			if (pos == null) {
				if (other.pos != null)
					return false;
			} else if (!pos.equals(other.pos))
				return false;
		}
		return true;
	}

	public boolean equalsRelaxedVersion(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (mlbTeam == null){
			if (other.mlbTeam != null)
				return false;
		}else if (!mlbTeam.equals(other.mlbTeam))
				return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (pos.equals(other.pos))
			return true;
		List<Position> eligibilities = Arrays.asList(other.eligible_positions).stream().map(v -> getPosition(v)).collect(Collectors.toList());
		return eligibilities.contains(pos);
	}
	
	public void copy(Player player) {
		this.id = player.id;
		this.firstname = player.firstname;
		this.lastname = player.lastname;
		this.position = player.position;
		this.pro_team = player.pro_team;
		this.free_agent = player.free_agent;
		this.eligible_positions = player.eligible_positions;
		this.mlbTeam = player.mlbTeam;
		this.pos = player.pos;
		this.fantasy_team_name = player.fantasy_team_name;
		this.owned_by_team_id = player.owned_by_team_id;
	}
	
	private Position getPosition(String pos) {
	    try {
	        return Position.getPosition(pos);
	    } catch (Exception ex) {
	    	log.debug(ex.getMessage());
	    	return null;
	    }
	}

	public String getOwned_by_team_id() {
		return owned_by_team_id;
	}

	public void setOwned_by_team_id(String owned_by_team_id) {
		this.owned_by_team_id = owned_by_team_id;
	}

	public List<String> getBA_grades() {
		return BA_grades;
	}

	public void setBA_grades(List<String> bA_grades) {
		BA_grades = bA_grades;
	}

}
