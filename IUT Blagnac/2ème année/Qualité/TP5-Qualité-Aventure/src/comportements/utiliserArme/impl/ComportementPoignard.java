package comportements.utiliserArme.impl;

import comportements.utiliserArme.ComportementArme;

public class ComportementPoignard implements ComportementArme{
	protected ComportementPoignard() {}
	
	@Override
	public void utiliserArme() {
		System.out.println("Utilisation Poignard");
	}
	
}
