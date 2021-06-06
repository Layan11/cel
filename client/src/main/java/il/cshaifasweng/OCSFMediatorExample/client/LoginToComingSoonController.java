/**
 * Sample Skeleton for 'LoginToComingSoon.fxml' Controller Class
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

public class LoginToComingSoonController implements Initializable{

    @FXML // fx:id="Usernametxt"
    private TextField Usernametxt; // Value injected by FXMLLoader

    @FXML // fx:id="Passwordtxt"
    private TextField Passwordtxt; // Value injected by FXMLLoader

    @FXML // fx:id="Login"
    private Button Login; // Value injected by FXMLLoader

    @FXML // fx:id="Invalid"
    private Label Invalid; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML // fx:id="invalid_label2"
    private Label invalid_label2; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData(GotComingSoonEvent event) {
		//System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("Coming_soon");
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

    @FXML
    void gotoedit(ActionEvent event) {
		String username = Usernametxt.getText();
		String pass = Passwordtxt.getText();
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setEngName(username);
		movie.setHebName(pass);
		list.add(movie);
		Invalid.setText(null);
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
			Invalid.setText("Sorry, invalid user");
		});
	}

	@Subscribe
	public void onFoundUser(UserFoundEvent event) {
		Platform.runLater(() -> {
			try {
				int userRole = event.getRole();// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> Costumer
												// Services Employee
				if (userRole == 1) {
					App.setRoot("Edit_Coming_Soon_Movie");
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
