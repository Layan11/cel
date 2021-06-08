package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import javafx.application.Platform;

public class Buy_LinkController {
	public static List<link> alllinksbuy;
    @FXML
    private TextField user_id;

    @FXML
    private TextField Method_pay;

    @FXML
    private TextField First_Name;

    @FXML
    private TextField Last_Name;

    @FXML
    private Label label;

    @FXML
    private Button purch_btn;
    @FXML
    private Button back;

    @FXML
    void backbtn(ActionEvent event) {
     	Platform.runLater(() -> {
//			Window window = Browse_movie_list.getScene().getWindow();
//			if (window instanceof Stage) {
//				((Stage) window).close();
//			}
//			Stage primaryStage = new Stage();
			//Parent root;
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
    void buy_btn(ActionEvent event)throws IOException {
    	
    // Movie K = browse_moviesController.selectedMovie;
    // String time= Screening_TimesController.selected_start_time;
    // String end_time = Screening_TimesController.selected_end_time;
    // link addlink =new link(K,10,20);
    
    //	DoubleObject msg = new DoubleObject("Add new link",addlink, null);
		//SimpleClient.getClient().sendToServer(msg);
    	
    	link mytestlink= new link(2,"K412312ungFuPanda",5,7);
    	DoubleObject msg = new DoubleObject("1Add new link ",mytestlink, null);
    	SimpleClient.getClient().sendToServer(msg);

    }

}
