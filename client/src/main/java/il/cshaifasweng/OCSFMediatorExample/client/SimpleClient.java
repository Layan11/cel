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
	public static List<Movie> moviesList = new ArrayList<Movie>();
	public static List<MovieTimes> movieTimes = new ArrayList<MovieTimes>();
	public static List<String> PRCMovies = new ArrayList<String>();
	public static List<String> PRCPrices = new ArrayList<String>();

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		TripleObject triple_msg = (TripleObject) msg;
		String myMsg = triple_msg.getMsg();

		if (myMsg.equals("no such movie")) {
			EventBus.getDefault().post(new NoSuchMovieEvent());
		}

		if (myMsg.equals("Movie saved")) {
			EventBus.getDefault().post(new GotcsnewMovieEvent());
		}

		if (myMsg.equals("More Info Movie")) {
			List<Movie> moreinfo_movies = triple_msg.getMovies();
			moreinfo_movies = triple_msg.getMovies();
			if (moreinfo_movies != null) {
				moviesList = moreinfo_movies;
				System.out.println("movieList size: " + moviesList.size());
				EventBus.getDefault().post(new GotMoreInfoEvent());
			} else {
				System.out.println("MOREINFO_movies is null");
			}
		}

		if (myMsg.equals("All Watch At Home Movies Movies")) {
			List<Movie> Homemovies = triple_msg.getMovies();
			Homemovies = triple_msg.getMovies();
			if (Homemovies != null) {
				moviesList = Homemovies;
				EventBus.getDefault().post(new GotWatchAtHomeEvent());
			} else {
				System.out.println("Homemovies is null");
			}
		}

		if (myMsg.equals("All Coming Soon Movies")) {
			List<Movie> CSmovies = triple_msg.getMovies();
			CSmovies = triple_msg.getMovies();
			if (CSmovies != null) {
				moviesList = CSmovies;
				EventBus.getDefault().post(new GotComingSoonEvent());
			} else {
				System.out.println("CSmovies is null");
			}
		}

		if (myMsg.equals("Haifa Movies")) {
			List<Movie> movies = triple_msg.getMovies();
			movies = triple_msg.getMovies();
			if (movies != null) {
				moviesList = movies;
				EventBus.getDefault().post(new GotfilteredMoviesEvent());
			} else {
				System.out.println("movies is null");
			}
		}
		if (myMsg.equals("Shefa-Amr Movies")) {
			List<Movie> movies = triple_msg.getMovies();
			movies = triple_msg.getMovies();
			if (movies != null) {
				moviesList = movies;
				EventBus.getDefault().post(new GotfilteredMoviesEvent());
			} else {
				System.out.println("movies is null");
			}
		}

		if (myMsg.equals("All Movies")) {
			List<Movie> movies = triple_msg.getMovies();
			movies = triple_msg.getMovies();
			if (movies != null) {
				moviesList = movies;
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
				movieTimes = MT;
				System.out.println("printing screening times in client :" + movieTimes.get(0).getTimes());
				System.out.println("printing screening times in client1 :" + movieTimes.get(1).getTimes());
				System.out.println("printing screening times in client2 :" + movieTimes.get(2).getTimes());
				System.out.println("printing screening times in client3 :" + movieTimes.get(3).getTimes());
				System.out.println("printing screening times in client4 :" + movieTimes.get(4).getTimes());

				System.out.println("printing the size og movietimes in client" + movieTimes.size());
				if (movieTimes.size() > 5) {
					System.out.println("in the if in client");
					System.out.println("printing 6th element of movietimes " + movieTimes.get(5).getId());
				}
				EventBus.getDefault().post(new GotScreeningTimesEvent());
			} else {
				System.out.println("MT is null");
			}
		}
		if (myMsg.equals("No such screening time to delete")) {
			EventBus.getDefault().post(new NoScreeningTimeToDeleteEvent());
		}
		if (myMsg.equals("No such screening time to update")) {
			EventBus.getDefault().post(new NoScreeningTimeToUpdateEvent());
		}
		if (myMsg.equals("No such user")) {
			EventBus.getDefault().post(new NoSuchUserEvent());
		}
		if (myMsg.startsWith("User found")) {
			EventBus.getDefault().post(new UserFoundEvent(myMsg));
		}
		if (myMsg.equals("Got the wanted movie")) {
			EventBus.getDefault().post(new gotMovieActorsEvent(triple_msg.getList()));
		}
		if (myMsg.equals("user found")) {
			EventBus.getDefault().post(new PermessionEvent());
		}
		if (myMsg.equals("Movie Deleted")) {
			EventBus.getDefault().post(new GotMovieDeletedEvent());
		}
		if (myMsg.equals("PRC movies")) {
			PRCMovies = triple_msg.getList();
			EventBus.getDefault().post(new GotPRCMoviesEvent());
		}
		if (myMsg.equals("PRC prices")) {
			PRCPrices = triple_msg.getList();
			EventBus.getDefault().post(new GotPRCPricesEvent());
		}
		if (myMsg.equals("Updated chart movies")) {
			PRCMovies = triple_msg.getList();
			EventBus.getDefault().post(new GotUpdatedChartMoviesEvent());
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}