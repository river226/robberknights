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
 
class PlayerTile() {
	
	private Tile[2] hand;
	private Tile[4] setA;
	private Tile[5] setB, setC, setD, setE;
	
	// currentSet hold the number of Tiles that have been drawn from the deck
	// handSize hold the current position in the hand array
	private int currentSet = 0, handSize = 0;
	
	// constructor, Build the deck and assemble hand
	public PlayerTiles() {
		shuffle();
		draw();
		draw();
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
	
	// Play a Tile fromt he hand, return null if hand is empty
	public Tile playTile(int tile) {
		if(tile < 2){
			handSize--;
			return showHand()[tile];
		}
		
		return null;
	}
	
	// retrieve the players hand
	public tile[] showHand() {
		return hand;
	}
	
	// return the current number of tiles in the hand
	public int handSize() {
		return handSize;
	}
	
	// randomize each tile, initialize deck
	private void shuffle() {
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town
		// for enviroment 0 = lake, 1 = plain, 2 = woods, 3 = mountains
		int none = 0, castle = 1, village = 2, town = 3;
		int lake = 0, plain = 1, woods = 2, mountains = 3;
		Tile[5] set;
		Random gen = new Random();
		
		// setA
		set[0] = new Tile(,,'A');
		set[1] = new Tile(,,'A');
		set[2] = new Tile(,,'A');
		set[3] = new Tile(,,'A');
		
		for(int i = 0; i < 4;){
			int p = gen.newInt(4);
			
			if(setA[p] == null){
				setA[p] = set[i]
				i++;
			}	
		}
		
		// setB
		set[0] = new Tile(,,'B');
		set[1] = new Tile(,,'B');
		set[2] = new Tile(,,'B');
		set[3] = new Tile(,,'B');
		set[4] = new Tile(,,'B');
		
		for(int i = 0; i < 5;){
			int p = gen.newInt(5);
			
			if(setB[p] == null){
				setB[p] = set[i]
				i++;
			}	
		}
		
		// setC
		set[0] = new Tile(,,'C');
		set[1] = new Tile(,,'C');
		set[2] = new Tile(,,'C');
		set[3] = new Tile(,,'C');
		set[4] = new Tile(,,'C');
		
		for(int i = 0; i < 5;){
			int p = gen.newInt(5);
			
			if(setC[p] == null){
				setC[p] = set[i]
				i++;
			}	
		}
		
		// setD
		set[0] = new Tile(,,'D');
		set[1] = new Tile(,,'D');
		set[2] = new Tile(,,'D');
		set[3] = new Tile(,,'D');
		set[4] = new Tile(,,'D');
		
		for(int i = 0; i < 5;){
			int p = gen.newInt(5);
			
			if(setD[p] == null){
				setD[p] = set[i]
				i++;
			}	
		}
		
		// setE
		set[0] = new Tile(,,'E');
		set[1] = new Tile(,,'E');
		set[2] = new Tile(,,'E');
		set[3] = new Tile(,,'E');
		set[4] = new Tile(,,'E');
		
		for(int i = 0; i < 5;){
			int p = gen.newInt(5);
			
			if(setE[p] == null){
				setE[p] = set[i]
				i++;
			}	
		}
	}
	
	// Draw a tile from the top of the deck and add it to the hand
	private int draw() {
		int x = numTiles();
		int s;
		
		if(x < 20) {
			s = addTile(setA[x]);
		}
		else if(x < 15) {
			s = addTile(setB[x - 5]);
		}
		else if(x < 10) {
			s = addTile(setC[x - 9]);
		}
		else if(x < 5) {
			s = addTile(setD[x - 14]);
		}
		else {
			s = addTile(setE[x - 19]);
		}
		
		currentSet--;
		return s;
	}
	
	// Gather the current number of tiles in the deck
	public int numTiles(){
		return 24-currentSet;
	}