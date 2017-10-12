package player.domain;

import player.exceptions.PositionNotFoundException;

public enum Position {

	P(1, new String[]{"P", "Pitcher", "RP", "SP", "LHP", "RHP"}),
	C(2, new String[]{"C", "Catcher"}),
	_1B(3, new String[]{"1B", "First", "First Base", "1st"}),
	_2B(4, new String[]{"2B", "Second", "Second Base", "2nd"}),
	_3B(5, new String[]{"3B", "Third", "Third Base", "3rd"}),
	_SS(6, new String[]{"SS", "Short Stop", "Short"}),
	_OF(7, new String[]{"OF", "LF", "CF", "RF", "Outfield", "Outfielder", "Left", "Right", "Center", "Left Fiedler", "Right Fielder", "Center Fielder"}),
	_DH(0, new String[]{"DH", "Designated Hitter"});
	
	private int pos;
	private String[] posNames;
	
	Position(int pos, String[] posNames){
		this.pos = pos;
		this.posNames = posNames;
	}
	
	public static Position getPosition(String pos) throws PositionNotFoundException{
		for (Position p : Position.values()){
			for (String s : p.posNames){
				if (pos.equalsIgnoreCase(s)){
					return p; 
				}
			}
		}
		throw new PositionNotFoundException(pos);
	}
	
	
}
