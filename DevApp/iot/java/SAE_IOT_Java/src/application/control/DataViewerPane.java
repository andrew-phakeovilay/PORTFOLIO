package application.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.view.DataViewerPaneController;

public class DataViewerPane extends Application {

	@SuppressWarnings("unused")
	private Stage _parentStage;
	
	public DataViewerPane() {
	}
	
	@Override
	public void start(Stage _parentStage) throws Exception {
		this._parentStage=_parentStage;

		FXMLLoader loader= new FXMLLoader();

		loader.setLocation(DataViewerPaneController.class.getResource("window.fxml"));
				
		BorderPane borderpane=loader.load();
		
		Scene sceneVisionnageDonnees=new Scene(borderpane);
		
		_parentStage.setScene(sceneVisionnageDonnees);
		_parentStage.setTitle("Visionnage des données");
		
		
		DataViewerPaneController Wcontroll = loader.getController();
		
		Wcontroll.initContext(_parentStage);
		Wcontroll.displayDialog();
		
		
	}
}
