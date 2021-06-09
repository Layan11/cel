package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PriceRequestsChart")
public class PriceRequestsChart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> movieEngName;
	@ElementCollection // (targetClass = String.class)
	private List<String> newPrice;

	public PriceRequestsChart() {
	}

	public PriceRequestsChart(List<String> movieEngName, List<String> newPrice) {
		this.movieEngName = movieEngName;
		this.newPrice = newPrice;
	}

	public List<String> getMovieEngName() {
		return movieEngName;
	}

	public void setMovieEngName(List<String> movieEngName) {
		this.movieEngName = movieEngName;
	}

	public List<String> getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(List<String> newPrice) {
		this.newPrice = newPrice;
	}

	public int getId() {
		return id;
	}

}
