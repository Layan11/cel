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

public class buy_ticketController implements Initializable {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private Button btn_choose;
	@FXML
	private Button back;

	@FXML
	void choose_seat(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("show_MapChair");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void goBack(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject(
				"Show Screening Times " + browse_moviesController.selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData1(GotScreeningTimesEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Screening_Times");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}
}
