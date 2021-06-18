package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ShowReportsController implements Initializable {

	@FXML
	private TextField TicketsInHaifatxt;

	@FXML
	private TextField returnedTicketsInHaifatxt;

	@FXML
	private TextField Links;

	@FXML
	private TextField TicketsInShefaAmrtxt;

	@FXML
	private TextField returnedTicketsInShefaAmrtxt;

	@FXML
	private TextField Packages;

	@FXML
	private Button back;

	@FXML
	void gotoback(ActionEvent event) throws Exception {
		App.setRoot("menu");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginController.loginRole == 0) {// show everything
//			Reports report = SimpleClient.report;
//			TicketsInHaifatxt.setText(Integer.toString(report.getTicketsInHaifa()));
//			returnedTicketsInHaifatxt.setText(Integer.toString(report.getReturnedTicketsInHaifa()));
//			Links.setText(Integer.toString(report.getLinks()));
//			TicketsInShefaAmrtxt.setText(Integer.toString(report.getTicketsInShefaAmr()));
//			returnedTicketsInShefaAmrtxt.setText(Integer.toString(report.getReturnedTicketsInShefaAmr()));
//			Packages.setText(Integer.toString(report.getPackages()));
		} else if (loginController.currentUser.equals("Ursula")) {// show haifa reports

		} else if (loginController.currentUser.equals("Consuela")) {// show shefaAmr reports

		}

	}

}
