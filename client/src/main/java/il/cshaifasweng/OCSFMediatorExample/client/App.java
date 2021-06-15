package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import il.cshaifasweng.OCSFMediatorExample.entities.link;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private SimpleClient client;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws IOException {
		client = SimpleClient.getClient();
		client.openConnection();
		scene = new Scene(loadFXML("primary"));
		stage.setScene(scene);
		App.stage = stage;
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
		stage.sizeToScene();

	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		// EventBus.getDefault().unregister(this);
		super.stop();
	}

	public static void main(String[] args) {

		launch();
	}

}