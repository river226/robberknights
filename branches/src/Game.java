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

	private Player[] players;
	private Board playfield;
	private final int numplayers; // default is set at 2 players
	private int c, totalKnights, totalTiles, turn; // for the moment c stands for the current player in the game
	
	// generate the elements of the GUI so the game can run, and then run the game
	public Game(int nump, String[] names) {
		numplayers = nump;
		c = 0;
		turn = 0;
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
	
	// Gets the players name
	public String getName(int i) {
		return players[i].getName();
	}
	
	// Get the Tiles from the player for the first move, and add them in a random order to the Playfield. update the the GUI, Board, and Each Player.
	public Tile[][] firstMove() {
		if(turn == 0) {
			Tile[][] t = new Tile[numplayers][3];
			
			for(int i = 0; i < numplayers; i++) {
				Tile[] c = players[i].firstMoveGet();
				
				for(int j = 0; j < 3; j++) {
					t[i][j] = c[j];
				}
			}
			
			return t;
		}
		
		return null;
	}
	
	// Return the tile not played
	public void returnToHand(int p, Tile t) {
		if(turn == 0) {
			players[p].firstMoveSet(t);
			totalTiles -= 2;
		}
	}
	
	// Place the Tiles on the board for the start of the game
	public Tile[][] setUpBoard(Tile[][] t) {
		if(turn == 0) { return playfield.setup(t); }
		return null;
	}
	
	// Manage the game including all updates
	// if we have no more tiles or knights the game is over
	public Turn nextTurn() { 
		Turn move = null;
		
		if(totalTiles > 0 || totalKnights > 0)
			move = new Turn(players[c]);
		
		if(c<numplayers)		
			c++;
		else
			c = 0;
			
		turn++;
			
		return move;
		
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
						playerScore[p-1] += field[i][j].getValue();
				}
			}
		}
		
		for(int k = 0; k < numplayers; k++)
			knightsLeft[k] = players[k].remainingKnights();
		
		//GUI.finalScore(playscore, knightLeft);
	}
	
	/*
	 * The Turn Class:
	 *
	 * The Turn class manages all the steps and update for a specific move.
	 */
	
	public class Turn {
		private int x, y, dir, Knightsleft;
		private int movecount;
		private Player current;
		
		public Turn(Player p) {
			dir = -1;
			Knightsleft = 0;
			movecount = 0;
			current = p;
		}
		
		// Set the direction of the movement
		// 0 = right, 1 = left, 2 = up, 3 = down
		public void setDirection(int d) {
			dir = d;
		}
		
		// retreve valid locations
		public LocationList getValidMoves() {
			return playfield.getValidMoves();
		}
		
		// mark the end of a move
		public void endMove() {
			dir = -1;
			Knightsleft = 0;
		}
		
		// return a card from the GUI to the hand
		public void returnTile(Tile t) {
			current.addTile(t);
		}
		
		// play a tile onto the board
		// return 0 for no castle, return 1 for castle
		public int playTile(Tile t, int x, int y) {
			playfield.placeTile(t, x, y);
			
			movecount++;
			totalTiles--;
			// add draw
			if(t.getHabitat().equals("castle"))
				return 1;
				
			return 0;
		}
		
		// get the minimum number of knights needed to play on current tile
		public int getMinKnights() {
			return playfield.getGrid()[x][y].minKnight();
		}
		
		// add knights to the tile
		public void playKnights(int x, int y, int n) {
			int[] tokens = new int[n];
			
			for(int i = 0; i < n; i++)
				tokens[i] = current.getID();
				
			totalKnights -= n;
			Knightsleft -= n;
			
			if(dir == 0) // right
				x++;
			else if(dir == 1) // left
				x--;
			else if(dir == 2) // up
				y--;
			else if(dir == 3) // down
				y++;
			
			try{
				playfield.getGrid()[x][y].setKnight(tokens);
			} catch (Exception e) {} 
		}
		
		// get the hand to play
		public Tile[] getHand() {
			x = 0;
			y = 0;
			dir = -1;
			Knightsleft = 0;
		
			if(movecount > 3)
				return current.getHand();
			
			return null;
		}

		// determine all the valid spots you can place a knight
		public LocationList KnightMoves() {
			Tile[][] b = playfield.getGrid();
			LocationList l = null;
			
			if(dir == -1) {
				if(b[x+1][y] != null && b[x+1][y].valid())
					l = new LocationList(x+1, y, l) ;
				if(b[x][y-1] != null && b[x][y-1].valid())
					l = new LocationList(x, y-1, l);
				if(b[x][y-1] != null && b[x][y+1].valid())
					l = new LocationList(x, y+1, l);
				if(b[x-1][y] != null && b[x-1][y].valid())
					l = new LocationList(x-1, y, l);
			} else if(Knightsleft != 0) { 
				if(dir == 0 && b[x+1][y].valid() && b[x+1][y].minKnight() < Knightsleft) // right
					l = new LocationList(x+1, y, l);
				else if(dir == 1 && b[x-1][y].valid() && b[x-1][y].minKnight() < Knightsleft) // left
					l = new LocationList(x-1, y, l);
				else if(dir == 2 && b[x][y-1].valid() && b[x][y-1].minKnight() < Knightsleft) // up
					l = new LocationList(x, y-1, l);
				else if(dir == 3 && b[x][y+1].valid() && b[x][y+1].minKnight() < Knightsleft) // down
					l = new LocationList(x, y+1, l);
			}
			
			return l;
		}
	}
}
