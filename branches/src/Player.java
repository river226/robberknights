/**
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The Player Class:
 *
 * The Player Class holds and manages the players deck, hand and knights
 * The player has a color and an id that is a number
 *
 * Deck:
 * The deck is made of 5 sets, A -> E. All but A hold 5 tiles. A holds 4.
 * Each set is suffled independently of the other, and are gone through in
 * alphabetical order.
 *
 * Hand:
 * Hand holds up to 2 tiles at any given moment
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;

class Player {

	// currentSet hold the number of Tiles that have been drawn from the deck
	// handSize hold the current position in the hand array
	private int currentSet, handSize;
	private final int id;
	private final String color;
	private int remainingKnights;
	private final String name;
	private Stack<Tile> hand;
	private Stack<Tile> deck;

	/**Player constructor. Creates player info
	 * 
	 * @param num player number (1-4)
	 * @param n player name
	 */
	public Player(int num, String n) {
		id = num;
		name = n;
		hand = new Stack();
		deck = new Stack();
		currentSet = 0;
		handSize = 0;

		if(id == 1)
			color = "Red";
		else if(id == 2)
			color = "Blue";
		else if(id == 3)
			color = "Yellow";
		else
			color = "Green";

		// player starts out with 30 knights
		remainingKnights = 30;

		Initialize();
	}

	/**Creates player deck
	 */
	private void Initialize() {
		ArrayList<Tile> tempDeck = new ArrayList(0);
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town
		// for enviroment 0 = lake, 1 = plain, 2 = woods, 3 = mountains
		int none = 0, castle = 1, village = 2, town = 3;
		int lake = 0, plain = 1, woods = 2, mountains = 3;

		// set A - Plain, Plain Village, Mountains, Plain Castle
		tempDeck.add(new Tile(plain, none, 'A'));
		tempDeck.add(new Tile(plain, village, 'A'));
		tempDeck.add(new Tile(mountains, none, 'A'));
		tempDeck.add(new Tile(plain, castle, 'A'));

		// set B - Water, Plain Castle, Plain Village, Woods Village, Plain Town
		tempDeck.add(new Tile(lake, none, 'B'));
		tempDeck.add(new Tile(plain, castle, 'B'));
		tempDeck.add(new Tile(plain, village, 'B'));
		tempDeck.add(new Tile(woods, village, 'B'));
		tempDeck.add(new Tile(plain, town, 'B'));

		// set C - Woods Town, Plain, Plain Castle, Plain Village, Woods Castle
		tempDeck.add(new Tile(woods, town, 'C'));
		tempDeck.add(new Tile(plain, none, 'C'));
		tempDeck.add(new Tile(plain, castle, 'C'));
		tempDeck.add(new Tile(plain, village, 'C'));
		tempDeck.add(new Tile(woods, castle,'C'));

		// set D - Plain Castle, Mountains, Woods Village, Plain Castle, Plain Village
		tempDeck.add(new Tile(plain, castle, 'D'));
		tempDeck.add(new Tile(mountains, none, 'D'));
		tempDeck.add(new Tile(woods, village, 'D'));
		tempDeck.add(new Tile(plain, castle, 'D'));
		tempDeck.add(new Tile(plain, village, 'D'));

		// set E - Plain Castle, Woods Village, Plain Village, Woods Castle, Plain
		tempDeck.add(new Tile(plain, castle, 'E'));
		tempDeck.add(new Tile(woods, village, 'E'));
		tempDeck.add(new Tile(plain, village, 'E'));
		tempDeck.add(new Tile(woods, castle, 'E'));
		tempDeck.add(new Tile(plain, none, 'E'));

		shuffle(tempDeck);
	}

	/**Randomize tile order in (aka shuffle) deck 
	 * 
	 * @param tempDeck array of tiles in the players deck
	 */
	private void shuffle(ArrayList<Tile> tempDeck) {
		Random gen = new Random();
		Tile temp;
		int x = 0;
		int set = 4;

		for(int i = 4; i < 24; i++){
			int p = 4 + gen.nextInt(5);

			temp = tempDeck.get(p);
			tempDeck.add(p, tempDeck.get(i));
			tempDeck.add(i, temp);
			x++;

			if(x == 3){
				i++;
				x = 0;

				if(i == 9 || i == 14 || i == 19)
					set += 5;
			}      
		}

		for(int j = 23; j >= 0; j--)
			deck.push(tempDeck.get(j));
	}


	/**Retrieves player color
	 * 
	 * @return String of color name
	 */
	public String getColor() {
		return color;
	}


	/**Get tiles in the hand
	 * 
	 * @return Tiles in hand
	 */
	public Tile[] getHand() {

		if(handSize() == 2)
			return new Tile[] {hand.pop(), hand.pop()};
		else if(handSize() == 1)
			return new Tile[] {hand.pop()};
		else
			return null;
	}

	/**Gets the player's name
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**Gets the player's number
	 * 
	 * @return player number
	 */

	public int getID() {
		return id;
	}

	/**Get knights remaining
	 * 
	 * @return remaining knights
	 */
	public int remainingKnights() {
		return remainingKnights;
	}

	/**Removes knights from player
	 * 
	 * @param count number of knights to be removed
	 */
	public void playKnights(int count) {
		remainingKnights -= count;
	}

	/**Pulls set A from the deck
	 * 
	 * @return Set A
	 */
	public Tile[] firstMoveGet() {
		Tile[] setA = new Tile[3];

		for(int i = 0; i < 3; i++) {
			setA[i] = deck.pop();
			currentSet--;
		}

		addTile(deck.pop());

		return setA;
	}

	/**Retrieve number of tiles remaining
	 * for current player
	 * @return number of tiles left
	 */
	public int numTiles(){
		return currentSet;
	}

	/**Retrieves hand size
	 * 
	 * @return number of tiles in hand
	 */
	public int handSize() {
		return handSize;
	}

	/**Adds a tile to the player's hand
	 * 
	 * @return 0 for success, 1 for failure
	 */
	public int draw() {
		if(numTiles() > 0) {
			if(handSize() < 2)
				return addTile(deck.pop());
		}

		return 1;
	}

	/**???
	 * 
	 * @param location = ???
	 * @return tile to add
	 */
	public Tile makeMove(int location) {
		Tile t;

		if(location == 1 && handSize() > 1) {
			t = hand.pop();
		}
		else if(location == 2 && handSize() == 2) {
			Tile temp = hand.pop();
			t = hand.pop();
			hand.push(t);
		}
		else
			return null;

		handSize--;
		draw();
		return t;
	}


	/**Add tile to hand
	 * 
	 * @param t tile to add
	 */
	public void firstMoveSet(Tile t) {
		addTile(t);
	}

	/**Add tile to hand
	 * 
	 * @param newTile Tile to add
	 * @return 0 = success, 1 = failure
	 */
	public int addTile(Tile newTile) {
		if(handSize() < 2) {
			hand.push(newTile);
			handSize++;
			return 0;
		}

		return 1;
	}

}

