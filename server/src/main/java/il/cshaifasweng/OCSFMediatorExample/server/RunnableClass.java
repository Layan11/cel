package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;

public class RunnableClass {
	long delay = 10 * 1000 * 6; // delay in milliseconds
	long delay2 = 86400000;
	long counter = 0;
	LoopTask task = new LoopTask();
	LoopTask2 task2 = new LoopTask2();

	Timer timer = new Timer("TaskName");
	Timer timer2 = new Timer("TaskName2");

	public void start() {
		timer.cancel();
		timer2.cancel();
		timer = new Timer("TaskName");
		timer2 = new Timer("TaskName2");
		Date executionDate = new Date(); // no params = now
		timer.scheduleAtFixedRate(task, executionDate, delay);
		timer2.scheduleAtFixedRate(task2, executionDate, delay2);

	}

	private class LoopTask extends TimerTask {
		Session session;

		public void run() {
			try {
				session = App.sessionFactory.openSession();
				session.beginTransaction();
				List<link> tmp = SimpleServer.getAlllinks();
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
				if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.getTransaction().commit();
				}
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();
		}
	}

	private class LoopTask2 extends TimerTask {
		Session session;

		public void run() {
			try {
				System.out.println("IN RUNNABLE 2");
				session = App.sessionFactory.openSession();
				session.beginTransaction();
				List<Movie> allMovies = SimpleServer.getMoviesList();
				List<Movie> moviesinbranches = new ArrayList<Movie>();
				List<User> allUsers = SimpleServer.getUserslist();
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
				System.out.println("Num of Movies in branches = " + moviesinbranches.size());

				for (int i = 0; i < moviesinbranches.size(); i++) {
					if (!session.isOpen()) {
						session = App.sessionFactory.openSession();
						session.beginTransaction();
					}
					System.out.println("Movie Name = " + moviesinbranches.get(i).getEngName());
					System.out.println("MOVIE TIMES SIZEEE" + moviesinbranches.get(i).getMovieTimes().getDate().size());
					List<String> dates = moviesinbranches.get(i).getMovieTimes().getDate();
					String SmallestDate = SimpleServer.FindSmallestDate(dates);
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
						System.out.println("size ===== " + UsersWithPackage.size());
						for (int k = 0; k < UsersWithPackage.size(); k++) {
							System.out.println("USER = " + UsersWithPackage.get(k).getUser_Name());
							String cont = "What are you doing today? Watching our new movie:  "
									+ moviesinbranches.get(i).getEngName() + "  for the first time in our branches!";
							messages MSGtosend = new messages("server", cont, UsersWithPackage.get(k).getUser_Name());
							session.save(MSGtosend);
							session.flush();
						}
					}
				}
				if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
					session.getTransaction().commit();
				}
			} catch (HibernateException e) {
				e.printStackTrace();
				if (session.getTransaction() != null && App.session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			}
			session.close();
		}
	}
}