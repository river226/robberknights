/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
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

	/**
	 * Tile Constructor
	 * @param envir 0 = lake, 1 = plain, 2 = forest, 3 = mountain
	 * @param hab 1 = castle, 2 = village, 3 = town
	 * @param let A - E representing section of deck
	 */
	public Tile(int envir, int hab, char let) {
		enviroment = envir;
		habitat = hab;
		letter = let;
		knights = new int[4];

		//set value castle = 1, village = 2, town = 3
				value = hab;
	}

	// breakdown and interpret 

	/**The environment (i.e. how many knights must be placed minimum) 
	 * of the tile
	 * 
	 * @return lake, plain, woods, or mountains
	 */
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

	/**The habitat that represents the number of points awarded
	 * for the tile
	 * @return castle, village, town, or none
	 */
	public String getHabitat() {
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town

		switch(habitat) {
		case(1): return "castle";
		case(2): return "village";
		case(3): return "town";
		default: return "none";
		}
	}

	/**Gets letter representing location in deck
	 * 
	 * @return A-E
	 */
	public char getLetter() {
		return letter;
	}

	/**Retrieves the score value of the 
	 * tile. Redundant with habitat
	 * @return score value
	 */
	public int getValue() {
		return value;
	}

	/** validates that a knight can be played on a tile
	 * 
	 * @return boolean
	 */
	public boolean valid() {
		if(enviroment > 0  && minKnight() <= 4 - numKnights)
			return true;
		return false;
	}

	/**Sets knights onto the tile
	 * 
	 * @param token = ?
	 * @throws Exception
	 */
	public void setKnight(int[] token) throws Exception {
		if(4-numKnights < token.length)
			throw new Exception();

		for(int i = 0; i < token.length; i++) {
			knights[numKnights] = token[i];
			numKnights++;
		}
	}

	/**Retrieve Knights from the top of
	 * the stack
	 * 
	 * @param pop = number of knights needed
	 * @return int[] of popped knights
	 * @throws Exception
	 */
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

	/**Retrieves the top knight on the tile
	 * 
	 * @return winning knight
	 */
	public int topKnight() {
		if(numKnights == 0) return -1;
		return knights[numKnights - 1];
	}

	/**Redundant with environment
	 * 
	 * @return minimum knights to be placed
	 * on the tile
	 */
	public int minKnight() {
		return enviroment;
	}
	
	public int getNumKnights() {
		return numKnights;
	}
	
	/**Gets the filepath for the image of 
	 * the file
	 * 
	 * @return filepath of image
	 */
	public String getImage() {
		String name = ".png";

		name = getEnviroment() + name;

		name =  getHabitat() + "_" + name;

		switch(topKnight()) { // Color
		case(1):
			name = "red_" + name; break;
		case(2):
			name = "blue_" + name; break;
		case(3):
			name = "yellow_" + name; break;
		case(4):
			name = "green_" + name; break;
		default :
			name = "none_" + name; break;
		}
		switch(getNumKnights()) {
		case(1):
			name = "one/" + name; break; 
		case(2):
			name = "two/" + name; break; 
		case(3):
			name = "three/" + name; break; 
		case(4):
			name = "four/" + name; break; 
		default: break;
	}

		name = "src/library/images/tiles/" + getEnviroment()  + "/" + name; // may need to add 'src/' to file path

		return name;
	}
}
