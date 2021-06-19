package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

	}
}
