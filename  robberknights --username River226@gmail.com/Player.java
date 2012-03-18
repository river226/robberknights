/**
 * Group 5 Robber Knights Game
 *
 * The Player Class:
 *
 * The Player Class holds and manages the players deck, hand and knights 
 * The player has a color and an id that is a number
 * 
 */

class Player() {

	private final int id;
	private final String color;
	private PlayerTiles tiles;
	private int remainingKnights;
	
	// constructor, initialize variables
	public Player(int num) {
		id = num;
		
		switch(id) {
			case(1): color = "Red";
			case(2): color = "Blue";
			case(3): color = "Yellow";
			case(4): color = "Green"
		}
		
		tiles = new PlayerTiles();
		
		// player starts out with 30 knights
		remainingKnights = 30;
	}
	
	// Get the players color 
	public String getColor() {
		return color;
	}
	
	// Get the Hand 
	public Tile[] getHand() {
		return tiles.showHand();
	}
	
	// Get the number of knights left to play
	public int remainingKnights() {
		return remainingKnights;
	}
	
	// Get the Score value of the Tile
	public void playKnights(int count) {
		remainingKnights -= count;
	}
	
	// Get the a tile from the hand and add a new tile
	public Tile makeMove(int location) {
		Tile t = tiles.playTile(location);
		tiles.draw();
		return t;
	}
}