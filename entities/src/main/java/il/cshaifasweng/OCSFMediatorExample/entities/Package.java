package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Package")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int package_id;
	private int number_of_ticekts;

	public Package() {
	}

	public Package(int x) {
		x = 20;
		this.number_of_ticekts = x;

	}

	public int get_ticks() {
		return this.number_of_ticekts;
	}

	public int get_id() {
		return this.package_id;
	}

	public void used_tick() {
		this.number_of_ticekts = this.number_of_ticekts - 1;
	}

}
