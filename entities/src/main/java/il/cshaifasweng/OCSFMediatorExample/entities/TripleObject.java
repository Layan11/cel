package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
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
	Reports report;
	List<Integer> list2;

	public TripleObject(String msg, List<Movie> movies, List<MovieTimes> movieTimes) {
		this.msg = msg;
		this.movies = movies;
		this.movieTimes = movieTimes;
	}
	

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

	public Reports getReport() {
		return report;
	}

	public void setReport(Reports report) {
		this.report = report;
	}
}
