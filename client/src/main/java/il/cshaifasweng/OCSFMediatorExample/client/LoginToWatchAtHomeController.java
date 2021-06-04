/**
 * Sample Skeleton for 'LoginToWatchAtHome.fxml' Controller Class
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

public class LoginToWatchAtHomeController implements Initializable{

    @FXML // fx:id="Login"
    private Button Login; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML // fx:id="Username_box"
    private TextField Username_box; // Value injected by FXMLLoader

    @FXML // fx:id="Password_box"
    private TextField Password_box; // Value injected by FXMLLoader

    @FXML // fx:id="invalid"
    private Label invalid; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Watch At Home", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData(GotWatchAtHomeEvent event) {
		//System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("Watch_At_Home");
//				Scene scene = new Scene(root);
//				primaryStage.setScene(scene);s
//				primaryStage.setTitle("Browse movies list");
//				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

    }

    @FXML
    void Login(ActionEvent event) {
		String username = Username_box.getText();
		String pass = Password_box.getText();
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setEngName(username);
		movie.setHebName(pass);
		list.add(movie);
		invalid.setText(null);
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
	public void onUser(PermessionEvent event) {
    	Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("Edit_Watch_At_Home_Movie");
//				Scene scene = new Scene(root);
//				primaryStage.setScene(scene);s
//				primaryStage.setTitle("Browse movies list");
//				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

    }

	@Subscribe
	public void onNoUser(NoSuchUserEvent event) {
		Platform.runLater(() -> {
			invalid.setText("Sorry, invalid user");
		});


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
	}

}
