/**
 * Group 5 Robber Knights Game
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
	private int currentSet = 0, handSize = 0;
	private final int id;
	private final String color;
	private int remainingKnights;
	private final String name;
	private Stack<Tile> hand;
	private Stack<Tile> deck = new Stack();
	
	// constructor, initialize variables
	public Player(int num, String n) {
		id = num;
		name = n;
		
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
		
		initialize();
	}
	
	// initialize the deck deck
	private void Initialize() {
		ArrayList<Tile> tempDeck() = new ArrayList(0);
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
	
	// randomize each tile, 
	private void shuffle(ArrayList<Tile> tempDeck) {
		Random gen = new Random(); 
		Tile temp;
		int x = 0;
		int set = 4;
		
		for(int i = 4; i < 24;){
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
	
	// Get the players color 
	public String getColor() {
		return color;
	}
	
	// Get the Hand 
	public Tile[] getHand() {
		handsize = 0;
		
		if(handSize() == 2)
			return Tile[] temp = [hand.pop(), hand.pop()];
		else if(handSize() == 1)
			return Tile[] temp = [hand.pop()];
		else
			return null;
	}
	
	// get the players name
	public String getName() {
		return name()
	}
	
	// get the playerID number
	public int getID() {
		return id;
	}
	
	// Get the number of knights left to play
	public int remainingKnights() {
		return remainingKnights;
	}
	
	// Get the Score value of the Tile
	public void playKnights(int count) {
		remainingKnights -= count;
	}

	// Make the first move by getting the the top set
	public Tile[] firstMoveGet() {
		Tile[] setA = new Tile[3];
		
		for(int i = 0; i < 3; i++) {
			setA[i] = deck.pop();
			currentSet--;
		}
		
		addTile(deck.pop());
		
		return setA;
	}
	
	// Gather the current number of tiles in the deck
	public int numTiles(){
		return currentSet;
	}
	
	// return the current number of tiles in the hand
	public int handSize() {
		return handSize;
	}
	
	// Draw a tile from the top of the deck and add it to the hand
	public int draw() {
		if(numTiles() < 0) {
			if(handSize() < 2)
				return addTile(deck.pop());
		}
		
		return 1;
	}
	
	// Get the a tile from the hand and add a new tile
	public Tile makeMove(int location) {
		Tile t = playTile(location);
		draw();
		return t;
	}
	// Restore the hand after making the first move
	public void firstMoveSet(Tile t) {
		addTile(t);
	}
	
	// Add a tile to the hand, return 1 if it failed 0 if successful
	public int addTile(Tile newTile) {
		if(handSize() < 2) {
			hand.push(newTile);
			handSize++;
			return 0;
		}
		
		return 1;
	}
	
}