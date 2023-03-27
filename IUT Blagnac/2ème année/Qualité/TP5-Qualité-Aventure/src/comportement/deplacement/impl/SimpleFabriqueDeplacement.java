package comportement.deplacement.impl;

import comportements.deplacement.ComportementDeplacement;

public class SimpleFabriqueDeplacement {
	public ComportementDeplacement creerComportementDeplacement(Deplacement d){
		switch(d) {
			case PIED :
				return new ComportementPied();
			case CHEVAL :
				return new ComportementCheval();
			case PORTEUR :
				return new ComportementPorteur();
			case CALECHE :
				return new ComportementCaleche();
			default:
				return null;
		}
	}
}
