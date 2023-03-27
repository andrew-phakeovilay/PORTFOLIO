package comportement.deplacement.impl;

import comportements.deplacement.ComportementDeplacement;

public class ComportementPied implements ComportementDeplacement{
	public ComportementPied() {}

	@Override
	public void deplacement() {
		System.out.println("Déplacement à pied");
	}
}
