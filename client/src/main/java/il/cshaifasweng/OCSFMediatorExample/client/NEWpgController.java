/**
 * Sample Skeleton for 'NEWpg.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.mapping.List;

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

public class NEWpgController implements Initializable {
	@FXML
	private TableView<MovieTimes> tableView;
//	@FXML
//	private TableColumn<MovieTimes, String> Screening_Times;
	@FXML
	private TableColumn<MovieTimes, List> Branch;

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void gotoback(ActionEvent event) throws IOException {
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

	public void getInfo(MovieTimes mt) {
		final ObservableList<MovieTimes> movieTimes = FXCollections.observableArrayList(mt);
		tableView.setEditable(true);
		// Screening_Times.setCellValueFactory(new PropertyValueFactory<MovieTimes,
		// String>("time"));
		Branch.setCellValueFactory(new PropertyValueFactory<MovieTimes, List>("times"));
		// Branch.setText(movietimes.getTimes());
		tableView.getColumns().setAll(Branch);
		tableView.setItems(movieTimes);
		return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(SimpleClient.movieTimes.get(0).getTimes());

		getInfo(SimpleClient.movieTimes.get(0));
	}

}
