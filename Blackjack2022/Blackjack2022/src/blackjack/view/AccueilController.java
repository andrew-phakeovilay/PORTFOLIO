package blackjack.view;

import java.net.URL;
import java.util.ResourceBundle;

import blackjack.BlackJackApp;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AccueilController implements Initializable{

	@FXML
	private Button butOK;
	@FXML
	private Button butQuitter;
	@FXML
	private TextField nbJoueurs;
	@FXML
	private TextField monnaie;

	private BlackJackApp blackjack;
	
	private Stage  accueilStage;
	@FXML
	private Button plusNb;
	@FXML
	private Button plusMonnaie;
	@FXML
	private Button moinsNb;
	@FXML
	private Button moinsMonnaie;
	
	private IntegerProperty propMonnaie;
	
	private IntegerProperty propNbJoueurs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void setBlackJack(BlackJackApp app) {
		this.blackjack = app;
	}

	@FXML
	private void actionQuitter() {
		this.accueilStage.close();
	}
	/*
	 * Après avoir saisie la monnaie et le nombre joueurs, passe à la fenêtre de jeu du BlackJack
	 * Si entrées erronées, affiche une Pop-Up
	 */
	@FXML
	private void actionOK() {
		Alert erreur = new Alert(Alert.AlertType.INFORMATION);
		erreur.setTitle("Erreur");
		erreur.initOwner(this.accueilStage);;
		erreur.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/attention.png").toExternalForm())));
		erreur.setHeaderText("Information");

		if(isNumber(this.monnaie.getText()) && isNumber(this.nbJoueurs.getText())) {
			if(Integer.parseInt(this.nbJoueurs.getText())>= 1 && Integer.parseInt(this.monnaie.getText()) > 0){
				this.blackjack.loadInterface();
			}
			else {
				erreur.setContentText(erreurMessage());
				erreur.showAndWait();
			}
		}
		else {
			erreur.setContentText("Le nombre de joueurs ou le montant monnaie ne sont pas des nombres.");
			erreur.showAndWait();
		}

	}


	public void setAccueilStage(Stage dialogStage) {
		this.accueilStage = dialogStage;
	}

	/*
	 * Prend en compte les erreurs de la monnaie et/ou du nombre de joueurs
	 */
	
	private String erreurMessage(){
		String message = "";

		if(Integer.parseInt(this.nbJoueurs.getText())< 1 && Integer.parseInt(this.monnaie.getText()) <= 0){
			return "Le nombre de joueurs et le montant de monnaie sont invalides.";
		}
		else if (Integer.parseInt(this.nbJoueurs.getText())<1 && Integer.parseInt(this.monnaie.getText()) > 0) {
			return "Le nombre de joueurs est invalide.";
		}
		else if (Integer.parseInt(this.monnaie.getText()) <= 0 && Integer.parseInt(this.nbJoueurs.getText())>=1) {
			return "Le montant de monnaie est invalide.";
		}
		return message;
	}
	
	/*
	 * Vérifie si le message est numérique
	 */

	public boolean isNumber(String message) {
		try {
			Integer.parseInt(message);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * Ajoute 25 à la monnaie
	 */
	@FXML
	private void plusMonnaie() {
		int valeur1 = Integer.parseInt(this.monnaie.getText());
		this.monnaie.setText(String.valueOf(valeur1+25));
	}
	/*
	 * Retire 25 à la monnaie
	 */
	@FXML
	private void moinsMonnaie() {
		int valeur1 = Integer.parseInt(this.monnaie.getText());
		this.monnaie.setText(String.valueOf(valeur1-25));
	}
	/*
	 * Ajoute 1 au nombre de joueurs
	 */
	@FXML
	private void plusNb() {
		int valeur2 = Integer.parseInt(this.nbJoueurs.getText());
		this.nbJoueurs.setText(String.valueOf(valeur2+1));
	}	
	/*
	 * Retire 1 au nombre de joueurs
	 */
	@FXML
	private void moinsNb() {
		int valeur2 = Integer.parseInt(this.nbJoueurs.getText());
		this.nbJoueurs.setText(String.valueOf(valeur2-1));
	}
	/*
	 * Synchronise le paramètre avec le montant de monnaie
	 */
	public void setPropVal(IntegerProperty monnaie) {
		this.propMonnaie = monnaie;
		this.monnaie.textProperty().bindBidirectional(this.propMonnaie, new NumberStringConverter());
	}
	/*
	 * Synchronise le paramètre avec le nombre de joueurs
	 */
	public void setPropValNb(IntegerProperty nb) {
		this.propNbJoueurs = nb;
		this.nbJoueurs.textProperty().bindBidirectional(this.propNbJoueurs, new NumberStringConverter());
		
	}
}
