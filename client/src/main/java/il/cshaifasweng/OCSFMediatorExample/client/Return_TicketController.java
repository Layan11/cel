package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;


import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Return_TicketController {

    @FXML
    private TextField tick_id;

    @FXML
    private Button Return_Ticket;

    @FXML
    private Button back_Btn;

    @FXML
    void back_btn(ActionEvent event) {
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
    void return_btn(ActionEvent event)throws Exception {
    	String mystr;
    	int x;
    	x= Integer.parseInt(tick_id.getText());
    	mystr="Delete Ticket "+x;
    	TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);

    }

}
