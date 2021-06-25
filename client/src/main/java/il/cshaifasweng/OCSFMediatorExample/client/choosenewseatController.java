package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
public class choosenewseatController {
	 @FXML
	    private Text stamtext;

	    @FXML
	    private Button btn_choosenewseat;

	    @FXML
	    void anotherpersonchoose(ActionEvent event) {
	    
	    	Platform.runLater(() -> {
				try {
					
					App.setRoot("show_MapChair");
					System.out.println("after the load line of brwose movies in primary");
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}});


	    }
}
