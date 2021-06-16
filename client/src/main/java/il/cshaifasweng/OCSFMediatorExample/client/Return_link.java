package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Return_link implements Initializable {
	public static List<link> alllinksreturn;
	@FXML
	private TextField Ret_Link_Label;

	@FXML
	private Button return_link_btn;

	@FXML
	private Button back_btn;
	@FXML
	private TextField retmsg;

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

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {
				retmsg.setText(msg.getMsg());
				if (false) {
					App.setRoot("idk");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void return_link_btn(ActionEvent event) throws Exception {
		String mystr;
		int x;

		x = Integer.parseInt(Ret_Link_Label.getText());
		mystr = "Delete link " + x;
		TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);
		retmsg.setVisible(true);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

}
