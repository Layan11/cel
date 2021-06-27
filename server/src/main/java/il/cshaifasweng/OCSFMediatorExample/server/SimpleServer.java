package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

//import il.cshaifasweng.OCSFMediatorExample.client.loginController;
import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.MapChair;
import il.cshaifasweng.OCSFMediatorExample.entities.MonthlyComplaints;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.Package;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequestsChart;
import il.cshaifasweng.OCSFMediatorExample.entities.Reports;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;
import il.cshaifasweng.OCSFMediatorExample.entities.purpleChar;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		TripleObject tuple_msg = null;
		String ObjctMsg = null;
		DoubleObject tuple_msg2 = null;
		if (msg.getClass() == DoubleObject.class) {
			tuple_msg2 = (DoubleObject) msg;
			ObjctMsg = tuple_msg2.getMsg();
		} else {
			tuple_msg = (TripleObject) msg;
			ObjctMsg = tuple_msg.getMsg();
		}

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
				String type = ObjctMsg.substring(15);
				Movie newMovie = new Movie();
				MovieTimes MTimes = new MovieTimes();
				MTimes = tuple_msg.getMovieTimes().get(0);
				App.session.save(MTimes);
				newMovie = tuple_msg.getMovies().get(0);
				newMovie.setMovieTimes(MTimes);
				String movieName = newMovie.getEngName();

				if (type.equals("Coming Soon")) {
					App.session.remove(getMovie(movieName).get(0));
				}
				if (type.equals("Watch at Home")) {
					Movie movie = getMovie(movieName).get(0);
//					String link = movie.getLink();
//					newMovie.setLink(link);
					App.session.remove(getMovie(movieName).get(0));
				}
				App.session.save(newMovie);
				List<String> NewTimes = newMovie.getMovieTimes().getTimes();
				for (int i = 0; i < NewTimes.size(); i++) {
					MapChair mc = new MapChair(10, 10, newMovie.getId(), NewTimes.get(i));
					mc.setNumOfBoughtSeat(0);
					App.session.save(mc);
				}
				App.session.getTransaction().commit();
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
					if (Helperl.get(i).getType() == 2 || Helperl.get(i).getType() == 3) {
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

		if (ObjctMsg.startsWith("Haifa"))

		{
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helperl = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for (int i = 0; i < Helperl.size(); i++) {
					String branch = Helperl.get(i).getBranch();
					if (Helperl.get(i).getType() == 0 || Helperl.get(i).getType() == 3) {
						System.out.println("branch : " + branch);
						if (branch.equals("Haifa")) {
							movies.add(Helperl.get(i));
						}
					}

				}
				client.sendToClient(new TripleObject("Haifa Movies", movies, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Shefa-Amr")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helperl2 = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for (int i = 0; i < Helperl2.size(); i++) {
					String branch = Helperl2.get(i).getBranch();
					if (Helperl2.get(i).getType() == 0 || Helperl2.get(i).getType() == 3) {
						System.out.println("branch : " + branch);
						if (branch.equals("Shefa-Amr")) {
							movies.add(Helperl2.get(i));
						}
					}
				}
				client.sendToClient(new TripleObject("Shefa-Amr Movies", movies, null));
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
					if (Helper_list3.get(i).getType() == 0 || Helper_list3.get(i).getType() == 3) {
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
			List<String> Newtimes = tuple_msg.getMovieTimes().get(0).getTimes();
			List<String> NewDates = tuple_msg.getMovieTimes().get(0).getDate();
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				List<String> newTimesList = new ArrayList<String>();
				Movie movieToUpdate = App.session.get(Movie.class, movie.getId());
				newTimesList.addAll(movieToUpdate.getMovieTimes().getTimes());
				newTimesList.addAll(Newtimes);
				movieToUpdate.getMovieTimes().SetMovieTimes(newTimesList);
				List<String> newDatesList = new ArrayList<String>();
				newDatesList.addAll(movieToUpdate.getMovieTimes().getDate());
				newDatesList.addAll(NewDates);
				movieToUpdate.getMovieTimes().setDate(newDatesList);
				for (int i = 0; i < Newtimes.size(); i++) {
					MapChair mc = new MapChair(10, 10, movieToUpdate.getId(), Newtimes.get(i));
					mc.setNumOfBoughtSeat(0);
					App.session.save(mc);
				}

				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();

			/*** saleh ***/
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				MapChair new_mapchair = new MapChair(10, 10, movie.getId(), Newtimes.get(0));
				App.session.save(new_mapchair);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}

			/*** saleh ***/

		}
		if (ObjctMsg.startsWith("Delete Screening Time")) {
			Movie movie = tuple_msg.getMovies().get(0);
			String wantedTime = ObjctMsg.substring(22);
			int idx = 0;
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movieToUpdate = App.session.get(Movie.class, movie.getId());
				List<String> hlpr1 = movieToUpdate.getMovieTimes().getTimes();
				for (int i = 0; i < hlpr1.size(); i++) {
					if (hlpr1.get(i).equals(wantedTime)) {
						hlpr1.remove(i);
						idx = i;
					}
				}
				List<String> hlpr2 = movieToUpdate.getMovieTimes().getDate();
				hlpr2.remove(idx);
				movieToUpdate.getMovieTimes().SetMovieTimes(hlpr1);
				movieToUpdate.getMovieTimes().setDate(hlpr2);
				MapChair mcToDelete = getMapChairByTime(wantedTime).get(0);
				App.session.remove(mcToDelete);
				try {
					client.sendToClient(new TripleObject("Deleted screening time", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();

			/*** saleh ***/
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				MapChair mapchairtodelete = new MapChair();
				mapchairtodelete = getmapchairid(movie.getId(), wantedTime).get(0);
				App.session.remove(mapchairtodelete);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();

			/*** saleh ***/
		}
		if (ObjctMsg.startsWith("Update Screening Time")) {
			String oldtime = "", newtime = "";
			int movie_id = 0;
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movie = ObjctMsg.substring(22);
				String oldTime = tuple_msg.getMovieTimes().get(0).getTimes().get(0);// old time is in place 0 new time
				String Newtime = tuple_msg.getMovieTimes().get(0).getTimes().get(1);
				String NewDate = tuple_msg.getMovieTimes().get(0).getDate().get(0);
				String oldDate = "";
				Movie movieToUpdate = getMovie(movie).get(0);
				/*** saleh ***/
				oldtime = oldTime;
				newtime = Newtime;
				movie_id = movieToUpdate.getId();
				/*** saleh ***/
				List<String> hlpr = movieToUpdate.getMovieTimes().getTimes();
				List<String> hlpr2 = movieToUpdate.getMovieTimes().getDate();

				for (int i = 0; i < hlpr.size(); i++) {
					if (hlpr.get(i).equals(oldTime)) {
						hlpr.remove(i);
						oldDate = hlpr2.get(i);
						hlpr2.remove(i);
					}
				}
				if (!Newtime.equals("")) {
					hlpr.add(Newtime);
				} else {
					hlpr.add(oldTime);
				}
				if (NewDate.equals("")) {
					hlpr2.add(oldDate);
				} else {
					hlpr2.add(NewDate);
				}
				movieToUpdate.getMovieTimes().SetMovieTimes(hlpr);
				movieToUpdate.getMovieTimes().setDate(hlpr2);
				MapChair mcToUpdate = getMapChairByTime(oldTime).get(0);
				mcToUpdate.setStart_time(Newtime);
				App.session.save(mcToUpdate);
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();

			/*** saleh ***/
			try {

				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				MapChair mapchairtodelete = new MapChair();
				mapchairtodelete = getmapchairid(movie_id, oldtime).get(0);
				mapchairtodelete.setStart_time(newtime);
				App.session.save(mapchairtodelete);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
			/*** saleh ***/

		}
		if (ObjctMsg.startsWith("Show Screening Times")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movie = ObjctMsg.substring(21);
				Movie hlpr = getMovie(movie).get(0);
				TripleObject to = new TripleObject("Movie Times", null, null);
				to.setList(hlpr.getMovieTimes().getTimes());
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
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
				List<User> ans = GetUser(username, pass);
				if (ans.size() == 0) {
					try {
						client.sendToClient(new TripleObject("No such user", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {// user found
					if (ans.get(0).isIs_Logged_In() == true)// user is connected
					{
						try {
							client.sendToClient(new TripleObject("User is already connected", null, null));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {

						ans.get(0).setIs_Logged_In(true);
						try {
							int userRole = ans.get(0).getRole();
							TripleObject to = new TripleObject("User found " + userRole, null, null);
							List<String> tmp = new ArrayList<String>();
							tmp.add(ans.get(0).getUser_Name());
							to.setList(tmp);
							client.sendToClient(to);
						} catch (IOException e) {
							e.printStackTrace();
						}
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
				List<String> timesToAdd = movietoadd.getMovieTimes().getTimes();
				for (int i = 0; i < timesToAdd.size(); i++) {
					MapChair mc = new MapChair(10, 10, movietoadd.getId(), timesToAdd.get(i));
					mc.setNumOfBoughtSeat(0);
					App.session.save(mc);
				}
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
				if (getMovie(ObjctMsg.substring(37)).size() == 0) {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					Movie tmpMovie = getMovie(ObjctMsg.substring(37)).get(0);

					tmpMovie.setType(3);// Type=0 for now broadcasting,type=1 for coming soon , type=2 for to watch at
										// home, type=3 for watch at home & now broadcasting
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
					App.session.remove(movietodelete);
				} else {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				List<String> timesToDelete = movietodelete.getMovieTimes().getTimes();
				for (int i = 0; i < timesToDelete.size(); i++) {
					MapChair mc = getMapChairByTime(timesToDelete.get(i)).get(0);
					App.session.remove(mc);
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
					System.out.println("GOT ACTORS LIST IN SERVER: ");
					System.out.println(to.getList());
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Update price ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movieName = ObjctMsg.substring(13);
				List<Movie> tmp = getMovie(movieName);
				if (tmp.size() != 0) {
					String newPrice = tuple_msg.getList().get(0);
					PriceRequestsChart chart = App.session.get(PriceRequestsChart.class, 1);
					chart.getMovieEngName().add(movieName);
					chart.getNewPrice().add(newPrice);
					try {
						client.sendToClient(new TripleObject("found movie to update price", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
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
		if (ObjctMsg.startsWith("Show PRC movies")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				PriceRequestsChart chart = App.session.get(PriceRequestsChart.class, 1);
				TripleObject to = new TripleObject("PRC movies", null, null);
				List<String> tmp = chart.getMovieEngName();
				to.setList(tmp);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Show PRC prices")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				PriceRequestsChart chart = App.session.get(PriceRequestsChart.class, 1);
				System.out.println("SIZE: " + chart.getNewPrice().size());
				TripleObject to = new TripleObject("PRC prices", null, null);
				to.setList(chart.getNewPrice());
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Check if restricted day ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String day = ObjctMsg.substring(24);
				purpleChar PC = getPC().get(0);
				boolean x = false;
				for (int i = 0; i < PC.getDays().size(); i++) {
					if (PC.getDays().get(i).equals(day)) {
						x = true;
					}
				}
				String res;
				if (x) {
					res = "Yes";
				} else {
					res = "No";
				}
				List<String> list = new ArrayList<String>();
				list.add(res);
				TripleObject to = new TripleObject("checked day", null, null);
				to.setList(list);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Deny request")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String newPrice = ObjctMsg.substring(13);
				int idx = 0;
				PriceRequestsChart chart = App.session.get(PriceRequestsChart.class, 1);
				List<String> hlpr = new ArrayList<String>();
				hlpr = chart.getNewPrice();
				for (int i = 0; i < hlpr.size(); i++) {
					if (hlpr.get(i).equals(newPrice)) {
						hlpr.remove(i);
						idx = i;
					}
				}
				chart.setNewPrice(hlpr);
				chart.getMovieEngName().remove(idx);
				TripleObject to = new TripleObject("Updated chart movies", null, null);
				to.setList(chart.getMovieEngName());
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Show restricted days")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				purpleChar pc = getPC().get(0);
				List<String> days = pc.getDays();
				TripleObject to = new TripleObject("All restricted days", null, null);
				to.setList(days);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Add restricted day ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String newDate = ObjctMsg.substring(19);
				String restrictionType = tuple_msg.getList().get(0);
				List<MapChair> MapChairList = new ArrayList<MapChair>();
				try {
					MapChairList = getAllMapChairs();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				int y = Integer.parseInt(restrictionType);
				for (int i = 0; i < MapChairList.size(); i++) {
					String time = MapChairList.get(i).getStart_time();
					int movieId = MapChairList.get(i).getMovie_id();
					Movie movie = getMovieFromId(movieId).get(0);
					List<String> dates = movie.getMovieTimes().getDate();
					List<String> times = movie.getMovieTimes().getTimes();
					System.out.println("time = " + MapChairList.get(i).getStart_time());
					for (int j = 0; j < times.size(); j++) {
						if (times.get(j).equals(time)) {
							if (dates.get(j).equals(newDate)) {
								MapChair mc = MapChairList.get(i);
								int cols = mc.getCols();
								int rows = mc.getRows();
								int x = cols * rows;
								if (1.2 * y < x) {
									System.out.println("IN THE FIRST IF");
									int bought = mc.getNumOfBoughtSeat();
									mc.setNmberAvailableChair(y - bought);
								} else if (x > 0.8 * y) {
									System.out.println("IN THE SECOND IF ans = " + 0.8 * y);
									double tmp = 0.8 * y;
									int Tmp = (int) tmp;
									int bought = mc.getNumOfBoughtSeat();
									mc.setNmberAvailableChair(Tmp - bought);
								} else if (x <= 0.8 * y) {
									System.out.println("IN THE THIRD IF");
									double tmp = x / 2;
									int Tmp = (int) tmp;
									int bought = mc.getNumOfBoughtSeat();
									mc.setNmberAvailableChair(Tmp - bought);
								}
							}
						}
					}
				}
				List<String> dates = new ArrayList<String>();
				purpleChar pc = getPC().get(0);
				dates = pc.getDays();
				dates.add(newDate);
				pc.setDays(dates);
				App.session.save(pc);
				App.session.flush();
				TripleObject to = new TripleObject("All updated restricted days", null, null);
				to.setList(dates);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.equals("Add new message")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = tuple_msg.getMovies().get(0);
				messages newmessage = new messages(movietoadd.getSummary(), movietoadd.getHebName(),
						movietoadd.getEngName());
				App.session.save(newmessage);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

//		if (ObjctMsg.startsWith("Show messages")) {
//			System.out.println("in the server of show messages");
//			try {
//				App.session = App.sessionFactory.openSession();
//				App.session.beginTransaction();
//				List<messages> Lofmessages = getMessageslist();
//				List<String> messagesContent = new ArrayList<String>();
//				List<String> from = new ArrayList<String>();
//				String currentUser = tuple_msg.getMovies().get(0).getEngName();
//				System.out.println("current user: " + currentUser);
//				for (int i = 0; i < Lofmessages.size(); i++) {
//					System.out.println("Lofmessages.get(i).getUser: " + Lofmessages.get(i).getUser());
//					if (Lofmessages.get(i).getUser().equals(currentUser)) {
//						messagesContent.add(Lofmessages.get(i).getMSGcontext());
//						from.add(Lofmessages.get(i).getFromName());
//					}
//					// complaintTime.add(Lofcomplaints.get(i).getTime());
//				}
//				TripleObject to = new TripleObject("All messages", null, null);
//				to.setmessageContext(messagesContent);
//				to.setFromMSG(from);
//				// to.setComplaintTime(complaintTime);
//				try {
//					client.sendToClient(to);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				App.session.getTransaction().commit();
//
//			} catch (HibernateException e) {
//				e.printStackTrace();
//				App.session.getTransaction().rollback();
//			}
//			App.session.close();
//		}
		if (ObjctMsg.startsWith("Show messages")) {
			System.out.println("in the server of show messages");
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				List<messages> Lofmessages = getMessageslist();
				List<String> messagesContent = new ArrayList<String>();
				List<String> from = new ArrayList<String>();
				List<String> ID = new ArrayList<String>();
				String currentUser = tuple_msg.getMovies().get(0).getEngName();
				// System.out.println("current user: "+ currentUser);
				for (int i = 0; i < Lofmessages.size(); i++) {

					LocalDate today = LocalDate.now();
					// System.out.println("Lofmessages.get(i).getUser: "+
					// Lofmessages.get(i).getUser());
					int greater = today.compareTo(Lofmessages.get(i).getDate());
					if (Lofmessages.get(i).getUser().equals(currentUser) && greater >= 0) {
						messagesContent.add(Lofmessages.get(i).getMSGcontext());
						from.add(Lofmessages.get(i).getFromName());
						ID.add(Lofmessages.get(i).getId());
					}
					// complaintTime.add(Lofcomplaints.get(i).getTime());
				}
				TripleObject to = new TripleObject("All messages", null, null);
				to.setmessageContext(messagesContent);
				to.setFromMSG(from);
				to.setMSGid(ID);
				// to.setComplaintTime(complaintTime);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.equals("Send msg to user")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				messages msg4 = tuple_msg.getmsg();
				App.session.save(msg4);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Delete restricted day ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String dateToDelete = ObjctMsg.substring(22);
				List<String> dates = new ArrayList<String>();
				purpleChar pc = getPC().get(0);
				dates = pc.getDays();
				for (int i = 0; i < dates.size(); i++) {
					if (dates.get(i).equals(dateToDelete)) {
						dates.remove(i);
					}
				}
				pc.setDays(dates);
				App.session.save(pc);
				App.session.flush();
				List<String> times = new ArrayList<String>();
				List<Movie> moviesList = getMoviesList();
				for (int i = 0; i < moviesList.size(); i++) {
					if (moviesList.get(i).getType() == 0 || moviesList.get(i).getType() == 3) {
						Movie movie = moviesList.get(i);
						List<String> movieDates = movie.getMovieTimes().getDate();
						List<String> movieTimes = movie.getMovieTimes().getTimes();
						for (int j = 0; j < movieDates.size(); j++) {
							if (movieDates.get(j).equals(dateToDelete)) {
								times.add(movieTimes.get(j));
							}
						}
					}
				}
				List<MapChair> mcList = new ArrayList<MapChair>();
				try {
					mcList = getAllMapChairs();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < times.size(); i++) {
					for (int j = 0; j < mcList.size(); j++) {
						if (mcList.get(j).getStart_time().equals(times.get(i))) {
							int rows = mcList.get(j).getRows();
							int cols = mcList.get(j).getCols();
							int size = rows * cols;
							int bought = mcList.get(j).getNumOfBoughtSeat();
							mcList.get(j).setNmberAvailableChair(size - bought);
						}
					}
				}
				TripleObject to = new TripleObject("All updated restricted days", null, null);
				to.setList(dates);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Approve request")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String newPrice = ObjctMsg.substring(16);
				int idx = 0;
				PriceRequestsChart chart = App.session.get(PriceRequestsChart.class, 1);
				List<String> hlpr = new ArrayList<String>();
				hlpr = chart.getNewPrice();
				for (int i = 0; i < hlpr.size(); i++) {
					if (hlpr.get(i).equals(newPrice)) {
						hlpr.remove(i);
						idx = i;
					}
				}
				chart.setNewPrice(hlpr);
				String movieName = chart.getMovieEngName().get(idx);
				chart.getMovieEngName().remove(idx);
				List<Movie> movie = getMovie(movieName);
				movie.get(0).setPrice(Integer.parseInt(newPrice));

				TripleObject to = new TripleObject("Updated chart movies", null, null);
				to.setList(chart.getMovieEngName());
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Movie dates")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movie = ObjctMsg.substring(12);
				MovieTimes MT = getMovie(movie).get(0).getMovieTimes();
				List<String> dates = MT.getDate();
				TripleObject to = new TripleObject("Dates", null, null);
				to.setList(dates);
				System.out.println("GOT DATES LIST IN SERVER: ");
				System.out.println(to.getList());
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Show package ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				User user = getUser(ObjctMsg.substring(13)).get(0);
				int packId = -1;
				if (getPackage(user.getPackageId()).size() > 0) {
					Package P = getPackage(user.getPackageId()).get(0);
					packId = P.get_ticks();
				}
				List<Movie> list = new ArrayList<Movie>();
				Movie movie = new Movie();
				movie.setLength(packId);
				list.add(movie);
				TripleObject to = new TripleObject("package num of tickets", list, null);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Show package2 ")) {
			try {
				System.out.println("in server show 2");
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				System.out.println("in server after session");
				// User user = getUser(ObjctMsg.substring(14)).get(0);
				int package_id = Integer.parseInt(ObjctMsg.substring(14));
				int packId = -1;
				System.out.println("in server before the if");
				if (getPackage(package_id).size() > 0) {
					Package P = getPackage(package_id).get(0);
					packId = P.get_ticks();
					System.out.println("in server in the if");
				}
				System.out.println("in server after if");
				List<Movie> list = new ArrayList<Movie>();
				Movie movie = new Movie();
				movie.setLength(packId);
				list.add(movie);
				System.out.println("in server show 2 part 2 ");
				TripleObject to = new TripleObject("package num of tickets2", list, null);
				try {
					System.out.println("before sending the message to client");
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Filter dates")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String from = tuple_msg.getList().get(0);
				String to = tuple_msg.getList().get(1);
				List<String> filteredMovies = new ArrayList<String>();
				List<String> filteredTimes = new ArrayList<String>();
				List<String> filteredDates = new ArrayList<String>();
				List<Movie> tmp = getMoviesList();
				List<Movie> all = new ArrayList<Movie>();
				Boolean found = false;
				List<MovieTimes> mtList = new ArrayList<MovieTimes>();
				for (int i = 0; i < tmp.size(); i++) {
					if (tmp.get(i).getType() == 0 || tmp.get(i).getType() == 3) {
						all.add(tmp.get(i));
					}
				}
				if (!from.equals("") && !to.equals("")) {
					for (int i = 0; i < all.size(); i++) {
						for (int j = 0; j < all.get(i).getMovieTimes().getDate().size(); j++) {
							if (isBigger(all.get(i).getMovieTimes().getDate().get(j), from) == true
									&& isBigger(all.get(i).getMovieTimes().getDate().get(j), to) == false) {
								// this date is bigger than from and smaller that to
								found = true;
								filteredMovies.add(all.get(i).getEngName());
								filteredDates.add(all.get(i).getMovieTimes().getDate().get(j));
								filteredTimes.add(all.get(i).getMovieTimes().getTimes().get(j));
							}
						}
					}
					if (found == true) {
						MovieTimes mvt = new MovieTimes(filteredTimes);
						mvt.setDate(filteredDates);
						mtList.add(mvt);
					}
				} else if (!from.equals("") && to.equals("")) {
					for (int i = 0; i < all.size(); i++) {
						for (int j = 0; j < all.get(i).getMovieTimes().getDate().size(); j++) {
							if (isBigger(all.get(i).getMovieTimes().getDate().get(j), from) == true) {
								// this date is bigger than from
								found = true;
								filteredMovies.add(all.get(i).getEngName());
								filteredDates.add(all.get(i).getMovieTimes().getDate().get(j));
								filteredTimes.add(all.get(i).getMovieTimes().getTimes().get(j));
							}
						}
					}
					if (found == true) {
						MovieTimes mvt = new MovieTimes(filteredTimes);
						mvt.setDate(filteredDates);
						mtList.add(mvt);
					}
				} else if (from.equals("") && !to.equals("")) {
					for (int i = 0; i < all.size(); i++) {
						for (int j = 0; j < all.get(i).getMovieTimes().getDate().size(); j++) {
							if (isBigger(all.get(i).getMovieTimes().getDate().get(j), to) == false) {
								// this date is smaller than to
								found = true;
								filteredMovies.add(all.get(i).getEngName());
								filteredDates.add(all.get(i).getMovieTimes().getDate().get(j));
								filteredTimes.add(all.get(i).getMovieTimes().getTimes().get(j));
							}
						}
					}
					if (found == true) {
						MovieTimes mvt = new MovieTimes(filteredTimes);
						mvt.setDate(filteredDates);
						mtList.add(mvt);
					}
				}
				TripleObject res;
				if (found == false) {
					res = new TripleObject("Filtered movies by date", null, null);
				} else {
					res = new TripleObject("Filtered movies by date", null, mtList);
				}
				res.setList(filteredMovies);
				try {
					client.sendToClient(res);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		////// elin
		if (ObjctMsg.equals("Add new person")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = tuple_msg.getMovies().get(0);
				User newuser = new User(movietoadd.getEngName(), movietoadd.getHebName());
				newuser.setRole(-1);
				newuser.setIs_Logged_In(true);
				App.session.save(newuser);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.equals("Add new complaint")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = tuple_msg.getMovies().get(0);
				complaint newcomplaint = new complaint(movietoadd.getEngName(), movietoadd.getHebName());

				/*
				 * Reports report = getReports(1).get(0); System.out.println("after get  ");
				 * List<Integer> ComplaintsPerDay = new ArrayList<Integer>(31); ComplaintsPerDay
				 * = report.getComplaintsPerDay();
				 * System.out.println("after got complaint per day , in1 =  : "
				 * +ComplaintsPerDay.get(1)); ComplaintsPerDay.add(month, 2);
				 * System.out.println("after adding 1  ");
				 * report.setComplaintsPerDay(ComplaintsPerDay);
				 * System.out.println("after settig complaint per day"); // newuser.setRole(-1);
				 * // newuser.setIs_Logged_In(true);
				 */

				// System.out.println("ttttt : " +ComplaintsPerDay.get(month));
				App.session.save(newcomplaint);
				App.session.flush();
				// App.session.save(report);
				// App.session.flush();
				// 3shan no5od lshaher l7ali
				LocalDate local = LocalDate.now();
				int month = local.getMonthValue();
				int day = local.getDayOfMonth();
				Calendar mCalendar = Calendar.getInstance();
				String current_month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
				System.out.println("month = " + current_month);
				System.out.println("month int = " + month);
				List<MonthlyComplaints> helplist = getMonthlyComplaints(1);
				MonthlyComplaints ComplaintsM = helplist.get(0);
				System.out.println("after getting the list ");
				int[] Array = new int[32];
				Array = ComplaintsM.getComplaintspermonth();
				System.out.println("after getting the array ");
				System.out.println("1Array[month] =  " + Array[month]);
				Array[day] = Array[day] + 1;
				ComplaintsM.setComplaintspermonth(Array);
				System.out.println("after setting the array ");
				System.out.println("2Array[month] =  " + Array[month]);
				App.session.save(newcomplaint);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Delete complaint")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				complaint movietodelete = new complaint();
				List<complaint> tmp = getComplaint(ObjctMsg.substring(17));
				if (tmp.size() != 0) {
					movietodelete = tmp.get(0);
					App.session.remove(movietodelete);
				} else {
					try {
						client.sendToClient(new TripleObject("no such movie", null, null));
					} catch (IOException e) {
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

		if (ObjctMsg.startsWith("Show complaints")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				List<complaint> Lofcomplaints = getComplaintslist();
				List<complaint> tmp = new ArrayList<complaint>();
				for (int i = 0; i < Lofcomplaints.size(); i++) {
					if (!Lofcomplaints.get(i).isHandled()) {
						tmp.add(Lofcomplaints.get(i));
					}
				}
				List<String> complaintsContent = new ArrayList<String>();
				List<String> complaintsUser = new ArrayList<String>();

				/*
				 * for (int i = 0; i < tmp.size(); i++) {
				 * complaintsContent.add(tmp.get(i).getComplaintcontext());
				 * complaintsUser.add(tmp.get(i).getName());}
				 */

				List<String> complaintTime = new ArrayList<String>();
				for (int i = 0; i < Lofcomplaints.size(); i++) {
					complaintsContent.add(Lofcomplaints.get(i).getComplaintcontext());
					complaintsUser.add(Lofcomplaints.get(i).getName());
					complaintTime.add(Lofcomplaints.get(i).getremainingTime());

				}
				TripleObject to = new TripleObject("All complaints", null, null);
				to.setComplaintsContent(complaintsContent);
				to.setComplaintsUser(complaintsUser);
				to.setComplaintTime(complaintTime);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Delete MSG")) {
			try {
				System.out.println("in the server of delete msg : ");
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				messages movietodelete = new messages();
				List<messages> tmp = getMessageById(ObjctMsg.substring(11));
				System.out.println("the msg : " + tmp);
				if (tmp.size() != 0) {
					movietodelete = tmp.get(0);
					App.session.remove(movietodelete);
				} else {
					try {
						client.sendToClient(new TripleObject("no such msg", null, null));
					} catch (IOException e) {
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

		if (ObjctMsg.equals("Add new message For movie")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Movie movietoadd = tuple_msg.getMovies().get(0);
				List<User> users = getUserslist();

				// converting the date
				String date = movietoadd.getEngName();
				System.out.println("the date before convert " + date);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

				System.err.println("date before convert" + date);
				// convert String to LocalDate
				LocalDate localDate = LocalDate.parse(date, formatter);
				System.out.println("after converting " + localDate);

				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getPackageId() > -1) {

						System.out.println("the user that i create th message for " + users.get(i).getUser_Name());

						messages newmessage = new messages(movietoadd.getSummary(), movietoadd.getHebName(),
								users.get(i).getUser_Name());
						newmessage.setDate(localDate);
						App.session.save(newmessage);
						App.session.flush();
						System.out.println("after flush");

					}

				}
				App.session.getTransaction().commit();
				System.out.println("after getTransaaction");
				System.out.println("out");
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

//>>>>>>> refs/remotes/origin/elinjamma
//		if (ObjctMsg.startsWith("Show messages")) {
//			System.out.println("in the server of show messages");
//			try {
//				App.session = App.sessionFactory.openSession();
//				App.session.beginTransaction();
//				List<messages> Lofmessages = getMessageslist();
//				List<String> messagesContent = new ArrayList<String>();
//				List<String> from = new ArrayList<String>();
//				List<String> ID = new ArrayList<String>();
//				String currentUser = tuple_msg.getMovies().get(0).getEngName();
//				// System.out.println("current user: "+ currentUser);
//				for (int i = 0; i < Lofmessages.size(); i++) {
//<<<<<<< HEAD
//					// System.out.println("Lofmessages.get(i).getUser: "+
//					// Lofmessages.get(i).getUser());
//					if (Lofmessages.get(i).getUser().equals(currentUser)) {
//						messagesContent.add(Lofmessages.get(i).getMSGcontext());
//						from.add(Lofmessages.get(i).getFromName());
//=======
//					
//					LocalDate today= LocalDate.now();
//					//System.out.println("Lofmessages.get(i).getUser: "+ Lofmessages.get(i).getUser());
//					int greater = today.compareTo(Lofmessages.get(i).getDate());
//					if(Lofmessages.get(i).getUser().equals(currentUser) && greater>=0)
//					{
//					messagesContent.add(Lofmessages.get(i).getMSGcontext());
//					from.add(Lofmessages.get(i).getFromName());
//					ID.add(Lofmessages.get(i).getId());
//>>>>>>> refs/remotes/origin/elinjamma
//					}
//					// complaintTime.add(Lofcomplaints.get(i).getTime());
//				}
//				TripleObject to = new TripleObject("All messages", null, null);
//				to.setmessageContext(messagesContent);
//				to.setFromMSG(from);
//<<<<<<< HEAD
//				// to.setComplaintTime(complaintTime);
//=======
//			    to.setMSGid(ID);
//				//to.setComplaintTime(complaintTime);
//>>>>>>> refs/remotes/origin/elinjamma
//				try {
//					client.sendToClient(to);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				App.session.getTransaction().commit();
//
//			} catch (HibernateException e) {
//				e.printStackTrace();
//				App.session.getTransaction().rollback();
//			}
//			App.session.close();
//		}

		//////// end elin
		if (ObjctMsg.startsWith("log-out")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String username = ObjctMsg.substring(8);
				User tmpUser = getUser(username).get(0);
				tmpUser.setIs_Logged_In(false);

				App.session.save(tmpUser);
				App.session.flush();
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Get hybrid movies")) {
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list3 = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for (int i = 0; i < Helper_list3.size(); i++) {
					if (Helper_list3.get(i).getType() == 3) {
						movies.add(Helper_list3.get(i));
					}
				}
				client.sendToClient(new TripleObject("All Hybrid Movies", movies, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Show reports")) {
			try {
				App.session = App.sessionFactory.openSession();
				Reports report = getReports(1).get(0);
				System.out.println("report in  server ; " + report.getPackages());
				TripleObject to = new TripleObject("All reports", null, null);
				List<Integer> list2 = new ArrayList<Integer>();
				list2.add(report.getTicketsInHaifa());
				list2.add(report.getReturnedTicketsInHaifa());
				list2.add(report.getTicketsInShefaAmr());
				list2.add(report.getReturnedTicketsInShefaAmr());
				list2.add(report.getLinks());
				list2.add(report.getPackages());
				to.setList2(list2);
				to.setMsg("All reports");
				System.out.println("SERVER");
				System.out.println("report = " + report.getReturnedTicketsInHaifa());
				System.out.println(to.getMsg());
				client.sendToClient(to);
				System.out.println("After send to client");
				// App.session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("ComplaintsReports")) {
			try {
				App.session = App.sessionFactory.openSession();

				List<MonthlyComplaints> helplist = getMonthlyComplaints(1);
				MonthlyComplaints ComplaintsM = helplist.get(0);
				System.out.println("after getting the list ");
				int[] CArray = new int[32];
				CArray = ComplaintsM.getComplaintspermonth();

				TripleObject to = new TripleObject("ComplaintsReports", null, null);
				to.setComplaintsPerMArraay(CArray);

				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();

			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Remove link")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movie = ObjctMsg.substring(12);
				Movie Movie = getMovie(movie).get(0);
				Movie.setType(0);
				List<Movie> Helper_list3 = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for (int i = 0; i < Helper_list3.size(); i++) {
					if (Helper_list3.get(i).getType() == 3) {
						movies.add(Helper_list3.get(i));
					}
				}
				TripleObject to = new TripleObject("Updated hybrid movies", movies, null);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("1Add new link")) {
			System.out.println("got to the first part");
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				System.out.println("Session Opened");
				link my_link = tuple_msg2.getlinks();
				System.out.println("Copied the link");
				App.session.save(my_link);
				System.out.println("Saved the link");
				App.session.flush();
				System.out.println("flish is done");
				client.sendToClient(new TripleObject("Your Link ID is: " + my_link.get_id(), null, null));
				Reports reports = getReports(1).get(0);
				int tmp = reports.getLinks();
				reports.setLinks(tmp + 1);
				App.session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("1Add new Ticket ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				Ticket my_Ticket = tuple_msg2.gettickets();
				String screeningTime = my_Ticket.gettime();
				Movie movie = getMovie(my_Ticket.get_movie()).get(0);
				MapChair mc = getmapchairid(movie.getId(), my_Ticket.gettime()).get(0);
				int numOfBoughtSeats = mc.getNumOfBoughtSeat();
				// Movie movie = getMovie(Integer.toString(movieId)).get(0);
				List<String> times = movie.getMovieTimes().getTimes();
				List<String> dates = movie.getMovieTimes().getDate();

				String date = null;
				for (int i = 0; i < times.size(); i++) {
					if (times.get(i).equals(screeningTime)) {
						date = dates.get(i);
					}
				}
				List<String> restrictedDates = getPC().get(0).getDays();
				boolean restriction = false;
				for (int i = 0; i < restrictedDates.size(); i++) {
					if (date.equals(restrictedDates.get(i))) {
						restriction = true;
					}
				}
				if (!(mc.getNmberAvailableChair() == 0)) {
					if (restriction) {
						// if its restrictionday screening date then just buy one and send id
						// else do all the rest.

						numOfBoughtSeats++;
						mc.setNumOfBoughtSeat(numOfBoughtSeats);
						int numOfAvailable = mc.getNmberAvailableChair();
						mc.setNmberAvailableChair(numOfAvailable - 1);
						App.session.save(mc);
						App.session.flush();
						for (int i = 1; i <= 100; i++) {
							if (!mc.getMapChair().contains(i)) {
								my_Ticket.setChair_num(Integer.toString(i));
								mc.getMapChair().add(i);
								App.session.save(mc);
								App.session.save(my_Ticket);
								App.session.flush();
								client.sendToClient(
										new TripleObject("Your Ticket ID is: " + my_Ticket.get_id(), null, null));
								i = 101;
								Reports allreports = getReports(1).get(0);
								if (my_Ticket.get_hall().equals("Haifa")) {
									int tmp2 = allreports.getTicketsInHaifa();
									allreports.setTicketsInHaifa(tmp2 + 1);
									App.session.getTransaction().commit();
								}
								if (my_Ticket.get_hall().equals("Shefa-Amr")) {
									int tmp2 = allreports.getTicketsInShefaAmr();
									allreports.setTicketsInShefaAmr(tmp2 + 1);
									App.session.getTransaction().commit();
								}

							}
						}
					} else {// no restriction
						numOfBoughtSeats++;
						mc.setNumOfBoughtSeat(numOfBoughtSeats);
						int numOfAvailable = mc.getNmberAvailableChair();
						mc.setNmberAvailableChair(numOfAvailable - 1);
						App.session.save(mc);
						App.session.flush();
						App.session.save(my_Ticket);
						App.session.flush();
						client.sendToClient(new TripleObject("Your Ticket ID is: " + my_Ticket.get_id(), null, null));

						Reports allreports = getReports(1).get(0);
						if (my_Ticket.get_hall().equals("Haifa")) {
							int tmp2 = allreports.getTicketsInHaifa();
							allreports.setTicketsInHaifa(tmp2 + 1);
							App.session.getTransaction().commit();
						}
						if (my_Ticket.get_hall().equals("Shefa-Amr")) {
							int tmp2 = allreports.getTicketsInShefaAmr();
							allreports.setTicketsInShefaAmr(tmp2 + 1);
							App.session.getTransaction().commit();
						}
					}
					App.session.getTransaction().commit();

					// updatenumberofchairs(my_Ticket.get_movie(), my_Ticket.gettime());
					App.session.save(my_Ticket);
					App.session.flush();
				} else if (mc.getNmberAvailableChair() == 0) {
					client.sendToClient(new TripleObject("The hall is full", null, null));
				}
				App.session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Add New Package ")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				TripleObject to;
				User user = getUser(ObjctMsg.substring(16)).get(0);
				if (user.getPackageId() != -1) {
					to = new TripleObject("You already have a package", null, null);
				} else {
					Package my_pack = tuple_msg2.getPackage();
					App.session.save(my_pack);
					App.session.flush();
					user.setPackageId(my_pack.get_id());
					to = new TripleObject("Your Package ID is: " + my_pack.get_id(), null, null);
					Reports allreports = getReports(1).get(0);
					int tmp = allreports.getPackages();
					allreports.setPackages(tmp + 1);
					App.session.getTransaction().commit();
				}
				client.sendToClient(to);
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Delete link")) {
			App.session = App.sessionFactory.openSession();
			App.session.beginTransaction();
			boolean x = deletelink(msg, client);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such link", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found link", null, null));
					Reports report = getReports(1).get(0);
					int tmp = report.getLinks();
					report.setLinks(tmp - 1);
					App.session.getTransaction().commit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("remove mapchair with new seat")) {
			App.session = App.sessionFactory.openSession();
			App.session.beginTransaction();	
			String num_seat = tuple_msg.getnumseat();
	//		System.out.println("tuple_msg.getID()"+tuple_msg.getID()); 
	//		System.out.println("tuple_msg.getTime()"+tuple_msg.getTime());
			updatenumberofchairs(tuple_msg.getID(), tuple_msg.getTime(),num_seat);
			
	
			System.out.println("im done of this");
			App.session.getTransaction().commit();
			App.session.close();
		}
		if (ObjctMsg.startsWith("Delete Ticket ")) {
			App.session = App.sessionFactory.openSession();
			App.session.beginTransaction();
			String ticketId = ObjctMsg.substring(14, 15);
			Ticket ticket = getTicket(Integer.parseInt(ticketId)).get(0);
			String branch = ticket.get_hall();
			Reports report = getReports(1).get(0);
			int tmpH = report.getTicketsInHaifa();
			int tmpS = report.getTicketsInShefaAmr();
			boolean x = deleteTicket(msg, client);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such Ticket", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (branch.equals("Shefa-Amr")) {
						report.setTicketsInShefaAmr(tmpS - 1);
					}
					if (branch.equals("Haifa")) {
						report.setTicketsInHaifa(tmpH - 1);
					}
					client.sendToClient(new TripleObject("found ticket", null, null));
					App.session.getTransaction().commit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Lesser Pack ")) {

			App.session = App.sessionFactory.openSession();
			boolean x = Lesser_Pack(msg, client);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such Package", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found Package", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			App.session.close();
		}

		// ****saleh****
		if (ObjctMsg.startsWith("get my map chair")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				int id_movie = tuple_msg.getID();
				String time_movie = tuple_msg.getTime();
				int mapchair_id = getmapchairid(id_movie, time_movie).get(0).getID();
				List<Integer> mc = getMapChair(mapchair_id);
				try {
					TripleObject msg2 = new TripleObject("your mapchair " + mapchair_id, mc);
					client.sendToClient(msg2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("update mapchair with new seat")) {
			App.session = App.sessionFactory.openSession();
			String num_seat = tuple_msg.getnumseat();
			int mapchair_id = getmapchairid(tuple_msg.getID(), tuple_msg.getTime()).get(0).getID();
			System.out.println("mapchair id " + mapchair_id);
			add_seat(mapchair_id, num_seat);
			App.session.close();
		}

		// ****saleh****


		if (ObjctMsg.startsWith("Cancel Screenings")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String date = ObjctMsg.substring(17);
				List<Ticket> Tlist = new ArrayList<Ticket>();
				Movie Mhelper = new Movie();
				Tlist = getTicketList();
				System.out.println("DATE:" + date);
				List<Movie> allmovies = new ArrayList<Movie>();
				allmovies = getMoviesList();
				List<String> datesList = new ArrayList<String>();
				List<String> timesList = new ArrayList<String>();
				int index;
				List<Movie> MoviesDeleted = new ArrayList<Movie>();
				List<Integer> indexes = new ArrayList<Integer>();
				String time;
				Reports reports = getReports(1).get(0);
				int tmpH = reports.getReturnedTicketsInHaifa();
				int tmpS = reports.getReturnedTicketsInShefaAmr();
				int CH = 0;
				int CS = 0;
				for (int i = 0; i < allmovies.size(); i++) {
					if (allmovies.get(i).getType() == 0 || allmovies.get(i).getType() == 3) {
						datesList = allmovies.get(i).getMovieTimes().getDate();
						timesList = allmovies.get(i).getMovieTimes().getTimes();
						for (int j = 0; j < datesList.size(); j++) {
							if (datesList.get(j).equals(date)) {
								time = timesList.get(j);
								System.out.println("movie:" + allmovies.get(i).getEngName());
								System.out.println("after removing");
								MoviesDeleted.add(allmovies.get(i));
								indexes.add(j);
								int Size = Tlist.size();

								for (int k = 0; k < Size; k++) {
									System.out.println("list size tickets = " + Tlist.size());
									if (Tlist.get(k).get_movie().equals(allmovies.get(i).getEngName())) {
										System.out.println("in if 1");
										String T1 = allmovies.get(i).getMovieTimes().getTimes().get(j);
										String T2 = Tlist.get(k).gettime();
										System.out.println("T1 = " + T1);
										System.out.println("T2 = " + T2);
										if (T1.equals(T2)) {
											System.out.println("in if 2");
											if (allmovies.get(i).getBranch().equals("Haifa")) {
												CH++;
											}
											if (allmovies.get(i).getBranch().equals("Shefa-Amr")) {
												System.out.println("in if shefa");
												CS = CS + 1;
												System.out.println("tttt CS:" + CS);
												System.out.println("tttt tmp:" + tmpS + 1);
											}
											System.out.println("removing : T id = " + Tlist.get(k).get_id());
											String cont="We canceled the screening of the movie "+Tlist.get(k).get_movie()+" \n You will get a refund of "+allmovies.get(i).getPrice();
											messages senduser=new messages("server",cont,Tlist.get(k).getuser());
											App.session.save(senduser);
											App.session.flush();
											
											App.session.remove(Tlist.get(k));
											// Tlist.remove(k);
											System.out.println("after removing the ticket ");
										}
									}
								}
								datesList.remove(j);
								timesList.remove(j);
								MapChair mc = getMapChairByTime(time).get(0);
								App.session.remove(mc);
								reports.setReturnedTicketsInHaifa(tmpH + CH);
								reports.setReturnedTicketsInShefaAmr(tmpS + CS);
								System.out.println("after setting the reports " + (tmpS + CS));
							}
						}
					}
				}
				System.out.println("after the for ");
				try {
					client.sendToClient(new TripleObject("Canceled Date", null, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();

			if (ObjctMsg.startsWith("Cancel Screenings")) {
				try {
					App.session = App.sessionFactory.openSession();
					App.session.beginTransaction();
					String date = ObjctMsg.substring(17);
					List<Ticket> Tlist = new ArrayList<Ticket>();
					Movie Mhelper = new Movie();
					Tlist = getTicketList();
					System.out.println("DATE:" + date);
					List<Movie> allmovies = new ArrayList<Movie>();
					allmovies = getMoviesList();
					List<String> datesList = new ArrayList<String>();
					List<String> timesList = new ArrayList<String>();
					int index;
					List<Movie> MoviesDeleted = new ArrayList<Movie>();
					List<Integer> indexes = new ArrayList<Integer>();
					Reports reports = getReports(1).get(0);
					int tmpH = reports.getReturnedTicketsInHaifa();
					int tmpS = reports.getReturnedTicketsInShefaAmr();
					int CH = 0;
					int CS = 0;
					for (int i = 0; i < allmovies.size(); i++) {
						if (allmovies.get(i).getType() == 0 || allmovies.get(i).getType() == 3) {
							datesList = allmovies.get(i).getMovieTimes().getDate();
							timesList = allmovies.get(i).getMovieTimes().getTimes();
							for (int j = 0; j < datesList.size(); j++) {
								if (datesList.get(j).equals(date)) {
									System.out.println("movie:" + allmovies.get(i).getEngName());
									// datesList.remove(j);
									// timesList.remove(j);
									// App.session.getTransaction().commit();
									// App.session.beginTransaction();
									System.out.println("after removing");
									MoviesDeleted.add(allmovies.get(i));
									indexes.add(j);
									int Size = Tlist.size();

									for (int k = 0; k < Size; k++) {
										System.out.println("list size tickets = " + Tlist.size());
										if (Tlist.get(k).get_movie().equals(allmovies.get(i).getEngName())) {
											System.out.println("in if 1");
											String T1 = allmovies.get(i).getMovieTimes().getTimes().get(j);
											String T2 = Tlist.get(k).gettime();
											System.out.println("T1 = " + T1);
											System.out.println("T2 = " + T2);
											if (T1.equals(T2)) {
												System.out.println("in if 2");
												if (allmovies.get(i).getBranch().equals("Haifa")) {
													CH++;
												}
												if (allmovies.get(i).getBranch().equals("Shefa-Amr")) {
													System.out.println("in if shefa");
													CS = CS + 1;
													System.out.println("tttt CS:" + CS);
													System.out.println("tttt tmp:" + tmpS + 1);

												}
												System.out.println("removing : T id = " + Tlist.get(k).get_id());
												App.session.remove(Tlist.get(k));
												// Tlist.remove(k);
												System.out.println("after removing the ticket ");

											}
										}
									}
									datesList.remove(j);
									timesList.remove(j);
									reports.setReturnedTicketsInHaifa(tmpH + CH);
									reports.setReturnedTicketsInShefaAmr(tmpS + CS);
									System.out.println("after setting the reports " + (tmpS + CS));
								}
							}
						}
					}

					System.out.println("after the for ");
					try {
						client.sendToClient(new TripleObject("Canceled Date", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
					App.session.getTransaction().commit();
				} catch (HibernateException e) {
					e.printStackTrace();
					App.session.getTransaction().rollback();
				}
				App.session.close();

			}

			// ****saleh****
			if (ObjctMsg.startsWith("remove mapchair with new seat")) {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String num_seat = tuple_msg.getnumseat();
		//		int mapchair_id = getmapchairid(tuple_msg.getID(), tuple_msg.getTime()).get(0).getID();
			//	System.out.println("mapchair id " + mapchair_id);
			//	remove_seat(mapchair_id, num_seat);
				System.out.println("im done");
				App.session.getTransaction().commit();
				App.session.close();

			}
		}

	}
	private void remove_seat_when_back(int id,String time,String num_seat) {
		
		remove_seat(getmapchairid(id, time).get(0).getID(), num_seat);
	}
	private int updatenumberofchairs(int moviename, String hour,String num_seat) {
		System.out.println("do i even get here");
		Connection c = null;
		java.sql.Statement stmt = null;
		ResultSet rs1 = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			//ResultSet rs2 = stmt.executeQuery("SELECT * FROM movies WHERE EngName= '" + moviename + "'");
			int chairnums = 0;
		//	int movieid=0;
			//while (rs2.next()) {
				//movieid = rs2.getInt("id");
		//	}
			System.out.println(hour+ moviename);
			ResultSet rs = stmt.executeQuery("SELECT * FROM mapchair WHERE start_time= '" + hour + "' AND movie_id='"+moviename+"'");
			
			while (rs.next()) {
				chairnums = rs.getInt("id");
			}
			
			System.out.println(chairnums);
			System.out.println(num_seat);
			stmt.executeUpdate(
					"Delete From mapchair_mymapchair WHERE MapChair_id = '" + chairnums + "' AND My_mapchairs = '" + num_seat + "'");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return 1;

	}

	// ***saleh***

	private static List<Ticket> get_equals_ticket(Ticket my_ticket) {
		App.session = App.sessionFactory.openSession();
		App.session.beginTransaction();
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
		Root<Ticket> userRoot = query.from(Ticket.class);
		Predicate predicateForchairnum = builder.equal(userRoot.get("chair_num"), my_ticket.getChair_num());
		Predicate predicateFormapchairid = builder.equal(userRoot.get("mapchairid"), my_ticket.getmapchairid());
		Predicate predicateForhall = builder.equal(userRoot.get("hall"), my_ticket.get_hall());
		Predicate predicateFormovie = builder.equal(userRoot.get("movie_of_tick"), my_ticket.get_movie());
		Predicate predicateFortime = builder.equal(userRoot.get("start_time"), my_ticket.getstarttime());
		Predicate predicateForticket = builder.and(predicateForchairnum, predicateFormapchairid, predicateForhall,
				predicateFormovie, predicateFortime);
		query.where(predicateForticket);
		List<Ticket> data = App.session.createQuery(query).getResultList();
		App.session.close();
		return data;
	}

	private int remove_seat(int mapchair_id, String num_seat) {
		System.out.println("in remove seat func");
		Connection c = null;
		java.sql.Statement stmt = null;
		List<Integer> mymapchairs = new ArrayList<Integer>();

		try {
			System.out.println("i fel 1");
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");
			System.out.println("i fel 2");
			System.out.println("Opened database successfully");
			System.out.println("i fel 3");
			System.out.println("DELETE FROM mapchair_mymapchair WHERE MapChair_id = '" + mapchair_id
					+ "' AND My_mapchairs= '" + num_seat + "'");
			System.out.println("i fel 4");
			stmt = c.createStatement();
			System.out.println("i fel 5");
			int rs = stmt.executeUpdate("DELETE FROM mapchair_mymapchair WHERE MapChair_id = '" + mapchair_id
					+ "' AND My_mapchairs = '" + num_seat + "'");
			System.out.println("i fel 6");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return 1;
	}

	private int add_seat(int mapchair_id, String num_seat) {

		Connection c = null;
		java.sql.Statement stmt = null;
		List<Integer> mymapchairs = new ArrayList<Integer>();

		try {

			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet RS2 = stmt.executeQuery(
					"select MY_mapchairs From mapchair_mymapchair where MapChair_id=" + mapchair_id + ";");
			while (RS2.next()) {
				mymapchairs.add((Integer) RS2.getInt(1));
				System.out.println((Integer) RS2.getInt(1));
			}
			mymapchairs.add(Integer.parseInt(num_seat));

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {
			App.session = App.sessionFactory.openSession();
			App.session.beginTransaction();
			MapChair mc = App.session.get(MapChair.class, mapchair_id);
			mc.setMapChair(mymapchairs);
			App.session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			App.session.getTransaction().rollback();
		}
		App.session.close();
		return 1;
	}

	private static List<MapChair> getmapchairid(int id_movie, String time) {
	
		CriteriaBuilder builder = App.session.getCriteriaBuilder();

		CriteriaQuery<MapChair> query = builder.createQuery(MapChair.class);

		Root<MapChair> userRoot = query.from(MapChair.class);

		Predicate predicateForMovieId = builder.equal(userRoot.get("movie_id"), Integer.toString(id_movie));
		
		Predicate predicateTimes = builder.equal(userRoot.get("start_time"), time);

		Predicate predicateForMapChair = builder.and(predicateForMovieId, predicateTimes);
		
		query.where(predicateForMapChair);
	
		List<MapChair> data = App.session.createQuery(query).getResultList();
		System.out.println("at the end the asnwer should be :"+data.get(0).getID());
		return data;
	}

	private List<Integer> getMapChair(int mapchair_id) {
		List<Integer> mymapchair = new ArrayList<Integer>();
		Connection c = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet RS2 = stmt.executeQuery(
					"select MY_mapchairs From mapchair_mymapchair where MapChair_id=" + mapchair_id + ";");

			while (RS2.next()) {
				mymapchair.add((Integer) RS2.getInt(1));
			}
			stmt.close();
			c.close();
			return mymapchair;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return mymapchair;

	}
	// work
	// ***saleh***

	private boolean Lesser_Pack(Object msg, ConnectionToClient client) {
		TripleObject tuple_msg = (TripleObject) msg;
		String ObjctMsg = tuple_msg.getMsg();
		System.out.println("i got here after");
		boolean let_in = false;
		String message = ObjctMsg.substring(12);
		int x = Integer.parseInt(message);
		System.out.println(x);
		Connection c = null;
		java.sql.Statement stmt = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;
		try {
			int z = 0;

			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");

			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			stmt2 = c.createStatement();
			rs1 = stmt2.executeQuery("SELECT * FROM package WHERE package_id=' " + x + "'");

			while (rs1.next()) {
				z = rs1.getInt("number_of_ticekts");

			}
			if (z == 1) {
				client.sendToClient(new TripleObject("Package have 0 Ticks so Deleted", null, null));
				int rs = stmt.executeUpdate("DELETE FROM package WHERE package_id = '" + x + "'");
				if (rs != 0) {
					let_in = true;
				}
				stmt.close();
				c.close();
			} else {
				z = z - 1;
				client.sendToClient(new TripleObject("Tickets Number is now: " + z, null, null));
				System.out.println("got ot the if");
				int rs = stmt.executeUpdate(
						"UPDATE package SET number_of_ticekts = '" + z + "' WHERE package_id = '" + x + "'");
				if (rs != 0) {
					let_in = true;
				}
				stmt.close();
				c.close();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		System.out.println("Operation done successfully");
		System.out.println(let_in);
		return let_in;
	}

	private boolean deleteTicket(Object msg, ConnectionToClient client) {
		TripleObject tuple_msg = (TripleObject) msg;
		String ObjctMsg = tuple_msg.getMsg();
		System.out.println("i got here after");
		boolean let_in = false;
		String message = ObjctMsg.substring(14, 15);

		int x = Integer.parseInt(message);
		System.out.println(x);
		System.out.println("DELETE FROM Ticket WHERE ticket_id = x");
		Connection c = null;
		java.sql.Statement stmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Statement stmt2 = null;
		String user = null;
		String user2 = ObjctMsg.substring(25);
		String date1 = null;
		int flag = 0;
		System.out.println(user2);
		try {

			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");

			int idmap = 0;
			String moviename = null;
			int movieid = 0;
			String chairnum = null;
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			stmt2 = c.createStatement();

			Calendar rightNow = Calendar.getInstance();
			String hour = null;

			int hour2 = rightNow.get(Calendar.HOUR_OF_DAY);
			rs1 = stmt2.executeQuery("SELECT * FROM tickets WHERE ticket_id=' " + x + "'");

			System.out.println("passed the selection");
			while (rs1.next()) {
				hour = rs1.getString("start_time");
				user = rs1.getString("user_name");
				idmap = rs1.getInt("mapchairid");
				chairnum = rs1.getString("chair_num");
				moviename = rs1.getString("movie_of_tick");
				date1 = rs1.getString("date");
				System.out.println(hour);
			}
			rs2 = stmt2.executeQuery("SELECT * FROM movies WHERE EngName= '" + moviename + "'");
			while (rs2.next()) {
				movieid = rs2.getInt("id");
			}
			int mapchair_id = getmapchairid(movieid, hour).get(0).getID();
			LocalDateTime myDateObj = LocalDateTime.now();
			System.out.println("Before Formatting: " + myDateObj);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

			String formattedDate = myDateObj.format(myFormatObj);
			String month = formattedDate.substring(3, 5);
			String day = formattedDate.substring(0, 2);
			System.out.println(month);
			int monthrn = Integer.parseInt(month);
			System.out.println(day);
			int dayrn = Integer.parseInt(day);
			int monthofticket = Integer.parseInt(date1.substring(3, 5));
			System.out.println("month of ticke" + monthofticket);

			int dayofticket = Integer.parseInt(date1.substring(0, 2));
			System.out.println("day of ticket" + dayofticket);
			/////
			rs2 = stmt2.executeQuery(
					"SELECT * FROM mapchair WHERE start_time='" + hour + "'   AND movie_id='" + movieid + "'");

			int chairnums = 0;
			while (rs2.next()) {
				chairnums = rs2.getInt("numberavailablechair");
			}
			chairnums++;

			String message2 = hour.substring(0, 2);
			String minsofticks = hour.substring(3, 5);
			int minsoftick = Integer.parseInt(minsofticks);
			System.out.println(minsoftick);
			int minsofday = rightNow.get(Calendar.MINUTE);

			System.out.println(minsofday);

			int y = Integer.parseInt(message2);
			int sumofRN = minsofday + hour2 * 60;
			int someofTicket = minsoftick + y * 60;
			System.out.println("sumofRN :" + sumofRN);
			System.out.println("someofTicket :" + someofTicket);
			int diff = (someofTicket - sumofRN) / 60;
			System.out.println("the diff is :" + diff);
			System.out.println("the value of hour2 is : " + hour2);
			System.out.println("the value of y is : " + y);
			System.out.println("the result is " + (hour2 - y));
			if (user.equals(user2)) {

				if (monthofticket > monthrn || (monthofticket == monthrn && dayofticket > dayrn)
						|| (monthofticket == monthrn && dayofticket == dayrn && diff >= 3)) {
					try {
						flag = 1;
						stmt2.executeUpdate("UPDATE mapchair SET numberavailablechair = '" + chairnums
								+ "' WHERE start_time = '" + hour + "'");
						int remove = remove_seat(mapchair_id, chairnum);
						System.out.println("im in the first if");
						client.sendToClient(new TripleObject("You get 100% refound", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if ((monthofticket == monthrn && dayofticket == dayrn && diff >= 1 && diff < 3 && flag == 0)) {
					try {
						flag = 1;
						stmt2.executeUpdate("UPDATE mapchair SET numberavailablechair = '" + chairnums
								+ "' WHERE start_time = '" + hour + "'");
						int remove = remove_seat(mapchair_id, chairnum);
						System.out.println("im in the second if");
						client.sendToClient(new TripleObject("You get 50% refound", null, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if ((monthofticket < monthrn || dayofticket < dayrn || diff < 1) && flag == 0) {
					try {
						stmt2.executeUpdate("UPDATE mapchair SET numberavailablechair = '" + chairnums
								+ "' WHERE start_time = '" + hour + "'");
						int remove = remove_seat(mapchair_id, chairnum);
						System.out.println("im in the third if");
						client.sendToClient(new TripleObject("You get no refound", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				int rs = stmt.executeUpdate("DELETE FROM Tickets WHERE ticket_id = '" + x + "'");
				if (rs != 0) {
					let_in = true;
				}
				stmt.close();
				c.close();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		System.out.println(let_in);
		return let_in;
	}

	private boolean deletelink(Object msg, ConnectionToClient client) {
		TripleObject tuple_msg = (TripleObject) msg;
		String ObjctMsg = tuple_msg.getMsg();
		boolean let_in = false;

		String message = ObjctMsg.substring(12, 13);

		System.out.println(message);
		int x = Integer.parseInt(message);

		System.out.println("DELETE FROM	links WHERE link_id =" + x);

		Connection c = null;
		Statement stmt = null;
		ResultSet rs1 = null;

		Statement stmt2 = null;
		String user = null;
		String user2 = ObjctMsg.substring(23);
		System.out.println(user2);
		try {

			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB?serverTimezone=UTC", "root", "samer123");

			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			stmt2 = c.createStatement();

			LocalDateTime rightNow = LocalDateTime.now();
			LocalDateTime rightNow2 = LocalDateTime.now();
			LocalDateTime getime = null;
			rightNow = rightNow.plusHours(3);

			rs1 = stmt2.executeQuery("SELECT * FROM links WHERE link_id=' " + x + "'");
			System.out.println("passed the selection");
			while (rs1.next()) {
				String str = rs1.getString("start_time_of_work");
				user = rs1.getString("user_name");
				str = str.replace(" ", "T");

				System.out.println(str);
				getime = LocalDateTime.parse(str);
			}
			System.out.println("Date: " + rightNow);
			System.out.println("Date2: " + getime);
			if (user2.equals(user)) {
				if (rightNow.isBefore(getime)) {
					try {
						System.out.println("im in the first if");
						client.sendToClient(new TripleObject("You get 100% refound", null, null));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					rightNow.minusHours(2);
					if (rightNow.isBefore(getime)) {
						try {
							System.out.println("im in the second if");
							client.sendToClient(new TripleObject("You get 50% refound", null, null));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							System.out.println("im in the third if");
							client.sendToClient(new TripleObject("You get no refound", null, null));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				int rs = stmt.executeUpdate("DELETE FROM links WHERE link_id= ' " + x + "'");
				if (rs != 0) {
					let_in = true;
				}
				stmt.close();
				c.close();
			} else {
				try {
					System.out.println("im in the wrong user if");
					client.sendToClient(new TripleObject("This Link is not for you", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		System.out.println("Operation done successfully");
		System.out.println(let_in);
		return let_in;

		// TODO: add close connection
	}

	///// elin jammal//
	private static List<complaint> getComplaintslist() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<complaint> query = builder.createQuery(complaint.class);
		query.from(complaint.class);
		List<complaint> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<User> getUserslist() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		query.from(User.class);
		List<User> data = App.session.createQuery(query).getResultList();
		return data;
	}

	///
	private static List<Movie> getMoviesList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Hall> getHallsList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Hall> query = builder.createQuery(Hall.class);
		query.from(Hall.class);
		List<Hall> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Ticket> getTicketList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
		query.from(Ticket.class);
		List<Ticket> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Ticket> getTicket(int ticketId) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
		Root<Ticket> userRoot = query.from(Ticket.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("ticket_id"), ticketId);
		query.where(predicateForMoviename);
		List<Ticket> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<MapChair> getMapChairByTime(String time) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<MapChair> query = builder.createQuery(MapChair.class);
		Root<MapChair> userRoot = query.from(MapChair.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("start_time"), time);
		query.where(predicateForMoviename);
		List<MapChair> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<purpleChar> getPC() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<purpleChar> query = builder.createQuery(purpleChar.class);
		query.from(purpleChar.class);
		List<purpleChar> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<link> getAlllinks() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<link> query = builder.createQuery(link.class);
		query.from(link.class);
		List<link> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Package> getPackage() throws Exception {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Package> query = builder.createQuery(Package.class);
		query.from(Package.class);
		List<Package> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<MapChair> getAllMapChairs() throws Exception {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<MapChair> query = builder.createQuery(MapChair.class);
		query.from(MapChair.class);
		List<MapChair> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<User> GetUser(String username, String pass) {
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

	private static List<complaint> getComplaint(String complaint) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<complaint> query = builder.createQuery(complaint.class);
		Root<complaint> userRoot = query.from(complaint.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("Complaintcontext"), complaint);
		query.where(predicateForMoviename);
		List<complaint> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<messages> getMessage(String message) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<messages> query = builder.createQuery(messages.class);
		Root<messages> userRoot = query.from(messages.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("MSGcontext"), message);
		query.where(predicateForMoviename);
		List<messages> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<messages> getMessageById(String ID) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<messages> query = builder.createQuery(messages.class);
		Root<messages> userRoot = query.from(messages.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("id"), ID);
		query.where(predicateForMoviename);
		List<messages> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Movie> getMovieFromId(int movieId) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		Root<Movie> userRoot = query.from(Movie.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("id"), movieId);
		query.where(predicateForMoviename);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<messages> getMessageslist() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<messages> query = builder.createQuery(messages.class);
		query.from(messages.class);
		List<messages> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<User> getUser(String userName) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> userRoot = query.from(User.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("User_Name"), userName);
		query.where(predicateForMoviename);
		List<User> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Package> getPackage(int packageId) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Package> query = builder.createQuery(Package.class);
		Root<Package> userRoot = query.from(Package.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("package_id"), packageId);
		query.where(predicateForMoviename);
		List<Package> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Reports> getReports(int reportId) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Reports> query = builder.createQuery(Reports.class);
		Root<Reports> userRoot = query.from(Reports.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("Report_id"), reportId);
		query.where(predicateForMoviename);
		List<Reports> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<MonthlyComplaints> getMonthlyComplaints(int MonthlyComplaintsId) {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<MonthlyComplaints> query = builder.createQuery(MonthlyComplaints.class);
		Root<MonthlyComplaints> userRoot = query.from(MonthlyComplaints.class);
		Predicate predicateForMoviename = builder.equal(userRoot.get("Cid"), MonthlyComplaintsId);
		query.where(predicateForMoviename);
		List<MonthlyComplaints> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static Boolean isBigger(String date1, String date2) {
		List<String> dates1 = Arrays.asList(date1.split("/"));
		List<String> dates2 = Arrays.asList(date2.split("/"));
		int year1 = Integer.parseInt(dates1.get(2));
		int year2 = Integer.parseInt(dates2.get(2));
		int month1 = Integer.parseInt(dates1.get(1));
		int month2 = Integer.parseInt(dates2.get(1));
		int day1 = Integer.parseInt(dates1.get(0));
		int day2 = Integer.parseInt(dates2.get(0));

		if (year1 > year2 || (year1 == year2) && (month1 > month2) || (month1 == month2) && (day1 > day2)) {
			return true;
		} else {
			return false;
		}
	}
}