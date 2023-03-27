package personnage;

import comportements.deplacement.ComportementDeplacement;
import comportements.utiliserArme.ComportementArme;

public abstract class Personnage {

	protected ComportementArme arme;
	protected ComportementDeplacement deplacement;
	
	public Personnage(ComportementArme a, ComportementDeplacement d) {
		this.setArme(a);
		this.setDeplacement(d);
	}
	
	public void combattre() {
		this.arme.utiliserArme();
	}

	public void setArme(ComportementArme a) {
		this.arme = a;
	}
	
	public void setDeplacement(ComportementDeplacement d) {
		this.deplacement = d;
	}
	
}	