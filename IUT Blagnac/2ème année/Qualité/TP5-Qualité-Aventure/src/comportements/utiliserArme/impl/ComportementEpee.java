package comportements.utiliserArme.impl;

import comportements.utiliserArme.ComportementArme;

public class ComportementEpee implements ComportementArme{
	protected ComportementEpee() {}
	
	
	@Override
	public void utiliserArme() {
		System.out.println("Utilisation Epee");
	}
}
