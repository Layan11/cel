package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

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
	private int start_time_of_work;
	private int end_time_of_work;

	public link() {

	}

	public link(int id, String _movie, int S_time, int E_Time) {
		this.link_id = id;
		this.movie = _movie;
		this.start_time_of_work = S_time;
		this.end_time_of_work = E_Time;

	}

	public int get_id() {
		return this.link_id;
	}

	public int get_start() {
		return this.start_time_of_work;
	}
}
