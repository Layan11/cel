/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.complaint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class showComplaintController implements Initializable{

    @FXML // fx:id="sendAnswer"
    private Button sendAnswer; // Value injected by FXMLLoader

    @FXML // fx:id="deny"
    private Button deny; // Value injected by FXMLLoader

    @FXML // fx:id="tableView2"
    private TableView<complaint> tableView2; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TableColumn<complaint, String> username; // Value injected by FXMLLoader

    @FXML // fx:id="complaintCol"
    private TableColumn<complaint, String> complaintCol; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="answerBox"
    private TextArea answerBox; // Value injected by FXMLLoader


    @FXML
    void goBack(ActionEvent event) throws IOException {
    	App.setRoot("menu");
    }

    @FXML
    void gotoDeny(ActionEvent event) {
         
    }

    @FXML
    void gotoSendAnswer(ActionEvent event) {
         
    }
    


	private void getComplaints() {
		// TODO Auto-generated method stub
		final ObservableList<complaint> comp = FXCollections.observableArrayList(SimpleClient.Allcomplaints);
		tableView2.setEditable(true);
		username.setCellValueFactory(new PropertyValueFactory<complaint, String>("Name"));
		complaintCol.setCellValueFactory(new PropertyValueFactory<complaint, String>("Complaintcontext"));
		tableView2.getColumns().setAll(username, complaintCol);
		tableView2.setItems(comp);

		return;
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//EventBus.getDefault().register(this);
		getComplaints();
	}

	


}
