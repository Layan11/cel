package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowHybridController implements Initializable {
	@FXML
	private TableView<Movie> tableView;
	@FXML
	private TableColumn<Movie, String> movies;

	@FXML
	void goBack(ActionEvent event) throws Exception {
		App.setRoot("choose_type_to_browse");
	}

	@FXML
	void gotoRemoveLink(ActionEvent event) throws Exception {
		String movie = tableView.getSelectionModel().getSelectedItem().getEngName();
		TripleObject msg = new TripleObject("Remove link " + movie, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onUpdatedHybrid(GotHybridMoviesEvent event) {
		Platform.runLater(() -> {
			final ObservableList<Movie> movie = FXCollections.observableArrayList(SimpleClient.moviesList);
			tableView.setEditable(true);
			movies.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
			tableView.getColumns().setAll(movies);
			tableView.setItems(movie);
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		final ObservableList<Movie> movie = FXCollections.observableArrayList(SimpleClient.moviesList);
		tableView.setEditable(true);
		movies.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		tableView.getColumns().setAll(movies);
		tableView.setItems(movie);
	}
}
