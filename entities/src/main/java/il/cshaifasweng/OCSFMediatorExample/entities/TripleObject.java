package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class TripleObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	List<Movie> movies;
	List<MovieTimes> movieTimes;
	int movie_id;
	String movie_time;
	List<Integer> mapchair;
	String num_seat;

	public TripleObject(String msg, List<Movie> movies, List<MovieTimes> movieTimes) {
		this.msg = msg;
		this.movies = movies;
		this.movieTimes = movieTimes;
	}
	//***saleh***
	public TripleObject(String msg,int id,String time) {
		this.movie_id=id;
		this.movie_time=time;
		this.msg=msg;
	}
	public TripleObject(String msg,int id,String time,String num_seat) {
		this.movie_id=id;
		this.movie_time=time;
		this.msg=msg;
		this.num_seat=num_seat;
	}
	public String getnumseat() {
		return this.num_seat;
	}
	public TripleObject(String msg,List<Integer> mc) {
		this.mapchair=mc;
		this.msg=msg;
	}
	public List<Integer> getMapChair(){
		return this.mapchair;
	}
	public int getID() {
		return this.movie_id;
	}
	public String getTime() {
		return this.movie_time;
	}
	//***saleh***

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

}
