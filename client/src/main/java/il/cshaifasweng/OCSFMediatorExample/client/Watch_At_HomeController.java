/**
 * Sample Skeleton for 'Watch_At_Home.fxml' Controller Class
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
import javafx.scene.control.cell.PropertyValueFactory;

public class Watch_At_HomeController implements Initializable {

	@FXML // fx:id="tableView"
	private TableView<Movie> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="Name"
	private TableColumn<Movie, String> Name; // Value injected by FXMLLoader

	@FXML // fx:id="price"
	private TableColumn<Movie, String> price; // Value injected by FXMLLoader

	@FXML // fx:id="Back"
	private Button Back; // Value injected by FXMLLoader

	@FXML // fx:id="More_Info"
	private Button More_Info; // Value injected by FXMLLoader

	@FXML // fx:id="Edit"
	private Button Edit; // Value injected by FXMLLoader

	public static Movie selected_watch_at_home_Movie;

	@FXML
	void Edit_HomeMovie(ActionEvent event) {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_watch_at_home_Movie = selected;
		System.out.println("selected name in WAH : " + selected_watch_at_home_Movie.getEngName());
		Platform.runLater(() -> {
			try {
				App.setRoot("Edit_Movie");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	@FXML
	void Back(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("choose_type_to_browse");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	@FXML
	void More_Info(ActionEvent event) throws Exception {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_watch_at_home_Movie = selected;
		System.out.println("selected name in WAH : " + selected_watch_at_home_Movie.getEngName());
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

			// System.out.println("before load: " +
			// SimpleClient.moviesList.get(0).getEngName());
			Parent root;
			try {
				App.setRoot("moreInfoToWatchAtHome");
				// System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	public void getMovies(/* ArrayList<Movie> movies */) {
		final ObservableList<Movie> CSmovie = FXCollections.observableArrayList(SimpleClient.moviesList);
		tableView.setEditable(true);
		Name.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		price.setCellValueFactory(new PropertyValueFactory<Movie, String>("Price"));
		tableView.getColumns().setAll(Name, price);
		tableView.setItems(CSmovie);
		return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginController.loginRole != 1)// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee
		{
			Edit.setVisible(false);
		}
		EventBus.getDefault().register(this);
		getMovies();

	}

}
