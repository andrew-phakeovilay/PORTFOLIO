package application.view;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.yaml.snakeyaml.Yaml;

import application.control.FileEditorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class FileEditorPaneController implements Initializable {

	// Fenêtre physique
	private Stage primaryStage;

	// Les radio boutons de la scene
	@FXML
	private RadioButton rbActivity;
	@FXML
	private RadioButton rbCo2;
	@FXML
	private RadioButton rbHumidity;
	@FXML
	private RadioButton rbIllumination;
	@FXML
	private RadioButton rbInfrared;
	@FXML
	private RadioButton rbInfraredAndVisible;
	@FXML
	private RadioButton rbPressure;
	@FXML
	private RadioButton rbTemperature;
	@FXML
	private RadioButton rbTvoc;

	// Les attributs textes de la scene
	@FXML
	private TextField tfFileName;
	@FXML
	private TextField tfActivity;
	@FXML
	private TextField tfCo2_1;
	@FXML
	private TextField tfCo2_2;
	@FXML
	private TextField tfHumidity_1;
	@FXML
	private TextField tfHumidity_2;
	@FXML
	private TextField tfIllumination;
	@FXML
	private TextField tfInfrared;
	@FXML
	private TextField tfInfraredAndVisible;
	@FXML
	private TextField tfPressure;
	@FXML
	private TextField tfTemperature_1;
	@FXML
	private TextField tfTemperature_2;
	@FXML
	private TextField tfTvoc;

	// Les attributs de fréquences de la scene
	@FXML
	private Slider sFrequency;
	@FXML
	private Label lFrequency;

	// attribut bouton appliquer de la scene
	@FXML
	private Button apply;

	// Thread
	RunBackground rb;

	FileEditorPane fep;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	// Manipulation de la fenêtre
	public void initContext(Stage _containingStage, FileEditorPane _fep) {
		this.primaryStage = _containingStage;
		this.fep = _fep;
		this.configure();
	}

	// Afficher la fenêtre
	public void displayDialog() {
		this.primaryStage.show();

		// Création du "code" à exécuter en thread (un Runnable)
		this.rb = new RunBackground(this);

		// Création d'un thread pour exécuter notre code de rb (rb.run())
		Thread t = new Thread(this.rb);

		// Démarrage du thread
		t.start();
	}


	/*
	 * Change l'interface de configuration en fonction des actions de l'utilisateur
	 */
	public void validateComponentState() {
		
		/**
		 * Pour tous les conditions .isSelected, on regarde si le bouton est sélectionné, donc on active son champ de text associé 
		 */
		if(this.rbActivity.isSelected()) {
			this.tfActivity.setDisable(false);
		}else {
			this.tfActivity.setDisable(true);
		}
		if(this.rbCo2.isSelected()) {
			this.tfCo2_1.setDisable(false);
			this.tfCo2_2.setDisable(false);
		}else {
			this.tfCo2_1.setDisable(true);
			this.tfCo2_2.setDisable(true);
		}
		if(this.rbHumidity.isSelected()) {
			this.tfHumidity_1.setDisable(false);
			this.tfHumidity_2.setDisable(false);
		}else {
			this.tfHumidity_1.setDisable(true);
			this.tfHumidity_2.setDisable(true);
		}
		if(this.rbIllumination.isSelected()) {
			this.tfIllumination.setDisable(false);
		}else {
			this.tfIllumination.setDisable(true);
		}
		if(this.rbInfrared.isSelected()) {
			this.tfInfrared.setDisable(false);
		}else {
			this.tfInfrared.setDisable(true);
		}
		if(this.rbInfraredAndVisible.isSelected()) {
			this.tfInfraredAndVisible.setDisable(false);
		}else {
			this.tfInfraredAndVisible.setDisable(true);
		}
		if(this.rbPressure.isSelected()) {
			this.tfPressure.setDisable(false);
		}else {
			this.tfPressure.setDisable(true);
		}
		if(this.rbTemperature.isSelected()) {
			this.tfTemperature_1.setDisable(false);
			this.tfTemperature_2.setDisable(false);
		}else {
			this.tfTemperature_1.setDisable(true);
			this.tfTemperature_2.setDisable(true);
		}
		if(this.rbTvoc.isSelected()) {
			this.tfTvoc.setDisable(false);
		}else {
			this.tfTvoc.setDisable(true);
		}
		this.lFrequency.setText((int)sFrequency.getValue()+" minutes");
	}

	/*
	 * Après avoir appuyé sur le bouton appliquer, nous vérifions si les données sont correctes
	 * AU MOINS un radio bouton selectionné ET son seuil entré
	 * IL FAUT avoir un nom pour le/les fichier(s) et une fréquence supérieur à 0
	 */
	@FXML
	private void appliedCheck() throws IOException {
		
		// Préparation du message d'information
		Alert msgError = new Alert(AlertType.INFORMATION);
		msgError.setTitle("Configuration du fichier");
		msgError.setHeaderText("Information");

		// Si aucun objet n'est selectionné ET il y a de nom ET pas de fréquence
		if(!(this.rbTvoc.isSelected()) && !(this.rbTemperature.isSelected()) && !(this.rbPressure.isSelected()) && 
				!(this.rbInfrared.isSelected()) && !(this.rbInfraredAndVisible.isSelected()) && !(this.rbIllumination.isSelected()) && 
				!(this.rbHumidity.isSelected()) && !(this.rbActivity.isSelected()) && !(this.rbCo2.isSelected()) || ((int)this.sFrequency.getValue() == 0 || this.tfFileName.getText().equals(""))) {
			// Affichage de l'Alert
			msgError.setContentText("Veuillez écrire un nom, choisir AU MOINS un objet et mettre une fréquence valide");
			msgError.showAndWait();
		}else {
			
			// Création de dictionnaire pour écrire dans le fichier .yaml
			Map<String, Object> data = new HashMap<String, Object>();
			// Ajout de clé valeur
			data.put("servers", "chirpstack.iut-blagnac.fr");
			data.put("port", 1883);
			data.put("name", this.tfFileName.getText());
			// Convertion de la fréquence minutes en secondes
			data.put("frequency", (int)sFrequency.getValue()*60);

			// Création des dictionnaires des objets selectionnés et de ses seuils
			Map<String, Boolean> objects = new HashMap<String, Boolean>();
			Map<String, Object> seuils = new HashMap<String, Object>();

			// Booléen vérifiant si tout est valide
			boolean valid = false;
			
			/*
			 * Dans ces conditions, nous vérifions si l'objet est selectionné, si son seuil est correct,
			 * nous insérons dans les dictionnaires objects et seuils et on met le valid en true
			 * 
			 * Sinon pop-up et valid est false
			 */
			if(this.rbActivity.isSelected()) {
				if(isNumber(this.tfActivity.getText())) {
					objects.put("activity", true);
					seuils.put("activity", Float.valueOf(this.tfActivity.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour Activité est vide ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			/*
			 *  Cas avec une intervalle, 1ère valeur de seuil doit être inférieure à la 2ème valeur de seuil
			 *  puis on ajoute dans seuils un tableau d'intervalle
			 */
			if(this.rbCo2.isSelected()) {
				if(isNumber(this.tfCo2_1.getText()) && isNumber(this.tfCo2_2.getText()) && Float.valueOf(this.tfCo2_1.getText()) < Float.valueOf(this.tfCo2_2.getText()) ) {
					objects.put("co2", true);
					seuils.put("co2", new float[] { Float.valueOf(this.tfCo2_1.getText()), Float.valueOf(this.tfCo2_2.getText()) });
					valid = true;
				}else {
					msgError.setContentText("Seuils pour CO2 sont vides ou invalides");
					msgError.showAndWait();
					valid = false;
				}
			}
			/*
			 * Cas humidité, les valeurs de seuils doivent être 0 et 100 car c'est en pourcentage
			 */
			if(this.rbHumidity.isSelected()) {
				if(isNumber(this.tfHumidity_1.getText()) && isNumber(this.tfHumidity_2.getText()) && Float.valueOf(this.tfHumidity_1.getText()) < Float.valueOf(this.tfHumidity_2.getText())
						&& Float.valueOf(this.tfHumidity_1.getText()) <= 100 && Float.valueOf(this.tfHumidity_1.getText()) >= 0
						&& Float.valueOf(this.tfHumidity_2.getText()) <= 100 && Float.valueOf(this.tfHumidity_2.getText()) >= 0) {
					objects.put("humidity", true);
					seuils.put("humidity", new float[] { Float.valueOf(this.tfHumidity_1.getText()), Float.valueOf(this.tfHumidity_2.getText()) });
					valid = true;
				}else {
					msgError.setContentText("Seuils pour Humidité sont vides ou invalides");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbIllumination.isSelected()) {
				if(isNumber(this.tfIllumination.getText())) {
					objects.put("illumination", true);
					seuils.put("illumination", Float.valueOf(this.tfIllumination.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour Illumination est vide ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbInfrared.isSelected()) {
				if(isNumber(this.tfInfrared.getText())) {
					objects.put("infrared", true);
					seuils.put("infrared", Float.valueOf(this.tfInfrared.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour l'infrarouge est vide ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbInfraredAndVisible.isSelected()) {
				if(isNumber(this.tfInfraredAndVisible.getText())) {
					objects.put("infrared_and_visible", true);
					seuils.put("infrared_and_visible", Float.valueOf(this.tfInfraredAndVisible.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour l'infrarouge et visible est vide ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbPressure.isSelected()) {
				if(isNumber(this.tfPressure.getText())) {
					objects.put("pressure", true);
					seuils.put("pressure", Float.valueOf(this.tfPressure.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour la pression vide est ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbTemperature.isSelected()) {
				if(isNumber(this.tfTemperature_1.getText()) && isNumber(this.tfTemperature_2.getText()) && Float.valueOf(this.tfTemperature_1.getText()) < Float.valueOf(this.tfTemperature_2.getText())) {
					objects.put("temperature", true);
					seuils.put("temperature", new float[] { Float.valueOf(this.tfTemperature_1.getText()), Float.valueOf(this.tfTemperature_2.getText()) });
					valid = true;
				}else {
					msgError.setContentText("Seuils pour la température sont vides ou invalides");
					msgError.showAndWait();
					valid = false;
				}
			}
			if(this.rbTvoc.isSelected()) {
				if(isNumber(this.tfTvoc.getText())) {
					objects.put("tvoc", true);
					seuils.put("tvoc", Float.valueOf(this.tfTvoc.getText()));
					valid = true;
				}else {
					msgError.setContentText("Seuil pour la qualité de l'air est vide ou invalide");
					msgError.showAndWait();
					valid = false;
				}
			}
			
			// Ajout des dictionnaires objects et seuils dans data
			data.put("object", objects);
			data.put("seuils", seuils);
			
			// On crée une variable yaml
			Yaml yaml = new Yaml();
			// On crée le fichier dans le path du projet
			PrintWriter writer = new PrintWriter(new File("./src/config_mqtt.yaml"));
			// On dépose le dictionnaire dans le writer (le fichier) avec la syntaxe yaml
			yaml.dump(data, writer);

			// Si toutes les vérifications sont justes, alors valid est true donc nous passon à la fenêtre des graphiques
			if(valid) {
				this.fep.afficheGraphique();
			}
		}
	}

	// Si on ferme avec la croix
	private void configure() {
		this.primaryStage.setOnCloseRequest(e -> this.closeWindow(e));
	}

	// Gestion du stage
	private Object closeWindow(WindowEvent e) {
		this.rb.stopIt();
		this.primaryStage.close();
		return null;
	}

	/*
	 * Vérifie si le message est numérique
	 */
	private boolean isNumber(String message) {
		try {
			Float.parseFloat(message);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
