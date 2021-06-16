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
import javafx.stage.Stage;

public class menuController implements Initializable {
	@FXML
	private Button browseMovies;
	@FXML
	private Button FileAComplaint;
	@FXML
	private Button UpdatePriceRequests;
	@FXML
	private Button ShowComplaints;
	@FXML
	private Button logout;
	@FXML
	private Button more_actions;
	@FXML
	private Button return_tick;
	@FXML
	private Button return_link;
	@FXML
	private Button lesser_pack;
	@FXML
	private Button pack_btn;
	@FXML
	private Button back;
	@FXML
	private Button packageStatus;

	@FXML
	void gotoPackage(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Show package " + loginController.currentUser, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onNumOfTickets(GotNumOfPacTicsEvent event) {
		Platform.runLater(() -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup2.fxml"));
				Parent Root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(Root1));
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
	}

	@FXML
	void gotoBack(ActionEvent event) throws Exception {
		App.setRoot("primary");
	}

	@FXML
	void pack_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Buy_Package");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void les_pack(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Update_Package");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void return_link(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Return_Link");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void return_tick(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Return_Ticket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotomore_actions(ActionEvent event) throws Exception {
		App.setRoot("MoreActions");

	}

	@FXML
	void gotoLogOut(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("log-out " + loginController.currentUser, null, null);
		SimpleClient.getClient().sendToServer(msg);
		App.setRoot("primary");
	}

	@FXML
	void gotobrowse_movies(ActionEvent event) throws Exception {
		Platform.runLater(() -> {
			try {
				App.setRoot("choose_type_to_browse");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void showRequests(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Show PRC movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData(GotPRCMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("priceRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);

		if (loginController.loginRole == -1) {// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS employee//
												// 3->no account
			UpdatePriceRequests.setVisible(false);
			ShowComplaints.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			back.setVisible(false);
		}
		if (loginController.loginRole == 0) {
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			back.setVisible(false);
		}
		if (loginController.loginRole == 1) {
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			UpdatePriceRequests.setVisible(false);
			back.setVisible(false);
		}
		if (loginController.loginRole == 2) {
			UpdatePriceRequests.setVisible(false);
			FileAComplaint.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			back.setVisible(false);
		}
		if (loginController.loginRole == 3) {// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS
												// employee 3->no account
			UpdatePriceRequests.setVisible(false);
			ShowComplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			return_tick.setVisible(false);
			return_link.setVisible(false);
			logout.setVisible(false);
			pack_btn.setVisible(false);
			packageStatus.setVisible(false);
		}
	}
}
