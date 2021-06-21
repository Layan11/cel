
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Screening_TimesController implements Initializable {
	@FXML
	private Button edit;
	@FXML
	private TextArea text_Screening_times;
	@FXML
	private Button back;
	@FXML
	private TableView<String> datesTable;
	@FXML
	private TableColumn<String, String> dates;
	@FXML
	private TableView<String> timesTable;
	@FXML
	private TableColumn<String, String> times;
	@FXML
	private Button delete;
	@FXML
	private Button add;
	@FXML
	private Button buy;
	@FXML
	private Label label;

	public static String action = "";
	public static String selectedScreeningTime = "";
	public static String selectedScreeningDate = "";

	@FXML
	void gotoBuy(ActionEvent event) throws Exception {
		restrictionPopupController.popped = 0;
		selectedScreeningTime = timesTable.getSelectionModel().getSelectedItem();
		selectedScreeningDate = datesTable.getItems().get(timesTable.getSelectionModel().getSelectedIndex());
		TripleObject msg = new TripleObject("Check if restricted day " + selectedScreeningDate, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onGotRestrictedAns(GotRestrictedAnsEvent event) {
		if (SimpleClient.restrictionAns.equals("Yes")) {
			if (restrictionPopupController.popped == 0) {
				restrictionPopupController.popped = 1;
			Platform.runLater(() -> {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("restrictionPopup.fxml"));
					Parent Root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(Root1));
					stage.setTitle("There's restrictions");
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				try {
					App.setRoot("Pay_Ticket");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});}
		} else if (SimpleClient.restrictionAns.equals("No")) {
			try {
				TripleObject msg = new TripleObject("get my map chair", browse_moviesController.selectedMovie.getId(),
						selectedScreeningTime);
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Subscribe
	public void onGotMapChair(gotMapChairevent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("buy_ticket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoAdd(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				action = "add";
				App.setRoot("UpdateMovies");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoDelete(ActionEvent event) throws Exception {
		String selectedTime = timesTable.getSelectionModel().getSelectedItem();
		selectedScreeningDate = datesTable.getItems().get(timesTable.getSelectionModel().getSelectedIndex());
		List<Movie> selectedMovie = new ArrayList<Movie>();
		selectedMovie.add(browse_moviesController.selectedMovie);
		TripleObject msg = new TripleObject("Delete Screening Time " + selectedTime, selectedMovie, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@FXML
	void gotoback(ActionEvent event) throws IOException {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData3(GotMoviesEventfromscreening event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onUpdatesScreenings(GotUpdatedScreeningsEvent event) throws Exception {
		TripleObject msg = new TripleObject(
				"Show Screening Times " + browse_moviesController.selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData4(GotScreeningTimesEvent event) {
		TripleObject msg = new TripleObject("Movie dates " + browse_moviesController.selectedMovie.getEngName(), null,
				null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onMovieDatesEvent(GotMovieDatesEvent event) {
		Platform.runLater(() -> {
			final ObservableList<String> movieDates = FXCollections.observableArrayList(SimpleClient.MovieDates);
			datesTable.setEditable(true);
			datesTable.setSelectionModel(null);
			dates.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			datesTable.getColumns().setAll(dates);
			datesTable.setItems(movieDates);

			final ObservableList<String> movieTimes = FXCollections.observableArrayList(SimpleClient.movieTimes);
			timesTable.setEditable(true);
			times.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			timesTable.getColumns().setAll(times);
			timesTable.setItems(movieTimes);
		});
	}

	@FXML
	void gotoedit(ActionEvent event) throws IOException {
		Platform.runLater(() -> {
			try {
				action = "edit";
				selectedScreeningTime = timesTable.getSelectionModel().getSelectedItem();
				App.setRoot("UpdateMovies");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		TripleObject msg = new TripleObject("Movie dates " + browse_moviesController.selectedMovie.getEngName(), null,
				null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (loginController.loginRole != 1)// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee,
											// 3->no Account
		{
			edit.setVisible(false);
			add.setVisible(false);
			delete.setVisible(false);
			label.setVisible(false);

		}
		if (loginController.loginRole == 3) {
			buy.setVisible(false);
		}
	}
}
