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
import javafx.scene.control.TextField;

public class Return_TicketController implements Initializable {

	@FXML
	private TextField tick_id;

	@FXML
	private Button Return_Ticket;

	@FXML
	private Button back_Btn;
	@FXML
	private TextField labelmsg;

	@FXML
	void back_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void return_btn(ActionEvent event) throws Exception {
		String mystr;
		int x;
		x = Integer.parseInt(tick_id.getText());
		mystr = "Delete Ticket " + x+" for user " + loginController.currentUser;
		TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);
		labelmsg.setVisible(true);

	}

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {
				labelmsg.setText(msg.getMsg());
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
