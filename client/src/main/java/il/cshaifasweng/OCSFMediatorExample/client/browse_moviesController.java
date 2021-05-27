/**
 * Sample Skeleton for 'browse_movies.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class browse_moviesController implements Initializable {
	@FXML
	private TableView<Movie> tableView;
	@FXML
	private TableColumn<Movie, String> firstNameColumn;
	@FXML
	private TableColumn<Movie, String> priceColumn;

	@FXML // fx:id="Go_back"
	private Button Go_back; // Value injected by FXMLLoader

	@FXML // fx:id="Show_screening_time"
	private Button Show_screening_time; // Value injected by FXMLLoader

	@FXML // fx:id="image1"
	private ImageView image1; // Value injected by FXMLLoader

	@FXML // fx:id="image2"
	private ImageView image2; // Value injected by FXMLLoader

	@FXML // fx:id="image3"
	private ImageView image3; // Value injected by FXMLLoader

	@FXML // fx:id="image4"
	private ImageView image4; // Value injected by FXMLLoader

	@FXML // fx:id="image5"
	private ImageView image5; // Value injected by FXMLLoader

	public static Movie selectedMovie;

	@FXML
	void gobacktoprimary(ActionEvent event) throws IOException {
//		Window window = ((Node) (event.getSource())).getScene().getWindow();
//		if (window instanceof Stage) {
//			((Stage) window).close();
//		}
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("List of movies");
//		primaryStage.show();
		Platform.runLater(() -> {
		Parent root;
		try {
			App.setRoot("primary");
			//System.out.println("after the load line of brwose movies in primary");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});



	}

	@FXML
	void gotoShow_screening_time(ActionEvent event) throws IOException {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selectedMovie = selected;
		TripleObject msg = new TripleObject("Show Screening Times", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}
	
	@Subscribe
	public void onData1(GotScreeningTimesEvent event) {
		//System.out.println("IN onData1");
		Platform.runLater(() -> {

			//System.out.println("before load: " + SimpleClient.moviesList.get(0).getEngName());
			Parent root;
			try {
				App.setRoot("Screening_Times");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	// this method will return an observableList of movie
	public void getMovies(/* ArrayList<Movie> movies */) {
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
		System.out.println("in initialize in browse movies controller : ");
																			
		EventBus.getDefault().register(this);
		getMovies();
		InputStream stream;
		try {
			stream = new FileInputStream(SimpleClient.moviesList.get(0).getImage());
			System.out.println(stream);
			Image img = new Image(stream);
			image1.setImage(img);
			image1.setFitWidth(90);
			image1.setFitHeight(85);
			image1.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream1;
		try {
			stream1 = new FileInputStream(SimpleClient.moviesList.get(1).getImage());
			System.out.println(stream1);
			Image img2 = new Image(stream1);
			image2.setImage(img2);
			image2.setFitWidth(90);
			image2.setFitHeight(85);
			image2.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream2;
		try {
			stream2 = new FileInputStream(SimpleClient.moviesList.get(2).getImage());
			System.out.println(stream2);
			Image img3 = new Image(stream2);
			image3.setImage(img3);
			image3.setFitWidth(90);
			image3.setFitHeight(85);
			image3.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream3;
		try {
			stream3 = new FileInputStream(SimpleClient.moviesList.get(3).getImage());
			System.out.println(stream3);
			Image img4 = new Image(stream3);
			image4.setImage(img4);
			image4.setFitWidth(90);
			image4.setFitHeight(85);
			image4.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream4;
		try {
			stream4 = new FileInputStream(SimpleClient.moviesList.get(4).getImage());
			System.out.println(stream4);
			Image img5 = new Image(stream4);
			image5.setImage(img5);
			image5.setFitWidth(90);
			image5.setFitHeight(85);
			image5.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
