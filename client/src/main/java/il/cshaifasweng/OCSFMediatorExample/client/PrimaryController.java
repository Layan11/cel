package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import il.cshaifasweng.OCSFMediatorExample.entities.MapChair;

/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

public class PrimaryController implements Initializable {

	@FXML
	private Button Browse_movie_list;

	@FXML
	private Button Filling_a_complaint;
	
  /*  @FXML
    private Button button_mapchair;*/


	@FXML
	void gotoFillingacomplaint(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FillingComplaint.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Complaints");
		primaryStage.show();

	}


	@FXML
	void gotobrowse_movies(ActionEvent event) throws Exception {
		//System.out.println("in gotobrowse movies in primary");
		Platform.runLater(() -> {
    		Parent root;
    		try {
    			App.setRoot("choose_type_to_browse");

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}});

//		TripleObject msg = new TripleObject("Browse movies", null, null);
//		SimpleClient.getClient().sendToServer(msg);

	}

   /* @FXML
    void gotomapchair(ActionEvent event) throws Exception {
    	Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("show_MapChair.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("mapchair");
		primaryStage.show();

    }*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
