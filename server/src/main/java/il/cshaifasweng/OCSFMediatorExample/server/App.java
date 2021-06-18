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
import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.MapChair;

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
		configuration.addAnnotatedClass(Cinema.class);
		configuration.addAnnotatedClass(Hall.class);
		configuration.addAnnotatedClass(MapChair.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void generateData() throws Exception {
		//****************
		Hall hall_1=new Hall(100);
		Hall hall_2=new Hall(100);
		Hall hall_3=new Hall(100);
		Hall hall_4=new Hall(100);
		Hall hall_5=new Hall(100);

		//****************
		
		
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
		Movie AladdinMovie = new Movie(false,"Aladdin", AladdinActorsList, 128, "אלאדין", "insert aladdin movie summary",
				"Jonathan Eirich", 20, AladdinImage, AladdinMovieTimes, "Haifa");
		session.save(AladdinMovie);
		session.flush();
		
		MapChair mc1=new MapChair(10,10,AladdinMovie.getId(),"18:00");
		MapChair mc2=new MapChair(10,10,AladdinMovie.getId(),"20:00");
		session.save(mc1);
		session.flush();
		session.save(mc2);
		session.flush();
		hall_1.add_IDMapChair(mc1.getID());
		hall_1.add_IDMapChair(mc2.getID());
		hall_1.add_IDMovie(AladdinMovie.getId());

//Shrek
		String ShrekImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\Shrek.jpg");
		List<String> ShrekActorsList = new ArrayList<String>();
		List<String> ShrekTimes = new ArrayList<String>();
		ShrekActorsList.add("Shrek");
		ShrekActorsList.add("Princess Fiona");
		ShrekTimes.add("17:30");
		MovieTimes ShrekMovieTimes = new MovieTimes(ShrekTimes);
		session.save(ShrekMovieTimes);
		Movie ShrekMovie = new Movie(false,"Shrek", ShrekActorsList, 95, "שרק", "insert shrek movie summary",
				"John H. Williams", 35, ShrekImage, ShrekMovieTimes, "Haifa");
		session.save(ShrekMovie);
		session.flush();
		MapChair mc_sh=new MapChair(10,10,ShrekMovie.getId(),"17:30");
		session.save(mc_sh);
		session.flush();
		hall_2.add_IDMapChair(mc_sh.getID());
		hall_2.add_IDMovie(ShrekMovie.getId());
//Snow White
		String SnowWhiteImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\SnowWhite.jpg");
		List<String> SnowWhiteActorsList = new ArrayList<String>();
		List<String> SnowWhiteTimes = new ArrayList<String>();
		SnowWhiteActorsList.add("Snow White");
		SnowWhiteActorsList.add("Dopey");
		SnowWhiteTimes.add("20:15");
		MovieTimes SnowWhiteMovieTimes = new MovieTimes(SnowWhiteTimes);
		session.save(SnowWhiteMovieTimes);
		Movie SnowWhiteMovie = new Movie(false,"Snow White", SnowWhiteActorsList, 88, "שלגייה",
				"insert snow white movie summary", "Walt Disney", 4, SnowWhiteImage, SnowWhiteMovieTimes, "Shefa-Amr");
		session.save(SnowWhiteMovie);
		session.flush();
		MapChair mc_SW=new MapChair(10,10,SnowWhiteMovie.getId(),"20:15");
		session.save(mc_SW);
		session.flush();
		hall_3.add_IDMovie(SnowWhiteMovie.getId());
		hall_3.add_IDMapChair(mc_SW.getID());

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
		Movie FastAndFuriousMovie = new Movie(false,"Fast and Furious", FnFActorsList, 107, "מהיר ועצבני",
				"insert fast and furious movie summary", "Neal H. Moritz", 45, FnFImage, FnFMovieTimes, "Shefa-Amr");
		session.save(FastAndFuriousMovie);
		session.flush();
		MapChair mc_FAF_1=new MapChair(10,10,FastAndFuriousMovie.getId(),"19:45");
		MapChair mc_FAF_2=new MapChair(10,10,FastAndFuriousMovie.getId(),"21:30");
		session.save(mc_FAF_1);
		session.flush();
		session.save(mc_FAF_2);
		session.flush();
		hall_4.add_IDMovie(FastAndFuriousMovie.getId());
		hall_4.add_IDMapChair(mc_FAF_1.getID());
		hall_4.add_IDMapChair(mc_FAF_2.getID());

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
		Movie DumboMovie = new Movie(false,"Dumbo", DumboActorsList, 112, "דמבו", "insert dumbo movie summary", "Tim Burton ",
				25, DumboImage, DumboMovieTimes, "Haifa");
		session.save(DumboMovie);
		session.flush();
		MapChair mc_D_1=new MapChair(10,10,DumboMovie.getId(),"19:50");
		MapChair mc_D_2=new MapChair(10,10,DumboMovie.getId(),"21:30");
		session.save(mc_D_1);
		session.flush();
		session.save(mc_D_2);
		session.flush();
		hall_5.add_IDMapChair(mc_D_1.getID());
		hall_5.add_IDMapChair(mc_D_2.getID());
		hall_5.add_IDMovie(DumboMovie.getId());
		
		//coming soon 
		
//minions
		String MinionsImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\Minions.jpg");
		Movie minionsMovie = new Movie();
		minionsMovie.setType(true);
		minionsMovie.setEngName("Minions");
		minionsMovie.setHebName("המיניונים");
		minionsMovie.setImage(MinionsImage);
		session.save(minionsMovie);
		session.flush();
		
//Madagascar
		String MadagascarImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\Madagascar.jpg");
		Movie MadagascarMovie = new Movie();
		MadagascarMovie.setType(true);
		MadagascarMovie.setEngName("Madagascar");
		MadagascarMovie.setHebName("מדגסקר");
		MadagascarMovie.setImage(MadagascarImage);
		session.save(MadagascarMovie);
		session.flush();
		
//IronMan
		String IronManImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\IronManjpg.jpg");
		Movie IronManMovie = new Movie();
		IronManMovie.setType(true);
		IronManMovie.setEngName("IronMan");
		IronManMovie.setHebName("איש הברזל");
		IronManMovie.setImage(IronManImage);
		session.save(IronManMovie);
		session.flush();
		
//KungFuPanda
		String KungFuPandaImage = ("C:\\Users\\carol\\git\\cellastone\\server\\movie pics\\ComingSoon\\MV5BODJkZTZhKungFuPanda.jpg");
		Movie KungFuPandaMovie = new Movie();
		KungFuPandaMovie.setType(true);
		KungFuPandaMovie.setEngName("KungFuPanda");
		KungFuPandaMovie.setHebName("קונק פו פנדה");
		KungFuPandaMovie.setImage(KungFuPandaImage);
		session.save(KungFuPandaMovie);
		session.flush();
		//******************************************************************************
		/*List<Integer> mapchair1=new ArrayList<Integer>();
		MapChair mapchair=new MapChair(10,10);
		session.save(mapchair);
		mapchair1.add(mapchair.getID());*/
		List<Integer> halls_id_list=new ArrayList<Integer>();
		
		session.save(hall_1);
		session.flush();
		session.save(hall_2);
		session.flush();
		session.save(hall_3);
		session.flush();
		session.save(hall_4);
		session.flush();
		session.save(hall_5);
		session.flush();
		halls_id_list.add(hall_1.getID());
		halls_id_list.add(hall_2.getID());
		halls_id_list.add(hall_3.getID());
		halls_id_list.add(hall_4.getID());
		halls_id_list.add(hall_5.getID());
		
		Cinema YesPlanet=new Cinema("Yes Planet","Haifa",halls_id_list);
		session.save(YesPlanet);
		session.flush();
		/*
		List<Integer> halls_list2=new ArrayList<Integer>();
		Hall hall_city_1=new Hall(100,mapchair1);
		Hall hall_city_2=new Hall(100,mapchair1);
		Hall hall_city_3=new Hall(100,mapchair1);
		Hall hall_city_4=new Hall(100,mapchair1);
		Hall hall_city_5=new Hall(100,mapchair1);
		session.save(hall_city_1);
		session.flush();
		session.save(hall_city_2);
		session.flush();
		session.save(hall_city_3);
		session.flush();
		session.save(hall_city_4);
		session.flush();
		session.save(hall_city_5);
		session.flush();
		halls_list2.add(hall_1.getID());
		halls_list2.add(hall_2.getID());
		halls_list2.add(hall_3.getID());
		halls_list2.add(hall_4.getID());
		halls_list2.add(hall_5.getID());
		Cinema CinemaCity=new Cinema("Cinema City","Rashon Lisyon",halls_list2);
		session.save(CinemaCity);
		session.flush();*/
		

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
			if(tmp.get(i).getType() == false) {
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
