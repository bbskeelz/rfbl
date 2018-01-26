package player.domain;

import player.exceptions.MLBTeamNotFoundException;

public enum MLBTeam {

	//TODO: convert these to xml resources.
	ARI(new String[]{"Arizona ","Diamondbacks ","ARI","Arizona  Diamondbacks "}),
	ATL(new String[]{"Atlanta ","Braves ","ATL","Atlanta  Braves "}),
	BAL(new String[]{"Baltimore ","Orioles ","BAL","Baltimore  Orioles "}),
	BOS(new String[]{"Boston ","Red Sox","BOS","Boston  Red Sox"}),
	CHC(new String[]{"Chicago ","Cubs ","CHC","Chicago  Cubs "}),
	CHW(new String[]{"Chicago ","White Sox","CHW","Chicago  White Sox"}),
	CIN(new String[]{"Cincinnati ","Reds ","CIN","Cincinnati  Reds "}),
	CLE(new String[]{"Cleveland ","Indians ","CLE","Cleveland  Indians "}),
	COL(new String[]{"Colorado ","Rockies ","COL","Colorado  Rockies "}),
	DET(new String[]{"Detroit ","Tigers ","DET","Detroit  Tigers "}),
	FLA(new String[]{"Florida ","Marlins ","FLA","Florida  Marlins ", "MIA", "Miami", "Miami Marlins"}),
	HOU(new String[]{"Houston ","Astros ","HOU","Houston  Astros "}),
	KC(new String[]{"KC","Kansas City","Royals","KAN","Kansas City Royals"}),
	LAA(new String[]{"Los Angeles","Angels","LAA","Los Angeles Angels "}),
	LAD(new String[]{"Los Angeles","Dodgers","LAD","Los Angeles Dodgers"}),
	MIL(new String[]{"Milwaukee ","Brewers","MIL","Milwaukee  Brewers "}),
	MIN(new String[]{"Minnesota ","Twins","MIN","Minnesota  Twins "}),
	NYM(new String[]{"New York","Mets","NYM","New York Mets"}),
	NYY(new String[]{"New York","Yankees","NYY","New York Yankees"}),
	OAK(new String[]{"Oakland ","Athletics ","OAK","Oakland  Athletics "}),
	PHI(new String[]{"Philadelphia ","Phillies ","PHI","Philadelphia  Phillies "}),
	PIT(new String[]{"Pittsburgh ","Pirates ","PIT","Pittsburgh  Pirates "}),
	SD(new String[]{"San Diego","Padres","SD","San Diego Padres"}),
	SF(new String[]{"San Francisco ","Giants","SF","San Francisco  Giants"}),
	SEA(new String[]{"Seattle ","Mariners ","SEA","Seattle  Mariners "}),
	STL(new String[]{"St. Louis ","Cardinals","STL","St. Louis  Cardinals"}),
	TB(new String[]{"Tampa Bay","Rays","TB","Tampa Bay Rays"}),
	TEX(new String[]{"Texas ","Rangers ","TEX","Texas  Rangers "}),
	TOR(new String[]{"Toronto ","Blue Jays","TOR","Toronto  Blue Jays"}),
	WAS(new String[]{"Washington ","Nationals ","WAS","Washington  Nationals "});


	private String[] names;
	MLBTeam(String[] names){
		this.names = names;
	}
	
	public static MLBTeam getMLBTeam(String team) throws MLBTeamNotFoundException{
		
		for (MLBTeam mlbTeam : values()){
			for (String s : mlbTeam.names){
				if(s.trim().replaceAll("\\s+","").equalsIgnoreCase(team.trim().replaceAll("\\s+","")))
					return mlbTeam;
			}
		}
		throw new MLBTeamNotFoundException(team);
	}
	
}
