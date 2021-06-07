/**
 * Sample Skeleton for 'FilteredMovies.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilteredMoviesController implements Initializable {

	@FXML // fx:id="tableview"
	private TableView<Movie> tableview; // Value injected by FXMLLoader

	@FXML // fx:id="Name"
	private TableColumn<Movie, String> Name; // Value injected by FXMLLoader

	@FXML // fx:id="Price"
	private TableColumn<Movie, String> Price; // Value injected by FXMLLoader

	@FXML // fx:id="Back"
	private Button Back; // Value injected by FXMLLoader

	@FXML // fx:id="titletxt"
	private TextField titletxt; // Value injected by FXMLLoader
	@FXML // fx:id="moreInfo"
	private Button moreInfo; // Value injected by FXMLLoader

	@FXML
	void gotoMoreInfo(ActionEvent event) throws Exception {
		Movie selected = tableview.getSelectionModel().getSelectedItem();
		browse_moviesController.selectedMovie = selected;// msh mtakde iza s7 nst3ml nfs el selected movie
		System.out.println("selected name in browseM : " + browse_moviesController.selectedMovie.getEngName());
		Movie mv = new Movie();
		List<Movie> mvlist = new ArrayList<Movie>();
		mv.setEngName(selected.getEngName());
		mvlist.add(mv);
		System.out.println("mv size : " + mvlist.size());
		TripleObject msg = new TripleObject("Show More info", mvlist, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData22(GotMoreInfoEvent event) {
		Platform.runLater(() -> {
			Parent root;
			try {
				App.setRoot("MoreInfoFiltered");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@FXML
	void Back(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData(GotMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void getMovies(/* ArrayList<Movie> movies */) {
		final ObservableList<Movie> movie = FXCollections.observableArrayList(SimpleClient.moviesList);
		System.out.println("size of movielist" + SimpleClient.moviesList.size());
		tableview.setEditable(true);
		Name.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		Price.setCellValueFactory(new PropertyValueFactory<Movie, String>("Price"));
		tableview.getColumns().setAll(Name, Price);
		tableview.setItems(movie);

		return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
		getMovies();
		titletxt.setText("Movies in " + SimpleClient.moviesList.get(0).getBranch() + " branch");

	}

}
