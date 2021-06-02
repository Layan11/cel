/**
 * Sample Skeleton for 'NEWpg.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
//import il.cshaifasweng.OCSFMediatorExample.server.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Screening_TimesController implements Initializable {

	@FXML // fx:id="edit"
	private Button edit; // Value injected by FXMLLoader

	@FXML // fx:id="text_Screening_times"
	private TextArea text_Screening_times; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData3(GotMoviesEventfromscreening event) {
		System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			// Parent root;
			try {
				App.setRoot("browse_movies");
//				Scene scene = new Scene(root);
//				primaryStage.setScene(scene);
//				primaryStage.setTitle("Browse movies list");
//				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	@FXML
	void gotoedit(ActionEvent event) throws IOException {
//		Window window = ((Node) (event.getSource())).getScene().getWindow();
//		if (window instanceof Stage) {
//			((Stage) window).close();
//		}
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("update_movies.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Update movies");
//		primaryStage.show();
		Platform.runLater(() -> {
			Parent root;
			try {
				App.setRoot("update_movies");
				// System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// System.out.println(SimpleClient.movieTimes.get(0).getTimes());
		EventBus.getDefault().register(this);
		if (SimpleClient.movieTimes != null) {
			// text_Screening_times.setText( "trying22" +
			// SimpleClient.movieTimes.get(0).getTimes() );
			// System.out.println("TRYING!!!!!!!!!!!!!!");
			System.out
					.println("Printing Screening in Screenings id = " + browse_moviesController.selectedMovie.getId());
			System.out.println("Printing the size of SimpleClient.movieTimes" + SimpleClient.movieTimes.size());
			int indx = browse_moviesController.selectedMovie.getMovieTimes().getId() - 1;
			System.out.println("indx = " + indx);
			System.out.println(SimpleClient.movieTimes.get(0).getTimes());
			System.out.println("should print the id of 3 " + SimpleClient.movieTimes.get(2).getId());
			System.out.println("should print the Screenings of 2 " + SimpleClient.movieTimes.get(2).getTimes());
			System.out.println(
					"should print the Screenings of Selected movie " + SimpleClient.movieTimes.get(indx).getTimes());
			text_Screening_times.setText("Times : " + SimpleClient.movieTimes.get(indx).getTimes());

		} else {
			System.out.println("movieTimes is null!!!!!!!!!!!!!!!!!!");
		}
	}

}
