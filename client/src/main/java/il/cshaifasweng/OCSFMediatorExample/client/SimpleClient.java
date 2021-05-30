package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;

public class SimpleClient extends AbstractClient {
	private static SimpleClient client = null;
	public static List<Movie> moviesList;// = new ArrayList<Movie>();
	public static List<MovieTimes> movieTimes = new ArrayList<MovieTimes>();

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
		
		if (myMsg.equals("All Coming Soon Movies"))
		{
			List<Movie> CSmovies = triple_msg.getMovies();
			CSmovies = triple_msg.getMovies();
			if (CSmovies != null) {
				// moviesList = new ArrayList<Movie>();
				moviesList = CSmovies;
				//System.out.println("First element: " + moviesList.get(0).getEngName());
				//System.out.println("Second element: " + moviesList.get(1).getEngName());
				EventBus.getDefault().post(new GotComingSoonEvent());
			} else {
				System.out.println("CSmovies is null");
			}
		}

		if (myMsg.equals("All Movies")) {
//			System.out.println("in all movies in client");
//			ArrayList<Movie> movies = new ArrayList<Movie>();
			List<Movie> movies = triple_msg.getMovies();
			movies = triple_msg.getMovies();
			if (movies != null) {
				// moviesList = new ArrayList<Movie>();
				moviesList = movies;
				System.out.println("First element: " + moviesList.get(0).getEngName());
				System.out.println("Second element: " + moviesList.get(1).getEngName());
				EventBus.getDefault().post(new GotMoviesEvent());
			} else {
				System.out.println("movies is null");
			}
		}

		if (myMsg.equals("All Movies Times")) {
			List<MovieTimes> MT = new ArrayList<MovieTimes>();
			MT = triple_msg.getMovieTimes();
			if (MT != null) {
				EventBus.getDefault().post(new GotScreeningTimesEvent());
				//List<MovieTimes> movieTimes = new ArrayList<MovieTimes>();
				//movieTimes = new ArrayList<MovieTimes>();
				movieTimes = MT;
				System.out.println("printing screening times in client :" + movieTimes.get(0).getTimes());
				System.out.println("printing screening times in client1 :" + movieTimes.get(1).getTimes());
				System.out.println("printing screening times in client2 :" + movieTimes.get(2).getTimes());
				System.out.println("printing screening times in client3 :" + movieTimes.get(3).getTimes());
				System.out.println("printing screening times in client4 :" + movieTimes.get(4).getTimes());
				//System.out.println("printing selected movie times in client: " + browse_moviesController.selectedMovie.getMovieTimes().getTimes());
				//System.out.println("printing screening times in client MT:" + movieTimes.get(0).getTimes());
				//System.out.println("printing screening times in client MT2:" + movieTimes.get(1).getTimes());
				EventBus.getDefault().post(new GotScreeningTimesEvent());
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
