/**
 * Group 5 Robber Knights Game
 *
 * The Board Class:
 *
 * The Board class maintains the playfield, and the placement of tiles
 * 
 */

class Board() {

	private Tile[][] grid;
	
	// constructor, initialize variables
	public Board(int playNum) {
		switch(
	}
	
	// Take a tile and a location (XxY) 
	public String placeTile(Tile t, String location) {
	
	}
	
	// Get the Grid 
	public Tile[][] getGrid() {
		return grid;
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