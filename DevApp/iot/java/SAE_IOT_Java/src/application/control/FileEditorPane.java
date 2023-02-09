package application.control;

import application.view.FileEditorPaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FileEditorPane extends Application {

	private Stage primaryStage;

	/**
	 * Démarre la fenêtre de l'édition du fichier de configuration
	 */
	@Override
	public void start(Stage primaryStage){

		this.primaryStage = primaryStage;

		try {
			primaryStage.setResizable(false);

			FXMLLoader loader= new FXMLLoader(FileEditorPaneController.class.getResource("fileeditorpane.fxml"));

			BorderPane root = loader.load();

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Configuration du fichier");

			FileEditorPaneController fedpc = loader.getController();
			fedpc.initContext(primaryStage, this);
			
			fedpc.displayDialog();

		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 * Démarre l'application
	 */
	public static void runApp() {
		Application.launch();
	}

	/**
	 * Fenêtre des graphiques
	 */
	public void afficheGraphique() 
	{
		DataViewerPane dvp = new DataViewerPane();
		try {
			dvp.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
