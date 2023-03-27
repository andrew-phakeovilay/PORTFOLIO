import stone.Stone;

public class Exo2 {
	public static void main(String args[]) {
		Stone pierreGrande = Stone.makeBigStone();
		int cpt = 0;
		while(pierreGrande.diameter() > 5) {
			pierreGrande.split();
			cpt++;
		}
		System.out.println("Nombre de splits : " + cpt);
		System.out.println(pierreGrande);
	}
}
