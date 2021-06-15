package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Hall implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number_of_chairs;
	private List<Integer> movie_id_list;
	private MapChair mapchairs;

	public Hall() {
	}

	public Hall(int num_chairs, List<Integer> movies_id, MapChair mapchairs) {
		this.number_of_chairs = num_chairs;
		this.movie_id_list = movies_id;
		this.mapchairs = mapchairs;
	}

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
		return this.movie_id_list;
	}

	public void setMovieIDList(List<Integer> new_list) {
		this.movie_id_list = new_list;
	}

	public MapChair getMapChairs() {
		return this.mapchairs;
	}

	public void setMapChairs(MapChair new_mapchair) {
		this.mapchairs = new_mapchair;
	}
}
