package application.action;

import java.util.Locale;
import java.util.Scanner;

import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.CompteException;

public class ActionDeposer implements Action {

	private String message;
	private String code;
	
	public ActionDeposer(String _msg, String _code) {
		this.message = _msg;
		this.code = _code;
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
	public void execute(AgenceBancaire ag) throws Exception {
		// TODO Auto-generated method stub
		Scanner lect;
		lect = new Scanner ( System.in );
		lect.useLocale(Locale.US);
		System.out.print("Num compte -> ");
		String numero = lect.next();
		System.out.print("Montant à déposer -> ");
		double montant = lect.nextDouble();
		deposerSurUnCompte(ag, numero, montant);
	}
	
	public static void deposerSurUnCompte (AgenceBancaire ag, String numeroCompte, double montant) {
		Compte c;
		c = ag.getCompte(numeroCompte);
		if (c==null) {
			System.out.println("Compte inexistant ...");
		} else {
			System.out.println("Solde avant dépôt: "+c.soldeCompte());
			try {
				c.deposer(montant);
				System.out.println("Montant déposé, solde : "+c.soldeCompte());
			} catch (CompteException e) {
				System.out.println("Erreur de dépot, solde inchangé : " + c.soldeCompte());
				System.out.println(e.getMessage());
			}
		}
	}

}
