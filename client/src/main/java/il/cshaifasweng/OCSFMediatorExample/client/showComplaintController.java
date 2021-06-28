/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

/**
 * Sample Skeleton for 'showComplaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Label invalid_label;

	@FXML
	void goBack(ActionEvent event) throws IOException {
		App.setRoot("menu");
	}

	public static String selecteduser;

	@FXML
	void gotoDelete(ActionEvent event) {
		String selected = tableView2.getSelectionModel().getSelectedItem();
		// String wantedMovie = movieName.getText();
		TripleObject msg = new TripleObject("Delete complaint " + selected, null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void gotoSendAnswer(ActionEvent event) {
		String selected = table2.getSelectionModel().getSelectedItem();

		selecteduser = selected; // selected user name that mean the one i want to reply
		String from = loginController.currentUser; // the worker that reply to the user
		System.out.println("selected name in browseM : " + selected);
		String answer = answerBox.getText(); /// the answer
		if (answer == "")
			invalid_label.setText("please honey enter a valid reply");
		else {
			invalid_label.setText("");
			Movie sendedmsg = new Movie();
			sendedmsg.setEngName(selected); // user name the one that i want to send a reply to
			sendedmsg.setHebName(answer); // the answer
			sendedmsg.setSummary(from);
			List<Movie> L = new ArrayList<Movie>();
			L.add(sendedmsg);
			TripleObject msg = new TripleObject("Add new message", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerBox.setText("");
			//// 3shan yru7 3sf7t elmenu?
			// try {
			// App.setRoot("menu");
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
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
