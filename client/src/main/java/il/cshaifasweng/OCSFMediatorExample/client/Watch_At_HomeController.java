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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Watch_At_HomeController implements Initializable {

	@FXML
	private TableView<Movie> tableView;
	@FXML
	private TableColumn<Movie, String> Name;
	@FXML
	private TableColumn<Movie, String> price;
	@FXML
	private Button Back;
	@FXML
	private Button More_Info;
	@FXML
	private Button Edit;
	@FXML
	private Button buy_link;
	@FXML
	private Button main;

	public static Movie selected_watch_at_home_Movie;

	@FXML
	void backtomain(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@FXML
	void buy_link_btn(ActionEvent event) throws IOException {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_watch_at_home_Movie = selected;
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Buy_Link");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void Edit_HomeMovie(ActionEvent event) {
		Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_watch_at_home_Movie = selected;
		System.out.println("selected name in WAH : " + selected_watch_at_home_Movie.getEngName());
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Edit_Movie");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void Back(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("choose_type_to_browse");
			} catch (IOException e) {
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
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("moreInfoToWatchAtHome");
			} catch (IOException e) {
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
		if (loginController.loginRole == 3)// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee
		{
			buy_link.setVisible(false);
		}
		EventBus.getDefault().register(this);
		getMovies();

	}

}
