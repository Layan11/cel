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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Update_Package implements Initializable {

	@FXML
	private Button update_num;
	@FXML
	private TextField pack_id;
	@FXML
	private Button back;
	@FXML
	private TextField msg_lab;
	@FXML
	private Button show;

	@FXML
	void showNum(ActionEvent event) {
		TripleObject msg = new TripleObject("Show package " + loginController.currentUser, null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onNumOfTickets(GotNumOfPacTicsEvent event) {
		Platform.runLater(() -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup3.fxml"));
				Parent Root2 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(Root2));
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
	}

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
	void update_num_btn(ActionEvent event) throws IOException {
		msg_lab.setVisible(false);
		int x = Integer.parseInt(pack_id.getText());
		String mystr = "Lesser Pack " + x;
		TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {
				msg_lab.setVisible(true);
				msg_lab.setText(msg.getMsg());
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
