package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Update_Package implements Initializable {

    @FXML
    private Button update_num;

    @FXML
    private TextField pack_id;

    @FXML
    private Button back;
    @FXML
    private TextField msg_lab;
    @FXML
    void back_btn(ActionEvent event) {
    	//System.out.println("IN onData1");
		Platform.runLater(() -> {

			//System.out.println("before load: " + SimpleClient.moviesList.get(0).getEngName());
			Parent root;
			try {
				App.setRoot("choose_type_to_browse");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
    }

    @FXML
    void update_num_btn(ActionEvent event)throws IOException {
    	msg_lab.setVisible(false);
    	int x = Integer.parseInt(pack_id.getText());
    	String mystr="Lesser Pack "+x;
    	TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);
		
    }
    @Subscribe
  	public void onData(TripleObject msg) {
  		System.out.println("IN onData");
  		
  		
  		Platform.runLater(() -> {
//  			Window window = Browse_movie_list.getScene().getWindow();
//  			if (window instanceof Stage) {
//  				((Stage) window).close();
//  			}
//  			Stage primaryStage = new Stage();
  			//Parent root;
  			
  			try {
  				
  				
  				msg_lab.setVisible(true);
  				msg_lab.setText(msg.getMsg());
  				
  			
  	  				
  					
  				
  				if(false) {
  					App.setRoot("idk");
  				}
//  				Scene scene = new Scene(root);
//  				primaryStage.setScene(scene);
//  				primaryStage.setTitle("Browse movies list");
//  				primaryStage.show();
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
