
/**
 * @author bruel
 *
 */
public class Colvert extends Canard {

	public Colvert() {
		compVol = new VolerAvecDesAiles();
		compCanCan = new Cancan();
	}
	
	@Override
	public void afficher() {
		System.out.println("Je suis un Colvert");
	}

}