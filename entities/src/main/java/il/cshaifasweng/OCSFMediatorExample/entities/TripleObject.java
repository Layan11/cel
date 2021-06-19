package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

public class TripleObject implements Serializable {
	private static final long serialVersionUID = 1L;
	String msg;
	List<Movie> movies;
	List<MovieTimes> movieTimes;
	@ElementCollection(fetch = FetchType.EAGER)
	List<String> list;
	List<Integer> list2;
	List<String> complaintsContent;
	List<String> complaintsUser;
	List<String> complaintTime;
	int movie_id;
	String movie_time;
	List<Integer> mapchair;
	String num_seat;

	
	public List<String> getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(List<String> complainttime) {
		this.complaintTime = complainttime;
	}
	
	public List<String> getComplaintsContent() {
		return complaintsContent;
	}

	public void setComplaintsContent(List<String> complaintsContent) {
		this.complaintsContent = complaintsContent;
	}

	public List<String> getComplaintsUser() {
		return complaintsUser;
	}

	public void setComplaintsUser(List<String> complaintsUser) {
		this.complaintsUser = complaintsUser;
	}

	public TripleObject(String msg, List<Movie> movies, List<MovieTimes> movieTimes) {
		this.msg = msg;
		this.movies = movies;
		this.movieTimes = movieTimes;
	}

	// ***saleh***
	public TripleObject(String msg, int id, String time) {
		this.movie_id = id;
		this.movie_time = time;
		this.msg = msg;
	}

	public TripleObject(String msg, int id, String time, String num_seat) {
		this.movie_id = id;
		this.movie_time = time;
		this.msg = msg;
		this.num_seat = num_seat;
	}

	public String getnumseat() {
		return this.num_seat;
	}

	public TripleObject(String msg, List<Integer> mc) {
		this.mapchair = mc;
		this.msg = msg;
	}

	public List<Integer> getMapChair() {
		return this.mapchair;
	}

	public int getID() {
		return this.movie_id;
	}

	public String getTime() {
		return this.movie_time;
	}
	// ***saleh***

	public List<Integer> getList2() {
		return list2;
	}

	public void setList2(List<Integer> list2) {
		this.list2 = list2;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMoviesTimes(List<MovieTimes> movieTimes) {
		this.movieTimes = movieTimes;
	}

	public List<MovieTimes> getMovieTimes() {
		return movieTimes;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		// TODO Auto-generated method stub
		return list;
	}
}
