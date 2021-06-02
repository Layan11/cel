/**
 * Sample Skeleton for 'choose_type_to_browse.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class choose_type_to_browseController implements Initializable{

    @FXML // fx:id="Now_In_Branches"
    private Button Now_In_Branches; // Value injected by FXMLLoader

    @FXML // fx:id="Coming_Soon"
    private Button Coming_Soon; // Value injected by FXMLLoader

    @FXML // fx:id="Watch_At_Home"
    private Button Watch_At_Home; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML
    void goback(ActionEvent event) {
    	Platform.runLater(() -> {
    		Parent root;
    		try {
    			App.setRoot("primary");

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}});

    }

    @FXML
    void gotocomingsoon(ActionEvent event) throws Exception {

		TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData11(GotComingSoonEvent event) {
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
    void gotomoviesinbranches(ActionEvent event) throws Exception {

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

    @FXML
    void gotowatchathome(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Watch At Home", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData112(GotWatchAtHomeEvent event) {
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



    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
	}
}


