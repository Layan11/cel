/**
 * Sample Skeleton for 'signIn.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SigninController implements Initializable {

	@FXML // fx:id="Login"
	private Button SignIn; // Value injected by FXMLLoader

	@FXML // fx:id="username_box"
	private TextField username_box; // Value injected by FXMLLoader

	@FXML // fx:id="password_box"
	private TextField password_box; // Value injected by FXMLLoader

	@FXML // fx:id="invalid_label"
	private Label invalid_label; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void goBackToPrimary(ActionEvent event) throws Exception {
		App.setRoot("primary2");
	}

	@FXML
	void gotoSignin(ActionEvent event) {

		String name = username_box.getText();
		String password = password_box.getText();
		if (name.equals(""))
			invalid_label.setText("Enter a valid name please");
		else if (password.equals("") || password.length() < 4)
			invalid_label.setText("Enter a valid password please that contanins more than 4 chars");
		else {
			loginController.loginRole = -1;
			loginController.currentUser = name;
			// User user=new User(name,password);
			invalid_label.setText("");
			Movie user = new Movie();
			user.setEngName(name);
			user.setHebName(password);
			List<Movie> L = new ArrayList<Movie>();
			L.add(user);
			TripleObject msg = new TripleObject("Add new person", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//// 3shan yru7 3sf7t elmenu?
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
