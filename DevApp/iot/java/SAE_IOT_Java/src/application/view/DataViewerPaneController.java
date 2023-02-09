package application.view;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.yaml.snakeyaml.Yaml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class DataViewerPaneController implements Initializable {
	
	// Fenêtre physique
	private Stage primaryStage;

	CategoryAxis xAxisActivite = new CategoryAxis();
    NumberAxis yAxisActivite = new NumberAxis();

    CategoryAxis xAxisCO2 = new CategoryAxis();
    NumberAxis yAxisCO2 = new NumberAxis();

    CategoryAxis xAxisHumidite = new CategoryAxis();
    NumberAxis yAxisHumidite = new NumberAxis();

    CategoryAxis xAxisIllumination = new CategoryAxis();
    NumberAxis yAxisIllumination = new NumberAxis();

    CategoryAxis xAxisInfrarouge = new CategoryAxis();
    NumberAxis yAxisInfrarouge = new NumberAxis();

    CategoryAxis xAxisInfrarougeVisible = new CategoryAxis();
    NumberAxis yAxisInfrarougeVisible = new NumberAxis();

    CategoryAxis xAxisPression = new CategoryAxis();
    NumberAxis yAxisPression = new NumberAxis();
    
    CategoryAxis xAxisTemperature = new CategoryAxis();
    NumberAxis yAxisTemperature = new NumberAxis();
    
    CategoryAxis xAxisQualite_air = new CategoryAxis();
    NumberAxis yAxisQualite_air = new NumberAxis();

    
	@FXML
	private BarChart<String,Number> bcActivite;
	@FXML
	private BarChart<String,Number> bcCo2;
	@FXML
	private BarChart<String,Number> bcHumidite;
	@FXML
	private BarChart<String,Number> bcIllumination;
	@FXML
	private BarChart<String,Number> bcInfrarouge;
	@FXML
	private BarChart<String,Number> bcInfrarougeVisible;
	@FXML
	private BarChart<String,Number> bcPression;
	@FXML
	private BarChart<String,Number> bcTemperature;
	@FXML
	private BarChart<String,Number> bcQualite_air;
	
	@FXML
	private NumberAxis yaxis;
	
	
	Yaml bGymYaml = new Yaml();
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config_mqtt.yaml");
	
	HashMap<String, Object> bGymYamlObject = bGymYaml.load(inputStream);
	

	private RunBackGroundData rb;
	
	/**
	 * Cette procédure va effectuer une lecture du yaml pour savoir quels graphiques on va montrer
	 * à l'écran, et ne montrer que ceux qu'il faut
	 */
	public void setVisiblesGraphs() {
		bcActivite.setVisible(false);
		bcCo2.setVisible(false);
		bcHumidite.setVisible(false);
		bcIllumination.setVisible(false);
		bcInfrarouge.setVisible(false);
		bcInfrarougeVisible.setVisible(false);
		bcPression.setVisible(false);
		bcTemperature.setVisible(false);
		bcQualite_air.setVisible(false);
		
		for(Map.Entry<String, String> e : ((HashMap<String, String>) bGymYamlObject.get("object")).entrySet()) {
			switch (e.getKey()) {
			case "activity":
				bcActivite.setVisible(true);
				break;
		
			case "illumination":
				bcIllumination.setVisible(true);
				break;
				
			case "co2":
				bcCo2.setVisible(true);
				break;
				
			case "humidity":
				bcHumidite.setVisible(true);
				break;
				
			case "temperature":
				bcTemperature.setVisible(true);
				break;
				
			case "infrared_and_visible":
				bcInfrarougeVisible.setVisible(true);
				break;
				
			case "infrared":
				bcInfrarouge.setVisible(true);
				break;
				
			case "pressure":
				bcPression.setVisible(true);
				break;
				
			case "tvoc":
				bcQualite_air.setVisible(true);
				break;
				
			default:
				System.out.println("Erreur dans la lecture");
				break;
		}
		}
	}

	/**
	 * Cette fonction permet de lire les données d'un fichier donné en paramètre.
	 * Le fichier doit se trouver dans le dossier parent de ce dossier.
	 * readLine() renvoie la dernière ligne du fichier, 
	 * 
	 * @param fileName le nom du fichier dont les données doivent être lues
	 * @return lastLine la dernière ligne du fichier
	 */	
	public double readLine(String fileName) {
		String filePath = "src/"+fileName;
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			try {
				String oneLine = null, tmp;
				while((tmp = br.readLine()) != null) {
					oneLine = tmp;
				}
				Double lastLine = Double.parseDouble(oneLine);
				br.close();
				return lastLine;				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//valeur par défaut
		return 0;
	}
	
	public void updatingGraphs() {
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(new XYChart.Data<String, Number>(sensor_activite, this.readLine(bGymYamlObject.get("name")+"_activity_donnees.txt")));
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.getData().add(new XYChart.Data<String, Number>(sensor_CO2, this.readLine(bGymYamlObject.get("name")+"_co2_donnees.txt")));
        
        XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
        series3.getData().add(new XYChart.Data<String, Number>(sensor_humidite, this.readLine(bGymYamlObject.get("name")+"_humidity_donnees.txt")));
        
        XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
        series4.getData().add(new XYChart.Data<String, Number>(sensor_illumination, this.readLine(bGymYamlObject.get("name")+"_illumination_donnees.txt")));
        
        XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();     
        series5.getData().add(new XYChart.Data<String, Number>(sensor_infrarouge, this.readLine(bGymYamlObject.get("name")+"_infrared_donnees.txt")));
        
        XYChart.Series<String, Number> series6 = new XYChart.Series<String, Number>();
        series6.getData().add(new XYChart.Data<String, Number>(sensor_infrarouge_visible, this.readLine(bGymYamlObject.get("name")+"_infrared_and_visible_donnees.txt")));
        
        XYChart.Series<String, Number> series7 = new XYChart.Series<String, Number>();
        series7.getData().add(new XYChart.Data<String, Number>(sensor_pression, this.readLine(bGymYamlObject.get("name")+"_pressure_donnees.txt")));
        
        XYChart.Series<String, Number> series8 = new XYChart.Series<String, Number>();
        series8.getData().add(new XYChart.Data<String, Number>(sensor_qualite_air, this.readLine(bGymYamlObject.get("name")+"_tvoc_donnees.txt")));
        
        XYChart.Series<String, Number> series9 = new XYChart.Series<String, Number>();
        series9.getData().add(new XYChart.Data<String, Number>(sensor_temperature, this.readLine(bGymYamlObject.get("name")+"_temperature_donnees.txt")));
       
 
        bcActivite.getData().add(series1);
        bcCo2.getData().add(series2);
        bcHumidite.getData().add(series3);
        bcIllumination.getData().add(series4);
        bcInfrarouge.getData().add(series5);
        bcInfrarougeVisible.getData().add(series6);
        bcPression.getData().add(series7);
        bcTemperature.getData().add(series8);
        bcQualite_air.getData().add(series9);
	}
	
	
	final static String sensor_activite = "Activité";
    final static String sensor_CO2 = "CO2";
    final static String sensor_humidite = "Humidité";
    final static String sensor_illumination = "Illumination";
    final static String sensor_infrarouge = "Infrarouge";
    final static String sensor_infrarouge_visible = "Infrarouge visible";
    final static String sensor_pression = "Pression";
    final static String sensor_temperature = "Température";
    final static String sensor_qualite_air = "Qualité de l'air";
 
    public void start(Stage stage) {
        
        bcActivite.setTitle("Evolution Activité");
        xAxisActivite.setLabel(sensor_activite);
    	yAxisActivite.setLabel("Arbitraire");
        
        bcCo2.setTitle("Evolution CO2");
        xAxisCO2.setLabel(sensor_CO2);       
        yAxisCO2.setLabel("ppm");
        
        bcHumidite.setTitle("Evolution humidité");
        xAxisHumidite.setLabel(sensor_humidite);       
        yAxisHumidite.setLabel("%");
        
        bcIllumination.setTitle("Evolution Illumination");
        xAxisIllumination.setLabel(sensor_illumination);       
        yAxisIllumination.setLabel("lux");
        
        bcInfrarouge.setTitle("Evolution Infrarouge");
        xAxisInfrarouge.setLabel(sensor_infrarouge);       
        yAxisInfrarouge.setLabel("Value");
        
        bcInfrarougeVisible.setTitle("Evolution Infrarouge_visible");
        xAxisInfrarougeVisible.setLabel("Country");       
        yAxisInfrarougeVisible.setLabel("Value");
        
        bcPression.setTitle("Evolution Pression");
        xAxisPression.setLabel(sensor_pression);       
        yAxisPression.setLabel("bar");
        
        bcTemperature.setTitle("Evolution température");
        xAxisTemperature.setLabel(sensor_temperature);       
        yAxisTemperature.setLabel("°C");
        
        bcQualite_air.setTitle("Evolution qualité_air");
        xAxisQualite_air.setLabel(sensor_qualite_air);       
        yAxisQualite_air.setLabel("Arbitraire");

    	//this.setVisiblesGraphs();
        
        
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
 
    // Manipulation de la fenêtre
	public void initContext(Stage _containingStage) {
		this.primaryStage = _containingStage;
		this.start(_containingStage);
		this.configure();
	}

	// Afficher la fenêtre
	public void displayDialog() {
		this.primaryStage.show();

		// Création du "code" à exécuter en thread (un Runnable)
		this.rb = new RunBackGroundData(this);

		// Création d'un thread pour exécuter notre code de rb (rb.run())
		Thread t = new Thread(this.rb);

		// Démarrage du thread
		t.start();
	}


	// Si on ferme avec la croix
	private void configure() {
		this.primaryStage.setOnCloseRequest(e -> this.closeWindow(e));
	}

	// Gestion du stage
	private Object closeWindow(WindowEvent e) {
	// 	this.rb.stopIt(); Arret du thread lorsque qu'on quitte la fenetre
		this.primaryStage.close();
		return null;
	}
}