/**
 * Sample Skeleton for 'BrowseMovies.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BrowseMoviesController {

	@FXML // fx:id="c1"
	private TableColumn<?, ?> c1; // Value injected by FXMLLoader

	@FXML // fx:id="c2"
	private TableColumn<?, ?> c2; // Value injected by FXMLLoader

	@FXML // fx:id="show"
	private Button show; // Value injected by FXMLLoader

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
		primaryStage.setTitle("Complaints");
		primaryStage.show();
	}

	@FXML
	void gotoshow(ActionEvent event) throws IOException {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Screening_Times.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Complaints");
		primaryStage.show();
	}

}
