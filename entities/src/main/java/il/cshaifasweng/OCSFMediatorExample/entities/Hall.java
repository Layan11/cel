package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Hall implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number_of_chairs;
	private List<Integer> movie_id_list;
	private MapChair mapchairs;
	
	public Hall(){}
	
	public Hall(int number_of_chairs, List<Integer> movie_id_list, MapChair mapchairs) {
		super();
		this.number_of_chairs = number_of_chairs;
		this.movie_id_list = movie_id_list;
		this.mapchairs = mapchairs;
	}

	public int getId() {
		return id;
	}

	public int getNumber_of_chairs() {
		return number_of_chairs;
	}
	public void setNumber_of_chairs(int number_of_chairs) {
		this.number_of_chairs = number_of_chairs;
	}
	public List<Integer> getMovie_id_list() {
		return movie_id_list;
	}
	public void setMovie_id_list(List<Integer> movie_id_list) {
		this.movie_id_list = movie_id_list;
	}
	public MapChair getMapchairs() {
		return mapchairs;
	}
	public void setMapchairs(MapChair mapchairs) {
		this.mapchairs = mapchairs;
	}
	
	

}
