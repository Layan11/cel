package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		TripleObject tuple_msg = (TripleObject) msg;
		String ObjctMsg = tuple_msg.getMsg();
		if (ObjctMsg.startsWith("Delete Movie Selected")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie helperMovie = new Movie();
				helperMovie = tuple_msg.getMovies().get(0);
				Movie MovieToDelete = App.session.get(Movie.class, helperMovie.getLength());
				App.session.remove(MovieToDelete);
				App.session.getTransaction().commit();
				client.sendToClient(new TripleObject("Movie Deleted", null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Save new Movie")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie newMovie = new Movie();
				MovieTimes MTimes = new MovieTimes();
				MTimes = tuple_msg.getMovieTimes().get(0);
				App.session.save(MTimes);
				newMovie = tuple_msg.getMovies().get(0);
				newMovie.setMovieTimes(MTimes);
				App.session.save(newMovie);
				App.session.getTransaction().commit();
				client.sendToClient(new TripleObject("Movie saved", null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Show More info")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list = getMoviesList();
				System.out.println("HelperList size: " + Helper_list.size());
				Movie mn = new Movie();
				mn = tuple_msg.getMovies().get(0);
				Movie wanted_movie = new Movie();
				List<Movie> wanted_list = new ArrayList<Movie>();
				for (int i = 0; i < Helper_list.size(); i++) {
					if (Helper_list.get(i).getEngName().equalsIgnoreCase(mn.getEngName())) {
						wanted_movie = Helper_list.get(i);
						wanted_list.add(wanted_movie);
					}
				}
				client.sendToClient(new TripleObject("More Info Movie", wanted_list, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Watch At Home")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helperl = getMoviesList();
				List<Movie> Watch_At_Home_Movies = new ArrayList<Movie>();
				for (int i = 0; i < Helperl.size(); i++) {
					if (Helperl.get(i).getType() == 2) {
						Watch_At_Home_Movies.add(Helperl.get(i));
					}
				}
				client.sendToClient(new TripleObject("All Watch At Home Movies Movies", Watch_At_Home_Movies, null));

			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Coming_Soon_Movies")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list2 = getMoviesList();
				List<Movie> Coming_Soon_Movies = new ArrayList<Movie>();
				for (int i = 0; i < Helper_list2.size(); i++) {
					if (Helper_list2.get(i).getType() == 1) {
						Coming_Soon_Movies.add(Helper_list2.get(i));
					}
				}
				client.sendToClient(new TripleObject("All Coming Soon Movies", Coming_Soon_Movies, null));

			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Browse movies")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list3 = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for (int i = 0; i < Helper_list3.size(); i++) {
					if (Helper_list3.get(i).getType() == 0) {
						movies.add(Helper_list3.get(i));
					}
				}
				client.sendToClient(new TripleObject("All Movies", movies, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Add Screening Time")) {
			Movie movie = tuple_msg.getMovies().get(0);
			String Newtime = tuple_msg.getMovieTimes().get(0).getTimes().get(0);
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				List<String> hlpr = movie.getMovieTimes().getTimes();
				hlpr.add(Newtime);
				Movie movieToUpdate = App.session.get(Movie.class, movie.getId());
				movieToUpdate.getMovieTimes().SetMovieTimes(hlpr);
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Delete Screening Time")) {
			Movie movie = tuple_msg.getMovies().get(0);
			String oldTime = tuple_msg.getMovieTimes().get(0).getTimes().get(0);
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movieToUpdate = App.session.get(Movie.class, movie.getId());
				List<String> hlpr = movieToUpdate.getMovieTimes().getTimes();
				boolean x = false;
				for (int i = 0; i < hlpr.size(); i++) {
					if (hlpr.get(i).equals(oldTime)) {
						x = true;
						hlpr.remove(i);
					}
				}
				if (x == false) {
					try {
						client.sendToClient(new TripleObject("No such screening time to delete", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				movieToUpdate.getMovieTimes().SetMovieTimes(hlpr);
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Update Screening Time")) {
			Movie movie = tuple_msg.getMovies().get(0);
			String oldTime = tuple_msg.getMovieTimes().get(0).getTimes().get(0);// old time is in place 0 new time is in
																				// place 1
			String Newtime = tuple_msg.getMovieTimes().get(0).getTimes().get(1);
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movieToUpdate = App.session.get(Movie.class, movie.getId());
				List<String> hlpr = movieToUpdate.getMovieTimes().getTimes();
				boolean x = false;
				for (int i = 0; i < hlpr.size(); i++) {
					if (hlpr.get(i).equals(oldTime)) {
						x = true;
						hlpr.remove(i);
					}
				}
				if (x == true) {
					hlpr.add(Newtime);
					movieToUpdate.getMovieTimes().SetMovieTimes(hlpr);
				} else {
					try {
						client.sendToClient(new TripleObject("No such screening time to update", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Show Screening Times")) {
			App.session = App.sessionFactory.openSession();
			List<MovieTimes> movieTimes = getAllMovieTimes();
			TripleObject to = new TripleObject("All Movies Times", null, movieTimes);
			System.out.println("printing screening times in server:" + movieTimes.get(0).getTimes());
			System.out.println("printing screening times in server 2: " + movieTimes.get(1).getTimes());
			System.out.println("printing screening times in server 3:" + movieTimes.get(2).getTimes());
			System.out.println("printing screening times in server 4: " + movieTimes.get(3).getTimes());
			System.out.println("printing screening times in server 5: " + movieTimes.get(4).getTimes());

			try {
				client.sendToClient(to);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			App.session.close();
		}
		if (ObjctMsg.equals("Login")) {
			String username = tuple_msg.getMovies().get(0).getEngName();
			String pass = tuple_msg.getMovies().get(0).getHebName();
			User user = new User();
			user.setUser_Name(username);
			user.setPassword(pass);
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				List<User> ans = getUser(username, pass);
				if (ans.size() == 0) {
					try {
						client.sendToClient(new TripleObject("No such user", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					ans.get(0).setIs_Logged_In(true);
					try {
						int userRole = ans.get(0).getRole();
						client.sendToClient(new TripleObject("User found " + userRole, null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.equals("Add new movie")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = tuple_msg.getMovies().get(0);
				if (movietoadd.getMovieTimes() != null) {
					App.session.save(movietoadd.getMovieTimes());
				}
				App.session.save(movietoadd);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Add exsisting movie to watch at home")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = new Movie();
				if (getMovie(ObjctMsg.substring(37)).size() == 0) {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Movie tmpMovie = getMovie(ObjctMsg.substring(37)).get(0);

					movietoadd.setType(2);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
											// home
					movietoadd.setEngName(tmpMovie.getEngName());
					movietoadd.setHebName(tmpMovie.getHebName());
					movietoadd.setArbName(tmpMovie.getArbName());
					movietoadd.setLength(tmpMovie.getLength());
					movietoadd.setActors(tmpMovie.getActors());
					movietoadd.setSummary(tmpMovie.getSummary());
					movietoadd.setProducer(tmpMovie.getProducer());
					movietoadd.setPrice(tmpMovie.getPrice());
					MovieTimes mt = new MovieTimes();
					movietoadd.setMovieTimes(mt);
					movietoadd.setImage(tmpMovie.getImage());
					movietoadd.setBranch(null);
					movietoadd.setLength(tmpMovie.getLength());
					movietoadd.setLink("IDKFORNOW");
					App.session.save(mt);
					App.session.save(movietoadd);
					App.session.flush();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Delete movie")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietodelete = new Movie();
				List<Movie> tmp = getMovie(ObjctMsg.substring(13));
				if (tmp.size() != 0) {
					movietodelete = tmp.get(0);
					// Movie tmp = App.session.find(Movie.class, movietodelete.getId());
					// App.session.remove(movietodelete.getMovieTimes());
					App.session.remove(movietodelete);
				} else {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Get movie actors ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movieName = ObjctMsg.substring(17);
				Movie movie = getMovie(movieName).get(0);
				List<String> actors = movie.getActors();
				try {
					TripleObject to = new TripleObject("Got the wanted movie", null, null);
					to.setList(actors);
					System.out.println("GET LIST IN SERVER: ");
					System.out.println(to.getList());
					client.sendToClient(to);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
	}

	private static List<Movie> getMoviesList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<User> getUser(String username, String pass) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> userRoot = query.from(User.class);
		Predicate predicateForUsername = builder.equal(userRoot.get("User_Name"), username);
		Predicate predicateForPass = builder.equal(userRoot.get("Password"), pass);
		Predicate predicateForUser = builder.and(predicateForUsername, predicateForPass);
		query.where(predicateForUser);
		List<User> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Movie> getMovie(String movieName) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		Root<Movie> userRoot = query.from(Movie.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("EngName"), movieName);
		query.where(predicateForMoviename);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<MovieTimes> getAllMovieTimes() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<MovieTimes> query = builder.createQuery(MovieTimes.class);
		query.from(MovieTimes.class);
		List<MovieTimes> data = App.session.createQuery(query).getResultList();
		System.out.println("SC in getallmovies : " + data.get(0).getTimes());
		System.out.println("SC in getallmovies : " + data.get(1).getTimes());
		return data;
	}
}
