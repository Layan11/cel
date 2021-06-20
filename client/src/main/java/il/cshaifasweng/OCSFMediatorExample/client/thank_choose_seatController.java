package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import il.cshaifasweng.OCSFMediatorExample.client.show_MapChairController;
import javafx.fxml.Initializable;


public class thank_choose_seatController {

    @FXML
    private Text seatnum;

    @FXML
    private Button home;

    @FXML
    private Button bnm;

    @FXML
    void browsemovies(ActionEvent event) {
    	Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});


    }

    @FXML
    void gohome(ActionEvent event) {
    	Platform.runLater(() -> {
			try {
				App.setRoot("primary");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});

    }
    public void initialize(java.net.URL location, ResourceBundle resources) {
    	seatnum.setText(show_MapChairController.num_chair);
    }

}