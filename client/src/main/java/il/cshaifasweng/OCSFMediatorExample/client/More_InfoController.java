/**
 * Sample Skeleton for 'More_Info.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class More_InfoController implements Initializable{

    @FXML // fx:id="EngNametxt"
    private TextField EngNametxt; // Value injected by FXMLLoader

    @FXML // fx:id="movie_img"
    private ImageView movie_img; // Value injected by FXMLLoader

    @FXML // fx:id="HebNametxt"
    private TextField HebNametxt; // Value injected by FXMLLoader

    @FXML // fx:id="Producertxt"
    private TextField Producertxt; // Value injected by FXMLLoader

    @FXML // fx:id="Summarytxt"
    private TextArea Summarytxt; // Value injected by FXMLLoader
    
    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML
    void goback(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Coming_Soon_Movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

    }
    
	@Subscribe
	public void onData111(GotComingSoonEvent event) {
		//System.out.println("IN onData");
		Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
			try {
				App.setRoot("Coming_soon");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
		
		Movie chosen_movie = new Movie();
		chosen_movie = SimpleClient.moviesList.get(0);
		
		
		EngNametxt.setText(chosen_movie.getEngName());
		HebNametxt.setText(chosen_movie.getHebName());
		Producertxt.setText(chosen_movie.getProducer());
		Summarytxt.setText(chosen_movie.getSummary());
		//Producertxt.setText(chosen_movie.getProducer());
		//Summarytxt.setText(chosen_movie.getSummary());
		
		InputStream stream1;
		try {
			stream1 = new FileInputStream(chosen_movie.getImage());
			System.out.println(stream1);
			Image img = new Image(stream1);
			movie_img.setImage(img);
			movie_img.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

    }


