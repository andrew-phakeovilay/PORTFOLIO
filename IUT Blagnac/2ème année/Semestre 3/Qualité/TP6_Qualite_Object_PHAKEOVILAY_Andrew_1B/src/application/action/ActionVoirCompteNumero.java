package application.action;

import java.util.Locale;
import java.util.Scanner;

import banque.AgenceBancaire;
import banque.Compte;

public class ActionVoirCompteNumero implements Action {

	private String message;
	private String code;
	
	public ActionVoirCompteNumero(String _msg, String _code){
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
	public void execute(Object e) throws Exception {
		// TODO Auto-generated method stub
		Scanner lect;
		AgenceBancaire ag = (AgenceBancaire) e;
		lect = new Scanner ( System.in );
		lect.useLocale(Locale.US);
		
		String numero;
		Compte c;
		
		System.out.print("Num compte -> ");
		numero = lect.next();
		c = ag.getCompte(numero);
		if (c==null) {
			System.out.println("Compte inexistant ...");
		} else {
			c.afficher();
		}
	}

}
