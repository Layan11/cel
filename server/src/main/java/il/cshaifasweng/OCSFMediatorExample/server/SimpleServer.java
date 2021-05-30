package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.MovieTimes;
import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
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

		if (ObjctMsg.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (ObjctMsg.startsWith("Delete movie ")) {
			App.session = App.sessionFactory.openSession();
			boolean x = deleteMovie(msg);
			if (x == false) {
				try {
					client.sendToClient(new TripleObject("no such movie", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					client.sendToClient(new TripleObject("found movie", null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			App.session.close();
		}
		if (ObjctMsg.startsWith("Coming_Soon_Movies"))
		{
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list = getMoviesList();
				List<Movie> Coming_Soon_Movies = new ArrayList<Movie>();
				for(int i = 0; i < Helper_list.size(); i++)
				{
					if(Helper_list.get(i).getType()==true)
					{
						Coming_Soon_Movies.add(Helper_list.get(i));
					}
				}
				client.sendToClient(new TripleObject("All Coming Soon Movies", Coming_Soon_Movies, null));

			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Browse movies")) {
			System.out.println("in browse movies in server");
			try {
				App.session = App.sessionFactory.openSession();
				List<Movie> Helper_list = getMoviesList();
				List<Movie> movies = new ArrayList<Movie>();
				for(int i = 0; i < Helper_list.size(); i++)
				{
					if(Helper_list.get(i).getType()==false)
					{
						movies.add(Helper_list.get(i));
					}
				}
				System.out.println("after get movies list in server print first movie from this list: "
						+ movies.get(0).getEngName());
				client.sendToClient(new TripleObject("All Movies", movies, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.session.close();
		}

		if (ObjctMsg.startsWith("Add Screening Time") || ObjctMsg.startsWith("Delete Screening Time")
				|| ObjctMsg.startsWith("Update Screening Time")) {
		App.session = App.sessionFactory.openSession();
			String name = tuple_msg.getMovies().get(0).getEngName();
			String Newtime = tuple_msg.getMovies().get(0).getHebName();
			String oldTime = tuple_msg.getMovies().get(0).getProducer();
			boolean res = editScreeningTime(name, Newtime, oldTime);
			if (res == false) {
				System.out.println("An error has occured");
			} else {
				List<MovieTimes> movieTimes = new ArrayList<MovieTimes>();
				movieTimes.get(0).SetMovieTimes(getMovieTimes(name));
				System.out.println("CHECKKKKKKKKKKKK: " + movieTimes.get(0).getTimes());
				TripleObject to = new TripleObject("All Movies Times", null, movieTimes);
				to.setMoviesTimes(movieTimes);
				try {
					client.sendToClient(to);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		}
		App.session.close();
	}

	private boolean deleteMovie(Object msg) {
		boolean let_in = false;
		String message = ((String) msg).substring(13);
		System.out.println("DELETE FROM movies WHERE EngName = '" + message + "'");
		Connection c = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "Hallaso1924c!");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			int rs = stmt.executeUpdate("DELETE FROM movies WHERE EngName = '" + message + "'");
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

	private static List<Movie> getMoviesList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
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

	private static List<String> getMovieTimes(String name) {
		List<String> times = new ArrayList<String>();
		Connection c = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "Hallaso1924c!");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet RS = stmt.executeQuery("SELECT FROM movies WHERE EngName = '" + name + "'");
			if (RS == null) {
				System.out.print("Error, movie not found");
				return null;
			}
			int movieId = RS.getInt("id");
			ResultSet rs = stmt.executeQuery("SELECT FROM movietimes_time WHERE id = '" + movieId + "'");
			if (rs != null) {
				times = (List<String>) rs;
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return times;
	}

	private boolean editScreeningTime(String name, String newTime, String oldTime) {
		boolean let_in = false;
		Connection c = null;
		Statement stmt = null;
		int id = -1;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "Hallaso1924c!");
			//c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			System.out.println("SELECT* FROM movies WHERE EngName = '" + name + "';");
			ResultSet rs = stmt.executeQuery("SELECT* FROM movies WHERE EngName = " + name );
			if (rs.next()) {
				id = rs.getInt("id");
			}
			if (oldTime == null) {
				List<String> newl = getMovieTimes(name);
				newl.add(newTime);
				MovieTimes newMT = new MovieTimes(newl);
				
				int rs2 = stmt.executeUpdate("INSERT INTO movietimes_time (MovieTimes_id, time) values (" + id
						+ ", '" + newl + "')");
				if (rs2 != -1) {
					let_in = true;
				}
			}

			if (newTime == null) {
				int rs2 = stmt.executeUpdate("DELETE FROM movietimes_time WHERE time = '" + oldTime + "'");
				if (rs2 != -1) {
					let_in = true;
				}
			}

			else {
				int rs2 = stmt.executeUpdate(
						"UPDATE movietimes_time SET time = '" + newTime + "' WHERE MovieTimes_id = " + id);
				if (rs2 != -1) {
					let_in = true;
				}
			}

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return let_in;
	}
}
