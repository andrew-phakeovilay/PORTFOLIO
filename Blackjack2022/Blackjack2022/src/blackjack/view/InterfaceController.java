package blackjack.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import blackjack.BlackJackApp;
import blackjack.om.BlackBot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InterfaceController implements Initializable {

	private BlackJackApp blackjack;
	private Stage interfaceStage;
	@FXML
	private Label monnaie;
	@FXML
	private TextField montantMise;
	@FXML
	private Label pointsJoueur;
	@FXML
	private Label pointsCroupier;
	@FXML
	private Label joueur;
	@FXML
	private HBox hbJoueur;
	@FXML
	private HBox hbSabot;
	@FXML
	private Label miseDonnee;
	@FXML
	private Button butTirer;
	@FXML
	private Button butMiser;
	@FXML
	private Button butRester;
	@FXML
	private Button butRecommencer;
	@FXML
	private Button butAccueil;
	@FXML
	private MenuItem menuAccueil;
	@FXML
	private MenuItem menuRecommencer;
	@FXML
	private ListView<String> viewMises;
	@FXML
	private ListView<String> viewScores;

	private BlackBot blackBot;

	private ArrayList<String> monnaieJoueurs = new ArrayList<String>();

	private int indice=0;

	private ArrayList<Integer> tabMises = new ArrayList<Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setBlackJack(BlackJackApp app) {
		this.blackjack = app;
	}
	public void setBlackBot(BlackBot bot) {
		this.blackBot = bot;
	}
	public void setInterfaceStage(Stage dialogStage) {
		this.interfaceStage = dialogStage;
	}

	/*
	 * Menu aide à propos
	 */
	@FXML
	private void aPropos() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("A propos");
		alert.setHeaderText("BlackJack");
		alert.initOwner(this.interfaceStage);
		alert.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/information.png").toExternalForm())));
		alert.setContentText("L'objectif est d'avoir autant ou plus de point que le croupier sans dépasser 21 mais nous ne savons que sa première carte.");
		alert.showAndWait();
	}
	/*
	 * Retour à l'accueil par le bouton Home ou le MenuItem Accueil
	 */
	@FXML
	private void accueil() {
		this.blackjack.loadAccueil();
	}
	/*
	 * Quitter le BlackJack par le bouton Quitter ou le MenuItem Quitter
	 */
	@FXML
	private void quitter() {
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle("Fermeture de l'Application");
		dialog.setContentText("Voulez-vous la partie ?");
		dialog.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/attention.png").toExternalForm())));
		dialog.setHeaderText("Quitter ?");
		dialog.initOwner(this.interfaceStage);
		Optional<ButtonType> reponse = dialog.showAndWait();
		if (reponse.get() == ButtonType.OK) {
			this.interfaceStage.close();
		}
	}
	/*
	 * Fonctionnalité du bouton Rester
	 */
	@FXML
	private void rester() {

	// Verifie si la mise du joueur à l'indice n'est pas 0
			if(this.tabMises.get(this.indice) != 0) { 

				// Affiche dans la ListView viewScores le score du joueur et enlève les cartes de hbJoueurs
				this.viewScores.getItems().add("Joueur " + (this.indice+1) + " : " + this.blackBot.getMainJoueurs(this.indice).getScore());
				this.hbJoueur.getChildren().clear();

				// Verifie si le joueur est perdant ou non
				if(!this.blackBot.getMainJoueurs(this.indice).isPerdante()) {
					this.blackBot.terminer(this.indice);
				}

				// Met le bouton Tirer desactivé
				this.butTirer.setDisable(false);

				// On passe au joueur suivant si il peut jouer (si sa mise n'est pas 0) en oubliant pas d'afficher ses 2 premières cartes
				for(int i = this.indice+1; i < this.tabMises.size(); i++){
					if(this.tabMises.get(i) !=0) {
						this.hbJoueur.getChildren().clear();
						afficherDepartJoueur(i);
						this.indice = i;
						i = this.tabMises.size();
					}
				}
			}
			// Verifie si tous les joueurs ne peuvent plus jouer, alors on on met les boutons, Label et TextField à l'état initial
			int var = 0;
			for(int i = 0; i < this.tabMises.size(); i++) {
				if(this.blackBot.getFinJoueurs(i) == true) {

					var++;
				}
				if(var == this.tabMises.size()) {

					this.joueur.setText("");
					this.pointsJoueur.setText("");
					this.miseDonnee.setText("");
					this.monnaie.setText("");

					this.butRester.setDisable(true);
					this.butTirer.setDisable(true);
					this.butRecommencer.setDisable(false);
					this.menuRecommencer.setDisable(false);
					// On passe à tirerCroupier()
					tirerCroupier();
				}
			}
	}

	/*
	 * Affiche les cartes suivantes du croupier et met à jour ses informations
	 */
	private void tirerCroupier() {
		for(int i = 1; i < this.blackBot.getMainBanque().getNbCartes(); i++) {
			String numero = this.blackBot.getMainBanque().getCarte(i).getNom();
			String symbole = this.blackBot.getMainBanque().getCarte(i).getNomCouleur();
			String URL = BlackJackApp.class.getResource("cartes/" + symbole + "-" + numero + ".png").toString();
			ImageView vueCarte = new ImageView(URL);
			vueCarte.setPreserveRatio(true);
			vueCarte.setFitWidth(100);
			this.hbSabot.getChildren().add(vueCarte);
		}
		this.pointsCroupier.setText(String.valueOf(this.blackBot.getMainBanque().getScore()));
		resultat();
	}

	/*
	 * Affiche par un Pop-Up le résultat des gains des joueurs. 
	 */
	private void resultat() {
		Alert res = new Alert(AlertType.INFORMATION);
		res.setTitle("Résultat");
		res.setHeaderText("Gains");
		res.initOwner(this.interfaceStage);
		res.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/gains.png").toExternalForm())));
		String resultat = "";
		for(int i = 0; i < this.tabMises.size(); i++) {
			resultat += "Le joueur " + (i+1) + " a eu un gain de " + this.blackBot.getGainJoueurs(i) + "\n";
			this.tabMises.set(i, 0);
			int monnaieAvant = Integer.parseInt(this.monnaieJoueurs.get(i));
			this.monnaieJoueurs.set(i, String.valueOf(this.blackBot.getGainJoueurs(i)+monnaieAvant));
		}
		res.setContentText(resultat);
		res.showAndWait();
	}
	/*
	 * Fonctionnalité du bouton Restart et du MenuItem Recommencer
	 */
	@FXML
	private void recommencer() {
		// Relance la partie à l'aide de la méthode relancerPartie(), revient à affichage initial, boutons activés/désactivés, indice revient à 0
		this.blackBot.relancerPartie();
		this.butMiser.setDisable(false);
		this.pointsCroupier.setText("");
		this.viewMises.getItems().clear();
		this.viewScores.getItems().clear();
		this.hbSabot.getChildren().clear();
		this.butRecommencer.setDisable(true);
		this.menuRecommencer.setDisable(true);
		this.montantMise.setDisable(false);
		this.indice = 0;
		this.joueurInit(indice);
	}


	/*
	 * Fonctionnalité du bouton Tirer
	 */
	@FXML
	private void tirer() {
		
		// utilise la méthode tirer de la classe BlackBot pour avoir les cartes suivantes
		this.blackBot.tirer(this.indice);

		// Prend la dernière carte du joueur et l'affiche dans hbJoueur et met à jour les points du joueur
		String numero = this.blackBot.getMainJoueurs(this.indice).getCarte(this.blackBot.getMainJoueurs(this.indice).getNbCartes()-1).getNom();
		String symbole = this.blackBot.getMainJoueurs(this.indice).getCarte(this.blackBot.getMainJoueurs(this.indice).getNbCartes()-1).getNomCouleur();
		String URL = BlackJackApp.class.getResource("cartes/" + symbole + "-" + numero + ".png").toString();
		Image carte = new Image(URL);
		ImageView vueCarte = new ImageView(carte);
		vueCarte.setPreserveRatio(true);
		vueCarte.setFitWidth(100);
		this.hbJoueur.getChildren().add(vueCarte);
		this.pointsJoueur.setText(String.valueOf(this.blackBot.getMainJoueurs(this.indice).getScore()));

		// Si le score du joueur est supérieur ou égal à 21, le joueur est en état fin et affiche dans le Pop-Up le résultat du joueur
		if(this.blackBot.getMainJoueurs(this.indice).getScore() >= 21){
			this.butTirer.setDisable(true);
			Alert total = new Alert(AlertType.INFORMATION);
			total.setTitle("Résultat");
			total.initOwner(this.interfaceStage);
			total.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/card.png").toExternalForm())));
			total.setHeaderText("Total");
			total.setContentText(this.blackBot.getMainJoueurs(this.indice).toString());
			total.showAndWait();
		}
	}
	/*
	 * Fonctionnalité du bouton Miser
	 */
	@FXML
	private void miserValeur() {

		// Verifie si le textField est numérique
		if(isNumber(this.montantMise.getText())){

			// Verifie si le montant est supérieur ou égale à 0 ET inférieur à la monnaie du joueur
			if(Integer.parseInt(this.montantMise.getText()) >= 0 && Integer.parseInt(this.montantMise.getText()) <= Integer.parseInt(this.monnaie.getText())) {

				// Met les mises des joueurs dans le tableau des mises de blackBot
				this.blackBot.miser(this.indice, Integer.parseInt(this.montantMise.getText()));

				// Crée la monnaie restante et la met dans le Label et l'ArrayList 
				int monnaieRestante = Integer.parseInt(this.monnaie.getText()) - Integer.parseInt(this.montantMise.getText());
				this.monnaieJoueurs.set(indice, String.valueOf(monnaieRestante));
				this.monnaie.setText(String.valueOf(monnaieRestante));

				// Ajoute dans la ListView les joueurs et leur mise
				this.viewMises.getItems().add("Joueur " + (this.indice+1) + " : " + this.blackBot.getMiseJoueurs(this.indice));

				// Après avoir appuyé sur le bouton Miser, remplace les Label joueur et monnaie par le joueur suivant
				if(this.monnaieJoueurs.size() > this.indice+1) {

					this.indice++;
					joueurInit(this.indice);
				}

				// Après que tous les joueurs ont misé, le bouton Miser, le TextField sont désactivés, les boutons Rester et Tirer sont activés
				else {
					this.butMiser.setDisable(true);
					this.montantMise.setDisable(true);
					this.butRester.setDisable(false);
					this.butTirer.setDisable(false);

					// Verification si tous les joueurs ont misé 0 ou non
					int nbM = 0;
					for(int i = 0; i < this.monnaieJoueurs.size(); i++) {
						if(this.tabMises.size()==this.monnaieJoueurs.size()) {
							this.tabMises.set(i, this.blackBot.getMiseJoueurs(i));
						}
						else {
							this.tabMises.add(this.blackBot.getMiseJoueurs(i));
						}
						if(this.blackBot.getMiseJoueurs(i) != 0) {
							nbM++;
						}
					}

					// Si personne a misé (miser 0) alors, des boutons sont désactivés et un Pop-Up apparaît et retour à loadAccueil() si le Pop-Up est fermé
					if(nbM==0){
						this.butRecommencer.setDisable(false);
						this.butRester.setDisable(true);
						this.butTirer.setDisable(true);
						Alert erreur = new Alert(Alert.AlertType.INFORMATION);
						erreur.setTitle("Erreur");
						erreur.setHeaderText("Information");
						erreur.setContentText("Aucun joueur a misé");
						erreur.initOwner(this.interfaceStage);
						erreur.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/attention.png").toExternalForm())));
						erreur.showAndWait();
						this.blackjack.loadAccueil();
					}

					// Sinon on distribue et on passe à afficherDepart()
					else {
						this.blackBot.distribuer();
						afficherDepart();
					}
				}

				// Met le TextField montantMise vide (enlever les chiffres)
				this.montantMise.setText("");
			}

			// Si le montantMise est invalide, affiche une Pop-Up
			else {
				Alert erreur = new Alert(Alert.AlertType.INFORMATION);
				erreur.setTitle("Erreur");
				erreur.setHeaderText("Information");
				erreur.initOwner(this.interfaceStage);
				erreur.setGraphic(new ImageView(new Image(BlackJackApp.class.getResource("resource/attention.png").toExternalForm())));
				erreur.setContentText("Le montant de mise est invalide. Veuillez entrer un nombre supérieur ou égal à 0 et ne dépassant pas votre monnaie.");
				erreur.showAndWait();
			}
		}
	}

	private void afficherDepart() {

		// Prend l'image du croupier en bonne taille et l'ajoute dans l'HBox hbSabot
		String numero = this.blackBot.getMainBanque().getCarte(0).getNom();
		String symbole = this.blackBot.getMainBanque().getCarte(0).getNomCouleur();
		String URL = BlackJackApp.class.getResource("cartes/" + symbole + "-" + numero + ".png").toString();
		Image carte = new Image(URL);
		ImageView vueCarte = new ImageView(carte);
		vueCarte.setPreserveRatio(true);
		vueCarte.setFitWidth(100);
		this.hbSabot.getChildren().add(vueCarte);

		// Met le nombre de point en ce moment du croupier dans le Label pointsCroupier
		this.pointsCroupier.setText(String.valueOf(this.blackBot.getMainBanque().getScore()));

		// Parcourt tous les joueurs et prend le premier Joueur qui a misé une montant supérieur à 0
		for(int i = 0; i < this.monnaieJoueurs.size(); i++) {
			if(this.tabMises.get(i) != 0) {
				this.indice = i;
				afficherDepartJoueur(this.indice);
				i = this.monnaieJoueurs.size();
			}
		}
	}	

	private void afficherDepartJoueur(int index) {

		// Met à jour les informations des Label joueur, monnaie et miseDonnee du joueur de l'index
		joueurInit(index);
		this.miseDonnee.setText(String.valueOf(this.blackBot.getMiseJoueurs(index)));

		// Affiche dans HBox hbJoueur les 2 premières cartes du joueur
		for(int i = 0; i < 2; i ++) {
			String numero = this.blackBot.getMainJoueurs(index).getCarte(i).getNom();
			String symbole = this.blackBot.getMainJoueurs(index).getCarte(i).getNomCouleur();
			String URL = BlackJackApp.class.getResource("cartes/" + symbole + "-" + numero + ".png").toString();
			Image carte = new Image(URL);
			ImageView vueCarte = new ImageView(carte);
			vueCarte.setPreserveRatio(true);
			vueCarte.setFitWidth(100);
			this.hbJoueur.getChildren().add(vueCarte);
		}

		// Met à jour le Label pointsJoueur avec le score du joueur
		this.pointsJoueur.setText(String.valueOf(this.blackBot.getMainJoueurs(index).getScore()));
	}

	/*
	 * Vérifie si le message est numérique
	 */
	private boolean isNumber(String message) {
		try {
			Integer.parseInt(message);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * Ajoute de la monnaie à tous les joueurs
	 */
	public void setMonnaie(int value, int nb) {
		for(int i = 0; i < nb; i++) {
			this.monnaieJoueurs.add(String.valueOf(value));
		}
	}

	/*
	 * Afficher le joueur et son numéro
	 */
	public void joueurInit(int numero) {
		this.joueur.setText("Joueur " + (numero+1));
		this.monnaie.setText(this.monnaieJoueurs.get(numero));
	}
}
