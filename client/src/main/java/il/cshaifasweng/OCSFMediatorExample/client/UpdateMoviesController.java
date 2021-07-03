/**
 * Sample Skeleton for 'trying.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.layout.Pane;

public class UpdateMoviesController implements Initializable {

	@FXML
	private TextArea times;
	@FXML
	private TextArea dates;
	@FXML
	private Label timeLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label noteLabel;
	@FXML
	private Button updateScreening;
	@FXML
	private Button addScreening;
	@FXML
    private Pane thank_pane;

    @FXML
    private TextField text_pane;

    @FXML
    private Button back_pabe;

    @FXML
    private Button add_pane;

    @FXML
    private Button edit_pane;
    @FXML
    private Pane error_pane;

    @FXML
    private TextField text_error;

    @FXML
    private Button error_back;
    
    @FXML
    void goback(ActionEvent event) {
    	error_pane.setVisible(false);

    }
    
    @FXML
    void edit_samemovie(ActionEvent event) {
    	thank_pane.setVisible(false);
    }
   

    @FXML
    void add_newtime(ActionEvent event) {
    	thank_pane.setVisible(false);

    }

	@FXML
	void back(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject(
				"Show Screening Times " + browse_moviesController.selectedMovie.getEngName(), null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@Subscribe
	public void onData4(GotScreeningTimesEvent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Screening_Times");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@FXML
	void gotoupdate(ActionEvent event) throws Exception {
		if(times.getText()=="" &&dates.getText()=="" ) {
			text_error.setText("Please enter new screnning time");
			error_pane.setVisible(true);
			
		}
		else if(dates.getText()!="" && !(dates.getText().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")))
		{
			text_error.setText("Please enter a valid date");
			error_pane.setVisible(true);
		}
		
		else if(times.getText()!="" && !(times.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")))
		{
			text_error.setText("Please enter a valid time");
			error_pane.setVisible(true);
		}
		/*else if(times.getText()!="" && !(times.getText().matches("([0-2]{1})([0-4]{1}):([0-9]{2})")))
		{
			text_error.setText("Please enter a valid time2");
			error_pane.setVisible(true);
		}
		*/
		else {
		List<MovieTimes> hlpr3 = new ArrayList<MovieTimes>();
		MovieTimes tmp3 = new MovieTimes();
		List<String> timesList3 = new ArrayList<String>();
		timesList3.add(Screening_TimesController.selectedScreeningTime); // old time is in place 0 new time is in place
																			// 1
		timesList3.add(times.getText());
		List<String> datesList3 = new ArrayList<String>();
		datesList3.add(dates.getText());
		tmp3.SetMovieTimes(timesList3);
		tmp3.setDate(datesList3);
		hlpr3.add(tmp3);
		TripleObject msg = new TripleObject(
				"Update Screening Time " + browse_moviesController.selectedMovie.getEngName(), null, hlpr3);
		SimpleClient.getClient().sendToServer(msg);
		times.clear();
		dates.clear();
		text_pane.setText("Thank you for updating the time");
		thank_pane.setVisible(true);
		add_pane.setVisible(false);}

	}
	
	private boolean checkDate(List<String> MylistDate, List<String> MylistTime)
	{
		if (MylistDate.size() != MylistTime.size())
		{
			text_error.setText("The number of times have equals the number of dates");
			error_pane.setVisible(true);
			return false;
		}
		for(int i=0;i<MylistDate.size();i++){
			String dates=MylistDate.get(i);
			String times=MylistTime.get(i);
			
		if(dates== "" || times=="")  {
			text_error.setText("Please enter new screnning time and date");
			error_pane.setVisible(true);
			return false;
			
		}
		else if( !(dates.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")))
		{
			text_error.setText("Please enter a valid date");
			error_pane.setVisible(true);
			return false;
		}
		
		else if(!(times.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")))
		{
			text_error.setText("Please enter a valid time");
			error_pane.setVisible(true);
			return false;
		}
		/*else if(!(times.matches("([0-2]{1})([0-4]{1}):([0-9]{2})")))
		{
			text_error.setText("Please enter a valid time");
			error_pane.setVisible(true);
			return false;
		}*/}
		return true;
	}

	@FXML
	void gotoadd(ActionEvent event) throws IOException {
		/*if(dates.getText()=="" || times.getText()=="")  {
			text_error.setText("Please enter new screnning time and date");
			error_pane.setVisible(true);
			
		}
		else if( !(dates.getText().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")))
		{
			text_error.setText("Please enter a valid date");
			error_pane.setVisible(true);
		}
		
		else if(!(times.getText().matches("([0-1]{1})([0-9]{1}):([0-9]{2})")))
		{
			text_error.setText("Please enter a valid time");
			error_pane.setVisible(true);
		}
		else if(!(times.getText().matches("([0-2]{1})([0-4]{1}):([0-9]{2})")))
		{
			text_error.setText("Please enter a valid time");
			error_pane.setVisible(true);
		}
		*/
		
		//else {
		List<Movie> list = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie = browse_moviesController.selectedMovie;
		list.add(movie);
		List<MovieTimes> hlpr = new ArrayList<MovieTimes>();
		MovieTimes tmp = new MovieTimes();
		List<String> timesList = Arrays.asList(times.getText().split("\\s*,\\s*"));
		tmp.SetMovieTimes(timesList);
		List<String> datesList = Arrays.asList(dates.getText().split("\\s*,\\s*"));
		if(checkDate(datesList,timesList)==true) {
		tmp.setDate(datesList);
		hlpr.add(tmp);
		TripleObject msg = new TripleObject("Add Screening Time", list, hlpr);
		SimpleClient.getClient().sendToServer(msg);
		times.clear();
		dates.clear();
		text_pane.setText("Thank you for adding new screnning time");
		thank_pane.setVisible(true);
		edit_pane.setVisible(false);}
	}
	






	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (Screening_TimesController.action.equals("add")) {
			updateScreening.setVisible(false);
		}
		if (Screening_TimesController.action.equals("edit")) {
			addScreening.setVisible(false);
			noteLabel.setText("");
			timeLabel.setText("New Screening Time");
			dateLabel.setText("New Screening Date");
		}
	}
}
