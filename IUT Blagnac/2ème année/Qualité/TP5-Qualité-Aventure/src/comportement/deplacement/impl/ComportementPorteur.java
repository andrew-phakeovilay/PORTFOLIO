package comportement.deplacement.impl;

import comportements.deplacement.ComportementDeplacement;

public class ComportementPorteur implements ComportementDeplacement{
	public ComportementPorteur() {}
	
	@Override
	public void deplacement() {
		System.out.println("Déplacement avec porteur");
	}
	
}
