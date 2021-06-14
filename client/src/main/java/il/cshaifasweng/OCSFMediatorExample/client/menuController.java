package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class menuController implements Initializable {
	@FXML // fx:id="browseMovies"
	private Button browseMovies; // Value injected by FXMLLoader

	@FXML // fx:id="FileAComplaint"
	private Button FileAComplaint; // Value injected by FXMLLoader

	@FXML // fx:id="UpdatePriceRequests"
	private Button UpdatePriceRequests; // Value injected by FXMLLoader

	@FXML // fx:id="ShowComplaints"
	private Button ShowComplaints; // Value injected by FXMLLoader
	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML
	void goback(ActionEvent event) throws Exception {
		App.setRoot("primary");
	}

	@FXML
	void gotobrowse_movies(ActionEvent event) throws Exception {
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
	void showRequests(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Show PRC movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData(GotPRCMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("priceRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (loginController.loginRole == -1) {// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee//
												// 3->no account
			UpdatePriceRequests.setVisible(false);
			ShowComplaints.setVisible(false);
		}
		if (loginController.loginRole == 0) {
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
		}
		if (loginController.loginRole == 1) {
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			UpdatePriceRequests.setVisible(false);
		}
		if (loginController.loginRole == 2) {
			UpdatePriceRequests.setVisible(false);
			FileAComplaint.setVisible(false);
		}
		if (loginController.loginRole == 3) {// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee//
			// 3->no account
			UpdatePriceRequests.setVisible(false);
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
		}
	}
}
