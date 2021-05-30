/**
 * Sample Skeleton for 'Coming_soon.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coming_soonController implements Initializable{

    @FXML // fx:id="Movie_Name1"
    private TextField Movie_Name1; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Name2"
    private TextField Movie_Name2; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Name3"
    private TextField Movie_Name3; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Name4"
    private TextField Movie_Name4; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Image1"
    private ImageView Movie_Image1; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Image2"
    private ImageView Movie_Image2; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Image3"
    private ImageView Movie_Image3; // Value injected by FXMLLoader

    @FXML // fx:id="Movie_Image4"
    private ImageView Movie_Image4; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

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
		Movie_Name1.setText(CSmovie.get(0).getEngName());
		Movie_Name2.setText(CSmovie.get(1).getEngName());
		Movie_Name3.setText(CSmovie.get(2).getEngName());
		Movie_Name4.setText(CSmovie.get(3).getEngName());
		InputStream stream;
		try {
			stream = new FileInputStream(SimpleClient.moviesList.get(0).getImage());
			System.out.println(stream);
			Image img = new Image(stream);
			Movie_Image1.setImage(img);
			//Movie_Image1.setFitWidth(90);
			//Movie_Image1.setFitHeight(85);
			Movie_Image1.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream1;
		try {
			stream1 = new FileInputStream(SimpleClient.moviesList.get(1).getImage());
			System.out.println(stream1);
			Image img1 = new Image(stream1);
			Movie_Image2.setImage(img1);
			//Movie_Image1.setFitWidth(90);
			//Movie_Image1.setFitHeight(85);
			Movie_Image2.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream2;
		try {
			stream2 = new FileInputStream(SimpleClient.moviesList.get(2).getImage());
			System.out.println(stream2);
			Image img2 = new Image(stream2);
			Movie_Image3.setImage(img2);
			//Movie_Image1.setFitWidth(90);
			//Movie_Image1.setFitHeight(85);
			Movie_Image3.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream stream3;
		try {
			stream3 = new FileInputStream(SimpleClient.moviesList.get(3).getImage());
			System.out.println(stream3);
			Image img3 = new Image(stream3);
			Movie_Image4.setImage(img3);
			//Movie_Image1.setFitWidth(90);
			//Movie_Image1.setFitHeight(85);
			Movie_Image4.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//tableView.setEditable(true);
		//firstNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("EngName"));
		//priceColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("Price"));
		//tableView.getColumns().setAll(firstNameColumn, priceColumn);
		//tableView.setItems(movie);

		return;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	   getMovies();
		
		
	}

}
