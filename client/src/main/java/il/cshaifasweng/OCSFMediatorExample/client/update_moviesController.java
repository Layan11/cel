package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class update_moviesController implements Initializable {
	@FXML
	private Button addScreening;

	@FXML
	private Label timeLabel;

	@FXML
	private Button back;

	@FXML
	private Button updateScreening;

	@FXML
	private Label dateLabel;

	@FXML
	private TextArea times;

	@FXML
	private TextArea dates;

	@FXML
	private Label noteLabel;

	@FXML
	void gotoadd(ActionEvent event) throws IOException {
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie = browse_moviesController.selectedMovie;
		list.add(movie);
		List<MovieTimes> hlpr = new ArrayList<MovieTimes>();
		MovieTimes tmp = new MovieTimes();
		List<String> timesList = Arrays.asList(times.getText().split("\\s*,\\s*"));
		tmp.SetMovieTimes(timesList);
		List<String> datesList = Arrays.asList(dates.getText().split("\\s*,\\s*"));
		tmp.setDate(datesList);
		hlpr.add(tmp);
		TripleObject msg = new TripleObject("Add Screening Time", list, hlpr);
		SimpleClient.getClient().sendToServer(msg);
		times.clear();
		dates.clear();
	}

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		TripleObject msg = new TripleObject(
				"Show Screening Times " + browse_moviesController.selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onGotScreeningTimes(GotScreeningTimesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Screening_Times");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoupdate(ActionEvent event) throws Exception {
		System.out.println("PPPPPP");
		List<MovieTimes> hlpr3 = new ArrayList<MovieTimes>();
		MovieTimes tmp3 = new MovieTimes();
		List<String> timesList3 = new ArrayList<String>();
		timesList3.add(Screening_TimesController.selectedScreeningTime); // old time is in place 0 new time is in place
																			// 1
		timesList3.add(times.getText());
		List<String> datesList3 = new ArrayList<String>();
		System.out.println("IN update controller. new date = ");
		System.out.println(dates.getText());
		datesList3.add(dates.getText());
		tmp3.SetMovieTimes(timesList3);
		tmp3.setDate(datesList3);
		hlpr3.add(tmp3);
		TripleObject msg = new TripleObject(
				"Update Screening Time " + browse_moviesController.selectedMovie.getEngName(), null, hlpr3);
		SimpleClient.getClient().sendToServer(msg);
		times.clear();
		dates.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (Screening_TimesController.action.equals("add")) {
			updateScreening.setVisible(false);
		}
		if (Screening_TimesController.action.equals("edit")) {
			addScreening.setVisible(false);
			noteLabel.setText("");
			timeLabel.setText("New Screening Time");
			dateLabel.setText("New Screening Date");
		}
	}
}
