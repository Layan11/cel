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
import javafx.scene.control.Button;

public class buy_ticketController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btn_choose;

	@FXML
	void choose_seat(ActionEvent event) {
		try {
			TripleObject msg = new TripleObject("get my map chair", browse_moviesController.selectedMovie.getId(),
					Screening_TimesController.selectedScreeningTime);
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onGotMapChair(gotMapChairevent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("show_MapChair");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void initialize() {
		// assert btn_choose != null : "fx:id=\"btn_choose\" was not injected: check
		// your FXML file 'buy_ticket.fxml'.";
		EventBus.getDefault().register(this);
	}
}
