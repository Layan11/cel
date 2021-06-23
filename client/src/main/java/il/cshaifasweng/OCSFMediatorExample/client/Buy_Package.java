package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Package;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Buy_Package implements Initializable {

	@FXML
	private Button back_btn;

	@FXML
	private Button buy_bnt;
	@FXML
	private TextField msglab;
    @FXML
    private TextField paymentM;

    @FXML
    private Label paymentlabel;

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
	void buy_btn(ActionEvent event) throws IOException {
		String PaymentMethod = paymentM.getText();
		popup4Controller.popped4 = 0;
		Package myPackge = new Package(20);
		myPackge.setPaymen_method(PaymentMethod);
		DoubleObject msg = new DoubleObject("Add New Package " + loginController.currentUser, null, null, myPackge);
		SimpleClient.getClient().sendToServer(msg);
		msglab.setVisible(true);
	}

	@Subscribe
	public void onNumOfTickets(AlreadyHavePackageEvent event) {
		if (popup4Controller.popped4 == 0) {
			popup4Controller.popped4 = 1;
			Platform.runLater(() -> {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup4.fxml"));
					Parent Root4 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(Root4));
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			});
		} else {
			return;
		}
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
