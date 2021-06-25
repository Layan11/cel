package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
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
	private Button Showthecomplaints;
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
	private Button reports;
	@FXML
	private Button showRestricted;
	@FXML
    private Button myMsgs;


	@FXML
	void gotoShowRestricted(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Show restricted days", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onRestrictedDays(GotrestrictedDaysEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("restrictedDays");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}


	@FXML
	void gotoPackage(ActionEvent event) throws Exception {
		popup2Controller.popped = 0;
		TripleObject msg = new TripleObject("Show package " + loginController.currentUser, null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onNumOfTickets(GotNumOfPacTicsEvent event) {
		if (popup2Controller.popped == 0) {
			popup2Controller.popped = 1;
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
		} else {
			return;
		}
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
	 void gotoMyMsgs(ActionEvent event) {
	    System.out.println("in gotoshowComplaint");
	    String name=loginController.currentUser;
	    Movie newMovie = new Movie();
		newMovie.setEngName(name);  //user
	    List<Movie> L = new ArrayList<Movie>();
		L.add(newMovie);
		TripleObject msg = new TripleObject("Show messages", L, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }

	 @Subscribe
		public void onGOTMsgs(gotallmessagessevent event) {
			System.out.println("in GotMsgs");
			Platform.runLater(() -> {
				try {
					App.setRoot("Dm_forUser");
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
	void gotoShowComplaint(ActionEvent event) throws Exception {
		System.out.println("in gotoshowComplaint");
		TripleObject msg = new TripleObject("Show complaints", null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	
	@Subscribe
	public void onGOTcomplaints(gotallcomplaintsevent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("showComplaint");
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
	void gotofileacomplaint(ActionEvent event) throws Exception {
		Platform.runLater(() -> {
			try {
				App.setRoot("FillingComplaint");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
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

	@FXML
	void gotoshowreports(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Show reports", null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onReports(GotReportEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("ShowReports");
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
			Showthecomplaints.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			back.setVisible(false);
			reports.setVisible(false);
		}
		if (loginController.loginRole == 0) {
			Showthecomplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			back.setVisible(false);
		}
		if (loginController.loginRole == 1) {
			Showthecomplaints.setVisible(false);
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
			reports.setVisible(false);
		}
		if (loginController.loginRole == 3) {// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> CS
												// employee 3->no account
			UpdatePriceRequests.setVisible(false);
			Showthecomplaints.setVisible(false);
			FileAComplaint.setVisible(false);
			more_actions.setVisible(false);
			lesser_pack.setVisible(false);
			return_tick.setVisible(false);
			return_link.setVisible(false);
			logout.setVisible(false);
			pack_btn.setVisible(false);
			packageStatus.setVisible(false);
			reports.setVisible(false);
			myMsgs.setVisible(false);
		}
	}
}
