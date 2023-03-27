
/**
 * @author bruel
 *
 */

public class CanardEnPlastique extends Canard {
	
	public CanardEnPlastique() {
		compVol = new NePasVoler();
		compCanCan = new Cancan();
	}

	@Override
	public void afficher() {
		System.out.println("Je suis un CanardEnPlastique!");
	}

	@Override
	public void voler() {
		System.out.println("Je ne vole pas!");
	}
}
