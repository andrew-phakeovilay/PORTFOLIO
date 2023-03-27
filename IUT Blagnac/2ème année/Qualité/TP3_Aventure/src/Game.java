import java.util.InputMismatchException;
import java.util.Scanner;
import appli.Roi;
import appli.Troll;
import comportements.impl.ComportementArc;
import comportements.impl.ComportementEpee;
import comportements.impl.ComportementPoignard;
import appli.Chevalier;
import appli.Troll;
import appli.Personnage;
import appli.Reine;

public class Game {

	public static void main(String[] args) {
		boolean enCours = true;
		Scanner scan = new Scanner(System.in);
		int choixPerso;
		int choixArme;
		Personnage perso = new Roi(new ComportementEpee()); //Initialisation par défaut

		do {
			System.out.println("MENU\nChoisissez un numéro de personnage :\n1 : Roi\n2 : Reine\n3 : Troll\n4 : Chevalier\n");
			try {
				choixPerso = scan.nextInt();
			}catch(InputMismatchException exception) {
				System.out.println("\nNuméro non valide ! \n\n\n\n");
				continue;
			}

			System.out.println("MENU\nChoisissez une arme :\n1 : Epee\n2 : Poignard\n3 : Arc\n");
			try {
				choixArme = scan.nextInt();
			}catch(InputMismatchException exception) {
				System.out.println("\nNuméro non valide !\n\n\n\n");
				continue;
			}

			switch(choixPerso) {
			case 1:
				perso = new Roi(new ComportementEpee());
				break;
			case 2:
				perso = new Reine(new ComportementEpee());
				break;
			case 3:
				perso = new Troll(new ComportementEpee());
				break;
			case 4:
				perso = new Chevalier(new ComportementEpee());
				break;
			}

			switch(choixArme) {
			case 2:
				perso.setArme(new ComportementPoignard());
				break;
			case 3:
				perso.setArme(new ComportementArc());
				break;
			default:
				break;
			}

			System.out.println("Voulez-vous créer un autre personnage ? o/n\n");
			String continuer = scan.next();
			enCours = continuer.toUpperCase().equals("O") ? true : false;
			if(!enCours)
				System.out.println("Au revoir ! ");
		}
		while(enCours);
	}

}