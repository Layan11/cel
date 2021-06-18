
package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint")
public class complaint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String Name;
	private String Complaintcontext;
	private boolean Handled;
	private LocalDateTime time;
	
	
	public complaint(String name, String complaintcontext) {
		super();
		Name = name;
		Complaintcontext = complaintcontext;
		Handled = false;
		time = LocalDateTime.now();
	}
	
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}
	//public void setId(int id) {
	//ٍ	this.id = id;
	//}
	
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
	
}
