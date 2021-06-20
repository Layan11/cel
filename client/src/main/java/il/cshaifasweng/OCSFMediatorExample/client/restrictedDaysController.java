package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;

public class restrictedDaysController implements Initializable {

	@FXML
	private TableView<String> table;

	@FXML
	private TableColumn<String, String> dates;

	@FXML
	private Button back;

	@FXML
	private Button delete;

	@FXML
	private Button edit;

	@FXML
	private Button add;

	@FXML
	private Label addLabel;

	@FXML
	private TextField dateToAdd;

	@FXML
	private TextField dateToUpdate;
	@FXML
	private Label updateLabel;
	
    @FXML
    private Button cancel_screenings;
    
    @FXML
    private TextField cancel_screeningstxt;

    @FXML
    void cancel_screenings(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("Cancel Screenings"+cancel_screeningstxt.getText(), null, null);
		SimpleClient.getClient().sendToServer(msg);
		cancel_screeningstxt.setText("");

    }
	@Subscribe
	public void onUpdatedRestrictedDays(GotCanceledDateEvent event) {
		Platform.runLater(() -> {
			final ObservableList<String> days = FXCollections.observableArrayList(SimpleClient.restrictedDays);
			table.setEditable(true);
			dates.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			table.getColumns().setAll(dates);
			table.setItems(days);
		});
	}
    
    

	@FXML
	void gotoAdd(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Add restricted day " + dateToAdd.getText(), null, null);
		SimpleClient.getClient().sendToServer(msg);
		dateToAdd.setText("");
	}

	@Subscribe
	public void onUpdatedRestrictedDays(GotUpdatedRestrictedDaysEvent event) {
		Platform.runLater(() -> {
			final ObservableList<String> days = FXCollections.observableArrayList(SimpleClient.restrictedDays);
			table.setEditable(true);
			dates.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
			table.getColumns().setAll(dates);
			table.setItems(days);
		});
	}

	@FXML
	void gotoBack(ActionEvent event) throws Exception {
		App.setRoot("menu");
	}

	@FXML
	void gotoDelete(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Delete restricted day " + table.getSelectionModel().getSelectedItem(),
				null, null);
		SimpleClient.getClient().sendToServer(msg);
	}

	@FXML
	void gotoEdit(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Update restricted day " + table.getSelectionModel().getSelectedItem(),
				null, null);
		List<String> list = new ArrayList<String>();
		list.add(dateToUpdate.getText());
		msg.setList(list);
		SimpleClient.getClient().sendToServer(msg);
		dateToUpdate.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		if (loginController.loginRole != 2) {
			delete.setVisible(false);
			edit.setVisible(false);
			add.setVisible(false);
			addLabel.setVisible(false);
			dateToAdd.setVisible(false);
			dateToUpdate.setVisible(false);
			updateLabel.setVisible(false);
			cancel_screeningstxt.setVisible(false);
			cancel_screenings.setVisible(false);
			
		}
		final ObservableList<String> days = FXCollections.observableArrayList(SimpleClient.restrictedDays);
		table.setEditable(true);
		dates.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		table.getColumns().setAll(dates);
		table.setItems(days);
	}

}
