package pizza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * = Asciidoclet
 *
 * Sample comments that include `source code` by mailto:jbruel@gmail.com[JMB].
 *
 * [source,java]
 * --
 * include::src/java/Pizzeria/src/Pizza.java[lines=14..34]
 * --
 *
 * @author bruel
 *
 */
public abstract class Pizza {
	protected String nom;
	protected String pate;
	protected String sauce;
	protected List<String> garnitures = new ArrayList<String>();

	protected Pizza (String _n, String _p, String _s) {
		this.nom = _n;
		this.pate = _p;
		this.sauce = _s;
		this.garnitures = new ArrayList<>();
	}

	protected Pizza (String _n, String _p, String _s, String ... _l) {
		this.nom = _n;
		this.pate = _p;
		this.sauce = _s;
		this.garnitures = new ArrayList<>();
		this.garnitures.addAll(Arrays.asList(_l));
	}

	public void preparer() {
		System.out.println("Préparation de " + this.nom);
		System.out.println("Étalage de la pâte...");
		System.out.println("Ajout de la sauce...");
		System.out.println("Ajout des garnitures: ");
			for (int i = 0; i < this.garnitures.size(); i++) {
		System.out.println(" " + this.garnitures.get(i)); }
	}
	public  void cuire() {
		System.out.println("Cuisson 25 minutes à 180°");
	}
	public  void couper() {
		System.out.println("Découpage en quatre parts triangulaires");
	}
	public  void emballer() {
		System.out.println("En on emballe!");

	}
	public String getNom() {
		return this.nom;
	}
}