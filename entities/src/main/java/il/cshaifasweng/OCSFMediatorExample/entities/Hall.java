package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Halls")
public class Hall implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number_of_chairs;
	@ElementCollection
	@Column(name = "movies_id")
	private List<Integer> moviesID;
	@ElementCollection
	@Column(name = "mapchairsID")
	private List<Integer> mapchairs;
//<<<<<<< HEAD
//	private List<Integer> movie_id_list;
//	private MapChair mapchairs;
//=======

	public Hall() {
	}

	public Hall(int num_chairs) {
		this.number_of_chairs = num_chairs;
		this.moviesID = new ArrayList<Integer>();
		this.mapchairs = new ArrayList<Integer>();
	}

	public Hall(int num_chairs, List<Integer> mc) {
		this.number_of_chairs = num_chairs;
		this.moviesID = new ArrayList<Integer>();
		;
		this.mapchairs = mc;
	}

//	public Hall(int num_chairs, List<Integer> movies_id, MapChair mapchairs) {
//		this.number_of_chairs = num_chairs;
//		this.movie_id_list = movies_id;
//		this.mapchairs = mapchairs;
//	}

	public int getID() {
		return this.id;
	}

	public void setID(int new_id) {
		this.id = new_id;
	}

	public int getNumberOfChairs() {
		return this.number_of_chairs;
	}

	public void setNumberOfChairs(int new_num) {
		this.number_of_chairs = new_num;
	}

	public List<Integer> getMovieIDList() {
		return this.moviesID;
	}

	public void setMovieIDList(List<Integer> new_list) {
		this.moviesID = new_list;
	}

	public void add_IDMapChair(Integer new_id) {
		this.mapchairs.add(new_id);
	}

	public void add_IDMovie(Integer new_id) {
		this.moviesID.add(new_id);
	}
}
