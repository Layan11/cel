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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShowReportsController implements Initializable {
    @FXML
    private Label Linksl;

    @FXML
    private Button ShowComplaintsReport;

    @FXML
    private TextField TicketsInHaifatxt;

    @FXML
    private TextField returnedTicketsInHaifatxt;

    @FXML
    private TextField Links;

    @FXML
    private TextField TicketsInShefaAmrtxt;

    @FXML
    private TextField returnedTicketsInShefaAmrtxt;

    @FXML
    private TextField Packages;

    @FXML
    private Button back;

    @FXML
    private Label HaifaTicketsLabel;

    @FXML
    private Label shefaAmrTicketsLabel;

    @FXML
    private Label HaifaReturnedTicketsLabel;

    @FXML
    private Label shefaAmrReturnedTicketsLabel;
    
    @FXML
    private Label packageslabel;
    

    @FXML
    void gotoShowComplaintsReport(ActionEvent event) throws Exception {
    	TripleObject msg = new TripleObject("ComplaintsReports", null, null);
		SimpleClient.getClient().sendToServer(msg);
    }
    
	@Subscribe
	public void onData11(GotComplaintsReportstEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("ComplaintsReports");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void gotoback(ActionEvent event) throws Exception {
		App.setRoot("menu");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		List<Integer> list2 = new ArrayList<Integer>();
		list2 = SimpleClient.list2;
		int TicketsInHaifa_num = list2.get(0);
		int returnedTicketsInHaifa_num = list2.get(1);
		int TicketsInShefaAmr_num = list2.get(2);
		int returnedTicketsInShefaAmr_num = list2.get(3);
		int Links_num = list2.get(4);
		int packagesnum = list2.get(5);
		
		System.out.println("in initialize pack = " + packagesnum);

		if (loginController.loginRole == 0) {// show everything

			TicketsInHaifatxt.setText(Integer.toString(TicketsInHaifa_num));
			returnedTicketsInHaifatxt.setText(Integer.toString(returnedTicketsInHaifa_num));
			Links.setText(Integer.toString(Links_num));
			TicketsInShefaAmrtxt.setText(Integer.toString(TicketsInShefaAmr_num));
			returnedTicketsInShefaAmrtxt.setText(Integer.toString(returnedTicketsInShefaAmr_num));
			Packages.setText(Integer.toString(packagesnum));
		} 
		else if (loginController.currentUser.equals("Ursula")) {// show haifa reports
			TicketsInHaifatxt.setText(Integer.toString(TicketsInHaifa_num));
			returnedTicketsInHaifatxt.setText(Integer.toString(returnedTicketsInHaifa_num));

			Linksl.setVisible(false);
			Packages.setVisible(false);
			Links.setVisible(false);
			TicketsInShefaAmrtxt.setVisible(false);
			returnedTicketsInShefaAmrtxt.setVisible(false);


			Links.setText(Integer.toString(Links_num));
			Packages.setText(Integer.toString(packagesnum));
			TicketsInShefaAmrtxt.setVisible(false);
			returnedTicketsInShefaAmrtxt.setVisible(false);
			shefaAmrTicketsLabel.setVisible(false);
			shefaAmrReturnedTicketsLabel.setVisible(false);
			Links.setVisible(false);
			ShowComplaintsReport.setVisible(false);
			packageslabel.setVisible(false);

		} else if (loginController.currentUser.equals("Consuela")) {// show shefaAmr reports

			TicketsInShefaAmrtxt.setText(Integer.toString(TicketsInShefaAmr_num));
			returnedTicketsInShefaAmrtxt.setText(Integer.toString(returnedTicketsInShefaAmr_num));

			Linksl.setVisible(false);
			Packages.setVisible(false);
			Links.setVisible(false);
			returnedTicketsInHaifatxt.setVisible(false);
			TicketsInHaifatxt.setVisible(false);

			Links.setText(Integer.toString(Links_num));
			TicketsInShefaAmrtxt.setText(Integer.toString(TicketsInShefaAmr_num));
			returnedTicketsInShefaAmrtxt.setText(Integer.toString(returnedTicketsInShefaAmr_num));
			Packages.setText(Integer.toString(packagesnum));
			TicketsInHaifatxt.setVisible(false);
			returnedTicketsInHaifatxt.setVisible(false);
			HaifaTicketsLabel.setVisible(false);
			HaifaReturnedTicketsLabel.setVisible(false);
			Links.setVisible(false);
			ShowComplaintsReport.setVisible(false);
			packageslabel.setVisible(false);
			

		}
	}
}
