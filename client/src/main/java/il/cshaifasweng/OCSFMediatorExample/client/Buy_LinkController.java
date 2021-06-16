package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Buy_LinkController implements Initializable {
	@FXML
	private TextField user_id;

	@FXML
	private TextField Method_pay;

	@FXML
	private TextField First_Name;

	@FXML
	private TextField Last_Name;

	@FXML
	private Label label;

	@FXML
	private Button purch_btn;
	@FXML
	private Button back;
	@FXML
	private TextField msglab;

	public static List<link> alllinksbuy;

	@FXML
	void backbtn(ActionEvent event) throws Exception {
		choose_type_to_browseController.browseType = "Watch at Home";
		TripleObject msg = new TripleObject("Watch At Home", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onGotWatchAtHome(GotWatchAtHomeEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Watch_At_Home");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void buy_btn(ActionEvent event) throws IOException {
		String movie = Watch_At_HomeController.selected_watch_at_home_Movie.getEngName();

		link mytestlink = new link(2, "K412312ungFuPanda", 5, 7);
		DoubleObject msg = new DoubleObject("1Add new link ", mytestlink, null, null);
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
