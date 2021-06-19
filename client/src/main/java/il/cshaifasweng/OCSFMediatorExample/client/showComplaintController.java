/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class showComplaintController implements Initializable {

	@FXML // fx:id="sendAnswer"
	private Button sendAnswer; // Value injected by FXMLLoader

	@FXML // fx:id="deny"
	private Button deny; // Value injected by FXMLLoader

	@FXML // fx:id="tableView2"
	private TableView<String> tableView2; // Value injected by FXMLLoader
	@FXML
	private TableView<String> table2;

    @FXML
    private TableView<String> table3;
    
	@FXML // fx:id="username"
	private TableColumn<String, String> username; // Value injected by FXMLLoader

	@FXML // fx:id="complaintCol"
	private TableColumn<String, String> complaintCol; // Value injected by FXMLLoader
	
	@FXML
    private TableColumn<String, String> timeCol;

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

	@SuppressWarnings("unchecked")
	private void getComplaints() {
		final ObservableList<String> comp = FXCollections.observableArrayList(SimpleClient.ComplaintsContent);
		tableView2.setEditable(true);
		complaintCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		tableView2.getColumns().setAll(complaintCol);
		tableView2.setItems(comp);

		final ObservableList<String> users = FXCollections.observableArrayList(SimpleClient.ComplaintsUser);
		table2.setEditable(true);
		username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table2.getColumns().setAll(username);
		table2.setItems(users);
		
		final ObservableList<String> Time = FXCollections.observableArrayList(SimpleClient.ComplaintTime);
		table3.setEditable(true);
		timeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table3.getColumns().setAll(timeCol);
		table3.setItems(Time);
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		getComplaints();
	}

}
