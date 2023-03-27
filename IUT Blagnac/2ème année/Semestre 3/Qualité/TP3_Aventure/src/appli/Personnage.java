package appli;

import comportements.ComportementArme;

public abstract class Personnage {

	protected ComportementArme arme;

	public Personnage(ComportementArme a) {
		this.arme =a ;
	}
	
	public void combattre() {
		this.arme.utiliserArme();
	}
	
	public void setArme (ComportementArme a ) {
		this.arme = a;
	}
	
}
