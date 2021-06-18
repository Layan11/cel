package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MapChair")
public class MapChair implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int num_rows;
	private int num_cols;
	private int numberavailablechair;
	
	//private int [] my_map_chairs;
	@ElementCollection
	@Column(name = "My_mapchairs")
	private List<Integer> MyMapChair;
	
	private int movie_id;
	private String start_time;
	
	public MapChair() {}
	public MapChair(int rows,int cols,List<Integer>mapchair) {
		this.num_rows=rows;
		this.num_cols=cols;
		this.numberavailablechair=rows*cols;
		//this.my_map_chairs=new int[rows*cols];
		this.MyMapChair=mapchair;
	}
	public MapChair(int rows,int cols,int movie_id,String start_time) {
		this.num_rows=rows;
		this.num_cols=cols;
		this.numberavailablechair=rows*cols;
		//this.my_map_chairs=new int[rows*cols];
		this.MyMapChair=new ArrayList<Integer>();
		this.movie_id=movie_id;
		this.start_time=start_time;
	}
	public int getID() {
		return this.id;
	}
	
	public int getRows() {
		return this.num_rows;
	}
	public void setRows(int new_r) {
		this.num_rows=new_r;
	}
	public int getCols() {
		return this.num_cols;
	}
	public void setCols(int new_c) {
		this.num_cols=new_c;
	}
	public int getNmberAvailableChair() {
		return this.numberavailablechair;
	}
	public void setNmberAvailableChair(int new_num) {
		this.numberavailablechair=new_num;
	}
	public void add_seat(int num) {
		this.MyMapChair.add(num);	}
	//public int[] getMapChair(){
	public List<Integer> getMapChair(){
		return this.MyMapChair;
	}
	//public void setMapChair(int [] new_map) {
	public void setMapChair(List<Integer> new_map) {
		this.MyMapChair=new_map;
	}
	
	
}
