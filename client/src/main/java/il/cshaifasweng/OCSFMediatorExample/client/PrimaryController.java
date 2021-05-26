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

/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

public class PrimaryController implements Initializable {

	@FXML
	private Button Browse_movie_list;

	@FXML
	private Button Filling_a_complaint;



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

//	@FXML
//	void gotoLogin(ActionEvent event) throws IOException {
//
//		Window window = ((Node) (event.getSource())).getScene().getWindow();
//		if (window instanceof Stage) {
//			((Stage) window).close();
//		}
//
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Log in");
//		primaryStage.show();
//
//	}

	@FXML
	void gotobrowse_movies(ActionEvent event) throws Exception {
		System.out.println("in gotobrowse movies in primary");

		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData(GotMoviesEvent event) {
		System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("browse_movies");
//				Scene scene = new Scene(root);
//				primaryStage.setScene(scene);
//				primaryStage.setTitle("Browse movies list");
//				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
	}
}
