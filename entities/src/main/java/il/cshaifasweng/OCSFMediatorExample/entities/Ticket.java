package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticket_id;
	
	private String	movie_of_tick;
	private String hall;
	//@JoinColumn(name = "User_id")
	//@OneToMany
	//User user;
	int chair_num;
	String time_of_ticket;
	
	public Ticket(){
		
	}
	public Ticket (int id,String _movie,String _hall,String time,int _chair_num) {
		this.ticket_id=id;
		this.movie_of_tick= _movie;
		this.hall= _hall;
		this.time_of_ticket=time;
		this.chair_num= _chair_num;
	}
	public String get_hall() {
		return this.hall;
	}
	public String get_movie() {
		return this.movie_of_tick;
	}
	//public void set_user(User _user) {
		//this.user=_userl;
	//}
}
