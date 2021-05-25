package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;

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

//wlzm nzed kbas edit hay lsf7a
public class Screening_TimesController implements Initializable {
//	@FXML
//	private TableView<MovieTimes> tableView;
//	@FXML
//	private TableColumn<MovieTimes, String> Screening_Times;
//	@FXML
//	private TableColumn<MovieTimes, String> Branch;

	@FXML // fx:id="movie_name"
	private TextField movie_name; // Value injected by FXMLLoader

	@FXML // fx:id="go_back"
	private Button go_back; // Value injected by FXMLLoader

	@FXML // fx:id="edit"
	private Button edit; // Value injected by FXMLLoader

	@FXML
	void goback(ActionEvent event) throws IOException {
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
	void gotoED(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Update_movielist.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("is");
		primaryStage.show();
	}

//	public void getInfo() {
//		final ObservableList<MovieTimes> movieTimes = FXCollections.observableArrayList(SimpleClient.movieTimes);
//		tableView.setEditable(true);
//		Screening_Times.setCellValueFactory(new PropertyValueFactory<MovieTimes, String>("time"));
//		Branch.setCellValueFactory(new PropertyValueFactory<MovieTimes, String>("id"));
//		tableView.getColumns().setAll(Screening_Times, Branch);
//		tableView.setItems(movieTimes);
//		return;
//	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// getInfo();
	}

}
