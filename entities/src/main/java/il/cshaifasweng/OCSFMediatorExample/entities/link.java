package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class link implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int link_id;

	private String movie;
	private LocalDateTime start_time_of_work;
	private LocalDateTime end_time_of_work;
	private String user_name;
	private String Method_of_payment;

	public link() {

	}

	public link( String _movie, LocalDateTime S_time, LocalDateTime E_Time,String user,String method) {
	
		this.movie = _movie;
		this.start_time_of_work = S_time;
		this.end_time_of_work = E_Time;
		this.user_name=user;
		this.Method_of_payment=method;
	}

	public int get_id() {
		return this.link_id;
	}
	public String getuser() {
		return this.user_name;
	}

	public LocalDateTime get_start() {
		return this.start_time_of_work;
	}
}
