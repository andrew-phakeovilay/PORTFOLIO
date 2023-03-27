

import java.util.Locale;
import java.util.Scanner;

import application.AccesAgenceBancaire;
import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.ABCompteDejaExistantException;
import banque.exception.ABCompteInexistantException;
import banque.exception.ABCompteNullException;
import banque.exception.CompteException;

public class ApplicationAgenceBancaire {

	/**
	 * Affichage du menu de l'application
	 * @param ag	AgenceBancaire pour r�cup�rer le nom et la localisation
	 */
	public static void afficherMenuGeneral(AgenceBancaire ag) {
		System.out.println("-- \n Agence " + ag.getNomAgence() + " de " + ag.getLocAgence() + "\n Menu Général\n--");
		System.out.println(" Choisir :");
		System.out.println("  1 - Liste des comptes de l'agence");
		System.out.println("  2 - Voir un compte (par son numéro)");
		System.out.println("  3 - Menu opérations sur comptes");
		System.out.println("  4 - Menu gestion des comptes\n");
		System.out.println("  0 - Pour quitter ce menu");
		System.out.print("Votre choix ?\n");
	}

	public static void afficherMenuOperations(AgenceBancaire ag) {
		System.out.println("-- \n Agence " + ag.getNomAgence() + " de " + ag.getLocAgence() + "\n Menu Général\n--");
		System.out.println(" Choisir :");
		System.out.println("  1 - Déposer de l'argent sur un compte");
		System.out.println("  2 - Retirer de l'argent sur un compte\n");
		System.out.println("  0 - Pour quitter ce menu");
		System.out.print("Votre choix ?\n");
	}

	public static void afficherMenuComptes(AgenceBancaire ag) {
		System.out.println("-- \n Agence " + ag.getNomAgence() + " de " + ag.getLocAgence() + "\n Menu Général\n--");
		System.out.println(" Choisir :");
		System.out.println("  1 - Ajouter un compte");
		System.out.println("  2 - Supprimer un compte\n");
		System.out.println("  0 - Pour quitter ce menu");
		System.out.print("Votre choix ?\n");
	}
	/**
	 * Temporisation : Affiche un message et attend la frappe de n'importe quel caract�re.
	 */
	public static void tempo () {
		Scanner lect ;

		lect = new Scanner (System.in );

		System.out.print("Tapper un car + return pour continuer ... \n");
		lect.next(); // Inutile � stocker mais ... 
	}

	public static void main(String argv[]) throws ABCompteNullException, ABCompteDejaExistantException, ABCompteInexistantException {

		String choix;

		boolean continuer ;
		Scanner lect;
		AgenceBancaire monAg ;

		String nom, numero;		
		Compte c;
		double montant;

		lect = new Scanner ( System.in );
		lect.useLocale(Locale.US);

		monAg = AccesAgenceBancaire.getAgenceBancaire();

		continuer = true;
		while (continuer) {
			ApplicationAgenceBancaire.afficherMenuGeneral(monAg);
			choix = lect.next();
			choix = choix.toLowerCase();
			switch (choix) {
			case "0" :
				ApplicationAgenceBancaire.tempo();
				continuer = false;
				break;
			case "1" :
				monAg.afficher();
				ApplicationAgenceBancaire.tempo();
				break;	
			case "2" :
				System.out.print("Num compte -> ");
				numero = lect.next();
				c = monAg.getCompte(numero);
				if (c==null) {
					System.out.println("Compte inexistant ...");
				} else {
					c.afficher();
				}
				ApplicationAgenceBancaire.tempo();
				break;
			case "3":
				boolean continuerOp = true;
				while(continuerOp) {
				ApplicationAgenceBancaire.afficherMenuOperations(monAg);
				String choixOp = lect.next();		
					switch(choixOp) {
					case "1":
						System.out.print("Num compte -> ");
						numero = lect.next();
						System.out.print("Montant � d�poser -> ");
						montant = lect.nextDouble();
						ApplicationAgenceBancaire.deposerSurUnCompte(monAg, numero, montant);
						ApplicationAgenceBancaire.tempo();
						break;
					case "2":
						System.out.print("Num compte -> ");
						numero = lect.next();
						System.out.print("Montant � retirer -> ");
						montant = lect.nextDouble();
						ApplicationAgenceBancaire.retirerSurUnCompte(monAg, numero, montant);
						ApplicationAgenceBancaire.tempo();
						break;
					case "0" :
						continuerOp = false;
						System.out.println("Fin de Menu opérations sur comptes");
						ApplicationAgenceBancaire.tempo();
						break;
					default :
						System.out.println("Erreur de saisie ...");
						ApplicationAgenceBancaire.tempo();
						break;
					}
				}
				break;
			case "4" :
				boolean continuerComptes = true;
				while(continuerComptes) {
				ApplicationAgenceBancaire.afficherMenuComptes(monAg);
				String choixCompte = lect.next();		
					switch(choixCompte) {
					case "1":
						System.out.print("Num compte -> ");
						numero = lect.next();
						System.out.print("Nom propriétaire -> ");
						nom = lect.next();
						monAg.addCompte(new Compte(numero, nom));
						ApplicationAgenceBancaire.tempo();
						break;
					case "2":
						System.out.print("Num compte -> ");
						numero = lect.next();
						monAg.removeCompte(numero);
						ApplicationAgenceBancaire.tempo();
						break;
					case "0" :
						continuerComptes = false;
						ApplicationAgenceBancaire.tempo();
						break;
					default :
						System.out.println("Erreur de saisie ...");
						ApplicationAgenceBancaire.tempo();
						break;
					}
				}
				break;
			default :
				System.out.println("Erreur de saisie ...");
				ApplicationAgenceBancaire.tempo();
				break;
			}
		}

	}

	public static void comptesDUnPropretaire (AgenceBancaire ag, String nomProprietaire) {
		Compte []  t; 

		t = ag.getComptesDe(nomProprietaire);
		if (t.length == 0) {
			System.out.println("pas de compte � ce nom ...");
		} else {
			System.out.println("  " + t.length + " comptes pour " + nomProprietaire);
			for (int i=0; i<t.length; i++)
				t[i].afficher();
		}
	}

	public static void deposerSurUnCompte (AgenceBancaire ag, String numeroCompte, double montant) {
		Compte c;

		c = ag.getCompte(numeroCompte);
		if (c==null) {
			System.out.println("Compte inexistant ...");
		} else {
			System.out.println("Solde avant d�p�t: "+c.soldeCompte());
			try {
				c.deposer(montant);
				System.out.println("Montant d�pos�, solde : "+c.soldeCompte());
			} catch (CompteException e) {
				System.out.println("Erreur de d�pot, solde inchang� : " + c.soldeCompte());
				System.out.println(e.getMessage());
			}
		}
	}

	public static void retirerSurUnCompte (AgenceBancaire ag, String numeroCompte, double montant) {
		Compte c;

		c = ag.getCompte(numeroCompte);
		if (c==null) {
			System.out.println("Compte inexistant ...");
		} else {
			System.out.println("Solde avant retrait : " + c.soldeCompte());
			try {
				c.retirer(montant);
				System.out.println("Montant retir�, solde : "+c.soldeCompte());
			} catch (CompteException e) {
				System.out.println("Erreur de d�pot, solde inchang� : " + c.soldeCompte());
				System.out.println(e.getMessage());
			}
		}

	}
}
