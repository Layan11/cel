package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoreInfoFilteredController implements Initializable {
	@FXML // fx:id="EngNametxt"
	private TextField EngNametxt; // Value injected by FXMLLoader

	@FXML // fx:id="movie_img"
	private ImageView movie_img; // Value injected by FXMLLoader

	@FXML // fx:id="HebNametxt"
	private TextField HebNametxt; // Value injected by FXMLLoader

	@FXML // fx:id="Producertxt"
	private TextField Producertxt; // Value injected by FXMLLoader

	@FXML // fx:id="Summarytxt"
	private TextArea Summarytxt; // Value injected by FXMLLoader

	@FXML // fx:id="Back"
	private Button Back; // Value injected by FXMLLoader

	@FXML // fx:id="ArbNametxt"
	private TextField ArbNametxt; // Value injected by FXMLLoader

	@FXML // fx:id="Lentxt"
	private TextField Lentxt; // Value injected by FXMLLoader

	@FXML // fx:id="ActorsTxt"
	private TextArea ActorsTxt; // Value injected by FXMLLoader

	@FXML
	void goback(ActionEvent event) throws Exception {
		TripleObject msg = new TripleObject("Browse movies", null, null);
		SimpleClient.getClient().sendToServer(msg);

	}

	@Subscribe
	public void onData111(GotMoviesEvent event) {
		Platform.runLater(() -> {
			try {
				App.setRoot("FilteredMovies");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Subscribe
	public void onGotMovieActors(gotMovieActorsEvent event) {
		Platform.runLater(() -> {
			List<String> actors = event.getMovieActors();
			String delim = "\n";

			StringBuilder sb = new StringBuilder();

			int i = 0;
			while (i < actors.size() - 1) {
				sb.append(actors.get(i));
				sb.append(delim);
				i++;
			}
			sb.append(actors.get(i));

			String res = sb.toString();
			System.out.println(res);
			ActorsTxt.setText(res);
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);

		Movie chosen_movie = new Movie();
		chosen_movie = SimpleClient.moviesList.get(0);

		EngNametxt.setText(chosen_movie.getEngName());
		HebNametxt.setText(chosen_movie.getHebName());
		Producertxt.setText(chosen_movie.getProducer());
		Summarytxt.setText(chosen_movie.getSummary());
		if (chosen_movie.getArbName() != null) {
			ArbNametxt.setText(chosen_movie.getArbName());
		}
		Lentxt.setText(Integer.toString(chosen_movie.getLength()) + " mins");
		TripleObject msg = new TripleObject("Get movie actors " + chosen_movie.getEngName(), null, null);
		try {
			SimpleClient.getClient().sendToServer(msg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		InputStream stream1;
		try {
			stream1 = new FileInputStream(chosen_movie.getImage());
			System.out.println(stream1);
			Image img = new Image(stream1);
			movie_img.setImage(img);
			movie_img.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
