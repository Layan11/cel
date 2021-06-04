/**
 * Sample Skeleton for 'Coming_soon.fxml' Controller Class
 */

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Coming_soonController implements Initializable{
	
	@FXML
	private TableView<Movie> tableView;

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML // fx:id="Show"
    private Button Show; // Value injected by FXMLLoader

    @FXML // fx:id="Edit"
    private Button Edit; // Value injected by FXMLLoader
    
    @FXML // fx:id="EngC"
    private TableColumn<Movie, String> EngC; // Value injected by FXMLLoader

    @FXML // fx:id="HebC"
    private TableColumn<Movie, String> HebC; // Value injected by FXMLLoader
    public static Movie selected_coming_soon_Movie;

    @FXML
    void ShowMovie(ActionEvent event) throws IOException {
    	Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_coming_soon_Movie = selected;
		System.out.println("selected name in cominfsoonC : " + selected_coming_soon_Movie.getEngName());
		Movie mv = new Movie();
		List<Movie> mvlist = new ArrayList<Movie>();
		mv.setEngName(selected.getEngName());
		mvlist.add(mv);
		System.out.println("mv size : " + mvlist.size());
		TripleObject msg = new TripleObject("Show More info", mvlist, null);
		SimpleClient.getClient().sendToServer(msg);
    }

    @Subscribe
	public void onData22(GotMoreInfoEvent event) {
		Platform.runLater(() -> {

			//System.out.println("before load: " + SimpleClient.moviesList.get(0).getEngName());
			Parent root;
			try {
				App.setRoot("More_Info");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}
    

    @FXML
    void gotoEditMovie(ActionEvent event) {
    	Movie selected = tableView.getSelectionModel().getSelectedItem();
		selected_coming_soon_Movie = selected;
		System.out.println("selected name in cominfsoonC : " + selected_coming_soon_Movie.getEngName());
    	Platform.runLater(() -> {
    		Parent root;
    		try {
    			App.setRoot("LoginToComingSoon");

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}});

    }



    @FXML
    void goback(ActionEvent event) {
		Platform.runLater(() -> {
    		Parent root;
    		try {
    			App.setRoot("choose_type_to_browse");

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}});

    }

    public void getMovies(/* ArrayList<Movie> movies */) {
		final ObservableList<Movie> CSmovie = FXCollections.observableArrayList(SimpleClient.moviesList);
		tableView.setEditable(true);
		EngC.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		HebC.setCellValueFactory(new PropertyValueFactory<Movie, String>("HebName"));
		tableView.getColumns().setAll(EngC, HebC);
		tableView.setItems(CSmovie);
		return;
		
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
	   getMovies();
		
		
	}


}
