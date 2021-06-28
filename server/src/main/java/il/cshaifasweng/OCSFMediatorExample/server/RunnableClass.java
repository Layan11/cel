package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.HibernateException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;

public class RunnableClass {
	long delay = 10 * 1000 * 6; // delay in milliseconds
	long counter = 0;
	LoopTask task = new LoopTask();

	Timer timer = new Timer("TaskName");

	public void start() {
		timer.cancel();
		timer = new Timer("TaskName");
		Date executionDate = new Date(); // no params = now
		timer.scheduleAtFixedRate(task, executionDate, delay);

	}

	private class LoopTask extends TimerTask {
		public void run() {
			try {
				App.session = App.sessionFactory.openSession();
				App.session.beginTransaction();
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
											App.session.save(senduser);
											App.session.flush();
										}
									}
								}
							}
						}
					}
				}
				App.session.getTransaction().commit();

				if (counter == 0 || counter == 1440 || counter == 2) {
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
						System.out.println("Movie Name = " + moviesinbranches.get(i).getEngName());
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
						System.out
								.println("smallest for " + moviesinbranches.get(i).getEngName() + " : " + SmallestDate);
						if (yearnoww == year11 && monthnoww == month11 && daynoww == day11) {
							for (int k = 0; k < UsersWithPackage.size(); k++) {
								System.out.println("USER = " + UsersWithPackage.get(i).getUser_Name());
								String cont = "ttttttttttttttttThe movie  " + moviesinbranches.get(i).getEngName()
										+ " is in our branches for the first time";
								messages MSGtosend = new messages("server", cont,
										UsersWithPackage.get(k).getUser_Name());
								App.session.save(MSGtosend);
								App.session.flush();
							}
						}
					}
					counter = 0;
				}
				counter++;
			} catch (HibernateException e) {
				e.printStackTrace();
				App.session.getTransaction().rollback();
			}
			App.session.close();
		}
	}
}
