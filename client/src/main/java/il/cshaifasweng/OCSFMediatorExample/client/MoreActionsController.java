package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import java.util.Date;  
import java.text.SimpleDateFormat;  
import java.text.ParseException;  

public class MoreActionsController implements Initializable {
	@FXML
	private Button addNewToWatchAtHome;
	@FXML
	private Button deleteMovie;
	@FXML
	private TextField movieName;
	@FXML
	private Button updatePrice;
	@FXML
	private Button addMovie;
	@FXML
	private Button addToComingSoon;
	@FXML
	private Button addOldToWatchAtHome;
	@FXML
	private TextField engName;
	@FXML
	private Label engNameLabel;
	@FXML
	private Label hebNameLabel;
	@FXML
	private TextField hebName;
	@FXML
	private TextField arbName;
	@FXML
	private TextField price;
	@FXML
	private Label priceLabel;
	@FXML
	private Label producerLabel;
	@FXML
	private Label actorsLabel;
	@FXML
	private Label lenLabel;
	@FXML
	private Label sumLabel;
	@FXML
	private TextField producer;
	@FXML
	private TextField len;
	@FXML
	private TextArea summary;
	@FXML
	private TextArea screeningTimes;
	@FXML
	private Label timesLabel;
	@FXML
	private Label branchLabel;
	@FXML
	private TextField branch;
	@FXML
	private TextField image;
	@FXML
	private Label imageLabel;
	@FXML
	private TextArea actors;
	@FXML
	private Label invalidAddLabel;
	@FXML
	private Label invalidMovie;
	@FXML
	private TextField oldLink;
	@FXML
	private TextField newPrice;
	@FXML
	private TextArea dates;
	@FXML
	private Label datesLabel;
	@FXML
	private Button Menu;
	
	

	@FXML
	void backtomenu(ActionEvent event) throws Exception {
		App.setRoot("menu");

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
	void gotoAddMovie(ActionEvent event) throws ParseException {
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
			//String engnameOfMovie=engNameLabel.getText();
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
			//elin
			 ///ana 3tbrt enno awal date fat hu awal date bn3rd feyu elfilm
			
			
			
			
			/*LocalDate minDate=LocalDate.of(2080, 1, 13); 
			for(int i=0 ; i <Dates.size();i++)
			{
			
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				  String date = Dates.get(i);
				  
				  System.out.println("date before convert" + date);
				  //convert String to LocalDate
				  LocalDate localDate = LocalDate.parse(date, formatter); 
				  
				  System.out.println("date after convert"+ localDate);
				  int before= minDate.compareTo(localDate);
				  if (before>0)  //that mean that i found erliest date
					  minDate=localDate;
				  
			}*/
			//String minimumdate=minDate.toString();
			System.out.println("the date that i sent" + Dates.get(0));
			String From=loginController.currentUser;
			String context="Great News! today is the premier of "+ engName.getText()+"We are waiting to see you hbebe/hbebty";
			Movie sendedmsg = new Movie();
			sendedmsg.setEngName(Dates.get(0));  
			sendedmsg.setHebName(context);    //the answer
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
			///end elin
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
			TripleObject msg1 = new TripleObject("Add new movie", movietoadd, null);
			try {
				SimpleClient.getClient().sendToServer(msg1);
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
				|| price.getText().equals("") || image.getText().equals("")) {
			invalidAddLabel.setText("You need to fill the mandatory fields");
			engNameLabel.setText("*");
			hebNameLabel.setText("*");
			sumLabel.setText("*");
			producerLabel.setText("*");
			actorsLabel.setText("*");
			lenLabel.setText("*");
			priceLabel.setText("*");
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
		popupController.popped11 = 0;
		invalidMovie.setText("");
		String name = movieName.getText();
		String price = newPrice.getText();
		List<String> tmp = new ArrayList<String>();
		tmp.add(price);
		TripleObject msg = new TripleObject("Update price " + name, null, null);
		msg.setList(tmp);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onGotMoviee(GotMovieToUpdatePriceEvent event) {
		if (popupController.popped11 == 0) {
			popupController.popped11 = 1;
			Platform.runLater(() -> {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
				Parent Root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(Root1));
				stage.setTitle("Request sent");
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
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
