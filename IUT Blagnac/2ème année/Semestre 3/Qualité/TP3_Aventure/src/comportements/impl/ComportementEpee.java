package comportements.impl;

import comportements.ComportementArme;

public class ComportementEpee implements ComportementArme{

	@Override
	public void utiliserArme() {
		System.out.println("J'utilise une épée");	
	}

}
