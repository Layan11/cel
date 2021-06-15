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
	@FXML
	private Label invalid_label;
	@FXML
	private Label invalid_label2;
	@FXML
	private Button back;

	public static int loginRole;
	public static String currentUser;

	@FXML
	void goBackToPrimary(ActionEvent event) throws Exception {
		App.setRoot("primary2");
	}

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
		TripleObject msg = new TripleObject("Login", list, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onNoUser(UserIsConnectedEvent event) {
		Platform.runLater(() -> {
			invalid_label.setText("Sorry,User is already connected");
		});
	}

	@Subscribe
	public void onNoUser(NoSuchUserEvent event) {
		Platform.runLater(() -> {
			invalid_label.setText("Sorry, invalid user/password");
		});
	}

	@Subscribe
	public void onFoundUser(UserFoundEvent event) {
		Platform.runLater(() -> {
			loginRole = event.getRole();// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> Costumer
			currentUser = event.getUserName();
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		back.setVisible(false);
	}
}