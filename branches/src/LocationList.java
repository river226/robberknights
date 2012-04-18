/**
 * Group 5 Robber Knights Game
 *
 * The LocationList Class:
 *
 * simple list structure to fascilitate the transfer of a list of loctions
 * 
 */

private class LocationList {

	private int x, y;
	private LocationList next;
	
	public LocationList(int x1, int y1, LocationList p) {
		x = x1;
		y = y1;
		next = p;
	}
	
	// get the x location
	public int getX() { 
		return x; 
	}
	
	// get the y location
	public int getY() { 
		return y; 
	}
	
	// returns null at the end of the list
	public LocationList getNext() { 
		return next; 
	}
}