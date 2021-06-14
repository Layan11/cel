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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoreActionsController implements Initializable {
	@FXML
	private Button addNewToWatchAtHome;

	@FXML // fx:id="deleteMovie"
	private Button deleteMovie; // Value injected by FXMLLoader

	@FXML // fx:id="movieName"
	private TextField movieName; // Value injected by FXMLLoader

	@FXML // fx:id="updatePrice"
	private Button updatePrice; // Value injected by FXMLLoader

	@FXML // fx:id="addMovie"
	private Button addMovie; // Value injected by FXMLLoader

	@FXML // fx:id="addToComingSoon"
	private Button addToComingSoon; // Value injected by FXMLLoader

	@FXML // fx:id="addOldToWatchAtHome"
	private Button addOldToWatchAtHome; // Value injected by FXMLLoader

	@FXML // fx:id="engName"
	private TextField engName; // Value injected by FXMLLoader

	@FXML // fx:id="engNameLabel"
	private Label engNameLabel; // Value injected by FXMLLoader

	@FXML // fx:id="hebNameLabel"
	private Label hebNameLabel; // Value injected by FXMLLoader

	@FXML // fx:id="hebName"
	private TextField hebName; // Value injected by FXMLLoader

	@FXML // fx:id="arbName"
	private TextField arbName; // Value injected by FXMLLoader

	@FXML // fx:id="price"
	private TextField price; // Value injected by FXMLLoader

	@FXML // fx:id="priceLabel"
	private Label priceLabel; // Value injected by FXMLLoader

	@FXML // fx:id="producerLabel"
	private Label producerLabel; // Value injected by FXMLLoader

	@FXML // fx:id="actorsLabel"
	private Label actorsLabel; // Value injected by FXMLLoader

	@FXML // fx:id="lenLabel"
	private Label lenLabel; // Value injected by FXMLLoader

	@FXML // fx:id="sumLabel"
	private Label sumLabel; // Value injected by FXMLLoader

	@FXML // fx:id="producer"
	private TextField producer; // Value injected by FXMLLoader

	@FXML // fx:id="len"
	private TextField len; // Value injected by FXMLLoader

	@FXML // fx:id="summary"
	private TextArea summary; // Value injected by FXMLLoader

	@FXML // fx:id="screeningTimes"
	private TextArea screeningTimes; // Value injected by FXMLLoader

	@FXML // fx:id="timesLabel"
	private Label timesLabel; // Value injected by FXMLLoader

	@FXML // fx:id="branchLabel"
	private Label branchLabel; // Value injected by FXMLLoader

	@FXML // fx:id="branch"
	private TextField branch; // Value injected by FXMLLoader

	@FXML // fx:id="image"
	private TextField image; // Value injected by FXMLLoader

	@FXML // fx:id="imageLabel"
	private Label imageLabel; // Value injected by FXMLLoader

	@FXML // fx:id="actors"
	private TextArea actors; // Value injected by FXMLLoader

	@FXML // fx:id="linkLabel"
	private Label linkLabel; // Value injected by FXMLLoader

	@FXML // fx:id="link"
	private TextField link; // Value injected by FXMLLoader

	@FXML // fx:id="invalidAddLabel"
	private Label invalidAddLabel; // Value injected by FXMLLoader
	@FXML // fx:id="invalidMovie"
	private Label invalidMovie; // Value injected by FXMLLoader
	@FXML // fx:id="newPrice"
	private TextField newPrice; // Value injected by FXMLLoader
	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader
	@FXML // fx:id="dates"
	private TextArea dates; // Value injected by FXMLLoader
	@FXML // fx:id="datesLabel"
	private Label datesLabel; // Value injected by FXMLLoader
	@FXML
	private TextField oldLink;
	
    @FXML // fx:id="Menu"
    private Button Menu; // Value injected by FXMLLoader

    @FXML
    void backtomenu(ActionEvent event) throws Exception {
    	App.setRoot("menu");

    }

	@FXML
	void goBack(ActionEvent event) throws Exception {
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
	void gotoAddMovie(ActionEvent event) {
		invalidMovie.setText("");
		if (engName.getText().equals("") || hebName.getText().equals("") || summary.getText().equals("")
				|| producer.getText().equals("") || actors.getText().equals("") || len.getText().equals("")
				|| price.getText().equals("") || screeningTimes.getText().equals("") || branch.getText().equals("")
				|| image.getText().equals("") || dates.getText().equals("")) {
			invalidAddLabel.setText("You need to fill the mandatory fields");
			engNameLabel.setText("*");
			hebNameLabel.setText("*");
			priceLabel.setText("*");
			producerLabel.setText("*");
			actorsLabel.setText("*");
			lenLabel.setText("*");
			sumLabel.setText("*");
			timesLabel.setText("*");
			branchLabel.setText("*");
			imageLabel.setText("*");
			datesLabel.setText("*");
		} else {
			invalidAddLabel.setText("");
			engNameLabel.setText("");
			hebNameLabel.setText("");
			priceLabel.setText("");
			producerLabel.setText("");
			actorsLabel.setText("");
			lenLabel.setText("");
			sumLabel.setText("");
			timesLabel.setText("");
			branchLabel.setText("");
			imageLabel.setText("");
			datesLabel.setText("");
			Movie movie = new Movie();
			movie.setEngName(engName.getText());
			List<String> actorsList = Arrays.asList(actors.getText().split("\\s*,\\s*"));
			movie.setActors(actorsList);
			movie.setLength(Integer.parseInt(len.getText()));
			movie.setHebName(hebName.getText());
			movie.setSummary(summary.getText());
			movie.setProducer(producer.getText());
			movie.setPrice(Integer.parseInt(price.getText()));
			MovieTimes mt = new MovieTimes();
			List<String> Times = new ArrayList<String>();
			List<String> Dates = new ArrayList<String>();
			Times = Arrays.asList(screeningTimes.getText().split("\\s*,\\s*"));
			Dates = Arrays.asList(dates.getText().split("\\s*,\\s*"));
			mt.SetMovieTimes(Times);
			mt.setDate(Dates);
			movie.setMovieTimes(mt);
			movie.setBranch(branch.getText());
			movie.setArbName(arbName.getText());
			movie.setImage(image.getText());
			movie.setType(0);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
								// home
			List<Movie> movietoadd = new ArrayList<Movie>();
			movietoadd.add(movie);
			TripleObject msg = new TripleObject("Add new movie", movietoadd, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void gotoAddNewToWatchAtHome(ActionEvent event) {
		invalidMovie.setText("");
		if (engName.getText().equals("") || hebName.getText().equals("") || summary.getText().equals("")
				|| producer.getText().equals("") || actors.getText().equals("") || len.getText().equals("")
				|| price.getText().equals("") || image.getText().equals("") || link.getText().equals("")) {
			invalidAddLabel.setText("You need to fill the mandatory fields");
			engNameLabel.setText("*");
			hebNameLabel.setText("*");
			sumLabel.setText("*");
			producerLabel.setText("*");
			actorsLabel.setText("*");
			lenLabel.setText("*");
			priceLabel.setText("*");
			imageLabel.setText("*");
			linkLabel.setText("*");
		} else {
			invalidAddLabel.setText("");
			engNameLabel.setText("");
			hebNameLabel.setText("");
			priceLabel.setText("");
			producerLabel.setText("");
			actorsLabel.setText("");
			lenLabel.setText("");
			sumLabel.setText("");
			linkLabel.setText("");
			imageLabel.setText("");
			Movie movie = new Movie();
			movie.setEngName(engName.getText());
			List<String> actorsList = Arrays.asList(actors.getText().split("\\s*,\\s*"));
			movie.setActors(actorsList);
			movie.setLength(Integer.parseInt(len.getText()));
			movie.setHebName(hebName.getText());
			movie.setSummary(summary.getText());
			movie.setProducer(producer.getText());
			movie.setPrice(Integer.parseInt(price.getText()));
			movie.setLink(len.getText());
			movie.setArbName(arbName.getText());
			movie.setImage(image.getText());
			movie.setType(2);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
								// home
			List<Movie> movietoadd = new ArrayList<Movie>();
			movietoadd.add(movie);
			TripleObject msg = new TripleObject("Add new movie", movietoadd, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void gotoAddOldToWatchAtHome(ActionEvent event) {
		invalidMovie.setText("");
		String wantedMovie = movieName.getText();
		TripleObject msg = new TripleObject("Add exsisting movie to watch at home " + wantedMovie, null, null);
		List<String> link = new ArrayList<String>();
		link.add(oldLink.getText());
		msg.setList(link);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		movieName.setText("");
		oldLink.setText("");
	}

	@FXML
	void gotoAddToComingSoon(ActionEvent event) {
		invalidMovie.setText("");
		if (engName.getText().equals("") || hebName.getText().equals("") || summary.getText().equals("")
				|| producer.getText().equals("") || actors.getText().equals("") || len.getText().equals("")
				|| price.getText().equals("") || image.getText().equals("")) {
			invalidAddLabel.setText("You need to fill the mandatory fields");
			engNameLabel.setText("*");
			hebNameLabel.setText("*");
			priceLabel.setText("*");
			producerLabel.setText("*");
			actorsLabel.setText("*");
			lenLabel.setText("*");
			sumLabel.setText("*");
			imageLabel.setText("*");

		} else {
			invalidAddLabel.setText("");
			engNameLabel.setText("");
			hebNameLabel.setText("");
			priceLabel.setText("");
			producerLabel.setText("");
			actorsLabel.setText("");
			lenLabel.setText("");
			sumLabel.setText("");
			imageLabel.setText("");
			Movie movie = new Movie();
			movie.setEngName(engName.getText());
			List<String> actorsList = Arrays.asList(actors.getText().split("\\s*,\\s*"));
			movie.setActors(actorsList);
			movie.setLength(Integer.parseInt(len.getText()));
			movie.setHebName(hebName.getText());
			movie.setSummary(summary.getText());
			movie.setProducer(producer.getText());
			movie.setPrice(Integer.parseInt(price.getText()));
			movie.setArbName(arbName.getText());
			movie.setImage(image.getText());
			movie.setType(1);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
								// home
			List<Movie> movietoadd = new ArrayList<Movie>();
			movietoadd.add(movie);
			TripleObject msg = new TripleObject("Add new movie", movietoadd, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void gotoDeleteMovie(ActionEvent event) {
		invalidMovie.setText("");
		String wantedMovie = movieName.getText();
		TripleObject msg = new TripleObject("Delete movie " + wantedMovie, null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		movieName.setText("");
	}

	@FXML
	void gotoUpdatePrice(ActionEvent event) throws Exception {
		invalidMovie.setText("");
		String name = movieName.getText();
		String price = newPrice.getText();
		List<String> tmp = new ArrayList<String>();
		tmp.add(price);
		TripleObject msg = new TripleObject("Update price " + name, null, null);
		msg.setList(tmp);
		SimpleClient.getClient().sendToServer(msg);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
			Parent Root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(Root1));
			stage.show();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Subscribe
	public void onNoSuchMovie(NoSuchMovieEvent event) {
		Platform.runLater(() -> {
			invalidMovie.setText("Sorry, no such movie");
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}
}
