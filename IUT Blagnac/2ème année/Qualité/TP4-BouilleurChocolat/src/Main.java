
public class Main {
	public static void main(String[] args) {

		BouilleurChocolatSafe bc = BouilleurChocolatSafe.getInstance();
		System.out.println(bc.estVide());
		bc.remplir();
		System.out.println(bc.estVide());
		System.out.println(bc.estBouilli());
		bc.bouillir();
		System.out.println(bc.estBouilli());
		bc.vider();
		System.out.println(bc.estVide());

		System.out.println("\n\n");

		BouilleurChocolatSafe bc1 = BouilleurChocolatSafe.getInstance();
		BouilleurChocolatSafe bc2 = BouilleurChocolatSafe.getInstance();		
		System.out.println("Vide : " + bc1.estVide());
		bc2.remplir();
		System.out.println("Vide : " + bc1.estVide());
		System.out.println("Bouilli : " + bc2.estBouilli());
		bc1.bouillir();
		System.out.println("Bouilli : " + bc2.estBouilli());

		System.out.println("\n\n");

		// Les objets ont la même référence et hashcode
		System.out.println(bc1 == bc2);
		System.out.println(bc1.hashCode());
		System.out.println(bc2.hashCode());

	}
}
