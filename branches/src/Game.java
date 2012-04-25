/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The Game Class:
 *
 * The Board class maintains the playfield, and the placement of tiles
 * 
 */

class Game {

	private BoardGame GUI;
	private Player[] players;
	private Board playfield;
	private final int numplayers, c, totalKnights, totalTiles; // default is set at 2 players for the moment c stands for the current player in the game
	
	// generate the elements of the GUI so the game can run, and then run the game
	public Game(int nump, String[] names) {
		numplayers = nump;
		c = 0;
		totalKnights = 120;
		totalTiles = 96;
		generatePlayfield();
		generatePlayers(names);
	}
	
	// Manage the generationg of the players playing the game
	private void generatePlayers(String[] names) {
		players = new Player[numplayers];
		
		for(int i = 0; i < numplayers; i++)
			players[i] = new Player(i+1, names[i]);
	}
	
	// Create the playfield for the game
	private void generatePlayfield() {
		playfield = new Board(numplayers);
	}
	
	// Get the playfield for the game
	public Tile[][] getBoard() {
		return playfield.getGrid();
	}
	
	// Get the Tiles from the player for the first move, and add them in a random order to the Playfield. update the the GUI, Board, and Each Player.
	public Tile[][] firstMove() {
		for(int i = 0; i < numplayers; i++) {
			Tile t = GUI.select(i+1, players[i].firstMoveGet());
			players[i].firstmoveset(t);
		}
	}
	
	// Return the tile not played
	public int returnToHand(int p, Tile t) {
		if(move = 0)
			players[p].firstMoveSet(t);
		
		totalTiles -= 2;
	}
	
	// Manage the game including all updates
	// if we have no more tiles or knights the game is over
	public Turn nextTurn() { 
		Turn move = null;
		
		if(totalTiles > 0 || totalKnights > 0)
			Turn move = new Turn(players[c]);
		
		if(c<numplayers)		
			c++;
		else
			c = 0;
			
		return move
		
	}
	
	// Total up the score for each player in the game
	public void scoreGame() {
		int[] playerScore = new int[numplayers];
		int[] knightsLeft = new int[numplayers];
		Tile[][] field = playfield.getGrid();
		
		for(int i = 1; i < 8 + numplayers; i++) {
			for(int j = 1; j < 8 + numplayers; j++) {
				if(field[i][j] != null) {
					int p = field[i][j].topKnight();
					if(p > 0)
						playerscore[p-1] += field[i][j].getValue
				}
			}
		}
		
		for(int k = 0; k < numplayers; k++)
			knightLeft[k] = players[k].remaingKnights();
		
		GUI.finalScore(playscore, knightLeft);
	}
}
