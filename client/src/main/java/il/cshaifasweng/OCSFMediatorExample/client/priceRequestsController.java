package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class priceRequestsController implements Initializable {

	@FXML // fx:id="tableView"
	private TableView<?> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="movieName"
	private TableColumn<?, ?> movieName; // Value injected by FXMLLoader

	@FXML // fx:id="newPrice"
	private TableColumn<?, ?> newPrice; // Value injected by FXMLLoader

	@Subscribe
	public void onGotUpdateDetails(GotPRCPricesEvent event) {
		Platform.runLater(() -> {
			System.out.println("ZA MOVIES: " + SimpleClient.PRCMovies);
			System.out.println("ZA prices: " + SimpleClient.PRCPrices);
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
