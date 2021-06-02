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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class update_moviesController implements Initializable {
	@FXML
	private Label invalidUpdate;
	@FXML // fx:id="Add_Screening_Time"
	private Button Add_Screening_Time; // Value injected by FXMLLoader

	@FXML // fx:id="new_time_add"
	private TextField new_time_add; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML // fx:id="time_delete"
	private TextField time_delete; // Value injected by FXMLLoader

	@FXML // fx:id="Delete_Screening_Time"
	private Button Delete_Screening_Time; // Value injected by FXMLLoader

	@FXML // fx:id="old_time"
	private TextField old_time; // Value injected by FXMLLoader

	@FXML // fx:id="new_time"
	private TextField new_time; // Value injected by FXMLLoader

	@FXML // fx:id="Update_Screening_Time"
	private Button Update_Screening_Time; // Value injected by FXMLLoader
	@FXML
	private Label invalidDelete;

	@FXML
	void gotoadd(ActionEvent event) throws IOException {
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie = browse_moviesController.selectedMovie;
		list.add(movie);
		List<MovieTimes> hlpr = new ArrayList<MovieTimes>();
		MovieTimes tmp = new MovieTimes();
		List<String> timesList = new ArrayList<String>();
		timesList.add(new_time_add.getText());
		tmp.SetMovieTimes(timesList);
		hlpr.add(tmp);
		TripleObject msg = new TripleObject("Add Screening Time", list, hlpr);
		SimpleClient.getClient().sendToServer(msg);
		new_time_add.clear();
		invalidDelete.setText(null);
		invalidUpdate.setText(null);

	}

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		invalidDelete.setText(null);
		invalidUpdate.setText(null);
		TripleObject msg = new TripleObject("Show Screening Times", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData4(GotScreeningTimesEventback event) {
		// System.out.println("IN onData1");
		Platform.runLater(() -> {

			// System.out.println("before load: " +
			// SimpleClient.moviesList.get(0).getEngName());
			Parent root;
			try {
				App.setRoot("Screening_Times");
				// System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	@Subscribe
	public void onUpdateError(NoScreeningTimeToUpdateEvent event) {
		Platform.runLater(() -> {
			invalidUpdate.setText("there's no such screening time to update");
		});
	}

	@Subscribe
	public void onDeleteError(NoScreeningTimeToDeleteEvent event) {
		Platform.runLater(() -> {
			invalidDelete.setText("there's no such screening time to delete");
		});
	}

	@FXML
	void gotodelete(ActionEvent event) throws IOException {
		List<Movie> list2 = new ArrayList<Movie>();
		Movie movie2 = new Movie();
		movie2 = browse_moviesController.selectedMovie;
		list2.add(movie2);
		List<MovieTimes> hlpr2 = new ArrayList<MovieTimes>();
		MovieTimes tmp2 = new MovieTimes();
		List<String> timesList2 = new ArrayList<String>();
		timesList2.add(time_delete.getText());
		tmp2.SetMovieTimes(timesList2);
		hlpr2.add(tmp2);
		TripleObject msg = new TripleObject("Delete Screening Time", list2, hlpr2);
		SimpleClient.getClient().sendToServer(msg);
		time_delete.clear();
		invalidDelete.setText(null);
		invalidUpdate.setText(null);

	}

	@FXML
	void gotoupdate(ActionEvent event) throws IOException {
		List<Movie> list3 = new ArrayList<Movie>();
		Movie movie3 = new Movie();
		movie3 = browse_moviesController.selectedMovie;
		list3.add(movie3);
		List<MovieTimes> hlpr3 = new ArrayList<MovieTimes>();
		MovieTimes tmp3 = new MovieTimes();
		List<String> timesList3 = new ArrayList<String>();
		timesList3.add(old_time.getText()); // old time is in place 0 new time is in place 1
		timesList3.add(new_time.getText());
		tmp3.SetMovieTimes(timesList3);
		hlpr3.add(tmp3);
		TripleObject msg = new TripleObject("Update Screening Time", list3, hlpr3);
		SimpleClient.getClient().sendToServer(msg);
		browse_moviesController.selectedMovie.getMovieTimes().getTimes();
		old_time.clear();
		new_time.clear();
		invalidUpdate.setText(null);
		invalidDelete.setText(null);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		// TODO Auto-generated method stub

	}
}
