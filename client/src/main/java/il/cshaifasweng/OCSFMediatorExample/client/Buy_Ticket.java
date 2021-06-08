package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Buy_Ticket {

    @FXML
    private TextField Id_check;

    @FXML
    private TextField way_to_pay;

    @FXML
    private Button Buy_btn;

    @FXML
    private TextField last_name;
    @FXML
    
    private TextField first_name;

    @FXML
    private Label Laber1;

    @FXML
    private Label label2;
    @FXML
    private Button back;

    @FXML
    void back_btn(ActionEvent event) {
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
    	
    //	Movie K = browse_moviesController.selectedMovie;
 //   	Hall hall= browse_hallController.selectedHall;
        //String time= Screening_TimesController.selected_start_time;
        //String end_time = Screening_TimesController.selected_end_time;
      //  Ticket addTicker =new Ticket(K,hall,time,end_time);
        //	DoubleObject msg = new DoubleObject("Add new Ticket",null, naddTicker);
    		//SimpleClient.getClient().sendToServer(msg);
    	Ticket mytestticket = new Ticket(2,"Test buy","test hall","test time",5);
    	DoubleObject msg = new DoubleObject("1Add new Ticket ",null, mytestticket);
    	SimpleClient.getClient().sendToServer(msg);


    }

}
