package comportements.utiliserArme.impl;

import comportements.utiliserArme.ComportementArme;

public class SimpleFabriqueArme {
	public ComportementArme creerComportementArme(Armes a){
		switch(a) {
			case Poignard :
				return new ComportementPoignard();
			case Arc :
				return new ComportementArc();
			case Epee :
				return new ComportementEpee();
			default:
				return null;
		}
	}
}
