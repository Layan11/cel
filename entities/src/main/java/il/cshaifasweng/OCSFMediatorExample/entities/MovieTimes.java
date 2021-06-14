package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "My_MovieTimes")
public class MovieTimes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> time;
	@ElementCollection
	private List<String> date;
	/*
	 * private List<Hall> hall; private List<String> branch; //private List<String>
	 * Date;
	 */

	public MovieTimes() {

	}

	public MovieTimes(List<String> times) {
		this.time = times;
	}

	public int getId() {
		return id;
	}

	public List<String> getTimes() {
		return time;
	}

	public void SetMovieTimes(List<String> time) {
		this.time = time;
	}

	public void PrintMovieT(List<String> mytimes) {
		System.out.format("MovieTimes: ");
		for (String String : mytimes) {
			System.out.format(" %s, ", String);
		}
	}

	public String getMovieT(List<String> mytimes) {
		String str = "";
		for (String Strings : mytimes) {
			str = str + Strings;
			str = str + ", ";
		}
		return str;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}
}
