/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The Board Class:
 *
 * The Board class maintains the playfield, and the placement of tiles
 * 
 */

import java.util.Random;

class Board {

	private Tile[][] grid;
	private int ritY, lefY, topX, botX, height, width; 
	private int nump;
	private final int max;

	/**Board constructor
	 * 
	 * @param playNum number of players
	 */
	public Board(int playNum) {
		nump = playNum;
		switch(nump) {
		case(2) : grid = new Tile[9][9]; break; // 7x7 with buffer on each side
		case(3) : grid = new Tile[11][11]; break; // 9x9 with buffer on each side
		default : grid = new Tile[12][12]; break;// 10x10 with buffer on each side
		}

		max = grid.length - 2;
		reset();
	}


	/**Place a tile
	 * 
	 * @param t Tile to be placed
	 * @param x int x coordinate 
	 * @param y int y coordinate
	 * @return int 0 = successful, 1 = failure
	 */
	public int placeTile(Tile t, int x, int y) {            
		if(grid[x][y] == null && valid(x, y)) {

			grid[x][y] = t;

			if(x > getBottomX())
				setBottomX(x);
			else if(x < getTopX())
				setTopX(x);

			if(y > getRightY())
				setRightY(y);
			else if(y < getLeftY())
				setLeftY(y);

			boardFixCheck();

			return 0;
		}

		return 1;
	}
	
	/**Returns minimun required knights to place on tile
	 * 
	 * @param x int x coordinate 
	 * @param y int y coordinate
	 * @return int 0 = no knights allowed, 1..3 = number of knights required
	 */
	public int getMinKnights(int x , int y){
		return grid[x][y].minKnight();
	}

	/**Retrieves grid
	 * 
	 * @return Tile[][] of grid
	 */
	public Tile[][] getGrid() {
		return grid;
	}

	/**
	 * ???
	 */
	public void reset() {
		setHeight(getRightY() - getLeftY());
		setWidth(getBottomX() - getTopX());
	}

	/**Retrieve the top tile row
	 * 
	 * @return int top row number
	 */
	public int getLeftY() {
		return lefY;
	}

	/**Retrieve the bottom tile row
	 * 
	 * @return int bottom row number
	 */
	public int getRightY() {
		return ritY;
	}


	/**Retrieve the leftmost tile row
	 * 
	 * @return int leftmost row number
	 */
	public int getTopX() {
		return topX;
	}

	/**Retrieve the rightmost tile row
	 * 
	 * @return int rightmost row number
	 */
	public int getBottomX() {
		return botX;
	}

	/**Retrieve the width of the play field
	 * 
	 * @return int width
	 */
	public int getWidth() {
		return width;
	}

	/**Retrieve the height of the playfield
	 * 
	 * @return int height
	 */
	public int getHeight() {
		return height;
	}

	/**Retrieve the max width of the play field
	 * 
	 * @return int max width
	 */
	public int getMax() {
		return max;
	}


	/**Set top row of the play field
	 * 
	 * @param x = int top row number
	 */
	public void setLeftY(int x) {
		lefY = x;
		reset();
	}

	/**Set bottom row of the play field
	 * 
	 * @param x = int bottom row number
	 */
	public void setRightY(int x) {
		ritY = x;
		reset();
	}

	/**Set the leftmost column of the play field
	 * 
	 * @param x = int leftmost column number
	 */
	public void setTopX(int x) {
		topX = x;
		reset();
	}

	/**Set the rightmost column of the play field
	 * 
	 * @param x = int rightmost column number
	 */
	public void setBottomX(int x) {
		botX = x;
		reset();
	}

	/**Set the width of the play field
	 * 
	 * @param x = int width number
	 */
	public void setWidth(int x) {
		width = x;
	}

	/**Set the wideth of the playfield
	 * 
	 * @param y = int height number
	 */
	public void setHeight(int y) {
		height = y;
	}

	/**Set ups board in initial configuration with 
	 * initial tile selections and appropriate shape 
	 * (2x2, 2x3, or 2x4)
	 * 
	 * @param t = Tile[][] of initial tiles
	 * @return Tile[][] of grid
	 */
	public Tile[][] setup(Tile[][] t) {
		Random gen = new Random();
		for(int l = 0; l < nump; l++) { // straighten array into nump x 2
			for(int p = 0;p  < 3; p++) {
				if(t[l][p] == null && p != 2){
					t[l][p] = t[l][p+1];
					t[l][p+1] = null;
				}
			}
		}
		for(int i = 0; i < 100; i++) { // randomize tiles
			int y1 = gen.nextInt(2);
			int x1 = gen.nextInt(nump);
			int y2 = gen.nextInt(2);
			int x2 = gen.nextInt(nump);
			Tile p = t[x1][y1];
			t[x1][y1] = t[x2][y2];
			t[x2][y2] = p;
		}

		int slot = max/2;
		int set = 4;
		if(gen.nextInt(100) > 50) { // randomize placement: vertical
			lefY = set;
			topX = slot;
			for(int j = 0; j < nump; j++) { // place tiles on grid
				for(int k = 0; k < 2; k++) {
					placeTile(t[j][k], set, slot);
					slot++;
				}
				set++;
				slot -= 2;
			}
			ritY = set;
			botX = slot;
		} else { // randomize placement: horizontal
			lefY = slot;
			topX = set;
			for(int j = 0; j < nump; j++) { // place tiles on grid
				for(int k = 0; k < 2; k++) {
					placeTile(t[j][k], slot, set);
					slot++;
				}
				set++;
				slot -= 2;
			}
			ritY = slot;
			botX = set;
		}

		return getGrid();
	}

