package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


public class Cinema implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String location;
	private List<Hall> Halls;
	public Cinema() {}
	public Cinema(String name,String location,List<Hall> Halls) {
		this.name=name;
		this.location=location;
		this.Halls=Halls;
	}
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String new_name) {
		this.name=new_name;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String new_loc) {
		this.location=new_loc;
	}
	public List<Hall> getHalls(){
		return this.Halls;
	}
	public void setHalls(List<Hall> new_halls) {
		this.Halls=new_halls;
	}
	
}
