package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Buy_LinkController implements Initializable {
	public static List<link> alllinksbuy;

	@FXML
	private TextField Method_pay;

	@FXML
	private Button purch_btn;
	@FXML
	private Button back;
	@FXML
	private TextField msglab;

	@FXML
	void backbtn(ActionEvent event) {
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			// Parent root;
			try {
				App.setRoot("choose_type_to_browse");
//				Scene scene = new Scene(root);
//				primaryStage.setScene(scene);
//				primaryStage.setTitle("Browse movies list");
//				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	@FXML
	void buy_btn(ActionEvent event) throws IOException {

		// Movie K = browse_moviesController.selectedMovie;
		// String time= Screening_TimesController.selected_start_time;
		// String end_time = Screening_TimesController.selected_end_time;
		// link addlink =new link(K,10,20);

		// DoubleObject msg = new DoubleObject("Add new link",addlink, null);
		// SimpleClient.getClient().sendToServer(msg);
		if (Method_pay.getText() != "") {
			Movie newMovie;
			newMovie = Watch_At_HomeController.selected_watch_at_home_Movie;
			String name = newMovie.getEngName();
			;
			LocalDateTime date3 = LocalDateTime.now();
			LocalDateTime date4 = LocalDateTime.now();
			date3 = date3.plusDays(1);
//			date3 = date3.plusHours(1);
//			date3 = date3.plusMinutes(3);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			date4 = date4.plusDays(2);
			String user = loginController.currentUser;
			String way_ = Method_pay.getText();
			link mytestlink = new link(name, date3, date4, user, way_);
			DoubleObject msg = new DoubleObject("1Add new link ", mytestlink, null, null);
			SimpleClient.getClient().sendToServer(msg);
			msglab.setVisible(true);
			String formatDateTime = date3.format(format);
			String formatDateTime2 = date4.format(format);
			String msg_to_user = "The movie you choose is: " + name + "\n The link  will start working on:\n "
					+ formatDateTime + "\n and stop at: \n" + formatDateTime2;
			System.out.println(msg_to_user);
			messages msgtouser = new messages("server", msg_to_user, loginController.currentUser);
			TripleObject mymsg = new TripleObject("Send msg to user", msgtouser);
			SimpleClient.getClient().sendToServer(mymsg);
		}

	}

	@Subscribe
	public void onData(TripleObject msg) {
		System.out.println("IN onData");

		Platform.runLater(() -> {
//   			Window window = Browse_movie_list.getScene().getWindow();
//   			if (window instanceof Stage) {
//   				((Stage) window).close();
//   			}
//   			Stage primaryStage = new Stage();
			// Parent root;
			try {
				msglab.setText(msg.getMsg());
				if (false) {
					App.setRoot("idk");
				}
//   				Scene scene = new Scene(root);
//   				primaryStage.setScene(scene);
//   				primaryStage.setTitle("Browse movies list");
//   				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
	}

}
