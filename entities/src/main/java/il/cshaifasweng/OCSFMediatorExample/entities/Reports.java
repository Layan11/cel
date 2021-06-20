package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reports")
public class Reports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Report_id;
	int TicketsInHaifa;
	int returnedTicketsInHaifa;
	int TicketsInShefaAmr;
	int returnedTicketsInShefaAmr;
	int Links;
	int Packages;
	/*List<Integer> ComplaintsPerDay = new ArrayList<Integer>();


	public List<Integer> getComplaintsPerDay() {
		return ComplaintsPerDay;
	}

	public void setComplaintsPerDay(List<Integer> complaintsPerDay) {
		ComplaintsPerDay = complaintsPerDay;
	}*/

	public Reports() {
		super();
	}

	public int getTicketsInHaifa() {
		return TicketsInHaifa;
	}

	public void setTicketsInHaifa(int ticketsInHaifa) {
		TicketsInHaifa = ticketsInHaifa;
	}

	public int getReturnedTicketsInHaifa() {
		return returnedTicketsInHaifa;
	}

	public void setReturnedTicketsInHaifa(int returnedTicketsInHaifa) {
		this.returnedTicketsInHaifa = returnedTicketsInHaifa;
	}

	public int getTicketsInShefaAmr() {
		return TicketsInShefaAmr;
	}

	public void setTicketsInShefaAmr(int ticketsInShefaAmr) {
		TicketsInShefaAmr = ticketsInShefaAmr;
	}

	public int getReturnedTicketsInShefaAmr() {
		return returnedTicketsInShefaAmr;
	}

	public void setReturnedTicketsInShefaAmr(int returnedTicketsInShefaAmr) {
		this.returnedTicketsInShefaAmr = returnedTicketsInShefaAmr;
	}

	public int getLinks() {
		return Links;
	}

	public void setLinks(int links) {
		Links = links;
	}

	public int getPackages() {
		return Packages;
	}

	public void setPackages(int packages) {
		Packages = packages;
	}

}
