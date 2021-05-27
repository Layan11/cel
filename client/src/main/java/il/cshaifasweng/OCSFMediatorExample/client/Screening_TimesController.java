/**
 * Sample Skeleton for 'NEWpg.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
//import il.cshaifasweng.OCSFMediatorExample.server.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Screening_TimesController implements Initializable {

    @FXML // fx:id="edit"
    private Button edit; // Value injected by FXMLLoader

    @FXML // fx:id="text_Screening_times"
    private TextArea text_Screening_times; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader


	
	@FXML
	void gotoback(ActionEvent event) throws IOException {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData3(GotMoviesEventfromscreening event) {
		System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("browse_movies");
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
	void gotoedit(ActionEvent event) throws IOException {
//		Window window = ((Node) (event.getSource())).getScene().getWindow();
//		if (window instanceof Stage) {
//			((Stage) window).close();
//		}
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("update_movies.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Update movies");
//		primaryStage.show();
		Platform.runLater(() -> {
		Parent root;
		try {
			App.setRoot("update_movies");
			//System.out.println("after the load line of brwose movies in primary");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});

	}

	public void getInfo() {
		MovieTimes K = browse_moviesController.selectedMovie.getMovieTimes();
	//	text_Screening_times.setText("trying");
	//	String txt = browse_moviesController.selectedMovie.getMovieTimes().getTimes().get(1);
	//	text_Screening_times.setText(txt);
	//	List<String> Klist = new ArrayList<String>(); 
	//	Klist=	K.getTimes();
     //	System.out.println( "in screening times"+ Klist);
	//	text_Screening_times.setText(Klist.get(1));
	/*	for(int i = 0; i<K.getTimes().size();i++)
		{
			text_Screening_times.setText(K.getTimes().get(i));
		}*/
		
//		ArrayList<MovieTimes> list = new ArrayList<MovieTimes>();
//		list.add(K);
//		
//		tableView.getColumns().addAll(Screening_Times);
//		Screening_Times.setCellValueFactory(new PropertyValueFactory<>("time"));
//		for(int i = 0 ; i < K.getTimes().size(); i++ )
//		{
//			tableView.getItems().add(new String(K.getTimes().get(i)));
//		}


//		final ObservableList<MovieTimes> movieTimes = FXCollections.observableArrayList(list);
//		tableView.setEditable(true);
//	   Screening_Times.setCellValueFactory(new PropertyValueFactory<MovieTimes,List<String>>("times"));
//	   tableView.getColumns().setAll(Screening_Times);
//	   tableView.setItems(movieTimes);
	/*	int Lsize = movieTimes.size();
		for( int i = 0; i < Lsize; i++)
		{
			//int Tsize = movieTimes.get(i).getTimes().size();
			for (int j = 0; j<2; j++)
			{
				String T = movieTimes.get(i).getTimes().get(i);
			Screening_Times.setText(T);
			}
		}*/
		
		//
		//

		return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//System.out.println(SimpleClient.movieTimes.get(0).getTimes());
		EventBus.getDefault().register(this);
		if(SimpleClient.movieTimes!=null)
		{
		//text_Screening_times.setText( "trying22" + SimpleClient.movieTimes.get(0).getTimes() );
		//System.out.println("TRYING!!!!!!!!!!!!!!");
		System.out.println("Printing Screening in Screenings id = "+browse_moviesController.selectedMovie.getId());
		int indx = browse_moviesController.selectedMovie.getId()-1;
		System.out.println("indx = " + indx);
		System.out.println(SimpleClient.movieTimes.get(0).getTimes());
		System.out.println("should print the id of 3 " + SimpleClient.movieTimes.get(2).getId());
		System.out.println("should print the Screenings of 2 " + SimpleClient.movieTimes.get(2).getTimes());
		System.out.println("should print the Screenings of Selected movie " + SimpleClient.movieTimes.get(indx).getTimes());
		text_Screening_times.setText( "Times : " +SimpleClient.movieTimes.get(indx).getTimes() );
		
		}
		else
		{
			System.out.println("movieTimes is null!!!!!!!!!!!!!!!!!!");
		}
		
		//MovieTimes toprnt = new MovieTimes();
		//toprnt = SimpleClient.movieTimes.get(indx);
		//System.out.println("toprnt = " + toprnt);
		
		//System.out.println( " = " + SimpleClient.movieTimes.get(indx).getTimes());
		//System.out.println( " = " + SimpleClient.movieTimes.get(1).getTimes());
		//System.out.println("Printing Screening in Screenings"+browse_moviesController.selectedMovie.getMovieTimes().getTimes());
		//getInfo();
	}

}
