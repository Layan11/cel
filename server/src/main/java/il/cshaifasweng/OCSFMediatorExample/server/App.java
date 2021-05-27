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

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void generateData() throws Exception {
//Aladdin
		String AladdinImage = ("C:\\Users\\carol\\git\\cel\\server\\movie pics\\Aladdin_poster_1992.jpg");
		List<String> AladdinActorsList = new ArrayList<String>();
		List<String> AladdinTimes = new ArrayList<String>();
		AladdinActorsList.add("Mena Massoud");
		AladdinActorsList.add("Naomi Scott");
		AladdinTimes.add("18:00");
		AladdinTimes.add("20:00");
		MovieTimes AladdinMovieTimes = new MovieTimes(AladdinTimes);
		session.save(AladdinMovieTimes);
		Movie AladdinMovie = new Movie("Aladdin", AladdinActorsList, 128, "אלאדין", "insert aladdin movie summary",
				"Jonathan Eirich", 20, AladdinImage, AladdinMovieTimes, "Haifa");
		session.save(AladdinMovie);
		session.flush();

//Shrek
		String ShrekImage = ("C:\\Users\\carol\\git\\cel\\server\\movie pics\\Shrek.jpg");
		List<String> ShrekActorsList = new ArrayList<String>();
		List<String> ShrekTimes = new ArrayList<String>();
		ShrekActorsList.add("Shrek");
		ShrekActorsList.add("Princess Fiona");
		ShrekTimes.add("17:30");
		MovieTimes ShrekMovieTimes = new MovieTimes(ShrekTimes);
		session.save(ShrekMovieTimes);
		Movie ShrekMovie = new Movie("Shrek", ShrekActorsList, 95, "שרק", "insert shrek movie summary",
				"John H. Williams", 35, ShrekImage, ShrekMovieTimes, "Haifa");
		session.save(ShrekMovie);
		session.flush();
//Snow White
		String SnowWhiteImage = ("C:\\Users\\carol\\git\\cel\\server\\movie pics\\SnowWhite.jpg");
		List<String> SnowWhiteActorsList = new ArrayList<String>();
		List<String> SnowWhiteTimes = new ArrayList<String>();
		SnowWhiteActorsList.add("Snow White");
		SnowWhiteActorsList.add("Dopey");
		SnowWhiteTimes.add("20:15");
		MovieTimes SnowWhiteMovieTimes = new MovieTimes(SnowWhiteTimes);
		session.save(SnowWhiteMovieTimes);
		Movie SnowWhiteMovie = new Movie("Snow White", SnowWhiteActorsList, 88, "שלגייה",
				"insert snow white movie summary", "Walt Disney", 4, SnowWhiteImage, SnowWhiteMovieTimes, "Shefa-Amr");
		session.save(SnowWhiteMovie);
		session.flush();

//Fast and Furious
		String FnFImage = ("C:\\Users\\carol\\git\\cel\\server\\movie pics\\FastAndTheFurious.jpg");
		List<String> FnFActorsList = new ArrayList<String>();
		List<String> FnFTimes = new ArrayList<String>();
		FnFActorsList.add("Vin Diesel");
		FnFActorsList.add("Paul Walker");
		FnFTimes.add("19:45");
		FnFTimes.add("21:30");
		MovieTimes FnFMovieTimes = new MovieTimes(FnFTimes);
		session.save(FnFMovieTimes);
		Movie FastAndFuriousMovie = new Movie("Fast and Furious", FnFActorsList, 107, "מהיר ועצבני",
				"insert fast and furious movie summary", "Neal H. Moritz", 45, FnFImage, FnFMovieTimes, "Shefa-Amr");
		session.save(FastAndFuriousMovie);
		session.flush();

//Dumbo
		String DumboImage = ("C:\\Users\\carol\\git\\cel\\server\\movie pics\\Dumbo.jpg");
		List<String> DumboActorsList = new ArrayList<String>();
		List<String> DumboTimes = new ArrayList<String>();
		DumboActorsList.add("Eva Green");
		DumboActorsList.add("Colin Farrell");
		DumboTimes.add("19:50");
		DumboTimes.add("21:30");
		MovieTimes DumboMovieTimes = new MovieTimes(DumboTimes);
		session.save(DumboMovieTimes);
		Movie DumboMovie = new Movie("Dumbo", DumboActorsList, 112, "דמבו", "insert dumbo movie summary", "Tim Burton ",
				25, DumboImage, DumboMovieTimes, "Haifa");
		session.save(DumboMovie);
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
		List<Movie> movies = getAllMovies();
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
