import application.AccesAgenceBancaire;
import application.action.Action;
import application.action.ActionCreerCompte;
import application.action.ActionDeposer;
import application.action.ActionListeDesComptes;
import application.action.ActionRetirer;
import application.action.ActionSuppCompte;
import application.action.ActionVoirCompteNumero;
import application.actionlist.ActionList;
import application.actionlist.ActionListAgenceBancaire;
import application.actionlist.ActionListGestionComptes;
import application.actionlist.ActionListOperations;
import banque.AgenceBancaire;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AgenceBancaire ag = AccesAgenceBancaire.getAgenceBancaire();
		
		Action a1 = new ActionListeDesComptes("Liste des comptes de l'agence", "L1");
		Action a2 = new ActionVoirCompteNumero("Voir un compte (par son numéro)", "V1");
		
		Action deposer = new ActionDeposer("Déposer de l'argent sur un compte", "D1");
		Action retirer = new ActionRetirer("Retirer de l'argent sur un compte", "R1");
		
		Action creer = new ActionCreerCompte("Créer un compte", "C1");
		Action supp = new ActionSuppCompte("Supprimer un compte", "S1");
		
		ActionList al1 = new ActionListAgenceBancaire("Menu Général", "1", "Menu Général");
		ActionList al2 = new ActionListOperations("Menu opérations sur comptes", "2", "Menu opérations sur comptes");
		ActionList al3 = new ActionListGestionComptes("Menu gestions des comptes", "3", "Menu gestions des comptes");
		
		al1.addAction(a1);
		al1.addAction(a2);
		al1.addAction(al2);
		al1.addAction(al3);
		
		al2.addAction(deposer);
		al2.addAction(retirer);
		
		al3.addAction(creer);
		al3.addAction(supp);
		
		al1.execute(ag);
	}

	/*
	 * Il est possible d'effectuer les actions pour Eleve et GroupeEleve, la forme est similaire à la banque
	 * 
	 * 
	 */
}
