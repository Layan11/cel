package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Buy_Ticket implements Initializable {

	@FXML
	private TextField Id_check;

	@FXML
	private TextField way_to_pay;

	@FXML
	private Button Buy_btn;

	@FXML
	private TextField last_name;
	@FXML

	private TextField first_name;

	@FXML
	private Label Laber1;

	@FXML
	private Label label2;
	@FXML
	private Button back;
	@FXML
	private TextField msglab;

	@FXML
	private Label label3;

	@FXML
	void back_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("choose_type_to_browse");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void buy_btn(ActionEvent event) throws IOException {
		String time = Screening_TimesController.selectedScreeningTime;
		String movie = browse_moviesController.selectedMovie.getEngName();
		String date = Screening_TimesController.selectedScreeningDate;

		Ticket mytestticket = new Ticket("Test buy", "test hall", 3, 5);
		DoubleObject msg = new DoubleObject("1Add new Ticket ", null, mytestticket, null);
		SimpleClient.getClient().sendToServer(msg);
		msglab.setVisible(true);
	}

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {
				msglab.setText(msg.getMsg());
				if (false) {
					App.setRoot("idk");
				}
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
