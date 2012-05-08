import java.awt.Color;

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
	public Turn move;


	/**Constructor of Game. Initializes elements of the GUI
	 * so that the game can start
	 * 
	 * @param nump = int Number of players
	 * @param names = String[] of names
	 * @param ages = int[] of player ages
	 */
	public Game(int nump, String[] names, int[] ages) {
		numplayers = nump;
		c = 0;
		turn = 0;
		totalKnights = 120;
		totalTiles = 96;
		generatePlayfield();
		
		String[] temp = new String[nump];
		
		for(int i = 0; i < nump; i++) {
			int t = 0;
			for(int j = 0; j < nump; j++) {
				if(ages[j] > ages[t])
					t = j;
			}
			temp[i] = names[t];
			ages[t] = 0;
		}
		
		generatePlayers(temp);
	}
	
	/**Constructor of Game. Initializes elements of the GUI
	 * so that the game can start
	 * 
	 * @param nump = int Number of players
	 * @param names = String[] of names
	 */
	public Game(int nump, String[] names) {
		numplayers = nump;
		c = 0;
		turn = 0;
		totalKnights = 120;
		totalTiles = 96;
		generatePlayfield();
		generatePlayers(names);
	}


	/**Generates Players
	 * 
	 * @param names: String[] of player names
	 */
	private void generatePlayers(String[] names) {
		players = new Player[numplayers];

		for(int i = 0; i < numplayers; i++)
			players[i] = new Player(i+1, names[i]);

	}

	/**Generate board
	 */
	private void generatePlayfield() {
		playfield = new Board(numplayers);
	}


	/**Returns the board
	 * 
	 * @return Tile[][] of the board
	 */
	public Tile[][] getBoard() {
		return playfield.getGrid();
	}

	/**Get player name
	 * 
	 * @param i int = player number
	 * @return String player name
	 */
	public String getName(int i) {
		return players[i].getName();
	}


	/**Creates initial board using selected tiles
	 * 
	 * @return Tile[][] of board
	 */
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

	/**Return non-selected initial tile to hand
	 * 
	 * @param p int = player number
	 * @param t Tile = tile to return
	 */
	public void returnToHand(int p, Tile t) {
		if(turn == 0) {
			players[p].firstMoveSet(t);
			totalTiles -= 2;
		}
	}

	/**Takes tile list onto empty board
	 * 
	 * @param t Tile[][] = tile to add
	 * @return Tile[][] = null
	 */
	public Tile[][] setUpBoard(Tile[][] t) {
		if(turn == 0) { return playfield.setup(t); }
		return null;
	}

	/**Manage the  game including all updates
	 * if we have no more tiles of knights, the game is over
	 * 
	 * @return Turn of next player
	 */
	public void nextTurn() { 
		move = null;
		if(c == numplayers)                
			c = 0;
		
		if(totalTiles > 0 || totalKnights > 0)
			move = new Turn(players[c++]);

		turn++;



	}

	/**
	 * Totals the score of each player
	 */
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
		private int x, y, dir, Knightsleft, playerKnights;
		private int movecount;
		private Player current;
		private boolean castlePlayed = false;


		/**Turn Constructor.
		 * 
		 * @param p Player whose turn it is
		 */
		public Turn(Player p) {
			dir = -1;
			Knightsleft = 0;
			movecount = 0;
			current = p;
			playerKnights = current.remainingKnights();
		}
		
		public String getReminingKnights() {
			return String.valueOf(current.remainingKnights());
		}
		
		public Color getColor() {
			return current.getColor();
		}
		
		public String getRemainingTiles() {
			return String.valueOf(current.numTiles());
		}
		
		public int getCurrentMove() {
			return movecount;
		}

		/**Set direction of the movement
		 * 
		 * @param d int 0 = right, 1 = left, 2 = up, 3 = down
		 */
		public void setDirection(int d) {
			dir = d;
		}


		/**Retrieve valid locations
		 * 
		 * @return LocationList of valid locations
		 */
		public LocationList getValidMoves() {
			return playfield.getValidMoves();
		}

		/**Ends the move
		 */
		public void endMove() {
			dir = -1;
			Knightsleft = 0;
			castlePlayed = false;
		}

		/** End the current Players turn
		 */
		public void endTurn(Tile[] ret) {
			if(ret.length == 1) {
				returnTile(ret[0]);
			}else if(ret.length == 2) {
				returnTile(ret[0]);
				returnTile(ret[1]);
			}
			movecount = 3;
			castlePlayed = false;
		}

		/**Return a tile from the GUI to the hand
		 * 
		 * @param t = Tile to return
		 */
		public void returnTile(Tile t) {
			current.addTile(t);
		}

		/**Get the minimum knights to play on tile
		 * 
		 * @return int minimum knights
		 */
		public int getMinKnights() {
			return playfield.getGrid()[x][y].minKnight();
		}

		/**Add knights on a tile
		 * 
		 * @param x int x coordinate
		 * @param y int y coordinate
		 * @param n int of number of knights
		 */
		public void playKnights(int x1, int y1, int n) {
			if(castlePlayed && getKnightsLeft() >= n) {
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
					playfield.getGrid()[x1][y1].setKnight(tokens);
				} catch (Exception e) {} 
			}
		}

		/** Set up for Knight movement
		 */
		public boolean startKnightMove() {
			if(castlePlayed && playerKnights > 0) {
				if(playerKnights >= 5) Knightsleft = 5;
				else Knightsleft = playerKnights;
				return true;
			}
			return false;
		}

		public int getKnightsLeft() {
			return Knightsleft;
		}


		/**Get current player's hand to display
		 * 
		 * @return Tile[] of hand
		 */
		public Tile[] getHand() {
			x = 0;
			y = 0;
			dir = -1;
			Knightsleft = 0;
			castlePlayed = false;

			if(movecount < 3)
				return current.getHand();

			return null;
		}

		/**determine valid knight placement locations
		 * 
		 * @return LocationList of valid locations (linked list)
		 */
		public LocationList KnightMoves() {
			Tile[][] b = playfield.getGrid();
			LocationList l = null;

			if(!castlePlayed)
				return l;

			if(b[x][y].topKnight() < 0) {
				l = new LocationList(x, y, l);
			} else if(dir == -1) {
				if(b[x+1][y] != null && b[x+1][y].valid())
					l = new LocationList(x+1, y, l) ;
				if(b[x][y-1] != null && b[x][y-1].valid())
					l = new LocationList(x, y-1, l);
				if(b[x][y+1] != null && b[x][y+1].valid())
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
		
		public String getName() {
			return current.getName();
		}
		
		// Play a tile to the board and return 1 to the hand
		public boolean makeMove(Tile place, int x1, int y1, Tile returnT) {
			this.x = x1;
			this.y = y1;
			
			if(playerKnights < current.remainingKnights())
				current.playKnights(playerKnights);

			if(place != null) {
				playfield.placeTile(place, x1, y1);
				if(place.getHabitat().equals("castle")) 
					castlePlayed = true;
				totalTiles--;
			}
			if(returnT != null) {
				current.addTile(returnT);
				if(current.numTiles() >= 1)
					current.draw();
			} else if(returnT == null && current.handSize() == 0) {
				if(current.numTiles() > 1) {
					current.draw();
					current.draw();
				} else if(current.numTiles() > 0) 
					current.draw();
			}

			movecount++;
			dir = -1;

			return castlePlayed;
		}
	}
}
