package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.Package;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequestsChart;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.link;

public class App {

	private static SimpleServer server;
	public static Session session;
	public static SessionFactory sessionFactory;

	private static SessionFactory getSessionFactory() throws HibernateException {

		Configuration configuration = new Configuration();

		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(MovieTimes.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(PriceRequestsChart.class);
		configuration.addAnnotatedClass(link.class);
		configuration.addAnnotatedClass(Ticket.class);
		configuration.addAnnotatedClass(Package.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void generateData() throws Exception {
		// Now broadcast in branches
//Aladdin
		String AladdinImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\Aladdin_poster_1992.jpg");
		List<String> AladdinActorsList = new ArrayList<String>();
		List<String> AladdinTimes = new ArrayList<String>();
		List<String> AladdinDates = new ArrayList<String>();
		List<String> AladdinBranches = new ArrayList<String>();
		AladdinActorsList.add("Mena Massoud");
		AladdinActorsList.add("Naomi Scott");
		AladdinTimes.add("18:00");
		AladdinTimes.add("20:00");
		AladdinDates.add("21/08/2021");
		AladdinDates.add("06/02/2021");
		AladdinBranches.add("Haifa");
		AladdinBranches.add("Haifa");
		MovieTimes AladdinMovieTimes = new MovieTimes(AladdinTimes);
		AladdinMovieTimes.setDate(AladdinDates);
		session.save(AladdinMovieTimes);
		Movie AladdinMovie = new Movie("Aladdin", AladdinActorsList, 128, "אלאדין",
				"Aladdin, a kind thief, woos Jasmine, the princess of Agrabah, with the help of Genie. When Jafar, the grand vizier, tries to usurp the king, Jasmine, Aladdin and Genie must stop him from succeeding.",
				"Jonathan Eirich", 20, AladdinImage, AladdinMovieTimes, "Haifa");
		AladdinMovie.setType(0);
		AladdinMovie.setArbName("علاء الدين");
		session.save(AladdinMovie);
		session.flush();

//Shrek
		String ShrekImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\Shrek.jpg");
		List<String> ShrekActorsList = new ArrayList<String>();
		List<String> ShrekTimes = new ArrayList<String>();
		List<String> ShrekDates = new ArrayList<String>();
		List<String> ShrekBranches = new ArrayList<String>();
		ShrekActorsList.add("Shrek");
		ShrekActorsList.add("Princess Fiona");
		ShrekTimes.add("17:30");
		ShrekBranches.add("Haifa");
		ShrekDates.add("01/07/2021");
		MovieTimes ShrekMovieTimes = new MovieTimes(ShrekTimes);
		ShrekMovieTimes.setDate(ShrekDates);
		session.save(ShrekMovieTimes);
		Movie ShrekMovie = new Movie("Shrek", ShrekActorsList, 95, "שרק",
				"Shrek, an ogre, embarks on a journey with a donkey to rescue Princess Fiona from a vile lord and regain his swamp.",
				"John H. Williams", 35, ShrekImage, ShrekMovieTimes, "Haifa");
		ShrekMovie.setType(0);
		ShrekMovie.setArbName("شريك");
		session.save(ShrekMovie);
		session.flush();
//Snow White
		String SnowWhiteImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\SnowWhite.jpg");
		List<String> SnowWhiteActorsList = new ArrayList<String>();
		List<String> SnowWhiteTimes = new ArrayList<String>();
		List<String> SnowWhiteDates = new ArrayList<String>();
		List<String> SnowWhiteBranches = new ArrayList<String>();
		SnowWhiteActorsList.add("Snow White");
		SnowWhiteActorsList.add("Dopey");
		SnowWhiteTimes.add("20:15");
		SnowWhiteBranches.add("Shefa-Amr");
		SnowWhiteDates.add("01/09/2021");
		MovieTimes SnowWhiteMovieTimes = new MovieTimes(SnowWhiteTimes);
		SnowWhiteMovieTimes.setDate(SnowWhiteDates);
		session.save(SnowWhiteMovieTimes);
		Movie SnowWhiteMovie = new Movie("Snow White", SnowWhiteActorsList, 88, "שלגייה",
				"When Snow White, a princess, is exiled by her stepmother, an evil queen who wants to kill her, she runs into a forest. Soon, she is rescued by seven dwarfs who form a friendship with her.",
				"Walt Disney", 4, SnowWhiteImage, SnowWhiteMovieTimes, "Shefa-Amr");
		SnowWhiteMovie.setType(0);
		SnowWhiteMovie.setArbName("بيضاء الثلج");
		session.save(SnowWhiteMovie);
		session.flush();

//Fast and Furious
		String FnFImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\FastAndTheFurious.jpg");
		List<String> FnFActorsList = new ArrayList<String>();
		List<String> FnFTimes = new ArrayList<String>();
		List<String> FnFDates = new ArrayList<String>();
		List<String> FnFBranches = new ArrayList<String>();
		FnFActorsList.add("Vin Diesel");
		FnFActorsList.add("Paul Walker");
		FnFTimes.add("19:45");
		FnFTimes.add("21:30");
		FnFBranches.add("Shefa-Amr");
		FnFBranches.add("Shefa-Amr");
		FnFDates.add("24/07/2021");
		FnFDates.add("10/10/2021");
		MovieTimes FnFMovieTimes = new MovieTimes(FnFTimes);
		FnFMovieTimes.setDate(FnFDates);
		session.save(FnFMovieTimes);
		Movie FastAndFuriousMovie = new Movie("Fast and Furious", FnFActorsList, 107, "מהיר ועצבני",
				"A spate of high-speed robberies in LA brings street racer Dominic Toretto and his crew under the LAPD scanner. FBI agent Brian goes undercover and befriends Toretto in a bid to investigate the matter.",
				"Neal H. Moritz", 45, FnFImage, FnFMovieTimes, "Shefa-Amr");
		FastAndFuriousMovie.setType(0);
		FastAndFuriousMovie.setArbName("السرعة والغضب");
		session.save(FastAndFuriousMovie);
		session.flush();

//Dumbo
		String DumboImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\Dumbo.jpg");
		List<String> DumboActorsList = new ArrayList<String>();
		List<String> DumboTimes = new ArrayList<String>();
		List<String> DumboDates = new ArrayList<String>();
		List<String> DumboBranches = new ArrayList<String>();
		DumboActorsList.add("Eva Green");
		DumboActorsList.add("Colin Farrell");
		DumboTimes.add("19:50");
		DumboTimes.add("21:30");
		DumboBranches.add("Haifa");
		DumboBranches.add("Haifa");
		DumboDates.add("13/07/2021");
		DumboDates.add("24/07/2021");
		MovieTimes DumboMovieTimes = new MovieTimes(DumboTimes);
		DumboMovieTimes.setDate(DumboDates);
		session.save(DumboMovieTimes);
		Movie DumboMovie = new Movie("Dumbo", DumboActorsList, 112, "דמבו",
				"Holt, a circus performer, is tasked with caring for a baby elephant with oversized ears, Dumbo. But when it is discovered that Dumbo can fly, a few wicked men try to take advantage of the situation.",
				"Tim Burton ", 25, DumboImage, DumboMovieTimes, "Haifa");
		DumboMovie.setType(0);
		DumboMovie.setArbName("دامبو");
		session.save(DumboMovie);
		session.flush();

		// coming soon

//minions
		String MinionsImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\ComingSoon\\Minions.jpg");
		Movie minionsMovie = new Movie();
		minionsMovie.setType(1);
		minionsMovie.setEngName("Minions");
		minionsMovie.setHebName("המיניונים");
		minionsMovie.setArbName("مينيونز");
		minionsMovie.setLength(86);
		minionsMovie.setProducer("Mireille Soria");
		minionsMovie.setPrice(80);
		minionsMovie.setSummary(
				"Minions Kevin, Stuart and Bob decide to find a new master. They embark on a global trip and meet Scarlett Overkill, a female super-villain who recruits them and hatches a plan to take over the world.");
		List<String> minionsActorsList = new ArrayList<String>();
		minionsActorsList.add("Ben Stiller");
		minionsActorsList.add("Chris Rock");
		minionsMovie.setActors(minionsActorsList);
		minionsMovie.setImage(MinionsImage);
		session.save(minionsMovie);
		session.flush();

//Madagascar
		String MadagascarImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\ComingSoon\\Madagascar.jpg");
		Movie MadagascarMovie = new Movie();
		MadagascarMovie.setType(1);
		MadagascarMovie.setEngName("Madagascar");
		MadagascarMovie.setHebName("מדגסקר");
		MadagascarMovie.setArbName("مدغشقر");
		MadagascarMovie.setLength(86);
		MadagascarMovie.setProducer("Mireille Soria");
		MadagascarMovie.setPrice(80);
		MadagascarMovie.setSummary(
				"Four spoiled animals from the New York Central Zoo escape with the unintentional help of four fugitive penguins. They subsequently find themselves in Madagascar amidst happy lemurs.");
		List<String> MadagascarActorsList = new ArrayList<String>();
		MadagascarActorsList.add("Ben Stiller");
		MadagascarActorsList.add("Chris Rock");
		MadagascarMovie.setActors(MadagascarActorsList);
		MadagascarMovie.setImage(MadagascarImage);
		session.save(MadagascarMovie);
		session.flush();

//IronMan
		String IronManImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\ComingSoon\\IronManjpg.jpg");
		Movie IronManMovie = new Movie();
		IronManMovie.setType(1);
		IronManMovie.setEngName("IronMan");
		IronManMovie.setHebName("איש הברזל");
		IronManMovie.setArbName("الرجل الحديدي");
		IronManMovie.setLength(126);
		IronManMovie.setProducer("Avi Arad");
		IronManMovie.setPrice(65);
		IronManMovie.setSummary(
				"When Tony Stark, an industrialist, is captured, he constructs a high-tech armoured suit to escape. Once he manages to escape, he decides to use his suit to fight against evil forces to save the world.");
		List<String> IronManActorsList = new ArrayList<String>();
		IronManActorsList.add(" Robert Downey Jr. ");
		IronManActorsList.add(" Terrence Howard");
		IronManMovie.setActors(IronManActorsList);
		IronManMovie.setImage(IronManImage);
		session.save(IronManMovie);
		session.flush();

//KungFuPanda
		String KungFuPandaImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\ComingSoon\\MV5BODJkZTZhKungFuPanda.jpg");
		Movie KungFuPandaMovie = new Movie();
		KungFuPandaMovie.setType(1);
		KungFuPandaMovie.setEngName("KungFuPanda");
		KungFuPandaMovie.setHebName("קונג פו פנדה");
		KungFuPandaMovie.setArbName("كونغ فو باندا");
		KungFuPandaMovie.setLength(92);
		KungFuPandaMovie.setProducer("Melissa Cobb");
		KungFuPandaMovie.setPrice(70);
		KungFuPandaMovie.setSummary(
				"When Po the Panda, a kung fu enthusiast, gets selected as the Dragon Warrior, he decides to team up with the Furious Five and destroy the evil forces that threaten the Valley of Peace.");
		List<String> KungFuPandaActorsList = new ArrayList<String>();
		KungFuPandaActorsList.add("Jack Black");
		KungFuPandaActorsList.add("Dustin Hoffman");
		KungFuPandaMovie.setActors(KungFuPandaActorsList);
		KungFuPandaMovie.setImage(KungFuPandaImage);
		session.save(KungFuPandaMovie);
		session.flush();

		// watch at home

//badboys		
		String BadBoysImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\WatchAtHome\\BadBoys.jpg");
		Movie BadBoysMovie = new Movie();
		BadBoysMovie.setType(2);
		BadBoysMovie.setEngName("BadBoys");
		BadBoysMovie.setHebName("בחורים רעים");
		BadBoysMovie.setArbName("أولاد سيئين");
		BadBoysMovie.setLength(119);
		BadBoysMovie.setProducer("Don Simpson");
		BadBoysMovie.setPrice(50);
		BadBoysMovie.setSummary(
				"Marcus, a family man, and Mike, a ladies' man, are partners in the Miami police. Things get complicated when they assume each other's identity while investigating a drug deal.");
		List<String> BadBoysActorsList = new ArrayList<String>();
		BadBoysActorsList.add("Will Smith");
		BadBoysActorsList.add(" Martin Lawrence");
		BadBoysMovie.setActors(BadBoysActorsList);
		BadBoysMovie.setImage(BadBoysImage);
		session.save(BadBoysMovie);
		session.flush();

//JohnnyEnglish		
		String JohnnyEnglishImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\WatchAtHome\\JohnnyEnglish.jpg");
		Movie JohnnyEnglishMovie = new Movie();
		JohnnyEnglishMovie.setType(2);
		JohnnyEnglishMovie.setEngName("JohnnyEnglish");
		JohnnyEnglishMovie.setHebName("גוני אינגלש");
		JohnnyEnglishMovie.setArbName("جوني انجليش");
		JohnnyEnglishMovie.setLength(88);
		JohnnyEnglishMovie.setProducer("Tim Bevan");
		JohnnyEnglishMovie.setPrice(59);
		JohnnyEnglishMovie.setSummary(
				"An evil smuggler plans to steal the Crown Jewels of the United Kingdom. After the country's top agents are killed, the only remaining hope is Johnny English, a rather unintelligent spy.");
		List<String> JohnnyEnglishActorsList = new ArrayList<String>();
		JohnnyEnglishActorsList.add("Rowan Atkinson");
		JohnnyEnglishActorsList.add(" Ben Miller");
		JohnnyEnglishMovie.setActors(JohnnyEnglishActorsList);
		JohnnyEnglishMovie.setImage(JohnnyEnglishImage);
		session.save(JohnnyEnglishMovie);
		session.flush();

//KarateKid
		String KarateKidImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\WatchAtHome\\KarateKid.jpg");
		Movie KarateKidMovie = new Movie();
		KarateKidMovie.setType(2);
		KarateKidMovie.setEngName("KarateKid");
		KarateKidMovie.setHebName("קראטה קיד");
		KarateKidMovie.setArbName("فتى الكاراتيه");
		KarateKidMovie.setLength(127);
		KarateKidMovie.setProducer("Jerry Weintraub");
		KarateKidMovie.setPrice(40);
		KarateKidMovie.setSummary(
				"Teenager Daniel LaRusso is bullied by Johnny Lawrence, who is adept at martial arts. In order to defend himself, he tries to learn karate from Mr Miyagi, his apartment's kind handyman.");
		List<String> KarateKidActorsList = new ArrayList<String>();
		KarateKidActorsList.add("Ralph Macchio");
		KarateKidActorsList.add("Noriyuki-Pat-Morita");
		KarateKidMovie.setActors(KarateKidActorsList);
		KarateKidMovie.setImage(KarateKidImage);
		session.save(KarateKidMovie);
		session.flush();

//TheSmurfs
		String TheSmurfsImage = ("C:\\Users\\Layan\\git\\cel\\server\\movie pics\\WatchAtHome\\TheSmurfs.jpg");
		Movie TheSmurfsMovie = new Movie();
		TheSmurfsMovie.setType(2);
		TheSmurfsMovie.setEngName("TheSmurfs");
		TheSmurfsMovie.setHebName("הדרדסים");
		TheSmurfsMovie.setArbName("السنافر");
		TheSmurfsMovie.setLength(103);
		TheSmurfsMovie.setProducer("Jordan Kerner");
		TheSmurfsMovie.setPrice(42);
		TheSmurfsMovie.setSummary(
				"While trying to escape the evil wizard Gargamel, the blue-skinned Smurfs get sucked into a vortex that teleports them to New York. Thereafter, they try their best to find a way out.");
		List<String> TheSmurfsActorsList = new ArrayList<String>();
		TheSmurfsActorsList.add("Neil Patrick Harris");
		TheSmurfsActorsList.add("Sofia Vergara");
		TheSmurfsMovie.setActors(TheSmurfsActorsList);
		TheSmurfsMovie.setImage(TheSmurfsImage);
		session.save(TheSmurfsMovie);
		session.flush();

//Users
		User NM = new User("Regina Phalange", "1111");
		NM.setRole(0);// -1->user,0 -> Network Manager, 1 -> Content Manager ,2 -> Costumer Services
						// Employee
		NM.setIs_Logged_In(false);
		User CM1 = new User("Princess Consuela", "2222");
		CM1.setRole(1);
		CM1.setIs_Logged_In(false);
		User CM2 = new User("Ursula", "3333");
		CM2.setRole(1);
		CM2.setIs_Logged_In(false);
		User CSE1 = new User("Marcel", "4444");
		CSE1.setRole(2);
		CSE1.setIs_Logged_In(false);
		User CSE2 = new User("Gunther", "5555");
		CSE2.setRole(2);
		CSE2.setIs_Logged_In(false);
		User user1 = new User("Joey", "6666");
		user1.setRole(-1);
		user1.setIs_Logged_In(false);
		User user2 = new User("Janice", "7777");
		user2.setRole(-1);
		user2.setIs_Logged_In(false);
		session.save(NM);
		session.save(CM1);
		session.save(CM2);
		session.save(CSE1);
		session.save(CSE2);
		session.save(user1);
		session.save(user2);
		session.flush();
		List<String> movies = new ArrayList<String>();
		List<String> newPrices = new ArrayList<String>();
		PriceRequestsChart PRC = new PriceRequestsChart(movies, newPrices);
		session.save(PRC);

		link mytestlink = new link(1, "KungFuPanda", 5, 7);
		session.save(mytestlink);
		session.flush();
		Ticket mytestticket = new Ticket("randomtest", "random hall", 15, 5);
		session.save(mytestticket);
		session.flush();
	}

	private static List<Movie> getAllMovies() throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = session.createQuery(query).getResultList();
		return data;
	}

	private static void printAllMovies() throws Exception {
		List<Movie> movies = new ArrayList<Movie>();
		List<Movie> tmp = getAllMovies();
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getType() == 0) {
				movies.add(tmp.get(i));
			}
		}
		for (Movie movie : movies) {
			System.out.print("****************************Movie id: ");
			System.out.print(movie.getId());
			System.out.print("*************************************");
			System.out.print("\nEnglish Name: ");
			System.out.print(movie.getEngName());
			System.out.print("\nHebrew Name: ");
			System.out.print(movie.getHebName());
			System.out.print("\nMovie Length: ");
			System.out.print(movie.getLength());
			System.out.print(" mins\nActors Names:");
			System.out.print(movie.getActors());
			System.out.print("\nMovie Summary: ");
			System.out.print(movie.getSummary());
			System.out.print("\nMovie Producer: ");
			System.out.print(movie.getProducer());
			System.out.print("\nPrice: ");
			System.out.print(movie.getPrice());
			System.out.print(" ILS\nMovie Screening Times: ");
			System.out.print(movie.getMovieTimes().getTimes());
			System.out.print("\n");

		}
	}

	public static void main(String[] args) throws IOException {
		server = new SimpleServer(3000);
		try {
			sessionFactory = getSessionFactory();

			session = sessionFactory.openSession();
			session.beginTransaction();

			generateData();

			System.out.print("Printing all movies now:\n");
			printAllMovies();

			session.getTransaction().commit(); // Save everything.

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
			}
		}
		server.listen();
	}
}
