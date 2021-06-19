/**
 * Sample Skeleton for 'FillingComplaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FillingComplaintController implements Initializable {

    @FXML // fx:id="complaint_box"
    private TextArea complaint_box; // Value injected by FXMLLoader

    @FXML // fx:id="name_box"
    private TextField name_box; // Value injected by FXMLLoader

    @FXML // fx:id="enteryournameLabel"
    private Label enteryournameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="Send_the_complaint"
    private Button Send_the_complaint; // Value injected by FXMLLoader

    @FXML // fx:id="Back_to_home"
    private Button Back_to_home; // Value injected by FXMLLoader

    @FXML // fx:id="invalid_label2"
    private Label invalid_label2; // Value injected by FXMLLoader

	@FXML
	void backPrimary(ActionEvent event) throws IOException {
		App.setRoot("menu");
	}
	
	
	@FXML
	void sendComplaintandhome(ActionEvent event) throws IOException 
	{
		//String name = getUser.getText();
		String name = name_box.getText();
		String complaint = complaint_box.getText();
		if (name.equals(""))
			invalid_label2.setText("Enter your name please");
		else if (complaint.equals(""))
			invalid_label2.setText("Enter your complaint please");
		else {
			
			invalid_label2.setText("");
			
			Movie comp = new Movie();
			comp.setEngName(name);
			comp.setHebName(complaint);
			List<Movie> L = new ArrayList<Movie>();
			L.add(comp);
			TripleObject msg = new TripleObject("Add new complaint", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//// 3shan yru7 3sf7t elmenu?
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	 


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
