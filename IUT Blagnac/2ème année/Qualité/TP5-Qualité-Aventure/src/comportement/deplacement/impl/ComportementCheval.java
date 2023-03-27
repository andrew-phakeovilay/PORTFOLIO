package comportement.deplacement.impl;

import comportements.deplacement.ComportementDeplacement;

public class ComportementCheval implements ComportementDeplacement{
	public ComportementCheval() {}

	@Override
	public void deplacement() {
		System.out.println("Déplacement à cheval");
	}
}
