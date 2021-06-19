/**
 * Sample Skeleton for 'Dm_forUser.fxml' Controller Class
 */
package il.cshaifasweng.OCSFMediatorExample.client;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Dm_forUser implements Initializable   {

    @FXML // fx:id="table1"
    private TableView<?> table1; // Value injected by FXMLLoader

    @FXML // fx:id="fromCol"
    private TableColumn<?, ?> fromCol; // Value injected by FXMLLoader

    @FXML // fx:id="table2"
    private TableView<?> table2; // Value injected by FXMLLoader

    @FXML // fx:id="MsgCol"
    private TableColumn<?, ?> MsgCol; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML
    void gotoBack(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
