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
	public static int PackageNumOfTickets;
	public static List<Integer> list2 = new ArrayList<Integer>();
	public static List<String> ComplaintsContent = new ArrayList<String>();
	public static List<String> ComplaintsUser = new ArrayList<String>();

	public static String restrictionAns;

	public static List<String> ComplaintTime = new ArrayList<String>();

	public static List<String> messageContent = new ArrayList<String>();
	public static List<String> FromMSG = new ArrayList<String>();
	public static List<String> MSGid = new ArrayList<String>();

	public static List<Integer> mc = new ArrayList<Integer>();
	public static int mapchair_id = 0;

	public static int[] ComplaintsPerMArraay;

	public static List<String> restrictedDays = new ArrayList<String>();
	public static String seatNumToDelete;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		TripleObject triple_msg = (TripleObject) msg;
		String myMsg = triple_msg.getMsg();
		System.out.println("in client , the msg is : " + myMsg);
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
		if (myMsg.equals("done removing")) {
			EventBus.getDefault().post(new doneRemovingEvent());
		}
		if (myMsg.equals("Filtered movies by date")) {
			if (triple_msg.getMovieTimes().size() > 0) {
				MovieNames = triple_msg.getList();
				movieTimes = triple_msg.getMovieTimes().get(0).getTimes();
				MovieDates = triple_msg.getMovieTimes().get(0).getDate();
			} else {
				MovieNames = null;
				movieTimes = null;
				MovieDates = null;
			}
			EventBus.getDefault().post(new GotFilteredMovieByDatesEvent());
		}
		if (myMsg.equals("All Hybrid Movies") || myMsg.equals("Updated hybrid movies")) {
			moviesList = triple_msg.getMovies();
			EventBus.getDefault().post(new GotHybridMoviesEvent());
		}
		if (myMsg.equals("All restricted days")) {
			restrictedDays = triple_msg.getList();
			EventBus.getDefault().post(new GotrestrictedDaysEvent());
		}
		if (myMsg.equals("All updated restricted days")) {
			restrictedDays = triple_msg.getList();
			EventBus.getDefault().post(new GotUpdatedRestrictedDaysEvent());
		}

		if (myMsg.equals("You get 100% refound")) {
			msg = "You get 100% refound";
			String Msg = "You will get 100% refund";
			TripleObject msg2 = new TripleObject(Msg, null, null);
			EventBus.getDefault().post(msg2);

		}
		if (myMsg.equals("You get 50% refound")) {
			msg = "You will get 50% refound";
			String Msg = "You will get 50% refund";
			TripleObject msg2 = new TripleObject(Msg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.equals("You get no refound")) {
			msg = "You get no refound";
			String Msg = "You will get no refund";
			TripleObject msg2 = new TripleObject(Msg, null, null);
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
		if (myMsg.startsWith("package num of tickets")) {
			PackageNumOfTickets = triple_msg.getMovies().get(0).getLength();
			EventBus.getDefault().post(new GotNumOfPacTicsEvent());
			System.out.println("in the if in the client");
		}
		if (myMsg.startsWith("package num of tickets2")) {
			PackageNumOfTickets = triple_msg.getMovies().get(0).getLength();
			EventBus.getDefault().post(new GotNumOfPacTicsEvent2());
		}
		if (myMsg.startsWith("You already have a package")) {
			EventBus.getDefault().post(new AlreadyHavePackageEvent());
		}
		if (myMsg.startsWith("found movie to update price")) {
			EventBus.getDefault().post(new GotMovieToUpdatePriceEvent());
		}
		if (myMsg.startsWith("All reports")) {
			list2 = triple_msg.getList2();
			EventBus.getDefault().post(new GotReportEvent());
		}
		if (myMsg.startsWith("This Link is not for you")) {
			TripleObject msg2 = new TripleObject(myMsg, null, null);
			EventBus.getDefault().post(msg2);
		}
		if (myMsg.startsWith("checked day")) {
			restrictionAns = triple_msg.getList().get(0);
			EventBus.getDefault().post(new GotRestrictedAnsEvent());
		}
		if (myMsg.equals("someone else buy this ticket")) {
			System.out.println("i am here");
			mc = triple_msg.getMapChair();
			EventBus.getDefault().post(new busyseat());
		}
		if (myMsg.startsWith("found ticket ")) {
			String seatNum = myMsg.substring(13);
			seatNumToDelete = seatNum;
			EventBus.getDefault().post(new GotSeatToDeleteEvent());
		}
		// elinjammal
		if (myMsg.startsWith("All complaints")) {
			ComplaintsContent = triple_msg.getComplaintsContent();
			ComplaintsUser = triple_msg.getComplaintsUser();
			ComplaintTime = triple_msg.getComplaintTime();
			EventBus.getDefault().post(new gotallcomplaintsevent());
		}

		if (myMsg.startsWith("All messages")) {
			System.out.println("in client All messages");
			messageContent = triple_msg.getmessageConetxt();
			FromMSG = triple_msg.getFromMSG();
			MSGid = triple_msg.getMSGid();
			// ComplaintTime = triple_msg.getComplaintTime();
			EventBus.getDefault().post(new gotallmessagessevent());
		}

		// ***saleh***
		if (myMsg.startsWith("your mapchair")) {// mapchair id mawgood b index 14
			mc = triple_msg.getMapChair();
			mapchair_id = Integer.parseInt(myMsg.substring(14));
			System.out.println("your map chair, simple client, mapchair id = " + mapchair_id);
			EventBus.getDefault().post(new gotMapChairevent());
		}

		if (myMsg.equals("someone else buy this ticket")) {
			System.out.println("i am here");
			mc = triple_msg.getMapChair();
			for (int i = 0; i < mc.size(); i++)
				System.out.println(mc.get(i));
			EventBus.getDefault().post(new busyseat());

		}

		// ***saleh***

		//
		if (myMsg.equals("ComplaintsReports")) {
			ComplaintsPerMArraay = triple_msg.getComplaintsPerMArraay();
			EventBus.getDefault().post(new GotComplaintsReportstEvent());
		}

		if (myMsg.equals("The hall is full")) {
			EventBus.getDefault().post(new FullHalltEvent());
		}
		if (myMsg.startsWith("All messages")) {
			System.out.println("in client All messages");
			messageContent = triple_msg.getmessageConetxt();
			FromMSG = triple_msg.getFromMSG();
			// ComplaintTime = triple_msg.getComplaintTime();
			EventBus.getDefault().post(new gotallmessagessevent());
		}

		if (myMsg.equals("Canceled Date")) {
			EventBus.getDefault().post(new GotCanceledDateEvent());
		}

		if (myMsg.equals("The hall is full")) {
			EventBus.getDefault().post(new FullHalltEvent());
		}

	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
}
