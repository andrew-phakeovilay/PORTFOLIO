package personnage;

import comportements.deplacement.ComportementDeplacement;
import comportements.utiliserArme.ComportementArme;

public class Chevalier extends Personnage{

	public Chevalier(ComportementArme a, ComportementDeplacement d) {
		super(a, d);
	}

}
