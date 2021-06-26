
package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint")
public class complaint implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
	private String Complaintcontext;
	private boolean Handled;
	private LocalDateTime time;
	private static int numOfcomplaints=0;

	public complaint() {

	}

	public complaint(String name, String complaintcontext) {
		super();
		Name = name;
		Complaintcontext = complaintcontext;
		Handled = false;
		time = LocalDateTime.now();
		numOfcomplaints++;
	}

	public String getTime() {
		return time.toString();
	}
	
	public String getremainingTime() {
		LocalDateTime now=LocalDateTime.now();
		

		 Long hours = ChronoUnit.HOURS.between(time, now);
	        Long minutes = ChronoUnit.MINUTES.between(time, now);
	        hours=24-hours;
	        System.out.println(hours + "hours");
	        System.out.println(minutes + "minutes");
	        //Long seconds = ChronoUnit.SECONDS.between(time, now);
		// Duration duration = Duration.between(time, now);
	        String str=Long.toString(hours) +" hours";
	        //System.out.println(str + "time");
		 
         
		 return str;
	}


	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getComplaintcontext() {
		return Complaintcontext;
	}

	public void setComplaintcontext(String complaintcontext) {
		Complaintcontext = complaintcontext;
	}

	public boolean isHandled() {
		return Handled;
	}

	public void setHandled(boolean handled) {
		Handled = handled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getNumOfcomplaints() {
		return numOfcomplaints;
	}

	public static void setNumOfcomplaints(int numOfcomplaints) {
		complaint.numOfcomplaints = numOfcomplaints;
	}
	
}
