package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;

public class SimpleClient extends AbstractClient {
	private static SimpleClient client = null;
	public static ArrayList<Movie> moviesList;
	public static ArrayList<MovieTimes> movieTimes;
	public static String msg = "i";

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		TripleObject triple_msg = (TripleObject) msg;
		String myMsg = triple_msg.getMsg();
//		if (msg.getClass().equals(Warning.class)) {
//			EventBus.getDefault().post(new WarningEvent((Warning) msg));
//		}

		if (myMsg.equals("no such movie")) {
			msg = ("no such movie");
		}

		if (myMsg.equals("All Movies")) {
			ArrayList<Movie> movies = new ArrayList<Movie>();
			movies = triple_msg.getMovies();
			if (movies != null) {
				moviesList = new ArrayList<Movie>();
				moviesList = movies;
				// System.out.println(movies.get(1).getEngName());
			} else {
				System.out.println("movies is null");
			}
		}

		if (myMsg.equals("All Movies Times")) {
			ArrayList<MovieTimes> MT = new ArrayList<MovieTimes>();
			MT = triple_msg.getMovieTimes();
			if (MT != null) {
				movieTimes = new ArrayList<MovieTimes>();
				movieTimes = MT;
				System.out.println("IN SIMPLE CLIENT: " + movieTimes.get(0).getTimes());
			} else {
				System.out.println("MT is null");
			}
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}
