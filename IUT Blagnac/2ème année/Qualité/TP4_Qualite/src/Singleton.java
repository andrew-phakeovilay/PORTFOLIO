import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Singleton {
	public static void main(String args[]) {
		Runtime objet = Runtime.getRuntime();
		System.out.println("Cores : " + objet.availableProcessors());
		System.out.println("Max memory : " + objet.maxMemory());
		System.out.println("Total memory : " + objet.totalMemory());
		System.out.println("Free memory : " + objet.freeMemory());
		System.out.println("JRE version : " + objet.version());
		
		//  gc() recycle les objets non utilisés.
		objet.exit(-20);
		System.out.println("test");
		// L'affichate de test ne fonctionne pas.
		
		Desktop page = Desktop.getDesktop();
		URI lien = new URI("https://www.iut-blagnac.fr/fr/");

	}
}
