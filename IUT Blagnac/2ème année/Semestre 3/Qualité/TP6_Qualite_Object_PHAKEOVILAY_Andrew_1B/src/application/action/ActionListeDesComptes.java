package application.action;

import banque.AgenceBancaire;

public class ActionListeDesComptes implements Action {

	private String message;
	private String code;
	
	public ActionListeDesComptes(String _msg, String _code) {
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
		AgenceBancaire ag = (AgenceBancaire) e;
		ag.afficher();
	}

}
