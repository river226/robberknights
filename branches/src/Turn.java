/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The Turn Class:
 *
 * The Turn class manages all the steps and update for a specific move.
 */

private class Turn extends Game {
		private int x, y, dir, Knightsleft;
		private int movecount;
		private Player current;
		
		public Turn(Player p) {
			dir = -1;
			knightsleft = 0;
			movecount = 0;
			current = p;
		}
		
		// Set the direction of the movement
		// 0 = right, 1 = left, 2 = up, 3 = down
		public int setDirection(int d) {
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
			
			if(t.getHabitat().equals("castle"));
				return 1;
				
			return 0;
		}
		
		// get the minimum number of knights needed to play on current tile
		public int getMinKnights() {
			return playfield.getGrid()[x][y].minKnights();
		}
		
		// add knights to the tile
		public void playKnights(int x, int y, int n) throws exception {
			int[] tokens = new int[n];
			
			for(int i = 0; i < n; i++)
				tokens[i] = player.getID();
				
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
				
			playfield.getGrid()[x][y].setKnight(tokens);
		}
		
		// get the hand to play
		public Tile[] getHand() {
			x = 0;
			y = 0;
			dir = -1;
			KnightsLeft = 0;
		
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
					l = new LoctionList(x+1, y, l) ;
				if(b[x][y-1] != null && b[x][y-1].valid())
					l = new LoctionList(x, y-1, l);
				if(b[x][y-1] != null && b[x][y+1].valid())
					l = new LoctionList(x, y+1, l);
				if(b[x-1][y] != null && b[x-1][y].valid())
					l = new LoctionList(x-1, y, l);
			} else if(knightsleft != 0) { 
				if(dir == 0 && b[x+1][y].valid() && b[x+1][y].minKnights() < KnightsLeft) // right
					l = new LocationList(x+1, y, l);
				else if(dir == 1 && b[x-1][y].valid() && b[x-1][y].minKnights() < KnightsLeft) // left
					l = new LocationList(x-1, y, l);
				else if(dir == 2 && b[x][y-1].valid() && b[x][y-1].minKnights() < KnightsLeft) // up
					l = new LocationList(x, y-1, l);
				else if(dir == 3 && b[x][y+1].valid() && b[x][y+1].minKnights() < KnightsLeft) // down
					l = new LocationList(x, y+1, l);
			}
			
			return l;
		}
}