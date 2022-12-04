package blackjack;

import java.io.IOException;

import blackjack.om.BlackBot;
import blackjack.view.AccueilController;
import blackjack.view.InterfaceController;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BlackJackApp extends Application{
	
	private Stage primaryStage;
	private BorderPane borderPane;
	private BlackBot blackBot;
	private final IntegerProperty monnaie = new SimpleIntegerProperty(0);
	private final IntegerProperty nb = new SimpleIntegerProperty(2);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.borderPane     = new BorderPane();
		this.primaryStage.setResizable(false);
		
		this.primaryStage.getIcons().add(new Image(BlackJackApp.class.getResource("resource/pique.png").toExternalForm()));
		Scene scene = new Scene(this.borderPane, 1200, 700);
		
		scene.getStylesheets().add(BlackJackApp.class.getResource("resource/style.css").toExternalForm());
		primaryStage.setTitle("BlackJack App");
		primaryStage.setScene(scene);

		loadAccueil();
		this.primaryStage.show();
	}
	
	public void loadInterface() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BlackJackApp.class.getResource("view/Interface.fxml"));
			
			BorderPane vueInterface = loader.load();
			
			InterfaceController ctrl = loader.getController();
			ctrl.setBlackJack(this);
			ctrl.setInterfaceStage(this.primaryStage);
			// Initialise le blackBot avec le nombre de joueurs dans le TextField nbJoueurs de loadAccueil()
			blackBot = new BlackBot(this.nb.get());
			// Donne la monnaie aux joueurs des TextField de loadAccueil()
			ctrl.setMonnaie(this.monnaie.get(), this.nb.get());
			ctrl.setBlackBot(blackBot);
			// Initialise le blackjack avec le joueur 1
			ctrl.joueurInit(0);
			
			this.borderPane.setCenter( vueInterface );
			
						
		} catch (IOException e) {
			System.out.println("Ressource FXML non disponible : Interface");
			System.exit(1);
		}	
	}
	
	public void loadAccueil() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BlackJackApp.class.getResource("view/Accueil.fxml"));
			
			BorderPane vueAccueil = loader.load();
		
			AccueilController ctrl = loader.getController();
			ctrl.setBlackJack(this);
			ctrl.setAccueilStage(this.primaryStage);
			ctrl.setPropVal(this.monnaie);
			ctrl.setPropValNb(this.nb);
			this.borderPane.setCenter(vueAccueil);
						
		} catch (IOException e) {
			System.out.println("Ressource FXML non disponible : Accueil");
			System.exit(1);
		}	
	}
	
}
