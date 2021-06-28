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

	@FXML
	private Button SignIn;
	@FXML
	private TextField username_box;
	@FXML
	private TextField password_box;
	@FXML
	private Label invalid_label;
	@FXML
	private Button back;

	@FXML
	void goBackToPrimary(ActionEvent event) throws Exception {
		// EventBus.getDefault().unregister(this);
		App.setRoot("primary");
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
				e.printStackTrace();
			}
			//// 3shan yru7 3sf7t elmenu?
			try {
				// EventBus.getDefault().unregister(this);
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// EventBus.getDefault().register(this);
	}
}
