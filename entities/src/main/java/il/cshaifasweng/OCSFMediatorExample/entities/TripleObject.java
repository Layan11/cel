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
	List<String> list;

	public TripleObject(String msg, List<Movie> movies, List<MovieTimes> movieTimes) {
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
		return list;
	}
}
