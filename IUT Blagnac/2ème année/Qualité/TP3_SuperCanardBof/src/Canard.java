
/**
 * @author bruel
 *
 */
abstract public class Canard {
	
	protected ComportementVol compVol;
	protected ComportementCanCan compCanCan;
	
	public void cancaner() {
		this.compCanCan.cancaner();
	}

	public void nager() {
		System.out.println("Je nage comme un Canard!");
	}

	public void voler() {
		this.compVol.voler();
	}

	abstract public void afficher();
	
}