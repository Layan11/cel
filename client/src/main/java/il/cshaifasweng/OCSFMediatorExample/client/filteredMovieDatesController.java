package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class filteredMovieDatesController implements Initializable {
	@FXML
	private TableView<String> moviesTable;

	@FXML
	private TableColumn<String, String> movies;

	@FXML
	private TableView<String> datesTable;

	@FXML
	private TableColumn<String, String> dates;

	@FXML
	private TableView<String> timesTable;

	@FXML
	private TableColumn<String, String> times;
	@FXML
	private Button back;

	@FXML
	void goBack(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void ongotmovies(GotMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final ObservableList<String> MovieNames = FXCollections.observableArrayList(SimpleClient.MovieNames);
		moviesTable.setEditable(true);
		moviesTable.setSelectionModel(null);
		movies.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		moviesTable.getColumns().setAll(movies);
		moviesTable.setItems(MovieNames);

		final ObservableList<String> MovieDates = FXCollections.observableArrayList(SimpleClient.MovieDates);
		datesTable.setEditable(true);
		datesTable.setSelectionModel(null);
		dates.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		datesTable.getColumns().setAll(dates);
		datesTable.setItems(MovieDates);

		final ObservableList<String> movieTimes = FXCollections.observableArrayList(SimpleClient.movieTimes);
		timesTable.setEditable(true);
		timesTable.setSelectionModel(null);
		times.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		timesTable.getColumns().setAll(times);
		timesTable.setItems(movieTimes);
	}
}
