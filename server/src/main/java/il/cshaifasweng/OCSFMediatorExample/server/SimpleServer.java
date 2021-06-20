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

import org.hibernate.HibernateException;

import il.cshaifasweng.OCSFMediatorExample.entities.MapChair;
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
		
		//****saleh****
		 if (ObjctMsg.startsWith("get my map chair")) {
			 App.session = App.sessionFactory.openSession();
			 int id_movie=tuple_msg.getID();
			 String time_movie=tuple_msg.getTime();
			 List<Integer>mc=getMapChair(getmapchairid(id_movie,time_movie));
			 try {
			 TripleObject msg2=new TripleObject("your mapchair",mc);
			 client.sendToClient(msg2);
			 }
			 catch (IOException e) {
					e.printStackTrace();
				}
			 App.session.close();
			
		}
		 
		 if  (ObjctMsg.startsWith("update mapchair with new seat")) {
			 App.session = App.sessionFactory.openSession();
			 String num_seat=tuple_msg.getnumseat();
			 int mapchair_id=getmapchairid(tuple_msg.getID(),tuple_msg.getTime());
			 if(is_seat_busy(mapchair_id,Integer.parseInt(num_seat))) {
				 try {
					 TripleObject msg_busy=new TripleObject("this seat is busy");
					client.sendToClient(msg_busy);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 else {
			 add_seat(mapchair_id,num_seat);
			 try {
				 System.out.println("1.mapchair id in SimpleServer "+mapchair_id);
				 TripleObject msg_notbusy=new TripleObject("this seat isn't busy");	
				 System.out.println("2.mapchair id in SimpleServer "+mapchair_id);
					client.sendToClient(msg_notbusy);
				System.out.println("3.mapchair id in SimpleServer "+mapchair_id);	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 App.session.close();
			 
		 }
		//****saleh****
	}
	
	//***saleh***
	
	private boolean is_seat_busy(int mapchair_id,int seat_num) {
		List<Integer> list_seats=getMapChair(mapchair_id);
		for(Integer seat:list_seats) {
			if(seat_num==seat)return true;
		}
		return false;
	}
	
	private int add_seat(int mapchair_id,String num_seat) {
		
		Connection c = null;
		 java.sql.Statement stmt = null;
		 List<Integer> mymapchairs=new ArrayList<Integer>();
		 
		 try {
				c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "S208343871s*");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				 ResultSet RS2 = stmt.executeQuery("select MY_mapchairs From mapchair_mymapchair where MapChair_id="+mapchair_id+";");
					while(RS2.next()) {
						mymapchairs.add((Integer)RS2.getInt(1));
						System.out.println((Integer)RS2.getInt(1));
					}
				mymapchairs.add(Integer.parseInt(num_seat));

				stmt.close();
				c.close();
			}
		 catch (Exception e) {
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
		
		
		
		/*Connection c = null;
		 java.sql.Statement stmt = null;
		 List<Integer> mymapchairs=new ArrayList<Integer>();
		 
		 try {
				c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "S208343871s*");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				 ResultSet RS2 = stmt.executeQuery("select MY_mapchairs From mapchair_mymapchair where MapChair_id="+mapchair_id+";");
					while(RS2.next()) {
						mymapchairs.add((Integer)RS2.getInt(1));
						System.out.println((Integer)RS2.getInt(1));
					}
				mymapchairs.add(Integer.parseInt(num_seat));
				
				
				System.out.println(mapchair_id);
				System.out.println(num_seat);
				int RS1 = stmt.executeUpdate("insert into mapchair_mymapchair (MapChair_id,My_mapchairs) values ("+mapchair_id+", '"+mymapchairs+"')");
				System.out.println(RS1);
				if (RS1==-1 ) {
					System.out.print("Error");
					return -1;
				}
				stmt.close();
				c.close();
			}
		 catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		 */
		 return 1;
	}
	private int getmapchairid(int id_movie,String time) {
		Connection c = null;
		java.sql.Statement stmt = null;
		int mapchair_id=-1;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "S208343871s*");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet RS1 = stmt.executeQuery("select *From mapchair where movie_id="+id_movie+" and start_time="+time+";");
			if (RS1==null ) {
				System.out.print("Error, movie at this time not found");
				return -1;
			}
			if(RS1.next()) mapchair_id=(int)RS1.getInt("id");
			stmt.close();
			c.close();
		}
	 catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}
	System.out.println("Operation done successfully");
	return mapchair_id;
	}
	
	
	private List<Integer> getMapChair(int mapchair_id) {
		List<Integer> mymapchair=new ArrayList<Integer>();
		Connection c = null;
		java.sql.Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NewDB", "root", "S208343871s*");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
		/*	ResultSet RS1 = stmt.executeQuery("select *From mapchair where movie_id="+id+" and start_time="+time+";");
			if (RS1==null ) {
				System.out.print("Error, mapchair not found");
				return null;
			}

			int mapchair_id=-1;
			if(RS1.next()) mapchair_id=(int)RS1.getInt("id");*/
			ResultSet RS2 = stmt.executeQuery("select MY_mapchairs From mapchair_mymapchair where MapChair_id="+mapchair_id+";");

			while(RS2.next()) {
				mymapchair.add((Integer)RS2.getInt(1));
			}
			stmt.close();
			c.close();
			return mymapchair;
		}
	 catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}
	System.out.println("Operation done successfully");
	return mymapchair;

	}
	//work 
	//***saleh***

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
				
				int rs2 = stmt.executeUpdate("INSERT INTO movietimes_time (MovieTimes_id, time) values (" + id+ ", '" + newl + "')");
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
