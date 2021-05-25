/**
 * Sample Skeleton for 'browse_movies.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class browse_moviesController implements Initializable {
	@FXML
	private TableView<Movie> tableView;
	@FXML
	private TableColumn<Movie, String> firstNameColumn;
	@FXML
	private TableColumn<Movie, String> branchColumn;

	@FXML // fx:id="Go_back"
	private Button Go_back; // Value injected by FXMLLoader

	@FXML // fx:id="Show_screening_time"
	private Button Show_screening_time; // Value injected by FXMLLoader

	@FXML
	void gobacktoprimary(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("List of movies");
		primaryStage.show();

	}

	@FXML
	void gotoShow_screening_time(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		TripleObject msg = new TripleObject("Show Screening Times", null, null);
		SimpleClient.getClient().sendToServer(msg);
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("NEWpg.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Screening_Times");
		primaryStage.show();

	}

	// this method will return an observableList of movie
	public void getMovies() {
		final ObservableList<Movie> movie = FXCollections.observableArrayList(SimpleClient.moviesList);
		tableView.setEditable(true);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		branchColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("HebName"));
		tableView.getColumns().setAll(firstNameColumn, branchColumn);
		tableView.setItems(movie);
		// movie = (ObservableList<Movie>) SimpleClient.moviesList;
		return;
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		getMovies();
	}
}
