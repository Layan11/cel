package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Package;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Buy_Package implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private Button buy_bnt;
    @FXML
    private TextField msglab;

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
    	Package myPackge = new Package(20);
    	DoubleObject msg = new DoubleObject("Add New Package",null, null,myPackge);
    	SimpleClient.getClient().sendToServer(msg);
    	msglab.setVisible(true);
    }
    @Subscribe
   	public void onData(TripleObject msg) {
   		System.out.println("IN onData");
   		
   		
   		Platform.runLater(() -> {
//   			Window window = Browse_movie_list.getScene().getWindow();
//   			if (window instanceof Stage) {
//   				((Stage) window).close();
//   			}
//   			Stage primaryStage = new Stage();
   			//Parent root;
   			try {
   				msglab.setText(msg.getMsg());
   				if(false) {
   					App.setRoot("idk");
   				}
//   				Scene scene = new Scene(root);
//   				primaryStage.setScene(scene);
//   				primaryStage.setTitle("Browse movies list");
//   				primaryStage.show();
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
   	}

}
