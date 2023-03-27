package personnage;

import comportements.deplacement.ComportementDeplacement;
import comportements.utiliserArme.ComportementArme;

public class Roi extends Personnage{

	public Roi(ComportementArme a, ComportementDeplacement d) {
		super(a, d);
	}

}
