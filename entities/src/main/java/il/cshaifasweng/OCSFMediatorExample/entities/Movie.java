//sofi bntymmm

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

@Entity
@Table(name = "movies")
public class Movie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "My_Movies_id")

	private int id;
	private Boolean Type; // Type=0 for now broadcasting,type=1 for coming soon 
	private String EngName;
	private String HebName;
	private int Length;
	@ElementCollection
	@Column(name = "My_ActorsNames")
	private List<String> ActorsNames;

	private String Summary;
	private String Producer;
	private int Price;
	//@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "MovieTimes_id")
   // @OneToMany(fetch = FetchType.LAZY)
	@OneToOne
	private MovieTimes Times;
//	@Lob
//	@Column(columnDefinition = "LONGBLOB")
//	private byte[] image;
	private String image;
	private String branch;

	public Movie() {

	}

	public Movie(Boolean Type, String EngName, List<String> actors, int len, String HebName, String summary, String producer,
			int price, String image, MovieTimes times, String branch) {
		super();
		this.Type = Type;
		this.EngName = EngName;
		this.Length = len;
		this.ActorsNames = actors;
		this.HebName = HebName;
		this.Summary = summary;
		this.Producer = producer;
		this.Price = price;
		this.setImage(image);
		this.Times = times;
		this.setBranch(branch);
	}
	
	public Boolean getType() {
		return Type;
	}

	public void setType(Boolean type) {
		this.Type = type;
	}

	public String getEngName() {
		return EngName;
	}

	public void setEngName(String engName) {
		this.EngName = engName;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int len) {
		this.Length = len;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		this.Price = price;
	}

	public String getProducer() {
		return Producer;
	}

	public void setProducer(String producer) {
		this.Producer = producer;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		this.Summary = summary;
	}

	public List<String> getActors() {
		return ActorsNames;
	}

	public void setActors(List<String> actorsNames) {
		this.ActorsNames = actorsNames;
	}

	public String getHebName() {
		return HebName;
	}

	public void setHebName(String hebName) {
		this.HebName = hebName;
	}

	public MovieTimes getMovieTimes() {
		return Times;
	}

	public void setMovieTimes(MovieTimes times) {
		this.Times = times;
	}

	public int getId() {
		return id;
	}

	public void addMovieTimes(String new_time) {
		this.Times.getTimes().add(new_time);
	}

	public boolean updateMovieTimes(String new_time, String old_time) {
		if (old_time == null) {
			this.Times.getTimes().remove(old_time);
			return true;
		}
		if (new_time == null) {
			this.Times.getTimes().add(new_time);
			return true;
		} else {
			if (this.Times.getTimes().contains(old_time)) {
				this.Times.getTimes().remove(old_time);
				this.Times.getTimes().add(new_time);
				return true;
			}
			return false;
		}
	}

	public String getMovieDetails2(List<Movie> movie_list) {
		String myStr = "";
		for (Movie Movie : movie_list) {
			myStr = myStr + " ID: " + Movie.getId() + " ,Name: " + Movie.getEngName() + " ,Price: " + Movie.getPrice()
					+ " lenth: " + Movie.getLength() + " Prod: " + Movie.getProducer();
			myStr = myStr + "\ntimes of view:" + Movie.Times.getMovieT(Movie.Times.getTimes());

			myStr = myStr + "\n";
		}
		return myStr;
	} 

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}