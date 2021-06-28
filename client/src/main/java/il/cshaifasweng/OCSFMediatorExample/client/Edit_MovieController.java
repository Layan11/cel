/**
 * Sample Skeleton for 'Edit_Coming_Soon_Movie.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Edit_MovieController implements Initializable {

	@FXML
	private Button Back;
	@FXML
	private TextField branchtxt;
	@FXML
	private TextField halltxt;
	@FXML
	private Button Add;
	@FXML
	private Label namelabel;
	@FXML
	private TextArea times;
	@FXML
	private TextArea dates;

	@FXML
	void Addmovie(ActionEvent event) throws Exception {
		if (choose_type_to_browseController.browseType.equals("Coming Soon")) {
			int selectedID = Coming_soonController.selected_coming_soon_Movie.getId();
			Movie newMovie = new Movie();
			newMovie = Coming_soonController.selected_coming_soon_Movie;
			Movie helperMovie = new Movie();
			helperMovie.setLength(selectedID);
			newMovie.setType(0);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
			// home, type=3 for watch at home& now broadcasting
			newMovie.setBranch(branchtxt.getText());
			List<String> Timeslist = Arrays.asList(times.getText().split("\\s*,\\s*"));
			List<String> Dateslist = Arrays.asList(dates.getText().split("\\s*,\\s*"));

			// elin
			// entbhu enno hun kman awal date bfwto hu 3ebara 3n abkr date
			System.out.println("the date that i sent" + Dateslist.get(0));
			String From = loginController.currentUser;
			String context = "Great News! today is the premier of the movie that you waited for so long! "
					+ Coming_soonController.selected_coming_soon_Movie.getEngName();
			Movie sendedmsg = new Movie();
			sendedmsg.setEngName(Dateslist.get(0));
			sendedmsg.setHebName(context); // the answer
			sendedmsg.setSummary(From);
			List<Movie> L = new ArrayList<Movie>();
			L.add(sendedmsg);

			TripleObject msg = new TripleObject("Add new message For movie", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			MovieTimes MTimes = new MovieTimes(Timeslist);
			MTimes.setDate(Dateslist);
			List<MovieTimes> MTimeslist = new ArrayList<MovieTimes>();
			MTimeslist.add(MTimes);
			List<Movie> ListnewMovie = new ArrayList<Movie>();
			ListnewMovie.add(newMovie);
			ListnewMovie.add(helperMovie);
			TripleObject msg1 = new TripleObject("Save new Movie " + choose_type_to_browseController.browseType,
					ListnewMovie, MTimeslist);
			SimpleClient.getClient().sendToServer(msg1);
		}
		if (choose_type_to_browseController.browseType.equals("Watch at Home")) {
			Movie newMovie = new Movie();
			newMovie = Watch_At_HomeController.selected_watch_at_home_Movie;
			newMovie.setType(3);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
			// home, type=3 for watch at home& now broadcasting
			newMovie.setBranch(branchtxt.getText());
			List<String> Timeslist = Arrays.asList(times.getText().split("\\s*,\\s*"));
			List<String> Dateslist = Arrays.asList(dates.getText().split("\\s*,\\s*"));

			// elin
			// entbhu enno hun kman awal date bfwto hu 3ebara 3n abkr date
			System.out.println("the date that i sent" + Dateslist.get(0));
			String From = loginController.currentUser;
			String context = "Great News! today " + Watch_At_HomeController.selected_watch_at_home_Movie.getEngName()
					+ " ";
			Movie sendedmsg = new Movie();
			sendedmsg.setEngName(Dateslist.get(0));
			sendedmsg.setHebName(context); // the answer
			sendedmsg.setSummary(From);
			List<Movie> L = new ArrayList<Movie>();
			L.add(sendedmsg);

			TripleObject msg = new TripleObject("Add new message For movie", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			MovieTimes MTimes = new MovieTimes(Timeslist);
			MTimes.setDate(Dateslist);
			List<MovieTimes> MTimeslist = new ArrayList<MovieTimes>();
			MTimeslist.add(MTimes);
			List<Movie> ListnewMovie = new ArrayList<Movie>();
			ListnewMovie.add(newMovie);
			TripleObject msg1 = new TripleObject("Save new Movie " + choose_type_to_browseController.browseType,
					ListnewMovie, MTimeslist);
			SimpleClient.getClient().sendToServer(msg1);
		}
		branchtxt.clear();
		halltxt.clear();
		times.clear();
		dates.clear();
	}

	@FXML
	void goBack(ActionEvent event) throws Exception {
		if (choose_type_to_browseController.browseType.equals("Coming Soon")) {
			TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
			SimpleClient.getClient().sendToServer(msg);
		}
		if (choose_type_to_browseController.browseType.equals("Watch at Home")) {
			TripleObject msg = new TripleObject("Watch At Home", null, null);
			SimpleClient.getClient().sendToServer(msg);
		}
	}

	@Subscribe
	public void onGotWatchAtHomeEvent(GotWatchAtHomeEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Watch_At_Home");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onGotComingSoonEvent(GotComingSoonEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Coming_soon");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (choose_type_to_browseController.browseType.equals("Coming Soon")) {
			namelabel.setText("Editing " + Coming_soonController.selected_coming_soon_Movie.getEngName() + " movie");
		}
		if (choose_type_to_browseController.browseType.equals("Watch at Home")) {
			namelabel
					.setText("Editing " + Watch_At_HomeController.selected_watch_at_home_Movie.getEngName() + " movie");
		}
	}
}
