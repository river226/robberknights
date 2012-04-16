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
	
	public int getX() { 
		return x; 
	}
	
	public int getY() { 
	return y; 
	}
	
	public LocationList getNext() { 
	return next; 
	}
}