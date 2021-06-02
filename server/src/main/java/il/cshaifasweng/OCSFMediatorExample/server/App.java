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
import il.cshaifasweng.OCSFMediatorExample.entities.User;


/**
 * Hello world!
 *
 */
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


		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void generateData() throws Exception {
		//Now broadcast in branches
//Aladdin
		String AladdinImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\Aladdin_poster_1992.jpg");
		List<String> AladdinActorsList = new ArrayList<String>();
		List<String> AladdinTimes = new ArrayList<String>();
		AladdinActorsList.add("Mena Massoud");
		AladdinActorsList.add("Naomi Scott");
		AladdinTimes.add("18:00");
		AladdinTimes.add("20:00");
		MovieTimes AladdinMovieTimes = new MovieTimes(AladdinTimes);
		session.save(AladdinMovieTimes);
		Movie AladdinMovie = new Movie(0,"Aladdin", AladdinActorsList, 128, "אלאדין", "insert aladdin movie summary",
				"Jonathan Eirich", 20, AladdinImage, AladdinMovieTimes, "Haifa");
		session.save(AladdinMovie);
		session.flush();

//Shrek
		String ShrekImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\Shrek.jpg");
		List<String> ShrekActorsList = new ArrayList<String>();
		List<String> ShrekTimes = new ArrayList<String>();
		ShrekActorsList.add("Shrek");
		ShrekActorsList.add("Princess Fiona");
		ShrekTimes.add("17:30");
		MovieTimes ShrekMovieTimes = new MovieTimes(ShrekTimes);
		session.save(ShrekMovieTimes);
		Movie ShrekMovie = new Movie(0,"Shrek", ShrekActorsList, 95, "שרק", "insert shrek movie summary",
				"John H. Williams", 35, ShrekImage, ShrekMovieTimes, "Haifa");
		session.save(ShrekMovie);
		session.flush();
//Snow White
		String SnowWhiteImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\SnowWhite.jpg");
		List<String> SnowWhiteActorsList = new ArrayList<String>();
		List<String> SnowWhiteTimes = new ArrayList<String>();
		SnowWhiteActorsList.add("Snow White");
		SnowWhiteActorsList.add("Dopey");
		SnowWhiteTimes.add("20:15");
		MovieTimes SnowWhiteMovieTimes = new MovieTimes(SnowWhiteTimes);
		session.save(SnowWhiteMovieTimes);
		Movie SnowWhiteMovie = new Movie(0,"Snow White", SnowWhiteActorsList, 88, "שלגייה",
				"insert snow white movie summary", "Walt Disney", 4, SnowWhiteImage, SnowWhiteMovieTimes, "Shefa-Amr");
		session.save(SnowWhiteMovie);
		session.flush();

//Fast and Furious
		String FnFImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\FastAndTheFurious.jpg");
		List<String> FnFActorsList = new ArrayList<String>();
		List<String> FnFTimes = new ArrayList<String>();
		FnFActorsList.add("Vin Diesel");
		FnFActorsList.add("Paul Walker");
		FnFTimes.add("19:45");
		FnFTimes.add("21:30");
		MovieTimes FnFMovieTimes = new MovieTimes(FnFTimes);
		session.save(FnFMovieTimes);
		Movie FastAndFuriousMovie = new Movie(0,"Fast and Furious", FnFActorsList, 107, "מהיר ועצבני",
				"insert fast and furious movie summary", "Neal H. Moritz", 45, FnFImage, FnFMovieTimes, "Shefa-Amr");
		session.save(FastAndFuriousMovie);
		session.flush();

//Dumbo
		String DumboImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\Dumbo.jpg");
		List<String> DumboActorsList = new ArrayList<String>();
		List<String> DumboTimes = new ArrayList<String>();
		DumboActorsList.add("Eva Green");
		DumboActorsList.add("Colin Farrell");
		DumboTimes.add("19:50");
		DumboTimes.add("21:30");
		MovieTimes DumboMovieTimes = new MovieTimes(DumboTimes);
		session.save(DumboMovieTimes);
		Movie DumboMovie = new Movie(0,"Dumbo", DumboActorsList, 112, "דמבו", "insert dumbo movie summary", "Tim Burton ",
				25, DumboImage, DumboMovieTimes, "Haifa");
		session.save(DumboMovie);
		session.flush();
		
		//coming soon 
		
//minions
		String MinionsImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\Minions.jpg");
		Movie minionsMovie = new Movie();
		minionsMovie.setType(1);
		minionsMovie.setEngName("Minions");
		minionsMovie.setHebName("המיניונים");
		minionsMovie.setLength(86);
		minionsMovie.setProducer("Mireille Soria");
		minionsMovie.setPrice(80);
		minionsMovie.setSummary("minions summary");
		List<String> minionsActorsList = new ArrayList<String>();
		minionsActorsList.add("Ben Stiller");
		minionsActorsList.add("Chris Rock");
		minionsMovie.setActors(minionsActorsList);
		minionsMovie.setImage(MinionsImage);
		session.save(minionsMovie);
		session.flush();
		
//Madagascar
		String MadagascarImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\Madagascar.jpg");
		Movie MadagascarMovie = new Movie();
		MadagascarMovie.setType(1);
		MadagascarMovie.setEngName("Madagascar");
		MadagascarMovie.setHebName("מדגסקר");
		MadagascarMovie.setLength(86);
		MadagascarMovie.setProducer("Mireille Soria");
		MadagascarMovie.setPrice(80);
		MadagascarMovie.setSummary("Madagascar summary");
		List<String> MadagascarActorsList = new ArrayList<String>();
		MadagascarActorsList.add("Ben Stiller");
		MadagascarActorsList.add("Chris Rock");
		MadagascarMovie.setActors(MadagascarActorsList);
		MadagascarMovie.setImage(MadagascarImage);
		session.save(MadagascarMovie);
		session.flush();
		
//IronMan
		String IronManImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\IronManjpg.jpg");
		Movie IronManMovie = new Movie();
		IronManMovie.setType(1);
		IronManMovie.setEngName("IronMan");
		IronManMovie.setHebName("איש הברזל");
		IronManMovie.setLength(126);
		IronManMovie.setProducer("Avi Arad");
		IronManMovie.setPrice(65);
		IronManMovie.setSummary("IronMan summary");
		List<String> IronManActorsList = new ArrayList<String>();
		IronManActorsList.add(" Robert Downey Jr. ");
		IronManActorsList.add(" Terrence Howard");
		IronManMovie.setActors(IronManActorsList);
		IronManMovie.setImage(IronManImage);
		session.save(IronManMovie);
		session.flush();
		
//KungFuPanda
		String KungFuPandaImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\MV5BODJkZTZhKungFuPanda.jpg");
		Movie KungFuPandaMovie = new Movie();
		KungFuPandaMovie.setType(1);
		KungFuPandaMovie.setEngName("KungFuPanda");
		KungFuPandaMovie.setHebName("קונג פו פנדה");
		KungFuPandaMovie.setLength(92);
		KungFuPandaMovie.setProducer("Melissa Cobb");
		KungFuPandaMovie.setPrice(70);
		KungFuPandaMovie.setSummary("KungFuPanda summary");
		List<String> KungFuPandaActorsList = new ArrayList<String>();
		KungFuPandaActorsList.add("Jack Black");
		KungFuPandaActorsList.add("Dustin Hoffman");
		KungFuPandaMovie.setActors(KungFuPandaActorsList);
		KungFuPandaMovie.setImage(KungFuPandaImage);
		session.save(KungFuPandaMovie);
		session.flush();
		
		//watch at home
		
//badboys		
		String BadBoysImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\WatchAtHome\\BadBoys.jpg");
		Movie BadBoysMovie = new Movie();
		BadBoysMovie.setType(2);
		BadBoysMovie.setEngName("BadBoys");
		BadBoysMovie.setHebName("בחורים רעים");
		BadBoysMovie.setLength(119);
		BadBoysMovie.setProducer("Don Simpson");
		BadBoysMovie.setPrice(50);
		BadBoysMovie.setSummary("badboys summary");
		List<String> BadBoysActorsList = new ArrayList<String>();
		BadBoysActorsList.add("Will Smith");
		BadBoysActorsList.add(" Martin Lawrence");
		BadBoysMovie.setActors(BadBoysActorsList);
		BadBoysMovie.setImage(BadBoysImage);
		session.save(BadBoysMovie);
		session.flush();
		
//JohnnyEnglish		
		String JohnnyEnglishImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\WatchAtHome\\JohnnyEnglish.jpg");
		Movie JohnnyEnglishMovie = new Movie();
		JohnnyEnglishMovie.setType(2);
		JohnnyEnglishMovie.setEngName("JohnnyEnglish");
		JohnnyEnglishMovie.setHebName("גוני אינגלש");
		JohnnyEnglishMovie.setLength(88);
		JohnnyEnglishMovie.setProducer("Tim Bevan");
		JohnnyEnglishMovie.setPrice(59);
		JohnnyEnglishMovie.setSummary("JohnnyEnglish summary");
		List<String> JohnnyEnglishActorsList = new ArrayList<String>();
		JohnnyEnglishActorsList.add("Rowan Atkinson");
		JohnnyEnglishActorsList.add(" Ben Miller");
		JohnnyEnglishMovie.setActors(JohnnyEnglishActorsList);
		JohnnyEnglishMovie.setImage(JohnnyEnglishImage);
		session.save(JohnnyEnglishMovie);
		session.flush();
		
//KarateKid
		String KarateKidImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\WatchAtHome\\KarateKid.jpg");
		Movie KarateKidMovie = new Movie();
		KarateKidMovie.setType(2);
		KarateKidMovie.setEngName("KarateKid");
		KarateKidMovie.setHebName("קראטה קיד");
		KarateKidMovie.setLength(127);
		KarateKidMovie.setProducer("Jerry Weintraub");
		KarateKidMovie.setPrice(40);
		KarateKidMovie.setSummary("KarateKid summary");
		List<String> KarateKidActorsList = new ArrayList<String>();
		KarateKidActorsList.add("Ralph Macchio");
		KarateKidActorsList.add("Noriyuki-Pat-Morita");
		KarateKidMovie.setActors(KarateKidActorsList);
		KarateKidMovie.setImage(KarateKidImage);
		session.save(KarateKidMovie);
		session.flush();
		
//TheSmurfs
		String TheSmurfsImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\WatchAtHome\\TheSmurfs.jpg");
		Movie TheSmurfsMovie = new Movie();
		TheSmurfsMovie.setType(2);
		TheSmurfsMovie.setEngName("TheSmurfs");
		TheSmurfsMovie.setHebName("הדרדסים");
		TheSmurfsMovie.setLength(103);
		TheSmurfsMovie.setProducer("Jordan Kerner");
		TheSmurfsMovie.setPrice(42);
		TheSmurfsMovie.setSummary("TheSmurfs summary");
		List<String> TheSmurfsActorsList = new ArrayList<String>();
		TheSmurfsActorsList.add("Neil Patrick Harris");
		TheSmurfsActorsList.add("Sofia Vergara");
		TheSmurfsMovie.setActors(TheSmurfsActorsList);
		TheSmurfsMovie.setImage(TheSmurfsImage);
		session.save(TheSmurfsMovie);
		session.flush();
		

//Users
		User NM = new User("Regina Phalange",  "1111",  0,  false);
		User CM1 = new User ("Princess Consuela",  "2222",  1,  false);
		User CM2 = new User("Ursula",  "3333",  1,  false);
		User CSE1 = new User("Marcel",  "4444",  2,  false);
		User CSE2 = new User("Gunther",  "5555",  2,  false);
		session.save(NM);
		session.save(CM1);
		session.save(CM2);
		session.save(CSE1);
		session.save(CSE2);
		session.flush();
		
		/*Network_Manager NM = new Network_Manager( "Regina Phalange",  "1111",  false,  0);
		session.save(NM);
		session.flush();
		Content_Manager CM1 = new  Content_Manager( "Princess Consuela",  "2222",  false,  1);
		Content_Manager CM2 = new  Content_Manager( "Ursula",  "3333",  false,  1);
		session.save(CM1);
		session.save(CM2);
		session.flush();
		Costumer_Services_Employee CSE1= new Costumer_Services_Employee( "Marcel",  "4444",  false,  2);
		Costumer_Services_Employee CSE2= new Costumer_Services_Employee( "Gunther",  "5555",  false,  2);
		session.save(CSE1);
		session.save(CSE2);
		session.flush();
*/
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
		for(int i=0; i<tmp.size(); i++)
		{
			if(tmp.get(i).getType() == 0) {
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
