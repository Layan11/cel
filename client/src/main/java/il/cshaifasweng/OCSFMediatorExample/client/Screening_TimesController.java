/**
 * Sample Skeleton for 'NEWpg.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.mapping.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
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

public class Screening_TimesController implements Initializable {
	@FXML // fx:id="edit"
	private Button edit; // Value injected by FXMLLoader

	@FXML
	private TableView<MovieTimes> tableView;

	@FXML
	private TableColumn<MovieTimes, List> Screening_Times;
	@FXML // fx:id="Branch"
	private TableColumn<Movie, String> Branch; // Value injected by FXMLLoader
	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("browse_movies.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("List of movies");
		primaryStage.show();
	}

	@FXML
	void gotoedit(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("update_movies.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Update movies");
		primaryStage.show();
	}

	public void getInfo(MovieTimes mt) {
		final ObservableList<MovieTimes> movieTimes = FXCollections.observableArrayList(mt);
		tableView.setEditable(true);
		Screening_Times.setCellValueFactory(new PropertyValueFactory<MovieTimes, List>("times"));
		tableView.getColumns().setAll(Screening_Times);
		tableView.setItems(movieTimes);

		return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(SimpleClient.movieTimes.get(0).getTimes());

		getInfo(browse_moviesController.selectedMovie.getMovieTimes());
	}

}
