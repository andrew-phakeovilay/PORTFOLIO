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
	public void execute(AgenceBancaire ag) throws Exception {
		// TODO Auto-generated method stub
		ag.afficher();
	}

}