	/**Find all of the valid locations for the next move
	 * Puts into LocationList, linked list format.
	 * @return LocationList of valid locations
	 */
	public LocationList getValidMoves() {
		LocationList l = null;
		Tile[][] t = getGrid();

		for(int i = 0; i < getMax() + 1; i++) {
			for(int j = i; j <= getMax() + 1; j++) {

				// check verticle
				if(t[i][j] == null) {
					if(i != getMax()+1 && t[i+1][j] != null) {
						if(valid(i,j))
							l = new LocationList(i,j,l);
					} else if(j != getMax()+1 && t[i][j+1] != null) {
						if(valid(i,j))
							l = new LocationList(i,j,l);
					} else if(i != 0 && t[i-1][j] != null) {
						if(valid(i,j))
							l = new LocationList(i,j,l);
					} else if(j != 0 && t[i][j-1] != null) {
						if(valid(i,j))
							l = new LocationList(i,j,l);
					} 
				}

				// check horizontal 
				if(t[j][i] == null) {
					if(j != getMax()+1 && t[j+1][i] != null) {
						if(valid(j,i))
							l = new LocationList(j,i,l);
					} else if(i != getMax()+1 && t[j][i+1] != null) {
						if(valid(j,i))
							l = new LocationList(j,i,l);
					} else if(j != 0 && t[j-1][i] != null) {
						if(valid(j,i))
							l = new LocationList(j,i,l);
					} else if(i != 0 && t[j][i-1] != null) {
						if(valid(j,i))
							l = new LocationList(j,i,l);
					} 
				}

			}
	}

		return l;
	}

	/**Determine if space is a valid move or not
	 * 
	 * @param x = int x coordinate
	 * @param y = int y coordinate
	 * @return boolean true = valid, false = invalid
	 */
	public boolean valid(int x, int y) {
		if(getWidth() == getMax()-1 && (x > getBottomX() || x < getTopX()))
			return false;
		else if(getHeight() == getMax()-1 && (y > getRightY() || y < getLeftY()))
			return false;
		else
			return true;
	}


	/**Shifts board if needed
	 */
	private void boardFixCheck() {
		if(getLeftY() == 0) {
			int c = 1;
			moveField(3, c);
			setLeftY(getLeftY() + c);
			setRightY(getRightY() + c);
			reset();
		}
		else if(getRightY() == getMax() + 1) {
			int c = 1;
			moveField(2, c);
			setLeftY(getLeftY()- c);
			setRightY(getRightY() - c);
			reset();
		}

		if(getTopX() == 0) {
			int c = 1;
			moveField(1, c);
			setTopX(getTopX() + c);
			setBottomX(getBottomX() + c);
			reset();
		}
		else if(getBottomX() == getMax() + 1) {
			int c = 1;
			moveField(0, c);
			setTopX(getTopX() - c);
			setBottomX(getBottomX() - c);
			reset();
		}
	}

	/**Moves the playfield
	 * 
	 * @param dir 0 = Up, 1 = Down, 2 = Left, 3 = Right
	 * @param dis distance to be shifted
	 */
	private void moveField(int dir, int dis) {
		if(dir == 0) {
			for(int i = getTopX(); i <= getBottomX(); i++) {
				for(int j = getLeftY(); j <= getRightY(); j++) {
					grid[i - dis][j] = grid[i][j];
					grid[i][j] = null;
				}
			}
		} else if(dir == 1) {
			for(int i = getBottomX(); i >= getTopX(); i--) {
				for(int j = getLeftY(); j <= getRightY(); j++) {
					grid[i + dis][j] = grid[i][j];
					grid[i][j] = null;
				}
			}
		} else if(dir == 2) {
			for(int i =  getLeftY(); i <= getRightY(); i++) {
				for(int j = getTopX(); j <= getBottomX(); j++) {
					grid[j][i - dis] = grid[j][i];
					grid[j][i] = null;
				}
			}
		} else {
			for(int i =  getRightY(); i >= getLeftY(); i--) {
				for(int j = getTopX(); j <= getBottomX(); j++) {
					grid[j][i + dis] = grid[j][i];
					grid[j][i] = null;
				}
			}
		}
	}
}
