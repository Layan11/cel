package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class update_moviesController implements Initializable {
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
	void gotoadd(ActionEvent event) throws IOException {
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setEngName(browse_moviesController.selectedMovie.getEngName());
		movie.setHebName(new_time_add.getText());
		movie.setProducer(null);
		list.add(movie);
		TripleObject msg = new TripleObject("Add Screening Time", list, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Screening_Times.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("List of movies");
		primaryStage.show();
	}

	@FXML
	void gotodelete(ActionEvent event) throws IOException {
		List<Movie> list2 = new ArrayList<Movie>();
		Movie movie2 = new Movie();
		movie2.setEngName(browse_moviesController.selectedMovie.getEngName());
		movie2.setHebName(null);
		movie2.setProducer(time_delete.getText());
		list2.add(movie2);
		TripleObject msg = new TripleObject("Delete Screening Time", list2, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@FXML
	void gotoupdate(ActionEvent event) throws IOException {
		List<Movie> list3 = new ArrayList<Movie>();
		Movie movie3 = new Movie();
		movie3.setEngName(browse_moviesController.selectedMovie.getEngName());
		movie3.setHebName(old_time.getText());
		movie3.setProducer(new_time.getText());
		list3.add(movie3);
		TripleObject msg = new TripleObject("Update Screening Time", list3, null);
		SimpleClient.getClient().sendToServer(msg);
		browse_moviesController.selectedMovie.getMovieTimes().getTimes();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
