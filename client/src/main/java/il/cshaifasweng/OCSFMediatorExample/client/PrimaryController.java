//package il.cshaifasweng.OCSFMediatorExample.client;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.application.Platform;
////import java.awt.event.ActionEvent;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//
///**
// * Sample Skeleton for 'primary.fxml' Controller Class
// */
//
//public class PrimaryController implements Initializable {
//
//	@FXML
//	private Button Browse_movie_list;
//
//	@FXML
//	private Button Filling_a_complaint;
//
//	@FXML
//	void gotoFillingacomplaint(ActionEvent event) throws IOException {
//		Window window = ((Node) (event.getSource())).getScene().getWindow();
//		if (window instanceof Stage) {
//			((Stage) window).close();
//		}
//
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("FillingComplaint.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Complaints");
//		primaryStage.show();
//
//	}
//
//	@FXML
//	void gotobrowse_movies(ActionEvent event) throws Exception {
//		Platform.runLater(() -> {
//			try {
//				App.setRoot("choose_type_to_browse");
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//	}
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//	}
//}

package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable {

	@FXML // fx:id="login"
	private Button login; // Value injected by FXMLLoader

	@FXML // fx:id="noLogin"
	private Button noLogin; // Value injected by FXMLLoader
	
	@FXML // fx:id="Signin"
	private Button Signin; // Value injected by FXMLLoader

	@FXML
	void gotoLogin(ActionEvent event) throws Exception {
		App.setRoot("login");
	}

	@FXML
	void gotoNoLogin(ActionEvent event) throws Exception {
		loginController.loginRole = 3;
		App.setRoot("menu");
	}
	
	@FXML
	void gotosignin(ActionEvent event) throws Exception {
		App.setRoot("signIn");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
