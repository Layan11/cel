package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;

public class SimpleClient extends AbstractClient {
	private static SimpleClient client = null;
	public static List<Movie> moviesList = new ArrayList<Movie>();
	public static List<String> movieTimes = new ArrayList<String>();
	public static List<String> PRCMovies = new ArrayList<String>();
	public static List<String> PRCPrices = new ArrayList<String>();
	public static List<String> MovieDates = new ArrayList<String>();
	public static List<String> MovieNames = new ArrayList<String>();

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

		if (myMsg.equals("Movie Times")) {
			movieTimes = triple_msg.getList();
			if (movieTimes != null) {
				EventBus.getDefault().post(new GotScreeningTimesEvent());
			} else {
				System.out.println("MT is null");
			}
		}

		if (myMsg.equals("No such user")) {
			EventBus.getDefault().post(new NoSuchUserEvent());
		}
		if (myMsg.equals("User is already connected")) {
			EventBus.getDefault().post(new UserIsConnectedEvent());
		}
		if (myMsg.startsWith("User found")) {
			EventBus.getDefault().post(new UserFoundEvent(myMsg, triple_msg.getList()));
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
		if (myMsg.equals("Dates")) {
			MovieDates = triple_msg.getList();
			EventBus.getDefault().post(new GotMovieDatesEvent());
		}
		if (myMsg.equals("Deleted screening time")) {
			EventBus.getDefault().post(new GotUpdatedScreeningsEvent());
		}
		if (myMsg.equals("Filtered movies by date")) {
			if (triple_msg.getMovieTimes().size() > 0) {
				MovieNames = triple_msg.getList();
				movieTimes = triple_msg.getMovieTimes().get(0).getTimes();
				MovieDates = triple_msg.getMovieTimes().get(0).getDate();
			}
			EventBus.getDefault().post(new GotFilteredMovieByDatesEvent());
		}
		if (myMsg.equals("All Hybrid Movies") || myMsg.equals("Updated hybrid movies")) {
			moviesList = triple_msg.getMovies();
			EventBus.getDefault().post(new GotHybridMoviesEvent());
		}

		if (myMsg.equals("You get 100% refound")) {
			msg = "You get 100% refound";
			System.out.println("100% refund");
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);

		}
		if (myMsg.equals("You get 50% refound")) {
			msg = "You get 50% refound";
			System.out.println("50% refund");
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.equals("You get no refound")) {
			msg = "You get no refound";
			System.out.println("no refund pls");
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);

		}
		if (myMsg.equals("no such link")) {
			msg = "No Such Link";
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.equals("no such Ticket")) {
			msg = "no such Ticket";
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.equals("no such Package")) {
			msg = "no such Package";
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.startsWith("Your Ticket ID is")) {

			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.startsWith("Your Link ID is")) {

			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.startsWith("Your Package ID is")) {
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);

		}
		if (myMsg.startsWith("Tickets Number")) {
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.startsWith("Package have 0 Ticks")) {
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}

	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}