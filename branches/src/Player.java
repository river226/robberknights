
public class Player {

	String name;
	String color;
	Hand hand;
	
	public Player(String name, String color){
		this.name = name;
		this.color = color;
	}
	
	
	public String toString(){
		return this.name + " " + this.color + " " + this.hand.displayHand();
	}
}
