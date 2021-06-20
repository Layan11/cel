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
@Table(name = "purpleChar")
public class purpleChar implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ElementCollection(fetch = FetchType.EAGER)
	List<String> restrictedDates;

	public purpleChar() {

	}

	public purpleChar(List<String> dates) {
		this.restrictedDates = dates;
	}

	public List<String> getDays() {
		return restrictedDates;
	}

	public void setDays(List<String> days) {
		this.restrictedDates = days;
	}

	public int getId() {
		return id;
	}
}
