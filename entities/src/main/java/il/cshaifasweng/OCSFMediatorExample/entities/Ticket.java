package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticket_id;
	private String movie_of_tick;
	private String hall;
	int chair_num;
	int start_time;

	public Ticket() {

	}

	public Ticket(String _movie, String _hall, int time, int _chair_num) {
		this.movie_of_tick = _movie;
		this.hall = _hall;
		this.start_time = time;
		this.chair_num = _chair_num;
	}

	public String get_hall() {
		return this.hall;
	}

	public String get_movie() {
		return this.movie_of_tick;
	}

	public int get_id() {
		return this.ticket_id;
	}
}
