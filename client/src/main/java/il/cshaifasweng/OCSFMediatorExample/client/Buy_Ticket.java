package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Buy_Ticket implements Initializable {
	int counter = 0;
	int counter2 = 0;
	static public String ticket_id="";  

	@FXML
	private TextField way_to_pay;
	@FXML
	private Button Buy_btn;
	@FXML
	private TextField Label1;
	@FXML
	private Button back;
	@FXML
	private TextField msglab;
	@FXML
	private Button restrictionBack;
	@FXML
	private Label fullHallLabel;
    @FXML
    private ImageView cinematicket;

	@FXML
	void back_btn(ActionEvent event) throws Exception {
		cinematicket.setVisible(false);
		if (counter2 == 0) {
			System.out.println(show_MapChairController.id + Screening_TimesController.selectedScreeningTime
					+ show_MapChairController.num_chair1);
			TripleObject msg = new TripleObject("remove mapchair with new seat",browse_moviesController.selectedMovie.getId(),
					Screening_TimesController.selectedScreeningTime, show_MapChairController.num_chair1);

			SimpleClient.getClient().sendToServer(msg);
		}
		counter2 = 0;
		counter = 0;
		try {
			TripleObject msg = new TripleObject("get my map chair", browse_moviesController.selectedMovie.getId(),
					Screening_TimesController.selectedScreeningTime);
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void gotoRestrictionBack(ActionEvent event) throws Exception {
		cinematicket.setVisible(false);
		TripleObject msg = new TripleObject(
				"Show Screening Times " + browse_moviesController.selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData1(GotScreeningTimesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("Screening_Times");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onGotMapChair(gotMapChairevent event) {
		Platform.runLater(() -> {
//			try {
//				App.setRoot("show_MapChair");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		});

	}

	@FXML
	void buy_btn(ActionEvent event) throws IOException {
		counter++;
		counter2++;
		if (counter < 2) {

			Movie newMovie;
			newMovie = browse_moviesController.selectedMovie;
			String time = Screening_TimesController.selectedScreeningTime;
			String movie = newMovie.getEngName();

			String seat = show_MapChairController.num_chair1;
			String hall = newMovie.getBranch();
			String user = loginController.currentUser;

			Ticket mytestticket = new Ticket(movie, hall, time, seat, user, Label1.getText(),
					show_MapChairController.id);
			DoubleObject msg = new DoubleObject("1Add new Ticket ", null, mytestticket, null);
			SimpleClient.getClient().sendToServer(msg);
			msglab.setVisible(true);
			back.setVisible(true);
			counter2++;
		}
		if (counter >= 2) {
			msglab.setText("You already Bought this ticket");
			msglab.setVisible(true);
		}
				
		
	/*	Platform.runLater(() -> {
			try {
				
				App.setRoot("thank_for_purchase");
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});*/
	}

	@Subscribe
	public void onFullHall(FullHalltEvent event) {
		Platform.runLater(() -> {
			fullHallLabel.setText("Sorry the hall is full");
		});
	}
	
	@Subscribe
	public void chooseanotherseat(busyseat event) {
		Platform.runLater(() -> {
			try {
				
				App.setRoot("choosenewseat");
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
	}

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {cinematicket.setVisible(true);
				msglab.setText(msg.getMsg());
				ticket_id=msg.getMsg()	;			
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
		if (SimpleClient.restrictionAns.equals("No")) {
			restrictionBack.setVisible(false);
		} else if (SimpleClient.restrictionAns.equals("Yes")) {
			back.setVisible(false);
		}
		fullHallLabel.setText("");
	}
}
