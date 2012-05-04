/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The LocationList Class:
 *
 * simple list structure to fascilitate the transfer of a list of loctions
 * 
 */

/**Hold a linked list of locations.
 * 
 * LocationList(int, int);
 * int getX();
 * int getY();
 * boolean isNext();
 * LocationList getNext();
 *
 */
class LocationList {

	private int x, y;
	private LocationList next;
	
	/**Constructor for LocationList, linked list style
	 * 
	 * @param x1 = int x coordinate on grid
	 * @param y1 = int y coordinate on grid
	 * @param p = LocationList of next spot
	 */
	public LocationList(int x1, int y1, LocationList p) {
		x = x1;
		y = y1;
		next = p;
	}
	
	/**Get the x location
	 * 
	 * @return x location
	 */
	public int getX() { 
		return x; 
	}
	
	/**Get the y location
	 * 
	 * @return y location
	 */
	public int getY() { 
		return y; 
	}
	
/**Determine whether or not there is another 
 * LocationList after the current one
 * 
 * @return true or false
 */
	public Boolean isNext() {
		if(next != null)
		{
			return true;
		}
		return false;
	}
	
	/**Returns the next LocationList
	 * 
	 * @return Next LocationList
	 */
	public LocationList getNext() { 
		return next; 
	}
}