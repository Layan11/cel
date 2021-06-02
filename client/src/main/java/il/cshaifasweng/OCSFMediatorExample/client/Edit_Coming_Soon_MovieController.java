/**
 * Sample Skeleton for 'Edit_Coming_Soon_Movie.fxml' Controller Class
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
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Edit_Coming_Soon_MovieController implements Initializable{

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML // fx:id="branchtxt"
    private TextField branchtxt; // Value injected by FXMLLoader

    @FXML // fx:id="halltxt"
    private TextField halltxt; // Value injected by FXMLLoader

    @FXML // fx:id="Screening1"
    private TextField Screening1; // Value injected by FXMLLoader

    @FXML // fx:id="Screening2"
    private TextField Screening2; // Value injected by FXMLLoader

    @FXML // fx:id="Screening3"
    private TextField Screening3; // Value injected by FXMLLoader
    
    @FXML // fx:id="Add"
    private Button Add; // Value injected by FXMLLoader

    @FXML
    void Addmovie(ActionEvent event) throws Exception {
    	Movie newMovie = new Movie();
		newMovie = Coming_soonController.selected_coming_soon_Movie;
		//System.out.println("selected name in edit : " + Coming_soonController.selected_coming_soon_Movie.getEngName());
		newMovie.setType(0);
		newMovie.setBranch(branchtxt.getText());
		List<String> Timeslist = new ArrayList<String>();
		Timeslist.add(Screening1.getText());
		Timeslist.add(Screening2.getText());
		Timeslist.add(Screening3.getText());
		MovieTimes MTimes = new MovieTimes(Timeslist);
		List<MovieTimes> MTimeslist = new ArrayList<MovieTimes>();
		MTimeslist.add(MTimes);
		//newMovie.setMovieTimes(MTimes);
		List<Movie> ListnewMovie = new ArrayList<Movie>();
		ListnewMovie.add(newMovie);
		TripleObject msg = new TripleObject("Save new Movie", ListnewMovie,MTimeslist );
		SimpleClient.getClient().sendToServer(msg);

    }
	@Subscribe
	public void onData22(GotcsnewMovieEvent event) {
		//System.out.println("IN onData");
		Platform.runLater(() -> {

			try {
				App.setRoot("choose_type_to_browse");
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
    void goBack(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData1111(GotComingSoonEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		// TODO Auto-generated method stub
	
	}

}
