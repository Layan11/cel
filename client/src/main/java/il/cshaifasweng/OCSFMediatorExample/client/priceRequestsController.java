package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class priceRequestsController implements Initializable {

	@FXML // fx:id="tableView"
	private TableView<String> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="movieName"
	private TableColumn<String, String> movieName; // Value injected by FXMLLoader
	@FXML // fx:id="approve"
	private Button approve; // Value injected by FXMLLoader
	@FXML // fx:id="deny"
	private Button deny; // Value injected by FXMLLoader
	@FXML // fx:id="newPrice"
	private TableColumn<String, String> newPrice; // Value injected by FXMLLoader
	@FXML // fx:id="tableView"
	private TableView<String> tableView2; // Value injected by FXMLLoader
	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void goBack(ActionEvent event) throws Exception {
		App.setRoot("menu");
	}

	@FXML
	void gotoApprove(ActionEvent event) throws Exception {
		String newPrice = tableView2.getSelectionModel().getSelectedItem();
		TripleObject msg = new TripleObject("Approve request " + newPrice, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@FXML
	void gotoDeny(ActionEvent event) throws Exception {
		String newPrice = tableView2.getSelectionModel().getSelectedItem();
		TripleObject msg = new TripleObject("Deny request " + newPrice, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onGotUpdatedChart(GotUpdatedChartMoviesEvent event) {
		TripleObject msg = new TripleObject("Show PRC prices", null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onGotUpdateDetails(GotPRCPricesEvent event) {
		Platform.runLater(() -> {
			final ObservableList<String> PRCMovies = FXCollections.observableArrayList(SimpleClient.PRCMovies);
			tableView.setEditable(true);
			tableView.setSelectionModel(null);
			movieName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			tableView.getColumns().setAll(movieName);
			tableView.setItems(PRCMovies);

			final ObservableList<String> PRCPrices = FXCollections.observableArrayList(SimpleClient.PRCPrices);
			tableView2.setEditable(true);
			newPrice.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			tableView2.getColumns().setAll(newPrice);
			tableView2.setItems(PRCPrices);
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		TripleObject msg = new TripleObject("Show PRC prices", null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
