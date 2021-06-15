
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class browse_moviesController implements Initializable {
	@FXML
	private TableView<Movie> tableView;
	@FXML
	private TableColumn<Movie, String> firstNameColumn;
	@FXML
	private TableColumn<Movie, String> priceColumn;

	@FXML
	private Button Go_back;
	@FXML
	private Button Show_screening_time;
	@FXML
	private Button More_Info;
	@FXML
	private ChoiceBox<String> ChoiceBox;
	@FXML
	private Button Show;
	@FXML
	private TextField from;
	@FXML
	private TextField to;
	@FXML
	private Button filter;

	public static Movie selectedMovie;

	@FXML
	void Show(ActionEvent event) throws Exception {
		String selectedBranch = ChoiceBox.getSelectionModel().getSelectedItem();
		TripleObject msg = new TripleObject(selectedBranch, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@FXML
	void gotoFilter(ActionEvent event) throws Exception {
		List<String> dates = new ArrayList<String>();
		dates.add(from.getText());
		dates.add(to.getText());
		TripleObject msg = new TripleObject("Filter dates", null, null);
		msg.setList(dates);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onFiltered(GotFilteredMovieByDatesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("filteredMovieDates");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onData(GotfilteredMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("FilteredMovies");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void show_More_Info(ActionEvent event) throws Exception {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selectedMovie = selected;
		System.out.println("selected name in browseM : " + selectedMovie.getEngName());
		Movie mv = new Movie();
		List<Movie> mvlist = new ArrayList<Movie>();
		mv.setEngName(selected.getEngName());
		mvlist.add(mv);
		System.out.println("mv size : " + mvlist.size());
		TripleObject msg = new TripleObject("Show More info", mvlist, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData22(GotMoreInfoEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("More_Info");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gobacktoprimary(ActionEvent event) throws IOException {
		Platform.runLater(() -> {
			try {
				App.setRoot("choose_type_to_browse");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoShow_screening_time(ActionEvent event) throws IOException {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selectedMovie = selected;
		TripleObject msg = new TripleObject("Show Screening Times " + selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData1(GotScreeningTimesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Screening_Times");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void getMovies() {
		final ObservableList<Movie> movie = FXCollections.observableArrayList(SimpleClient.moviesList);
		tableView.setEditable(true);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Price"));
		tableView.getColumns().setAll(firstNameColumn, priceColumn);
		tableView.setItems(movie);

		return;
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		getMovies();
		ChoiceBox.getItems().add("Haifa");
		ChoiceBox.getItems().add("Shefa-Amr");

	}
}
