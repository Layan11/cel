package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MapChair implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rows;
	private int cols;
	private int numberavailablechair;
	private int [][] my_map_chairs;
	
	public MapChair() {}
	
	

	public MapChair(int rows, int cols, int numberavailablechair, int[][] my_map_chairs) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.numberavailablechair = numberavailablechair;
		this.my_map_chairs = my_map_chairs;
	}



	public int getId() {
		return id;
	}


	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getNumberavailablechair() {
		return numberavailablechair;
	}

	public void setNumberavailablechair(int numberavailablechair) {
		this.numberavailablechair = numberavailablechair;
	}

	public int[][] getMy_map_chairs() {
		return my_map_chairs;
	}

	public void setMy_map_chairs(int[][] my_map_chairs) {
		this.my_map_chairs = my_map_chairs;
	}
	
	

}
