package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class TripleObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	ArrayList<Movie> movies;
	ArrayList<MovieTimes> movieTimes;

	public TripleObject(String msg, ArrayList<Movie> movies, ArrayList<MovieTimes> movieTimes) {
		this.msg = msg;
		this.movies = movies;
		this.movieTimes = movieTimes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMoviesTimes(ArrayList<MovieTimes> movieTimes) {
		this.movieTimes = movieTimes;
	}

	public ArrayList<MovieTimes> getMovieTimes() {
		return movieTimes;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

}
