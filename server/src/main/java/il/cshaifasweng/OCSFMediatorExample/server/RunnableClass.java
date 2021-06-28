package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;

public class RunnableClass {
	long delay = 10 * 1000 * 6; // delay in milliseconds
	public static Session session;
//	public static SessionFactory sessionFactory;

	LoopTask task = new LoopTask();
	LoopTask2 task2 = new LoopTask2();

	Timer timer = new Timer("TaskName");
	Timer timer2 = new Timer("TaskName2");

	public void start() {
		timer.cancel();
		timer = new Timer("TaskName");
		timer2 = new Timer("TaskName2");
		Date executionDate = new Date(); // no params = now
		timer.scheduleAtFixedRate(task, executionDate, delay);
		timer2.scheduleAtFixedRate(task2, executionDate, delay);

	}

	private class LoopTask extends TimerTask {
		public void run() {
			try {
				session = App.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				List<link> tmp = getAlllinks();
				link mylink;
				for (int i = 0; i < tmp.size(); i++) {
					mylink = tmp.get(i);
					if (mylink != null) {
						LocalDateTime myDateObj = mylink.get_start();
						LocalDateTime rightNow2 = LocalDateTime.now();
						int yearmovie = myDateObj.getYear();
						int yearnow = rightNow2.getYear();
						int monthmovie = myDateObj.getMonthValue();
						int monthnow = rightNow2.getMonthValue();
						int daymovie = myDateObj.getDayOfMonth();
						int daynow = rightNow2.getDayOfMonth();
						int hourmovie = myDateObj.getHour();
						int hournow = rightNow2.getHour();
						int minmovie = myDateObj.getMinute();
						int minnow = rightNow2.getMinute();
						System.out.println("date now, year: " + yearnow + " month: " + monthnow + " day:" + daynow
								+ " hournow " + hournow + " minnow: " + minnow);
						System.out.println("date link, year: " + yearmovie + " month: " + monthmovie + " day:"
								+ daymovie + " hourmovie: " + hourmovie + " minmovie:" + minmovie);
						if (yearmovie == yearnow) {
							if (monthmovie == monthnow) {
								if (daymovie == daynow) {
									if (minmovie == minnow) {
										if (hournow + 1 == hourmovie) {
											String cont = "the link with id: " + mylink.get_id()
													+ " \nwill start working after 1 hour";
											messages senduser = new messages("server", cont, mylink.getuser());
											session.save(senduser);
											session.flush();
										}
									}

								}

							}
						}
					}
				}
				if (tx.getStatus().equals(TransactionStatus.ACTIVE)) { 
				    tx.commit();
				}
				//session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();
		}
	}

	private class LoopTask2 extends TimerTask {
		public void run() {
			try {
				session = App.sessionFactory.openSession();
				Transaction tx2 = session.beginTransaction();
				List<Movie> allMovies = getMoviesList();
				List<Movie> moviesinbranches = new ArrayList<Movie>();
				List<User> allUsers = getUserslist();
				List<User> UsersWithPackage = new ArrayList<User>();

				for (int i = 0; i < allUsers.size(); i++) {
					if (allUsers.get(i).getPackageId() > -1) {
						UsersWithPackage.add(allUsers.get(i));
					}
				}
				for (int i = 0; i < allMovies.size(); i++) {
					if (allMovies.get(i).getType() == 0 || allMovies.get(i).getType() == 3) {
						moviesinbranches.add(allMovies.get(i));
					}
				}
				for (int i = 0; i < moviesinbranches.size(); i++) {
					List<String> dates = allMovies.get(i).getMovieTimes().getDate();
					String SmallestDate = FindSmallestDate(dates);
					LocalDateTime rightNoww = LocalDateTime.now();
					int yearnoww = rightNoww.getYear();
					int monthnoww = rightNoww.getMonthValue();
					int daynoww = rightNoww.getDayOfMonth();
					List<String> dates1 = Arrays.asList(SmallestDate.split("/"));
					int year11 = Integer.parseInt(dates1.get(2));
					int month11 = Integer.parseInt(dates1.get(1));
					int day11 = Integer.parseInt(dates1.get(0));
					System.out.println("smallest for " + moviesinbranches.get(i).getEngName() + " : " + SmallestDate);
					if (yearnoww == year11 && monthnoww == month11 && daynoww == day11) {
						System.out.println("in if");
						for (int k = 0; k < UsersWithPackage.size(); k++) {
							System.out.println("USER = " + UsersWithPackage.get(i).getUser_Name());
							String cont = "The movie  " + moviesinbranches.get(i).getEngName()
									+ " is in our branches for the first time";
							messages MSGtosend = new messages("server", cont, UsersWithPackage.get(k).getUser_Name());
							session.save(MSGtosend);
							session.flush();
						}
						System.out.println("INSIDE THE IF");
						// App.session.save(senduser);
						session.flush();
					}
				}
				if (tx2.getStatus().equals(TransactionStatus.ACTIVE)) { 
				    tx2.commit();
				}
				//session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();
		}

	}

	private static List<link> getAlllinks() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<link> query = builder.createQuery(link.class);
		query.from(link.class);
		List<link> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static List<Movie> getMoviesList() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
		query.from(Movie.class);
		List<Movie> data = App.session.createQuery(query).getResultList();
		return data;
	}

	private static String FindSmallestDate(List<String> dates) {
		String date1 = dates.get(0);
		String Smallest = date1;
		String date2;
		for (int i = 1; i < dates.size(); i++) {
			date2 = dates.get(i);
			if (isBigger(Smallest, date2)) {
				Smallest = date2;
			}
		}
		return Smallest;
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

	private static List<User> getUserslist() {
		CriteriaBuilder builder = App.session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		query.from(User.class);
		List<User> data = App.session.createQuery(query).getResultList();
		return data;
	}
}