public class BouilleurChocolatSafe {
	private boolean vide;
	private boolean bouilli;
	final static private BouilleurChocolatSafe uniqueInstance = new BouilleurChocolatSafe();
	
	BouilleurChocolatSafe() {
		this.vide = false;
		this.bouilli = false;
	}
	
	public static BouilleurChocolatSafe getInstance() {
		return uniqueInstance;
	}
	public void remplir() {
		if(estVide()) {
			this.vide = false;
			this.bouilli = false;
		}
	}
	public void vider() {
		if(!estVide() && estBouilli()) {
			this.vide = true;
			this.bouilli = false;
		}
	}
	public void bouillir() {
		if(!estVide() && !estBouilli()) {
			this.bouilli = true;
		}
	}
	public boolean estVide() { return this.vide;}
	public boolean estBouilli() { return this.bouilli;}
}
