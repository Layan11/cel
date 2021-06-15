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
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Buy_Ticket	implements Initializable {

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
    private TextField msglab;

    @FXML
    private Label label3;

    @FXML
    void back_btn(ActionEvent event)  {
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
    	
    	//int x;
    //	Movie K = browse_moviesController.selectedMovie;
 //   	Hall hall= browse_hallController.selectedHall;
        //String time= Screening_TimesController.selected_start_time;
        //String end_time = Screening_TimesController.selected_end_time;
      //  Ticket addTicker =new Ticket(K,hall,time,end_time);
        //	DoubleObject msg = new DoubleObject("Add new Ticket",null, naddTicker);
    		//SimpleClient.getClient().sendToServer(msg);
    //	if(label3.getText()=="") { //new user
    //		String str1 = first_name.getText();
   // 		String str2 = last_name.getText();
 //   		String str3 = way_to_pay.getText();
    	//	User user = New User(str1,str2,str3);
    //	DoubleObject msg2 = new DoubleObject("Add new User ",null, null,null,user);
    	//SimpleClient.getClient().sendToServer(msg2);
    	
    //	}
    //	if(label3.getText()!="") { //if old user get his id and create ticket
    	//String str3= Id_check.getText();
    	// x= Integer.parseInt(str3);
    	
    //	}
    	Ticket mytestticket = new Ticket("Test buy","test hall",3,5);
    	DoubleObject msg = new DoubleObject("1Add new Ticket ",null, mytestticket,null);
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
