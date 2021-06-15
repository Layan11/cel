/**
 * Sample Skeleton for 'choose_type_to_browse.fxml' Controller Class
 */

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

public class choose_type_to_browseController implements Initializable {

	@FXML
	private Button Now_In_Branches;

	@FXML
	private Button Coming_Soon;

	@FXML
	private Button Watch_At_Home;

	@FXML
	private Button Back;

	@FXML
	private Button hybrid;

	@FXML
	private Button return_tick;

	@FXML
	private Button return_link;

	@FXML
	private Button buy_link;

	@FXML
	private Button buy_tic;

	@FXML
	private Button pack_btn;

	@FXML
	private Button lesser_pack;

	public static String browseType = "";

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
	void buy_tic_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Buy_Ticket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void buy_link_btn(ActionEvent event) throws IOException {
		Platform.runLater(() -> {
			try {
				App.setRoot("Buy_Link");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void goback(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotocomingsoon(ActionEvent event) throws Exception {
		browseType = "Coming Soon";
		TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData11(GotComingSoonEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Coming_soon");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotomoviesinbranches(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData(GotMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotowatchathome(ActionEvent event) throws Exception {
		browseType = "Watch at Home";
		TripleObject msg = new TripleObject("Watch At Home", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData112(GotWatchAtHomeEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Watch_At_Home");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoHybrid(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Get hybrid movies", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onHybridMovies(GotHybridMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("ShowHybrid");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (loginController.loginRole == -1 || loginController.loginRole == 0 || loginController.loginRole == 2) {
			hybrid.setVisible(false);
		}
	}
}
