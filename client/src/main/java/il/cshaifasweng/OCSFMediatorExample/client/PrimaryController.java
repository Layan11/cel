package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable {

	@FXML
	private Button login;
	@FXML
	private Button noLogin;
	@FXML
	private Button Signin;

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

	}
}