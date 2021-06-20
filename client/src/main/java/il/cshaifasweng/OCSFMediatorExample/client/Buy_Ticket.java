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

import javafx.scene.control.TextField;


public class Buy_Ticket implements Initializable {
	int counter=0;
	int counter2=0;



	@FXML
	private Button Buy_btn;

	@FXML
	private TextField Label1;
	@FXML
	private Button back;
	@FXML
	private TextField msglab;



	@FXML
	void back_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				if(counter2==0) {
			
					TripleObject msg = new TripleObject("remove mapchair with new seat",show_MapChairController.id,Screening_TimesController.timesave,show_MapChairController.num_chair1);
			    	try {
						SimpleClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				counter=0;
				App.setRoot("choose_type_to_browse");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void buy_btn(ActionEvent event) throws IOException { 
		counter++;
		if(counter<2) {
			counter2++;
		Movie newMovie;
		newMovie = browse_moviesController.selectedMovie;	
		String time = Screening_TimesController.timesave;
		String movie = newMovie.getEngName();
		
		String seat = show_MapChairController.num_chair1;
		String hall = newMovie.getBranch();
		String user=loginController.currentUser;

		Ticket mytestticket = new Ticket(movie, hall, time, seat,user,Label1.getText(),show_MapChairController.id);
		DoubleObject msg = new DoubleObject("1Add new Ticket ", null, mytestticket, null);
		SimpleClient.getClient().sendToServer(msg);
		msglab.setVisible(true);
		back.setVisible(true);
		counter2++;
		}
		if(counter>=2) {
			msglab.setText("You already Bought this ticket");
			msglab.setVisible(true);
			
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
