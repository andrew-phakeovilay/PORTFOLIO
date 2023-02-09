package application.view;

import javafx.application.Platform;

// Code d'un thread qui met à jour le fichier de configuration

public class RunBackground implements Runnable {

	// Contrôle de l'exécution du thread : isRun == true => s'exécute
	private boolean isRun;

	// Controller pour la mise à jour du fichier de configuration
	private FileEditorPaneController fepc;
	
	// Constructeur 
	// _fepc : le controller contenant la configuration du fichier
	public RunBackground(FileEditorPaneController _fepc) {
		this.fepc = _fepc;
		this.isRun = true;
	}

	// Corps du thread
	@Override
	public void run() {
		// Tant que le thread courant s'exécute
		while (this.isRun) {
			// Paramètres de la mise à jour d'un quartier au hasard du PieChart
			
			// Mise en file d'attente (dans un Runnable) de la mise à jour du PieChart via mf.miseAJourPieChart()
			// Ce Runnable sera exécuté par le thread GUI "dès que possible"
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					RunBackground.this.fepc.validateComponentState();
				}
			});

			// Documentation de Platform.runLater ()
			// If you need to update a GUI component from a non-GUI thread, you can use that to put your update in a queue and it will be handled by the GUI thread as soon as possible.

			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
			}
		}
	}
	
	// Méthode pour arrêter le thread courant (c'est à dire que la méthode run() se termine)
	public void stopIt() {
		this.isRun = false;
	}

}
