package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ComplaintsReportsController implements Initializable{

    @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;

    @FXML
    private Button back;
    
    @FXML
    private Label titlelabel;

    @FXML
    void back(ActionEvent event) throws Exception{
    	TripleObject msg = new TripleObject("Show reports", null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onReports(GotReportEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("ShowReports");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
    }

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
		
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		
		LocalDate local = LocalDate.now();
		int month = local.lengthOfMonth();
		int currmonth = local.getMonthValue();
		
		for(int i=0;i<=month;i++)
		{
			series1.getData().add(new XYChart.Data<String,Integer>(Integer.toString(i),SimpleClient.ComplaintsPerMArraay[i]));
		}
		chart.getData().addAll(series1);
		
		titlelabel.setText("Complaints Report for month " +currmonth);
	
	}

}
