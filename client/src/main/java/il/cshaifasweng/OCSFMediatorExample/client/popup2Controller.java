package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class popup2Controller implements Initializable {
	@FXML
	private Label label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (SimpleClient.PackageNumOfTickets == -1) {
			label.setText("You don't have a package");
		} else {
			label.setText("You have " + SimpleClient.PackageNumOfTickets + " tickets in your package");
		}
	}
}
