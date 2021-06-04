/**
 * Sample Skeleton for 'Edit_Watch_At_Home_Movie.fxml' Controller Class
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

public class Edit_Watch_At_Home_MovieController implements Initializable{

    @FXML // fx:id="branchtxt"
    private TextField branchtxt; // Value injected by FXMLLoader

    @FXML // fx:id="halltxt"
    private TextField halltxt; // Value injected by FXMLLoader

    @FXML // fx:id="SC1"
    private TextField SC1; // Value injected by FXMLLoader

    @FXML // fx:id="SC2"
    private TextField SC2; // Value injected by FXMLLoader

    @FXML // fx:id="SC3"
    private TextField SC3; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML // fx:id="Add"
    private Button Add; // Value injected by FXMLLoader
    
    @FXML // fx:id="Delete_Movie"
    private Button Delete_Movie; // Value injected by FXMLLoader

    @FXML // fx:id="Movienametxt"
    private TextField Movienametxt; // Value injected by FXMLLoader
    
    @FXML
    void DeleteMovie(ActionEvent event) throws Exception {
    	int selectedID = Watch_At_HomeController.selected_watch_at_home_Movie.getId();
    	Movie helperMovie = new Movie();
    	helperMovie.setLength(selectedID);
    	List<Movie> helperList = new ArrayList<Movie>();
    	helperList.add(helperMovie);
    	TripleObject msg = new TripleObject("Delete Movie", helperList,null );
		SimpleClient.getClient().sendToServer(msg);
    }
    
    @Subscribe
	public void onData(GotMovieDeletedEvent event) {
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
    void Add_to_now_broadcasting(ActionEvent event) throws Exception {
    	Movie newMovie = new Movie();
		newMovie = Watch_At_HomeController.selected_watch_at_home_Movie;
		//System.out.println("selected name in edit : " + Coming_soonController.selected_coming_soon_Movie.getEngName());
		newMovie.setType(0);
		newMovie.setBranch(branchtxt.getText());
		List<String> Timeslist = new ArrayList<String>();
		Timeslist.add(SC1.getText());
		Timeslist.add(SC2.getText());
		Timeslist.add(SC3.getText());
		MovieTimes MTimes = new MovieTimes(Timeslist);
		List<MovieTimes> MTimeslist = new ArrayList<MovieTimes>();
		MTimeslist.add(MTimes);
		//newMovie.setMovieTimes(MTimes);
		List<Movie> ListnewMovie = new ArrayList<Movie>();
		ListnewMovie.add(newMovie);
		TripleObject msg = new TripleObject("Save new Movie", ListnewMovie,MTimeslist );
		SimpleClient.getClient().sendToServer(msg);

    }

    @FXML
    void Back(ActionEvent event) throws Exception {
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
		Movienametxt.setText("Editing " +Watch_At_HomeController.selected_watch_at_home_Movie.getEngName() + "Movie");
		
	}
    	

    
}
