/**
 * Sample Skeleton for 'Dm_forUser.fxml' Controller Class
 */
package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Dm_forUserController implements Initializable   {

    @FXML // fx:id="table1"
    private TableView<String> table1; // Value injected by FXMLLoader

    @FXML // fx:id="fromCol"
    private TableColumn<String, String> fromCol; // Value injected by FXMLLoader

    @FXML // fx:id="table2"
    private TableView<String> table2; // Value injected by FXMLLoader

    @FXML // fx:id="MsgCol"
    private TableColumn<String, String> MsgCol; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader
    
    @FXML
    private Button DeleteMsg;




    @FXML
    void GotoDeleteMsg(ActionEvent event) {
    	String selected = table2.getSelectionModel().getSelectedItem(); 
		//String wantedMovie = movieName.getText();
		TripleObject msg = new TripleObject("Delete MSG " + selected, null, null); //11
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		final ObservableList<String> message = FXCollections.observableArrayList(SimpleClient.messageContent);
		table2.setEditable(true);
		MsgCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table2.getColumns().setAll(MsgCol);
		table2.setItems(message);
		
		
	}

    
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		getMessages();
		
	}



}
