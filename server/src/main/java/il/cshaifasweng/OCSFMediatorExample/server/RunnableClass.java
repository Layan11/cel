package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;

import il.cshaifasweng.OCSFMediatorExample.entities.TripleObject;
import il.cshaifasweng.OCSFMediatorExample.entities.link;
import il.cshaifasweng.OCSFMediatorExample.entities.messages;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class RunnableClass  {
	long delay = 10 * 1000*6; // delay in milliseconds
	
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
			System.out.println("i am here");
			App.session = App.sessionFactory.openSession();
			App.session.beginTransaction();
			List<link> tmp =  getAlllinks();
			link mylink;
			for (int i = 0; i < tmp.size(); i++) {
				mylink=tmp.get(i);
				if(mylink!=null) {
				LocalDateTime myDateObj = mylink.get_start();
				LocalDateTime rightNow2 = LocalDateTime.now();
				int yearmovie = myDateObj.getYear();
				int yearnow= rightNow2.getYear();
				int monthmovie= myDateObj.getMonthValue();
				int monthnow =rightNow2.getMonthValue();
				int daymovie= myDateObj.getDayOfMonth();
				int daynow= rightNow2.getDayOfMonth();
				int hourmovie=myDateObj.getHour();
				int hournow=rightNow2.getHour();
				int minmovie=myDateObj.getMinute();
				int minnow=rightNow2.getMinute();
				System.out.println("date now, year: "+yearnow+" month: "+monthnow+" day:" +daynow+" hournow "+hournow+" minnow: "+minnow);
				System.out.println("date link, year: "+yearmovie+" month: "+monthmovie+" day:" +daymovie+" hourmovie: "+hourmovie+" minmovie:" +minmovie);
				if(yearmovie==yearnow) {
					if(monthmovie==monthnow) {
						if(daymovie==daynow) {
							if(minmovie==minnow) {
								if(hournow+1==hourmovie) {
									String cont="the link with id: "+mylink.get_id()+" \nwill start working after 1 hour";
									messages senduser=new messages("server",cont,mylink.getuser());
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
		} catch (HibernateException e) {
			e.printStackTrace();
			App.session.getTransaction().rollback();
		}
		App.session.close();
	}
	   }
	private static List<link> getAlllinks()  {
	CriteriaBuilder builder = App.session.getCriteriaBuilder();
	CriteriaQuery<link> query = builder.createQuery(link.class);
	query.from(link.class);
	List<link> data = App.session.createQuery(query).getResultList();
	return data;
}
	 
	
}
