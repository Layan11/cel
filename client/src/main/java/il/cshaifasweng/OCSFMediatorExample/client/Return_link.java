package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Return_link implements Initializable {
	public static List<link> alllinksreturn;
	@FXML
	private TextField Ret_Link_Label;

	@FXML
	private Button return_link_btn;

	@FXML
	private Button back_btn;
	@FXML
	private TextField retmsg;
	int flag2=0;
	@FXML
	void back_btn(ActionEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("menu");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onData(TripleObject msg) {
		Platform.runLater(() -> {
			try {
			
				retmsg.setText(msg.getMsg());
				if(flag2==1) {
				String msg_to_user ="The Return of the link was exectuted \n succesfully "
			    		+ "\n  " +retmsg.getText() ;
				System.out.println(msg_to_user);
				messages msgtouser =new messages("server",msg_to_user,loginController.currentUser);
				TripleObject mymsg=new TripleObject("Send msg to user", msgtouser);
				SimpleClient.getClient().sendToServer(mymsg);
				}
				flag2=0;
				if (false) {
					App.setRoot("idk");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void return_link_btn(ActionEvent event) throws Exception {
		String mystr;
		int x;

		x = Integer.parseInt(Ret_Link_Label.getText());
		
		mystr = "Delete link " + x +" for user " + loginController.currentUser;
		System.out.println(mystr);
		TripleObject msg = new TripleObject(mystr, null, null);
		SimpleClient.getClient().sendToServer(msg);
		retmsg.setVisible(true);
	    flag2=1;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

}
