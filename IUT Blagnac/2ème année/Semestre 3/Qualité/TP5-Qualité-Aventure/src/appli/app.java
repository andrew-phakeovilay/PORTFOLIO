package appli;

import java.util.InputMismatchException;
import java.util.Scanner;

import comportement.deplacement.impl.Deplacement;
import comportement.deplacement.impl.SimpleFabriqueDeplacement;
import comportements.utiliserArme.impl.Armes;
import comportements.utiliserArme.impl.ComportementArc;
import comportements.utiliserArme.impl.ComportementEpee;
import comportements.utiliserArme.impl.ComportementPoignard;
import comportements.utiliserArme.impl.SimpleFabriqueArme;
import personnage.Chevalier;
import personnage.Personnage;
import personnage.Reine;
import personnage.Roi;
import personnage.Troll;

public class app {

	public static void main(String[] args) {
		boolean enCours = true;
		Scanner scan = new Scanner(System.in);
		int choixPerso;
		int choixArme;
		int choixDeplacement;
		Personnage perso = null;
		
		do {	
			System.out.println("MENU\nChoisissez un numro de personnage :\n1 : Roi\n2 : Reine\n3 : Troll\n4 : Chevalier\n");
			try {
				choixPerso = scan.nextInt();
			}catch(InputMismatchException exception) {
				System.out.println("\nNum�ro non valide\n\n\n\n");
				continue;
			}
			
			System.out.println("MENU\nChoisissez une arme :\n1 : Epee\n2 : Poignard\n3 : Arc\n");
			try {
				choixArme = scan.nextInt();
			}catch(InputMismatchException exception) {
				System.out.println("\nNum�ro non valide\n\n\n\n");
				continue;
			}
			
			System.out.println("MENU\nChoisissez un mode de d�placement :\n1 : A pied\n2 : A cheval\n3 : En cal�che\n4 : Avec un porteur");
			try {
				choixDeplacement = scan.nextInt();
			}catch(InputMismatchException exception) {
				System.out.println("\nNum�ro non valide\n\n\n\n");
				continue;
			}
			
			//EXO 5
			//QUESTION 6
			//
			//Cela n'aurait pas d'utilit� d'utiser un Singleton avec une fabrique. Cela voudrait dire qu'il existe une seule arme,
			//chaque personnage aurait donc la m�me arme. Une fabrique permet de cr�er une nouvelle arme unique � chaque cr�ation
			//de personnage.
			
			SimpleFabriqueArme sfa = new SimpleFabriqueArme();
			SimpleFabriqueDeplacement sfd = new SimpleFabriqueDeplacement();
			switch(choixPerso) {
				case 1:
					perso = new Roi(null, null);
					break;
				case 2:
					perso = new Reine(null, null);
					break;
				case 3:
					perso = new Troll(null, null);
					break;
				case 4:
					perso = new Chevalier(null, null);
					break;
			}
			
			switch(choixArme) {
				case 1:
					perso.setArme(sfa.creerComportementArme(Armes.Epee));
					break;
				case 2:
					perso.setArme(sfa.creerComportementArme(Armes.Poignard));
					break;
				case 3:
					perso.setArme(sfa.creerComportementArme(Armes.Arc));
					break;
			}
			
			switch(choixDeplacement) {
				case 1:
					perso.setDeplacement(sfd.creerComportementDeplacement(Deplacement.PIED));
					break;
				case 2:
					perso.setDeplacement(sfd.creerComportementDeplacement(Deplacement.CHEVAL));
					break;
				case 3:
					perso.setDeplacement(sfd.creerComportementDeplacement(Deplacement.CALECHE));
					break;
				case 4:
					perso.setDeplacement(sfd.creerComportementDeplacement(Deplacement.PORTEUR));
					break;
			}
			
			System.out.println("Voulez-vous cr�er un autre personnage ? o/n\n");
			String continuer = scan.next();
			enCours = continuer.toUpperCase().equals("O") ? true : false;
			if(!enCours)
				System.out.println("Au revoir");
		}while(enCours);
	}

}
