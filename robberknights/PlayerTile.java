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
 
class PlayerTile {
	
	private Tile[] hand;
	private Tile[] setA, setB, setC, setD, setE; // the deck
	
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
		currentSet = 2;
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
	
	// randomize each tile, initialize deck
	private void shuffle() {
		// for habitat    0 = none, 1 = castle, 2 = village, 3 = town
		// for enviroment 0 = lake, 1 = plain, 2 = woods, 3 = mountains
		int none = 0, castle = 1, village = 2, town = 3;
		int lake = 0, plain = 1, woods = 2, mountains = 3;
		Tile[] set = new Tile[5];
		Tile[] setA = new Tile[4];
		Tile[] setB = new Tile[5];
		Tile[] setC = new Tile[5];
		Tile[] setD = new Tile[5];
		Tile[] setE = new Tile[5];
		Random gen = new Random(); 
		
		// setA - Plain, Plain Village, Mountains, Plain Castle
		set[0] = new Tile(plain, none, 'A');
		set[1] = new Tile(plain, village, 'A');
		set[2] = new Tile(mountains, none, 'A');
		set[3] = new Tile(plain, castle, 'A');
		
		for(int i = 0; i < 4;){
			int p = gen.nextInt(4);
			
			if(setA[p] == null){
				setA[p] = set[i];
				i++;
			}	
		}
		
		// setB - Water, Plain Castle, Plain Village, Woods Village, Plain Town
		set[0] = new Tile(lake, none, 'B');
		set[1] = new Tile(plain, castle, 'B');
		set[2] = new Tile(plain, village, 'B');
		set[3] = new Tile(woods, village, 'B');
		set[4] = new Tile(plain, town, 'B');
		
		for(int i = 0; i < 5;){
			int p = gen.nextInt(5);
			
			if(setB[p] == null){
				setB[p] = set[i];
				i++;
			}	
		}
		
		// setC - Woods Town, Plain, Plain Castle, Plain Village, Woods Castle
		set[0] = new Tile(woods, town, 'C');
		set[1] = new Tile(plain, none, 'C');
		set[2] = new Tile(plain, castle, 'C');
		set[3] = new Tile(plain, village, 'C');
		set[4] = new Tile(woods, castle,'C');
		
		for(int i = 0; i < 5;){
			int p = gen.nextInt(5);
			
			if(setC[p] == null){
				setC[p] = set[i];
				i++;
			}	
		}
		
		// setD - Plain Castle, Mountains, Woods Village, Plain Castle, Plain Village
		set[0] = new Tile(plain, castle, 'D');
		set[1] = new Tile(mountains, none, 'D');
		set[2] = new Tile(woods, village, 'D');
		set[3] = new Tile(plain, castle, 'D');
		set[4] = new Tile(plain, village, 'D');
		
		for(int i = 0; i < 5;){
			int p = gen.nextInt(5);
			
			if(setD[p] == null){
				setD[p] = set[i];
				i++;
			}	
		}
		
		// setE - Plain Castle, Woods Village, Plain Village, Woods Castle, Plain
		set[0] = new Tile(plain, castle, 'E');
		set[1] = new Tile(woods, village, 'E');
		set[2] = new Tile(plain, village, 'E');
		set[3] = new Tile(woods, castle, 'E');
		set[4] = new Tile(plain, none, 'E');
		
		for(int i = 0; i < 5;){
			int p = gen.nextInt(5);
			
			if(setE[p] == null){
				setE[p] = set[i];
				i++;
			}	
		}
	}
	
	// Draw a tile from the top of the deck and add it to the hand
	public int draw() {
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
}