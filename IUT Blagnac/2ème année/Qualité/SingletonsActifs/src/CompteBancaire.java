// Classe des comptes bancaires simpliste.
public class CompteBancaire {
	private String numero; 	// Numéro du compte.
	private double solde; 	// Argent disponible sur le compte.

	public CompteBancaire(String _numero) {
		this.numero = _numero;
		this.solde = 0.0;
	}

	// Méthode pour d�poser de l'argent sur le compte.
	public void deposerArgent(double depot) {
		if (depot > 0.0) {
			this.solde += depot;
			Journalisation.getInstance(TypesOperation.operation).ajouterLog("Dépôt de " + depot + " sur le compte " + this.numero + ".");
		} else {
			Journalisation.getInstance(TypesOperation.erreur).ajouterLog("/!\\ Dépôt d'une valeur négative impossible (" + this.numero + ").");
		}
	}

	// Méthode qui permet de retirer de l'argent sur le compte.
	public void retirerArgent(double retrait) {
		if (retrait > 0.0) {
			if (this.solde >= retrait) {
				// On retranche la somme retirée au solde.
				this.solde -= retrait;
				Journalisation.getInstance(TypesOperation.operation).ajouterLog("Retrait de " + retrait + " sur le compte " + this.numero);
			} else {
				Journalisation.getInstance(TypesOperation.erreur).ajouterLog("/!\\ La banque n'autorise pas de découvert (" + this.numero + ").");
			}
		} else {
			Journalisation.getInstance(TypesOperation.erreur).ajouterLog("/!\\ Retrait d'une valeur négative impossible (" + this.numero + ").");
		}
	}
}