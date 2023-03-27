package personnage;

import comportements.deplacement.ComportementDeplacement;
import comportements.utiliserArme.ComportementArme;

public class Troll extends Personnage{

	public Troll(ComportementArme a, ComportementDeplacement d) {
		super(a, d);
	}
}
