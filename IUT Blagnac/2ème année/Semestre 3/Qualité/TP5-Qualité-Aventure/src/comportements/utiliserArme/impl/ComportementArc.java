package comportements.utiliserArme.impl;

import comportements.utiliserArme.ComportementArme;

public class ComportementArc implements ComportementArme{
	protected ComportementArc() {}
	
	@Override
	public void utiliserArme() {
		System.out.println("Utilisation Arc");
	}

}
