package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;

import java.io.IOException;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class show_MapChairController implements Initializable {
	private String num_chair="-1";
	private Button mybutton;
	
	  @FXML
	    private Button seat1;

	    @FXML
	    private Button seat2;

	    @FXML
	    private Button seat3;

	    @FXML
	    private Button seat7;

	    @FXML
	    private Button seat6;

	    @FXML
	    private Button seat5;

	    @FXML
	    private Button seat4;

	    @FXML
	    private Button seat9;

	    @FXML
	    private Button seat8;

	    @FXML
	    private Button seat10;

	    @FXML
	    private Button seat11;

	    @FXML
	    private Button seat91;

	    @FXML
	    private Button seat81;

	    @FXML
	    private Button seat71;

	    @FXML
	    private Button seat61;

	    @FXML
	    private Button seat51;

	    @FXML
	    private Button seat41;

	    @FXML
	    private Button seat31;

	    @FXML
	    private Button seat21;

	    @FXML
	    private Button seat12;

	    @FXML
	    private Button seat14;

	    @FXML
	    private Button seat93;

	    @FXML
	    private Button seat83;

	    @FXML
	    private Button seat73;

	    @FXML
	    private Button seat63;

	    @FXML
	    private Button seat53;

	    @FXML
	    private Button seat43;

	    @FXML
	    private Button seat33;

	    @FXML
	    private Button seat23;

	    @FXML
	    private Button seat13;

	    @FXML
	    private Button seat92;

	    @FXML
	    private Button seat82;

	    @FXML
	    private Button seat72;

	    @FXML
	    private Button seat62;

	    @FXML
	    private Button seat52;

	    @FXML
	    private Button seat42;

	    @FXML
	    private Button seat32;

	    @FXML
	    private Button seat22;

	    @FXML
	    private Button seat56;

	    @FXML
	    private Button seat47;

	    @FXML
	    private Button seat46;

	    @FXML
	    private Button seat37;

	    @FXML
	    private Button seat36;

	    @FXML
	    private Button seat27;

	    @FXML
	    private Button seat26;

	    @FXML
	    private Button seat17;

	    @FXML
	    private Button seat16;

	    @FXML
	    private Button seat95;

	    @FXML
	    private Button seat94;

	    @FXML
	    private Button seat85;

	    @FXML
	    private Button seat84;

	    @FXML
	    private Button seat75;

	    @FXML
	    private Button seat74;

	    @FXML
	    private Button seat65;

	    @FXML
	    private Button seat64;

	    @FXML
	    private Button seat55;

	    @FXML
	    private Button seat45;

	    @FXML
	    private Button seat35;

	    @FXML
	    private Button seat25;

	    @FXML
	    private Button seat15;

	    @FXML
	    private Button seat54;

	    @FXML
	    private Button seat44;

	    @FXML
	    private Button seat34;

	    @FXML
	    private Button seat24;

	    @FXML
	    private Button seat58;

	    @FXML
	    private Button seat50;

	    @FXML
	    private Button seat49;

	    @FXML
	    private Button seat48;

	    @FXML
	    private Button seat40;

	    @FXML
	    private Button seat39;

	    @FXML
	    private Button seat38;

	    @FXML
	    private Button seat30;

	    @FXML
	    private Button seat29;

	    @FXML
	    private Button seat20;

	    @FXML
	    private Button seat19;

	    @FXML
	    private Button seat18;

	    @FXML
	    private Button seat97;

	    @FXML
	    private Button seat96;

	    @FXML
	    private Button seat87;

	    @FXML
	    private Button seat86;

	    @FXML
	    private Button seat77;

	    @FXML
	    private Button seat76;

	    @FXML
	    private Button seat67;

	    @FXML
	    private Button seat66;

	    @FXML
	    private Button seat57;

	    @FXML
	    private Button seat90;

	    @FXML
	    private Button seat80;

	    @FXML
	    private Button seat70;

	    @FXML
	    private Button seat60;

	    @FXML
	    private Button seat100;

	    @FXML
	    private Button seat99;

	    @FXML
	    private Button seat98;

	    @FXML
	    private Button seat89;

	    @FXML
	    private Button seat88;

	    @FXML
	    private Button seat79;

	    @FXML
	    private Button seat78;

	    @FXML
	    private Button seat69;

	    @FXML
	    private Button seat68;

	    @FXML
	    private Button seat59;

	    @FXML
	    private Button seat28;

	    @FXML
	    private Pane pane1;
	    
	    @FXML
	    private Pane pane2;
	    
	    @FXML
	    private Pane pane3;
	    
	    @FXML
	    private Pane pane4;

	    @FXML
	    private Button yesbtn;

	    @FXML
	    private Button nobtn;
	    
	    @FXML
	    private Button home;
	    
	    @FXML
	    private Button bnm;
	    
	    @FXML
	    private Button anotherseat;
	    
	    @FXML
	    private Text seatnum;
	    


	   

	   

	    @FXML
	    void chooseseat10(ActionEvent event) {
	    	click_btnseat(seat10);

	    }

	    @FXML
	    void chooseseat100(ActionEvent event) {
	    	click_btnseat(seat100);
	    }

	    @FXML
	    void chooseseat11(ActionEvent event) {
	    	click_btnseat(seat11);
	    }

	    @FXML
	    void chooseseat12(ActionEvent event) {
	    	click_btnseat(seat12);
	    }

	    @FXML
	    void chooseseat13(ActionEvent event) {
	    	click_btnseat(seat13);
	    }

	    @FXML
	    void chooseseat14(ActionEvent event) {
	    	click_btnseat(seat14);
	    }

	    @FXML
	    void chooseseat15(ActionEvent event) {
	    	click_btnseat(seat15);
	    }

	    @FXML
	    void chooseseat16(ActionEvent event) {
	    	click_btnseat(seat16);
	    }

	    @FXML
	    void chooseseat17(ActionEvent event) {
	    	click_btnseat(seat17);
	    }

	    @FXML
	    void chooseseat18(ActionEvent event) {
	    	click_btnseat(seat18);
	    }

	    @FXML
	    void chooseseat19(ActionEvent event) {
	    	click_btnseat(seat19);
	    }


	    @FXML
	    void chooseseat20(ActionEvent event) {
	    	click_btnseat(seat20);
	    }

	    @FXML
	    void chooseseat21(ActionEvent event) {
	    	click_btnseat(seat21);
	    }

	    @FXML
	    void chooseseat22(ActionEvent event) {
	    	click_btnseat(seat22);
	    }

	    @FXML
	    void chooseseat23(ActionEvent event) {
	    	click_btnseat(seat23);
	    }

	    @FXML
	    void chooseseat24(ActionEvent event) {
	    	click_btnseat(seat24);
	    }

	    @FXML
	    void chooseseat25(ActionEvent event) {
	    	click_btnseat(seat25);
	    }

	    @FXML
	    void chooseseat26(ActionEvent event) {
	    	click_btnseat(seat26);
	    }

	    @FXML
	    void chooseseat27(ActionEvent event) {
	    	click_btnseat(seat27);
	    }

	    @FXML
	    void chooseseat28(ActionEvent event) {
	    	click_btnseat(seat28);
	    }

	    @FXML
	    void chooseseat29(ActionEvent event) {
	    	click_btnseat(seat29);
	    }

	   
	    @FXML
	    void chooseseat30(ActionEvent event) {
	    	click_btnseat(seat30);
	    }

	    @FXML
	    void chooseseat31(ActionEvent event) {
	    	click_btnseat(seat31);

	    }

	    @FXML
	    void chooseseat32(ActionEvent event) {
	    	click_btnseat(seat32);

	    }

	    @FXML
	    void chooseseat33(ActionEvent event) {
	    	click_btnseat(seat33);

	    }

	    @FXML
	    void chooseseat34(ActionEvent event) {
	    	click_btnseat(seat34);

	    }

	    @FXML
	    void chooseseat35(ActionEvent event) {
	    	click_btnseat(seat35);

	    }

	    @FXML
	    void chooseseat36(ActionEvent event) {
	    	click_btnseat(seat36);

	    }

	    @FXML
	    void chooseseat37(ActionEvent event) {
	    	click_btnseat(seat37);

	    }

	    @FXML
	    void chooseseat38(ActionEvent event) {
	    	click_btnseat(seat38);

	    }

	    @FXML
	    void chooseseat39(ActionEvent event) {
	    	click_btnseat(seat39);

	    }

	  

	    @FXML
	    void chooseseat40(ActionEvent event) {
	    	click_btnseat(seat40);

	    }

	    @FXML
	    void chooseseat41(ActionEvent event) {
	    	click_btnseat(seat41);
	    }

	    @FXML
	    void chooseseat42(ActionEvent event) {
	    	click_btnseat(seat42);
	    }

	    @FXML
	    void chooseseat43(ActionEvent event) {
	    	click_btnseat(seat43);
	    }

	    @FXML
	    void chooseseat44(ActionEvent event) {
	    	click_btnseat(seat44);

	    }

	    @FXML
	    void chooseseat45(ActionEvent event) {
	    	click_btnseat(seat45);
	    }

	    @FXML
	    void chooseseat46(ActionEvent event) {
	    	click_btnseat(seat46);
	    }

	    @FXML
	    void chooseseat47(ActionEvent event) {
	    	click_btnseat(seat47);
	    }

	    @FXML
	    void chooseseat48(ActionEvent event) {
	    	click_btnseat(seat48);
	    }

	    @FXML
	    void chooseseat49(ActionEvent event) {
	    	click_btnseat(seat49);
	    }

	    @FXML
	    void chooseseat5(ActionEvent event) {
	    	click_btnseat(seat5);
	    }

	    @FXML
	    void chooseseat50(ActionEvent event) {
	    	click_btnseat(seat50);
	    }

	    @FXML
	    void chooseseat51(ActionEvent event) {
	    	click_btnseat(seat51);
	    }

	    @FXML
	    void chooseseat52(ActionEvent event) {
	    	click_btnseat(seat52);
	    }

	    @FXML
	    void chooseseat53(ActionEvent event) {
	    	click_btnseat(seat53);
	    }

	    @FXML
	    void chooseseat54(ActionEvent event) {
	    	click_btnseat(seat54);
	    }

	    @FXML
	    void chooseseat55(ActionEvent event) {
	    	click_btnseat(seat55);
	    }

	    @FXML
	    void chooseseat56(ActionEvent event) {
	    	click_btnseat(seat56);
	    }

	    @FXML
	    void chooseseat57(ActionEvent event) {
	    	click_btnseat(seat57);
	    }

	    @FXML
	    void chooseseat58(ActionEvent event) {
	    	click_btnseat(seat58);
	    }

	    @FXML
	    void chooseseat59(ActionEvent event) {
	    	click_btnseat(seat59);
	    }

	    @FXML
	    void chooseseat6(ActionEvent event) {
	    	click_btnseat(seat6);
	    }

	    @FXML
	    void chooseseat60(ActionEvent event) {
	    	click_btnseat(seat60);
	    }

	    @FXML
	    void chooseseat61(ActionEvent event) {
	    	click_btnseat(seat61);
	    }

	    @FXML
	    void chooseseat62(ActionEvent event) {
	    	click_btnseat(seat62);
	    }

	    @FXML
	    void chooseseat63(ActionEvent event) {
	    	click_btnseat(seat63);
	    }

	    @FXML
	    void chooseseat64(ActionEvent event) {
	    	click_btnseat(seat64);
	    }

	    @FXML
	    void chooseseat65(ActionEvent event) {
	    	click_btnseat(seat65);
	    }

	    @FXML
	    void chooseseat66(ActionEvent event) {
	    	click_btnseat(seat66);
	    }

	    @FXML
	    void chooseseat67(ActionEvent event) {
	    	click_btnseat(seat67);
	    }

	    @FXML
	    void chooseseat68(ActionEvent event) {
	    	click_btnseat(seat68);
	    }

	    @FXML
	    void chooseseat69(ActionEvent event) {
	    	click_btnseat(seat69);
	    }

	    @FXML
	    void chooseseat7(ActionEvent event) {
	    	click_btnseat(seat7);
	    }

	    @FXML
	    void chooseseat70(ActionEvent event) {
	    	click_btnseat(seat70);
	    }

	    @FXML
	    void chooseseat71(ActionEvent event) {
	    	click_btnseat(seat71);
	    }

	    @FXML
	    void chooseseat72(ActionEvent event) {
	    	click_btnseat(seat72);
	    }

	    @FXML
	    void chooseseat73(ActionEvent event) {
	    	click_btnseat(seat73);
	    }

	    @FXML
	    void chooseseat74(ActionEvent event) {
	    	click_btnseat(seat74);
	    }

	    @FXML
	    void chooseseat75(ActionEvent event) {
	    	click_btnseat(seat75);
	    }

	    @FXML
	    void chooseseat76(ActionEvent event) {
	    	click_btnseat(seat76);
	    }

	    @FXML
	    void chooseseat77(ActionEvent event) {
	    	click_btnseat(seat77);
	    }

	    @FXML
	    void chooseseat78(ActionEvent event) {
	    	click_btnseat(seat78);
	    }

	    @FXML
	    void chooseseat79(ActionEvent event) {
	    	click_btnseat(seat79);
	    }

	    @FXML
	    void chooseseat8(ActionEvent event) {
	    	click_btnseat(seat8);
	    }

	    @FXML
	    void chooseseat80(ActionEvent event) {
	    	click_btnseat(seat80);
	    }

	    @FXML
	    void chooseseat81(ActionEvent event) {
	    	click_btnseat(seat81);
	    }

	    @FXML
	    void chooseseat82(ActionEvent event) {
	    	click_btnseat(seat82);
	    }

	    @FXML
	    void chooseseat83(ActionEvent event) {
	    	click_btnseat(seat83);
	    }

	    @FXML
	    void chooseseat84(ActionEvent event) {
	    	click_btnseat(seat84);
	    }

	    @FXML
	    void chooseseat85(ActionEvent event) {
	    	click_btnseat(seat85);
	    }

	    @FXML
	    void chooseseat86(ActionEvent event) {
	    	click_btnseat(seat86);
	    }

	    @FXML
	    void chooseseat87(ActionEvent event) {
	    	click_btnseat(seat87);
	    }

	    @FXML
	    void chooseseat88(ActionEvent event) {
	    	click_btnseat(seat88);
	    }

	    @FXML
	    void chooseseat89(ActionEvent event) {
	    	click_btnseat(seat89);
	    }

	    @FXML
	    void chooseseat9(ActionEvent event) {
	    	click_btnseat(seat9);
	    }

	    @FXML
	    void chooseseat90(ActionEvent event) {
	    	click_btnseat(seat90);
	    }

	    @FXML
	    void chooseseat91(ActionEvent event) {
	    	click_btnseat(seat91);
	    }

	    @FXML
	    void chooseseat92(ActionEvent event) {
	    	click_btnseat(seat92);
	    }

	    @FXML
	    void chooseseat93(ActionEvent event) {
	    	click_btnseat(seat93);
	    }

	    @FXML
	    void chooseseat94(ActionEvent event) {
	    	click_btnseat(seat94);
	    }

	    @FXML
	    void chooseseat95(ActionEvent event) {
	    	click_btnseat(seat95);
	    }

	    @FXML
	    void chooseseat96(ActionEvent event) {
	    	click_btnseat(seat96);
	    }

	    @FXML
	    void chooseseat97(ActionEvent event) {
	    	click_btnseat(seat97);
	    }

	    @FXML
	    void chooseseat98(ActionEvent event) {
	    	click_btnseat(seat98);
	    }

	    @FXML
	    void chooseseat99(ActionEvent event) {
	    	click_btnseat(seat99);
	    }
	   
    @FXML
    void chooseseat1(ActionEvent event) {	
    	click_btnseat(seat1);
    	

    }
    
    @FXML
    void chooseseat2(ActionEvent event) {
    	click_btnseat(seat2);

    }
    
    @FXML
    void chooseseat3(ActionEvent event) {	
    	click_btnseat(seat3);

    }
    @FXML
    void chooseseat4(ActionEvent event) {	
    	click_btnseat(seat4);

    }
    @FXML
    void chooseanotherseat(ActionEvent event) {	
    	pane2.setVisible(false);
    	pane3.setVisible(false);

    }
    
    @FXML
    void gohome(ActionEvent event) {
    	Platform.runLater(() -> {
			try {
				App.setRoot("primary");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});



    }
    
    @FXML
    void browsemovies(ActionEvent event) {	
    	Platform.runLater(() -> {
			try {
				App.setRoot("browse_movies");
				//System.out.println("after the load line of brwose movies in primary");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});


    }
    
    
  
    @FXML
    void answeryes(ActionEvent event) {
    	num_chair=mybutton.getText();
    	System.out.println(num_chair);
    	mybutton.setStyle("-fx-background-color: red;");
    	Movie m= browse_moviesController.selectedMovie;
		TripleObject msg = new TripleObject("update mapchair with new seat",(int)m.getId(),browse_moviesController.time_movie,num_chair);
    	try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	seatnum.setText(num_chair);
    	pane1.setVisible(false);
    	pane4.setVisible(true);
    	
    	    	

    }
    
    @FXML
    void answerno(ActionEvent event) {
    	mybutton.setStyle("-fx-background-color: green;");
    	pane1.setVisible(false);
    	pane3.setVisible(false);


    }
    private void click_btnseat(Button mybtn) {
    	if(mybtn.getStyle().equals("-fx-background-color: red;")) { 
    		pane3.setVisible(true);
    		pane2.setVisible(true);
    		return;
    	}
    	mybtn.setStyle("-fx-background-color: blue;");
    	mybutton=mybtn;
    	pane3.setVisible(true);
    	pane1.setVisible(true);
    }
    
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		
		Movie m= browse_moviesController.selectedMovie;
		int movie_id;
		for(int i=0;i<SimpleClient.mc.size();i++) {
			switch(SimpleClient.mc.get(i)){
			case 1:
				seat1.setStyle("-fx-background-color: red;");
				break;
			case 2:
				seat2.setStyle("-fx-background-color: red;");
				break;
			case 3:
				seat3.setStyle("-fx-background-color: red;");
				break;
			case 4:
				seat4.setStyle("-fx-background-color: red;");
				break;
			case 5:
				seat5.setStyle("-fx-background-color: red;");
				break;	
			case 6:
				seat6.setStyle("-fx-background-color: red;");
				break;	
			case 7:
				seat7.setStyle("-fx-background-color: red;");
				break;
			case 8:
				seat8.setStyle("-fx-background-color: red;");
				break;
			case 9:
				seat9.setStyle("-fx-background-color: red;");
				break;
			case 10:
				seat10.setStyle("-fx-background-color: red;");
				break;	
			case 11:
				seat11.setStyle("-fx-background-color: red;");
				break;
			case 12:
				seat12.setStyle("-fx-background-color: red;");
				break;
			case 13:
				seat13.setStyle("-fx-background-color: red;");
				break;
			case 14:
				seat14.setStyle("-fx-background-color: red;");
				break;
			case 15:
				seat15.setStyle("-fx-background-color: red;");
				break;
			case 16:
				seat16.setStyle("-fx-background-color: red;");
				break;
			case 17:
				seat17.setStyle("-fx-background-color: red;");
				break;
			case 18:
				seat18.setStyle("-fx-background-color: red;");
				break;
			case 19:
				seat19.setStyle("-fx-background-color: red;");
				break;
			case 20:
				seat20.setStyle("-fx-background-color: red;");
				break;
			case 21:
				seat21.setStyle("-fx-background-color: red;");
				break;
			case 22:
				seat22.setStyle("-fx-background-color: red;");
				break;
			case 23:
				seat23.setStyle("-fx-background-color: red;");
				break;
			case 24:
				seat24.setStyle("-fx-background-color: red;");
				break;
			case 25:
				seat25.setStyle("-fx-background-color: red;");
				break;
			case 26:
				seat26.setStyle("-fx-background-color: red;");
				break;
			case 27:
				seat27.setStyle("-fx-background-color: red;");
				break;
			case 28:
				seat28.setStyle("-fx-background-color: red;");
				break;
			case 29:
				seat29.setStyle("-fx-background-color: red;");
				break;
			case 30:
				seat30.setStyle("-fx-background-color: red;");
				break;
			case 31:
				seat31.setStyle("-fx-background-color: red;");
				break;	
			case 32:
				seat32.setStyle("-fx-background-color: red;");
				break;	
			case 33:
				seat33.setStyle("-fx-background-color: red;");
				break;
			case 34:
				seat34.setStyle("-fx-background-color: red;");
				break;
			case 35:
				seat35.setStyle("-fx-background-color: red;");
				break;
			case 36:
				seat36.setStyle("-fx-background-color: red;");
				break;	
			case 37:
				seat37.setStyle("-fx-background-color: red;");
				break;
			case 38:
				seat38.setStyle("-fx-background-color: red;");
				break;
			case 39:
				seat39.setStyle("-fx-background-color: red;");
				break;
			case 40:
				seat40.setStyle("-fx-background-color: red;");
				break;
			case 41:
				seat41.setStyle("-fx-background-color: red;");
				break;
			case 42:
				seat42.setStyle("-fx-background-color: red;");
				break;
			case 43:
				seat43.setStyle("-fx-background-color: red;");
				break;
			case 45:
				seat45.setStyle("-fx-background-color: red;");
				break;
			case 46:
				seat46.setStyle("-fx-background-color: red;");
				break;
			case 47:
				seat47.setStyle("-fx-background-color: red;");
				break;
			case 48:
				seat48.setStyle("-fx-background-color: red;");
				break;
			case 49:
				seat49.setStyle("-fx-background-color: red;");
				break;
			case 50:
				seat50.setStyle("-fx-background-color: red;");
				break;
			case 51:
				seat51.setStyle("-fx-background-color: red;");
				break;
			case 52:
				seat52.setStyle("-fx-background-color: red;");
				break;
			case 53:
				seat53.setStyle("-fx-background-color: red;");
				break;	
			case 54:
				seat54.setStyle("-fx-background-color: red;");
				break;
			case 55:
				seat55.setStyle("-fx-background-color: red;");
				break;	
			case 56:
				seat56.setStyle("-fx-background-color: red;");
				break;	
			case 57:
				seat57.setStyle("-fx-background-color: red;");
				break;
			case 58:
				seat58.setStyle("-fx-background-color: red;");
				break;
			case 59:
				seat59.setStyle("-fx-background-color: red;");
				break;
			case 60:
				seat60.setStyle("-fx-background-color: red;");
				break;	
			case 61:
				seat61.setStyle("-fx-background-color: red;");
				break;
			case 62:
				seat62.setStyle("-fx-background-color: red;");
				break;
			case 63:
				seat63.setStyle("-fx-background-color: red;");
				break;
			case 64:
				seat64.setStyle("-fx-background-color: red;");
				break;
			case 65:
				seat65.setStyle("-fx-background-color: red;");
				break;
			case 66:
				seat66.setStyle("-fx-background-color: red;");
				break;
			case 67:
				seat67.setStyle("-fx-background-color: red;");
				break;
			case 68:
				seat68.setStyle("-fx-background-color: red;");
				break;
			case 69:
				seat69.setStyle("-fx-background-color: red;");
				break;
			case 70:
				seat70.setStyle("-fx-background-color: red;");
				break;
			case 71:
				seat71.setStyle("-fx-background-color: red;");
				break;
			case 72:
				seat72.setStyle("-fx-background-color: red;");
				break;
			case 73:
				seat73.setStyle("-fx-background-color: red;");
				break;
			case 74:
				seat74.setStyle("-fx-background-color: red;");
				break;
			case 75:
				seat75.setStyle("-fx-background-color: red;");
				break;
			case 76:
				seat76.setStyle("-fx-background-color: red;");
				break;
			case 77:
				seat77.setStyle("-fx-background-color: red;");
				break;
			case 78:
				seat78.setStyle("-fx-background-color: red;");
				break;
			case 79:
				seat79.setStyle("-fx-background-color: red;");
				break;
			case 80:
				seat80.setStyle("-fx-background-color: red;");
				break;
			case 81:
				seat81.setStyle("-fx-background-color: red;");
				break;	
			case 82:
				seat82.setStyle("-fx-background-color: red;");
				break;	
			case 83:
				seat83.setStyle("-fx-background-color: red;");
				break;
			case 84:
				seat84.setStyle("-fx-background-color: red;");
				break;
			case 85:
				seat85.setStyle("-fx-background-color: red;");
				break;
			case 86:
				seat86.setStyle("-fx-background-color: red;");
				break;	
			case 87:
				seat87.setStyle("-fx-background-color: red;");
				break;
			case 88:
				seat88.setStyle("-fx-background-color: red;");
				break;
			case 89:
				seat89.setStyle("-fx-background-color: red;");
				break;
			case 90:
				seat90.setStyle("-fx-background-color: red;");
				break;
			case 91:
				seat91.setStyle("-fx-background-color: red;");
				break;
			case 92:
				seat92.setStyle("-fx-background-color: red;");
				break;
			case 93:
				seat93.setStyle("-fx-background-color: red;");
				break;
			case 95:
				seat95.setStyle("-fx-background-color: red;");
				break;
			case 96:
				seat96.setStyle("-fx-background-color: red;");
				break;
			case 97:
				seat97.setStyle("-fx-background-color: red;");
				break;
			case 98:
				seat98.setStyle("-fx-background-color: red;");
				break;
			case 99:
				seat99.setStyle("-fx-background-color: red;");
				break;
			case 100:
				seat100.setStyle("-fx-background-color: red;");
				break;
			case 94:
				seat94.setStyle("-fx-background-color: red;");
				break;
			case 44:
				seat44.setStyle("-fx-background-color: red;");
				break;
				

			}
			
		}
		
		
	}

   
}
