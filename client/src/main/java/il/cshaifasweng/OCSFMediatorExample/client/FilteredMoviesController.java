/**
 * Sample Skeleton for 'FilteredMovies.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FilteredMoviesController {

    @FXML // fx:id="tableview"
    private TableView<?> tableview; // Value injected by FXMLLoader

    @FXML // fx:id="Name"
    private TableColumn<?, ?> Name; // Value injected by FXMLLoader

    @FXML // fx:id="Price"
    private TableColumn<?, ?> Price; // Value injected by FXMLLoader

    @FXML // fx:id="Back"
    private Button Back; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) {
    	

    }

}
