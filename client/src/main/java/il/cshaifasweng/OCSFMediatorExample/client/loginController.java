/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class loginController implements Initializable {

	@FXML
	private Button Login;

	@FXML
	private TextField username_box;

	@FXML
	private TextField password_box;
	@FXML // fx:id="invalid_label"
	private Label invalid_label; // Value injected by FXMLLoader
	@FXML // fx:id="invalid_label2"
	private Label invalid_label2; // Value injected by FXMLLoader

	@FXML
	void gotoLogin(ActionEvent event) {
		String username = username_box.getText();
		String pass = password_box.getText();
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setEngName(username);
		movie.setHebName(pass);
		list.add(movie);
		invalid_label.setText(null);
//		username_box.clear();
//		password_box.clear();
		TripleObject msg = new TripleObject("Login", list, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onNoUser(NoSuchUserEvent event) {
		Platform.runLater(() -> {
			invalid_label.setText("Sorry, invalid user");
		});
	}

	@Subscribe
	public void onFoundUser(UserFoundEvent event) {
		Platform.runLater(() -> {
			try {
				int userRole = event.getRole();// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> Costumer
												// Services Employee
				if (userRole == 1) {
					App.setRoot("MoreActions");
				} else {
					invalid_label2.setText("Sorry, this user does'nt have\npermission to do more actions");
				}
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