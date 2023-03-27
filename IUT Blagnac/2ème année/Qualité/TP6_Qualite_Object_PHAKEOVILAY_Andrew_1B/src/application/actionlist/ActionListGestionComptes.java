package application.actionlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import application.action.Action;
import banque.AgenceBancaire;

public class ActionListGestionComptes implements ActionList {

	private String message, code, title;
	private List<Action>listeActions = new ArrayList<Action>();

	public ActionListGestionComptes(String _msg, String _code, String _title) {
		this.message = _msg;
		this.code = _code;
		this.title = _title;
	}

	@Override
	public String actionMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public String actionCode() {
		// TODO Auto-generated method stub
		return this.code;
	}


	@Override
	public void execute(Object e) throws Exception {
		// TODO Auto-generated method stub
		boolean continuer = true;
		AgenceBancaire ag = (AgenceBancaire) e;
		while(continuer) {
			System.out.println("-- \n Agence " + ag.getNomAgence() + " de " + ag.getLocAgence() + "\n " + this.title + "\n--");
			System.out.println(" Choisir :");
			System.out.println("  1 - Ajouter un compte");
			System.out.println("  2 - Supprimer un compte\n");
			System.out.println("  0 - Pour quitter ce menu");
			System.out.print("Votre choix ?\n");
			
			String choix;
			Scanner lect = new Scanner ( System.in );
			lect.useLocale(Locale.US);
			choix = lect.next();
			choix = choix.toLowerCase();
			switch(choix) {
			case "1":
				this.listeActions.get(0).execute(ag);
				tempo();
				break;
			case "2":
				this.listeActions.get(1).execute(ag);
				tempo();
				break;
			case "0" :
				continuer = false;
				System.out.println("Fin de Menu gestions des comptes");
				break;
			default :
				System.out.println("Erreur de saisie ...");
				tempo();
				break;
			}
		}

	}

	@Override
	public String listTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.listeActions.size();
	}

	@Override
	public boolean addAction(Action ac) {
		// TODO Auto-generated method stub
		listeActions.add(ac);
		return false;
	}
	public static void tempo () {
		Scanner lect ;

		lect = new Scanner (System.in );

		System.out.print("Tapper un car + return pour continuer ... \n");
		lect.next(); // Inutile � stocker mais ... 
	}
}

