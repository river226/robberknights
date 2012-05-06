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
	private int botY, topY, lefX, ritX, height, width; 
	private int nump;
	private final int max;

	/**Board constructor
	 * 
	 * @param playNum number of players
	 */
	public Board(int playNum) {
		nump = playNum;
		switch(nump) {
		case(2) : grid = new Tile[10][10]; break; // 8x8 with buffer on each side
		case(3) : grid = new Tile[11][11]; break; // 9x9 with buffer on each side
		default : grid = new Tile[12][12]; break;// 10x10 with buffer on each side
		}

		max = grid.length - 2;
		ritX = grid.length/2;
		topY = ritX;
		botY = topY;
		lefX = botY;
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

			if(x > getRightX())
				setRightX(x);
			else if(x < getLeftX())
				setLeftX(x);

			if(y > getBottomY())
				setBottomY(y);
			else if(y < getTopY())
				setTopY(y);

			boardFixCheck();

			return 0;
		}

		return 1;
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
		setHeight(getBottomY() - getTopY());
		setWidth(getRightX() - getLeftX());
	}

	/**Retrieve the top tile row
	 * 
	 * @return int top row number
	 */
	public int getTopY() {
		return topY;
	}

	/**Retrieve the bottom tile row
	 * 
	 * @return int bottom row number
	 */
	public int getBottomY() {
		return botY;
	}


	/**Retrieve the leftmost tile row
	 * 
	 * @return int leftmost row number
	 */
	public int getLeftX() {
		return lefX;
	}

	/**Retrieve the rightmost tile row
	 * 
	 * @return int rightmost row number
	 */
	public int getRightX() {
		return ritX;
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
	public void setTopY(int x) {
		topY = x;
		reset();
	}

	/**Set bottom row of the play field
	 * 
	 * @param x = int bottom row number
	 */
	public void setBottomY(int x) {
		botY = x;
		reset();
	}

	/**Set the leftmost column of the play field
	 * 
	 * @param x = int leftmost column number
	 */
	public void setLeftX(int x) {
		lefX = x;
		reset();
	}

	/**Set the rightmost column of the play field
	 * 
	 * @param x = int rightmost column number
	 */
	public void setRightX(int x) {
		ritX = x;
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
			for(int j = 0; j < nump; j++) { // place tiles on grid
				for(int k = 0; k < 2; k++) {
					placeTile(t[j][k], set, slot);
					slot++;
				}
				set++;
				slot -= 2;
			}
		} else { // randomize placement: horizontal
			for(int j = 0; j < nump; j++) { // place tiles on grid
				for(int k = 0; k < 2; k++) {
					placeTile(t[j][k], slot, set);
					slot++;
				}
				set++;
				slot -= 2;
			}
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
		if(getWidth() == getMax()-1 && (x > getRightX() || x < getLeftX()))
			return false;
		else if(getHeight() == getMax()-1 && (y > getBottomY() || y < getTopY()))
			return false;
		else
			return true;
	}


	/**Shifts board if needed
	 */
	private void boardFixCheck() {
		if(getTopY() == 0) {
			int c = (getMax() + 2) - getBottomY()/2;
			moveField(3, c);
			setTopY(getTopY() - c);
			setBottomY(getBottomY() - c);
		}
		else if(getBottomY() == getMax() + 2) {
			int c = (getTopY()/2);
			moveField(2, c);
			setTopY(getTopY()+ c);
			setBottomY(getBottomY() + c);
		}

		if(getLeftX() == 0) {
			int c = (getMax() + 2) - getRightX()/2;
			moveField(1, c);
			setLeftX(getLeftX() + c);
			setRightX(getRightX() + c);
		}
		else if(getRightX() == getMax() + 2) {
			int c = (getLeftX()/2);
			moveField(0, c);
			setLeftX(getLeftX() - c);
			setRightX(getRightX() - c);
		}
	}

	/**Moves the playfield
	 * 
	 * @param dir 0 = left, 1 = right, 2 = up, 3 = down
	 * @param dis distance to be shifted
	 */
	private void moveField(int dir, int dis) {
		if(dir == 0) {
			for(int i = getLeftX(); i <= getRightX(); i++) {
				for(int j = getTopY(); j <= getBottomY(); j++) {
					grid[i - dis][j] = grid[i][j];
					grid[i][j] = null;
				}
			}
		}
		else if(dir == 1) {
			for(int i = getRightX(); i <= getLeftX(); i--) {
				for(int j = getTopY(); j <= getBottomY(); j++) {
					grid[i + dis][j] = grid[i][j];
					grid[i][j] = null;
				}
			}
		}
		else if(dir == 2) {
			for(int i =  getTopY(); i <= getBottomY(); i--) {
				for(int j = getLeftX(); j <= getRightX(); j++) {
					grid[j][i - dis] = grid[i][j];
					grid[i][j] = null;
				}
			}
		}
		else {
			for(int i =  getBottomY(); i <= getTopY(); i++) {
				for(int j = getLeftX(); j <= getRightX(); j++) {
					grid[j][i + dis] = grid[i][j];
					grid[i][j] = null;
				}
			}
		}
	}
}
