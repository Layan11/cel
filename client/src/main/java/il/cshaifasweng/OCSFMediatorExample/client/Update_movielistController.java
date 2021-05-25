/**
 * Sample Skeleton for 'Update_movielist.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Update_movielistController {

	@FXML // fx:id="invalidDelete_label"
	private Label invalidDelete_label; // Value injected by FXMLLoader

	@FXML // fx:id="newTime_box"
	private TextField newTime_box; // Value injected by FXMLLoader

	@FXML // fx:id="oldTime_box"
	private TextField oldTime_box; // Value injected by FXMLLoader

	@FXML // fx:id="Update_Screening_Time"
	private Button Update_Screening_Time; // Value injected by FXMLLoader

	@FXML // fx:id="newTime_box1"
	private TextField newTime_box1; // Value injected by FXMLLoader

	@FXML // fx:id="oldTime_box1"
	private TextField oldTime_box1; // Value injected by FXMLLoader

	@FXML // fx:id="Add_Screening_Time"
	private Button Add_Screening_Time; // Value injected by FXMLLoader

	@FXML // fx:id="Delete_Screening_Time"
	private Button Delete_Screening_Time; // Value injected by FXMLLoader

	@FXML // fx:id="back_to_home"
	private Button back_to_home; // Value injected by FXMLLoader

	@FXML // fx:id="movie_name"
	private TextField movie_name; // Value injected by FXMLLoader

	@FXML
	void Add_Screening_Time(ActionEvent event) {
		String movieName = movie_name.getText();
		String new_time = newTime_box1.getText();
		Movie movie = new Movie();
		movie.setEngName(movieName);
		movie.setHebName(new_time);
		ArrayList<Movie> list = new ArrayList<Movie>();
		list.add(movie);
		TripleObject to = new TripleObject("Add Screening Time", list, null);
		try {
			SimpleClient.getClient().sendToServer(to);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (SimpleClient.msg.equals("i")) {
			invalidDelete_label.setText("BLABOLA");
			newTime_box1.clear();
		}
	}

	@FXML
	void back_to_home(ActionEvent event) throws IOException {
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

}
