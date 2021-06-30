/**
 * Sample Skeleton for 'Dm_forUser.fxml' Controller Class
 */
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Dm_forUserController implements Initializable {

	@FXML
	private TableView<String> table1;
	@FXML
	private TableColumn<String, String> fromCol;
	@FXML
	private TableView<String> table2;
	@FXML
	private TableColumn<String, String> MsgCol;
	@FXML
	private Button backButton;
	@FXML
	private Button DeleteMsg;
	@FXML
	private TableView<String> IDtable;
	@FXML
	private TableColumn<String, String> IDcolumn;

	@FXML
	void GotoDeleteMsg(ActionEvent event) {
		System.out.println("in the GotoDeleteMsg : ");
		String selected = IDtable.getSelectionModel().getSelectedItem();
		// String wantedMovie = movieName.getText();
		TripleObject msg = new TripleObject("Delete MSG " + selected, null, null); // 11
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void askforupdated(askforupdatedEvent event) {
		Platform.runLater(() -> {
			String name = loginController.currentUser;
			Movie newMovie = new Movie();
			newMovie.setEngName(name); // user
			List<Movie> L = new ArrayList<Movie>();
			L.add(newMovie);
			TripleObject msg = new TripleObject("Show messages", L, null);
			try {
				SimpleClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onGOTMsgs(gotallmessagessevent event) {
		Platform.runLater(() -> {
			try {
				EventBus.getDefault().unregister(this);
				App.setRoot("Dm_forUser");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoBack(ActionEvent event) throws IOException {

		App.setRoot("menu");
	}

	@SuppressWarnings("unchecked")
	private void getMessages() {
		// TODO Auto-generated method stub
		final ObservableList<String> from = FXCollections.observableArrayList(SimpleClient.FromMSG);
		table1.setEditable(true);
		fromCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table1.getColumns().setAll(fromCol);
		table1.setItems(from);
		table1.setSelectionModel(null);

		final ObservableList<String> message = FXCollections.observableArrayList(SimpleClient.messageContent);
		table2.setEditable(true);
		MsgCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table2.getColumns().setAll(MsgCol);
		table2.setItems(message);
		table2.setSelectionModel(null);

		final ObservableList<String> IDS = FXCollections.observableArrayList(SimpleClient.MSGid);
		IDtable.setEditable(true);
		IDcolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		IDtable.getColumns().setAll(IDcolumn);
		IDtable.setItems(IDS);

	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		table1.setFixedCellSize(50.0);
		IDtable.setFixedCellSize(50.0);
		table2.setFixedCellSize(50.0);
		getMessages();

	}

}
