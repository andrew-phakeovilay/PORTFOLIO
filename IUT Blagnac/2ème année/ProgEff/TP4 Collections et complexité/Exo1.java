import stone.Stone;

public class Exo1 {
	
	public static void main(String args[]) {
		Stone pierre = Stone.makeSmallStone();
		System.out.println(pierre);
		
		Stone pierreEnDeux = pierre.split();
		System.out.println("1er morceau " + pierre);
		System.out.println("2ème morceau " + pierreEnDeux);
	}
}
