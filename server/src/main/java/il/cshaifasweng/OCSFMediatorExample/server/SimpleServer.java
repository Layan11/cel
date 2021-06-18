package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import il.cshaifasweng.OCSFMediatorExample.entities.DoubleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.Package;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequestsChart;
import il.cshaifasweng.OCSFMediatorExample.entities.Reports;
import il.cshaifasweng.OCSFMediatorExample.entities.Ticket;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
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
				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
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
		}
		if (ObjctMsg.startsWith("Update Screening Time")) {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
				String movie = ObjctMsg.substring(22);
				String oldTime = tuple_msg.getMovieTimes().get(0).getTimes().get(0);// old time is in place 0 new time
																					// is in place 1
				String Newtime = tuple_msg.getMovieTimes().get(0).getTimes().get(1);
				String NewDate = tuple_msg.getMovieTimes().get(0).getDate().get(0);
				String oldDate = "";
				Movie movieToUpdate = getMovie(movie).get(0);
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

				App.session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
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
				// App.session.beginTransaction();
				Reports report = getReports(1).get(0);
				System.out.println("report in  server ; " + report.getPackages());
				TripleObject to = new TripleObject("All reports", null, null);
				// List<Reports> list = new ArrayList<Reports>();
				// list.add(report);
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
				App.session.save(my_Ticket);
				App.session.flush();
				client.sendToClient(new TripleObject("Your Ticket ID is: " + my_Ticket.get_id(), null, null));
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
			System.out.println("got to delete");
			App.session = App.sessionFactory.openSession();
			boolean x = deletelink(msg, client);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such link", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found link", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Delete Ticket ")) {
			System.out.println("i got here");
			App.session = App.sessionFactory.openSession();
			boolean x = deleteTicket(msg, client);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such Ticket", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found ticket", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found Package", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			App.session.close();
		}

	}

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
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "root-Pass1.@");

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
		String message = ObjctMsg.substring(14);
		int x = Integer.parseInt(message);
		System.out.println("DELETE FROM Ticket WHERE ticket_id = x");
		Connection c = null;
		java.sql.Statement stmt = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "root-Pass1.@");

			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			stmt2 = c.createStatement();

			Calendar rightNow = Calendar.getInstance();
			int hour = 3;

			int hour2 = rightNow.get(Calendar.HOUR_OF_DAY);
			rs1 = stmt2.executeQuery("SELECT * FROM tickets WHERE ticket_id=' " + x + "'");
			System.out.println("passed the selection");
			while (rs1.next()) {
				hour = rs1.getInt("start_time");
				System.out.println(hour);
			}
			if (hour2 - hour >= 3) {
				try {
					System.out.println("im in the first if");
					client.sendToClient(new TripleObject("You get 100% refound", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (hour2 - hour >= 1 && hour2 - hour < 3) {
				try {
					System.out.println("im in the second if");
					client.sendToClient(new TripleObject("You get 50% refound", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (hour2 - hour < 1) {
				try {
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
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		System.out.println(let_in);
		return let_in;

		// TODO: add close connection
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

			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "root-Pass1.@");

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
							// TODO Auto-generated catch block
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

	private static List<Movie> getMoviesList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Ticket> getTicketList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
		query.from(Ticket.class);
		List<Ticket> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<link> getAlllinks() throws Exception {
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
//	private static List<MovieTimes> getAllMovieTimes() {
//		CriteriaBuilder builder = App.session.getCriteriaBuilder();
//		CriteriaQuery<MovieTimes> query = builder.createQuery(MovieTimes.class);
//		query.from(MovieTimes.class);
//		List<MovieTimes> data = App.session.createQuery(query).getResultList();
//		System.out.println("SC in getallmovies : " + data.get(0).getTimes());
//		System.out.println("SC in getallmovies : " + data.get(1).getTimes());
//		return data;
//	}

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