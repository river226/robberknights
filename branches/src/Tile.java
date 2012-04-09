/**
 * Group 5 Robber Knights Game
 *
 * The Tile Class:
 *
 * The tile class maintains it's type and maintains all knights that are 
 * placed in the Tile. The knights are maintained by their player number
 * identifying them as belonging to that player. 
 * 
 */

class Tile {
	
	private int enviroment = -1, habitat = -1, value = 0, numKnights = 0;
	
	private int[] knights;
	
	// can only be A, B, C, D, E
	private char letter = 'F'; 
	
	// constructor, initialize variables
	public Tile(int envir, int hab, char let) {
		enviroment = envir;
		habitat = hab;
		letter = let;
		knights = new int[4];
		
		//set value castle = 1, village = 2, town = 3
		value = hab;
	}
	
	// breakdown and interpret 
	
	public String getEnviroment() {
		// for enviroment 0 = lake, 1 = plain, 2 = woods, 3 = mountains
		
		switch(enviroment) {
			case(0): return "lake";
			case(1): return "plain";
			case(2): return "woods";
			case(3): return "mountains";
			default: return null;
		}
	}
	
	// breakdown and interpret 
	public String getHabitat() {
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town
		
		switch(habitat) {
			case(1): return "castle";
			case(2): return "village";
			case(3): return "town";
			default: return "none";
		}
	}
	
	// retrieve the Tile Letter
	public char getLetter() {
		return letter;
	}
	
	// Get the Score value of the Tile
	public int getValue() {
		return value;
	}
	
	// Determine if the tile is a valid spot to move
	public boolean valid() {
		if(habitat != 0 && habitat != -1 && minKnight() <= 4 - numKnights)
			return true;
		return false;
	}
	
	// Set Knights onto the Tile
	public void setKnight(int[] token) throws Exception {
		if(4-numKnights < token.length)
			throw new Exception();
			
		for(int i = 0; i < token.length; i++) {
			knights[numKnights] = token[i];
			numKnights++;
		}
	}
	
	// Retrieve Knights from the top of the Knight Stack
	public int[] popKnight(int pop) throws Exception {
		if(4-numKnights < pop)
			throw new Exception();
			
		int[] removed = new int[pop];
		int valid = topKnight();
		
		for(int i = 0; i < pop; i++){
			if(topKnight() != valid)
				throw new Exception();
			
			removed[i] = knights[numKnights - 1];
			numKnights--;
		}
		
		return removed;
	}
	
	// Return the current winning Knight
	public int topKnight() {
		return knights[numKnights - 1];
	}
	
	// Return the required knights to take the Tile
	public int minKnight() {
		return enviroment;
	}
	
	// Get the filepath for the image of the file
	public string getImage() {
		String name = ".png";
		
		name = getEnviroment() + name;
		
		name =  getHabitat() + "_" + name;
		
		color = topKnight();
		
		switch(color) {
			case(1):
				name = "red_" + name; break;
			case(2):
				name = "blue_" + name; break;
			case(3):
				name = "yellow_" + name; break;
			case(4):
				name = "green_" + name; break;
			default
				name = "none_" + name; break;
		}
		
		name = "/library/images/tiles/" + getEnviroment()  + "/" + name;
		
		return name;
	}
}