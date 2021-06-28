package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class choosenewseatController {
	@FXML
	private Text stamtext;

	@FXML
	private Button btn_choosenewseat;

	@FXML
	void anotherpersonchoose(ActionEvent event) {

		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("show_MapChair");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
