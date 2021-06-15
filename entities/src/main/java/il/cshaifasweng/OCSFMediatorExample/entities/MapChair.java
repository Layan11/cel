package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MapChair implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rows;
	private int cols;
	private int numberavailablechair;
	private int[][] my_map_chairs;

	public MapChair() {
	}

	public MapChair(int rows, int cols, int numavailable, int[][] map) {
		this.rows = rows;
		this.cols = cols;
		this.numberavailablechair = numavailable;
		this.my_map_chairs = map;
	}

	public int getRows() {
		return this.rows;
	}

	public void setRows(int new_r) {
		this.rows = new_r;
	}

	public int getCols() {
		return this.cols;
	}

	public void setCols(int new_c) {
		this.cols = new_c;
	}

	public int getNmberAvailableChair() {
		return this.numberavailablechair;
	}

	public void setNmberAvailableChair(int new_num) {
		this.numberavailablechair = new_num;
	}

	public int[][] getMapChair() {
		return this.my_map_chairs;
	}

	public void setMapChair(int[][] new_map) {
		this.my_map_chairs = new_map;
	}
}
