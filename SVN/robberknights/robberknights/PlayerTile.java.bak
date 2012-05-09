/**
 * Group 5 Robber Knights Game
 *
 * The PlayerTile Class:
 *
 * The player tile class maintains the deck and hand for the player. 
 * 
 * Deck:
 * The deck is made of 5 sets, A -> E. All but A hold 5 tiles. A holds 4.
 * Each set is suffled independently of the other, and are gone through in 
 * alphabetical order. 
 *
 * Hand:
 * Hand holds up to 2 tiles at any given moment
 * 
 */
 
import java.util.Random;
import java.util.ArrayList;
 
class PlayerTile {
	
	private Tile[] hand;
	private ArrayList<Tile> deck = new ArrayList(0); // unchecked, 0 is not an illegal argument
	
	// currentSet hold the number of Tiles that have been drawn from the deck
	// handSize hold the current position in the hand array
	private int currentSet = 0, handSize = 0;
	
	// constructor, Build the deck and assemble hand
	public PlayerTile() {
		shuffle();
	}
	
	// Add a tile to the hand, return 1 if it failed 0 if successful
	public int addTile(Tile newTile) {
		if(handSize() < 2) {
			hand[handSize()] = newTile;
			handSize++;
			return 0;
		}
		
		return 1;
	}
	
	// retrive setA for playing the first few tiles to be played on the board
	public Tile[] getSetA() {
		Tile[] setA = new Tile[3];
		
		for(int i = 0; i < 3; i++)
			setA[i] = deck.get(i);
		
		currentSet = 2;
		
		addTile(deck.get(3));
		
		return setA;
	}
	
	// Play a Tile fromt he hand, return null if hand is empty
	public Tile playTile(int tile) {
		if(tile < 2){
			handSize--;
			return showHand()[tile];
		}
		
		return null;
	}
	
	// retrieve the players hand
	public Tile[] showHand() {
		return hand;
	}
	
	// return the current number of tiles in the hand
	public int handSize() {
		return handSize;
	}
	
	// initialize the deck deck
	private void Initialize() {
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town
		// for enviroment 0 = lake, 1 = plain, 2 = woods, 3 = mountains
		int none = 0, castle = 1, village = 2, town = 3;
		int lake = 0, plain = 1, woods = 2, mountains = 3;
		
		// set A - Plain, Plain Village, Mountains, Plain Castle
		deck.add(new Tile(plain, none, 'A'));
		deck.add(new Tile(plain, village, 'A'));
		deck.add(new Tile(mountains, none, 'A'));
		deck.add(new Tile(plain, castle, 'A'));
		
		// set B - Water, Plain Castle, Plain Village, Woods Village, Plain Town
		deck.add(new Tile(lake, none, 'B'));
		deck.add(new Tile(plain, castle, 'B'));
		deck.add(new Tile(plain, village, 'B'));
		deck.add(new Tile(woods, village, 'B'));
		deck.add(new Tile(plain, town, 'B'));
		
		// set C - Woods Town, Plain, Plain Castle, Plain Village, Woods Castle
		deck.add(new Tile(woods, town, 'C'));
		deck.add(new Tile(plain, none, 'C'));
		deck.add(new Tile(plain, castle, 'C'));
		deck.add(new Tile(plain, village, 'C'));
		deck.add(new Tile(woods, castle,'C'));
		
		// set D - Plain Castle, Mountains, Woods Village, Plain Castle, Plain Village
		deck.add(new Tile(plain, castle, 'D'));
		deck.add(new Tile(mountains, none, 'D'));
		deck.add(new Tile(woods, village, 'D'));
		deck.add(new Tile(plain, castle, 'D'));
		deck.add(new Tile(plain, village, 'D'));
		
		// set E - Plain Castle, Woods Village, Plain Village, Woods Castle, Plain
		deck.add(new Tile(plain, castle, 'E'));
		deck.add(new Tile(woods, village, 'E'));
		deck.add(new Tile(plain, village, 'E'));
		deck.add(new Tile(woods, castle, 'E'));
		deck.add(new Tile(plain, none, 'E'));
	}
	
	// randomize each tile, 
	private void shuffle() {
		Random gen = new Random(); 
		Tile temp;
		int x = 0;
		int set = 4;
		
		for(int i = 4; i < 24;){
			int p = 4 + gen.nextInt(5);
			
			temp = deck.get(p);
			deck.add(p, deck.get(i));
			deck.add(i, temp);
			x++;
			
			if(x == 3){
				i++;
				x = 0;
				
				if(i == 9 || i == 14 || i == 19)
					set += 5;
			}	
		}		
	}
	
	// Draw a tile from the top of the deck and add it to the hand
	public int draw() {
		if(numTiles() < 0) {
			if(handSize() < 2)
				return addTile(deck.get(currentSet));
		}
		
		return 1;
	}
	
	// Gather the current number of tiles in the deck
	public int numTiles(){
		return 24-currentSet;
	}
}